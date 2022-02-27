package fi.metropolia.herbreferenceguide.camera;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;

import java.util.ArrayList;

public class ImageLoadingThread implements Runnable{

    private final Message imageThreadMsg;
    private final Bundle imageThreadBundle = new Bundle();
    private ArrayList<CameraImage> sendImageLists;
    private final ImageGalleryActivity imageGalleryActivity;
    private final Handler objHandler;
    public static final String MSG_IMAGES = "Image list sent";
    public static final String MSG_SIZE = "Image size warning";

    public ImageLoadingThread(Handler objHandler, ImageGalleryActivity imageGalleryActivity) {
        this.imageThreadMsg = objHandler.obtainMessage();
        this.imageGalleryActivity = imageGalleryActivity;
        this.objHandler = objHandler;
    }

    @Override
    public void run() {
        try {
            sendImageLists = loadImages();
        } catch (Exception e){
            e.printStackTrace();
        }
        imageThreadBundle.putParcelableArrayList(MSG_IMAGES,sendImageLists);
        imageThreadMsg.setData(imageThreadBundle);
        objHandler.sendMessage(imageThreadMsg);
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
        Cursor cursor = imageGalleryActivity.getContentResolver().query(
                collection,
                projection,
                selection,
                selectionArgs,
                null);
        int size = cursor.getCount();

        if(size == 0) {
            imageThreadBundle.putString(MSG_SIZE, "There is no images in storage");
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
        Intent intent = imageGalleryActivity.getIntent();
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
        CameraImage image = new CameraImage(imageIndex,name,rotatedBitmap,path);
        imageLists.add(image);
    }
}
