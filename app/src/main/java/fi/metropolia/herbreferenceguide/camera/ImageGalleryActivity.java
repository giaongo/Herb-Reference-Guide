package fi.metropolia.herbreferenceguide.camera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import fi.metropolia.herbreferenceguide.R;
import fi.metropolia.herbreferenceguide.RecyclerViewInterface;

public class ImageGalleryActivity extends AppCompatActivity implements RecyclerViewInterface {
    private RecyclerView recyclerView;
    private ArrayList<CameraImage> imageList = new ArrayList<>();

    Handler objHandler = new Handler(Looper.getMainLooper()) {
        private ArrayList<CameraImage> receivedImageList;

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle objectBundle = msg.getData();
            receivedImageList = objectBundle.getParcelableArrayList("MSG_KEY");
            ImageAdapter imageAdapter = new ImageAdapter(receivedImageList,ImageGalleryActivity.this, ImageGalleryActivity.this);
            recyclerView.setAdapter(imageAdapter);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);
        //Initialise recycler view
        recyclerView = findViewById(R.id.imageRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));


        ImageLoadingThread imageLoadingThread = new ImageLoadingThread(this);
        Thread imageLoading = new Thread(imageLoadingThread);
        imageLoading.start();
        Toast.makeText(this, "Main is running", Toast.LENGTH_SHORT).show();
    }

    private ArrayList<CameraImage> loadImages() {
        ArrayList<CameraImage> imageLists = new ArrayList<>();
        Uri collection;
        String selection = MediaStore.Images.Media.DISPLAY_NAME + " LIKE ?";
        String[] selectionArgs = {"herb%"};

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
        } else {
            collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }

        String[] projection = {
                //DATA : absolute filesystem path to the media item on disk
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME
        };
        Cursor cursor = getApplicationContext().getContentResolver().query(
                collection,
                projection,
                selection,
                selectionArgs,
                null);
        int size = cursor.getCount();

        if(size == 0) {
            Toast.makeText(this, "no size", Toast.LENGTH_SHORT).show();
        } else {
            // Cache column indices
            int imageIndex = 0;
            int dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            int nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
            while(cursor.moveToNext()) {
                String path = cursor.getString(dataColumn);
                String name = cursor.getString(nameColumn);
                configureBitmap(imageIndex,name,path,imageLists);
                imageIndex++;
            }
            cursor.close();
        }
        return imageLists;
    }
    private void configureBitmap(int imageIndex,String name,String path,ArrayList<CameraImage> imageLists) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        Matrix matrix = new Matrix();
        Intent intent = getIntent();
        int lensFacing = intent.getIntExtra(CameraActivity.LENS_FACING,0);
        /* BackCamera = 1 , FrontCamera = 0. Besides, this rotation configurations for image display
        is based on our front and back camera test in actual devices)
         */
        if(lensFacing == 1) {
            matrix.postRotate(90);
        } else {
            matrix.postRotate(270);
        }
        bitmap = Bitmap.createScaledBitmap(bitmap,(bitmap.getWidth()) / 2,
                (bitmap.getHeight())/2,true);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),
                        matrix,true);
        CameraImage image = new CameraImage(imageIndex,name,rotatedBitmap);
        imageLists.add(image);
    }
    
    @Override
    public void onItemClick(int position) {
        Snackbar.make(ImageGalleryActivity.this.findViewById(R.id.galleryParent),R.string.snackBarLabel,Snackbar.LENGTH_LONG)
            .setAction(R.string.snackBarAction, view -> deletePhoto(position))
            .show();

    }

    private void deletePhoto(int position) {
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();



    }
    private class ImageLoadingThread implements Runnable{
        Message imageThreadMsg = objHandler.obtainMessage();
        Bundle imageThreadIntent = new Bundle();
        private ArrayList<CameraImage> sendImageLists;
        private ImageGalleryActivity imageGalleryActivity;

        public ImageLoadingThread(ImageGalleryActivity imageGalleryActivity) {
            this.imageGalleryActivity = imageGalleryActivity;
        }

        @Override
        public void run() {
            try {
                sendImageLists = loadImages();
                imageGalleryActivity.setImageList(sendImageLists);
            } catch (Exception e){
                e.printStackTrace();
            }
            imageThreadIntent.putParcelableArrayList("MSG_KEY",sendImageLists);
            imageThreadMsg.setData(imageThreadIntent);
            objHandler.sendMessage(imageThreadMsg);
        }
    }

    public void setImageList(ArrayList<CameraImage> imageList) {
        this.imageList = imageList;
    }
}
