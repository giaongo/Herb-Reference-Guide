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

import fi.metropolia.herbreferenceguide.R;
import fi.metropolia.herbreferenceguide.RecyclerViewInterface;

public class ImageGalleryActivity extends AppCompatActivity implements RecyclerViewInterface {
    private RecyclerView recyclerView;
    private ArrayList<CameraImage> imageList = new ArrayList<>();

    Handler objHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle objectBundle = msg.getData();
            if(objectBundle.containsKey(ImageLoadingThread.MSG_IMAGES)) {
                ArrayList<CameraImage> receivedImageList = objectBundle.getParcelableArrayList(ImageLoadingThread.MSG_IMAGES);
                setImageList(receivedImageList);
                updateAdapter();
            }
            if(objectBundle.containsKey(ImageLoadingThread.MSG_SIZE)) {
                String receivedImageWarning = objectBundle.getString(ImageLoadingThread.MSG_SIZE);
                Toast.makeText(ImageGalleryActivity.this, receivedImageWarning, Toast.LENGTH_SHORT).show();
            }
            if(objectBundle.containsKey(ImageDeletingThread.MSG_DELETE)) {
                String receivedImageDelete = objectBundle.getString(ImageDeletingThread.MSG_DELETE);
                Toast.makeText(ImageGalleryActivity.this,receivedImageDelete,Toast.LENGTH_SHORT).show();
                startActivity(getIntent());
                finish();
                overridePendingTransition(0, 0);
            }

        }
    };


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

    private void updateAdapter() {
        ImageAdapter imageAdapter = new ImageAdapter(imageList, ImageGalleryActivity.this, ImageGalleryActivity.this);
        recyclerView.setAdapter(imageAdapter);

    }

    @Override
    public void onItemClick(int position) {
        Snackbar.make(ImageGalleryActivity.this.findViewById(R.id.galleryParent),R.string.snackBarLabel,Snackbar.LENGTH_LONG)
            .setAction(R.string.snackBarAction, view -> deletePhoto(position))
            .show();
    }

    private void deletePhoto(int position) {
        ImageDeletingThread imageDeletingThread = new ImageDeletingThread(position,imageList,this,objHandler);
        Thread deletingThread = new Thread(imageDeletingThread);
        deletingThread.start();

    }

    public void setImageList(ArrayList<CameraImage> imageList) {
        this.imageList = imageList;
    }
}
