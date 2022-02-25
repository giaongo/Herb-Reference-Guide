package fi.metropolia.herbreferenceguide.camera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import fi.metropolia.herbreferenceguide.R;
import fi.metropolia.herbreferenceguide.RecyclerViewInterface;

public class ImageGalleryActivity extends AppCompatActivity implements RecyclerViewInterface {
    ArrayList<CameraImage> imageLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);
        imageLists = new ArrayList<>();
        loadImages();
        initRecyclerView();
    }
    private void loadImages() {
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
                configureBitmap(imageIndex,name,path);
                imageIndex++;
            }
            cursor.close();
        }
    }
    private void configureBitmap(int imageIndex,String name,String path) {
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

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.imageRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        ImageAdapter imageAdapter = new ImageAdapter(imageLists,this, this);
        recyclerView.setAdapter(imageAdapter);
    }

    @Override
    public void onItemClick(int position) {

        Snackbar.make(ImageGalleryActivity.this.findViewById(R.id.galleryParent),"test",Snackbar.LENGTH_LONG)
            .show();

    }
}