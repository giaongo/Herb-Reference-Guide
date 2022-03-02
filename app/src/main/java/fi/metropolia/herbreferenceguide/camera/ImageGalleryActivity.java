package fi.metropolia.herbreferenceguide.camera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import fi.metropolia.herbreferenceguide.*;
import fi.metropolia.herbreferenceguide.RecyclerViewInterface;

/**
 * This activity is used for  displaying all images taken with the starting name
 * herb and providing deleting image feature.
 * @author Giao Ngo
 * @version 1.0
 * @since 2022-03-01
 */
public class ImageGalleryActivity extends AppCompatActivity implements RecyclerViewInterface {
    private RecyclerView recyclerView;
    private ArrayList<CameraImage> imageList = new ArrayList<>();

    /**
     * Create an instance of Handler class that overrides handleMessage function
     * Code reference:
     * @see <a href="https://developer.android.com/reference/android/os/Handler#handleMessage(android.os.Message)>
     * Handler</a>
     * <a href="https://www.youtube.com/watch?v=IVFWC0rwfL4&ab_channel=CodingPursuits">
     *  Running android tasks in background thread</a>
     */
    Handler objHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle objectBundle = msg.getData();
            /* Processes message sent from ImageLoadingThread by getting ArrayList, assigns to array image
            and updates RecyclerView adapter*/
            if(objectBundle.containsKey(ImageLoadingThread.MSG_IMAGES)) {
                ArrayList<CameraImage> receivedImageList = objectBundle.getParcelableArrayList(ImageLoadingThread.MSG_IMAGES);
                setImageList(receivedImageList);
                updateAdapter();
            }
            /* Processes message sent from ImageLoadingThread informing no image in storage*/
            if(objectBundle.containsKey(ImageLoadingThread.MSG_SIZE)) {
                String receivedImageWarning = objectBundle.getString(ImageLoadingThread.MSG_SIZE);
                Toast.makeText(ImageGalleryActivity.this, receivedImageWarning, Toast.LENGTH_SHORT).show();
            }
            /* Processes message sent from ImageDeletingThread informing successful image delete
            * and relaunches this activity to update the image display */
            if(objectBundle.containsKey(ImageDeletingThread.MSG_DELETE)) {
                String receivedImageDelete = objectBundle.getString(ImageDeletingThread.MSG_DELETE);
                Toast.makeText(ImageGalleryActivity.this,receivedImageDelete,Toast.LENGTH_SHORT).show();
                /* Restart activity to avoid black background splash on the screen:
                  https://stackoverflow.com/questions/2486934/programmatically-relaunch-recreate-an-activity */
                startActivity(getIntent());
                finish();
                overridePendingTransition(0, 0);
            }
        }
    };

    /**
     * Sets activity label title, initialises RecyclerView and runs ImageLoadingThread
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);
        setTitle(R.string.camera_gallery);
        recyclerView = findViewById(R.id.imageRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(ImageGalleryActivity.this,3));
        updateAdapter();
        ImageLoadingThread imageLoadingThread = new ImageLoadingThread(objHandler,this);
        Thread imageLoading = new Thread(imageLoadingThread);
        imageLoading.start();
    }

    /**
     * Creates RecyclerView adapter and sets adapter to RecyclerView component
     */
    private void updateAdapter() {
        ImageAdapter imageAdapter = new ImageAdapter(imageList, ImageGalleryActivity.this, ImageGalleryActivity.this);
        recyclerView.setAdapter(imageAdapter);
    }

    /**
     * Overrides OnItemClick function that displays delete message from snack bar and executes
     * delete function once the snack bar action is clicked
     * @param position int clicked view position
     */
    @Override
    public void onItemClick(int position) {
        Snackbar.make(ImageGalleryActivity.this.findViewById(R.id.galleryParent),R.string.snackBarLabel,Snackbar.LENGTH_LONG)
            .setAction(R.string.snackBarAction, view -> deletePhoto(position))
            .show();
    }

    /**
     * Executes ImageDeletingThread
     * @param position int clicked view position
     */
    private void deletePhoto(int position) {
        ImageDeletingThread imageDeletingThread = new ImageDeletingThread(position,imageList,this,objHandler);
        Thread deletingThread = new Thread(imageDeletingThread);
        deletingThread.start();
    }

    /**
     * Sets new ArrayList to the current one
     * @param imageList ArrayList
     */
    public void setImageList(ArrayList<CameraImage> imageList) {
        this.imageList = imageList;
    }
}
