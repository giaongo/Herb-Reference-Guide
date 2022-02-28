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

import fi.metropolia.herbreferenceguide.R;

/**
 * This background thread is defined for loading images from MediaStore
 * @author Giao Ngo
 * @version 1.0
 * @since 2022-02-28
 */
public class ImageLoadingThread implements Runnable{
    private final Message imageThreadMsg;
    private final Bundle imageThreadBundle = new Bundle();
    private ArrayList<CameraImage> sendImageLists;
    private final ImageGalleryActivity imageGalleryActivity;
    private final Handler objHandler;
    public static final String MSG_IMAGES = "Image list sent";
    public static final String MSG_SIZE = "Image size warning";

    /**
     * This constructor is used to initialise the instance of background thread
     * @param objHandler Handler
     * @param imageGalleryActivity ImageGalleryActivity
     */
    public ImageLoadingThread(Handler objHandler, ImageGalleryActivity imageGalleryActivity) {
        this.imageThreadMsg = objHandler.obtainMessage();
        this.imageGalleryActivity = imageGalleryActivity;
        this.objHandler = objHandler;
    }

    /**
     * Overrides run function, get called when the thread is started. This threads executes
     * loadImages(), puts ArrayList  of images  to  Bundle, sets that Bundle to Message and sends
     * that Message to Handler to proceed with main application.
     * Code reference to run android tasks in background thread:
     * @see <a href="https://www.youtube.com/watch?v=IVFWC0rwfL4&ab_channel=CodingPursuits">
     * Running android tasks in background thread</a>
     */
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

    /**
     * Queries images from MediaStore that the image's name starts with herb.
     * @return ArrayList<CameraImage>
     * @see <a href="https://developer.android.google.cn/training/data-storage/shared/media?hl=en#java">
     * Access media files from shared storage: Query a media collection</a>
     */
    private ArrayList<CameraImage> loadImages() {
        ArrayList<CameraImage> imageLists = new ArrayList<>();
        Uri collection;
        String selection = MediaStore.Images.Media.DISPLAY_NAME + " LIKE ?";
        String[] selectionArgs = {"herb%"};
        // define the uri to work with Android 10 above and below
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
        } else {
            collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        // media database columns
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
            imageThreadBundle.putString(MSG_SIZE, imageGalleryActivity.getString(R.string.imageSizeWarning));
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

    /**
     * Configures size of bitmap and rotates according to the test angle degree in android devices.
     * @param imageIndex int
     * @param name String
     * @param path String
     * @param imageLists ArrayList<CameraImage>
     * Code reference to resize and rotate bitmap:
     * @see <a href="https://stackoverflow.com/questions/29982528/how-do-i-rotate-a-bitmap-in-android">
     * How do I rotate bitmap in android</a>
     */
    private void configureBitmap(int imageIndex,String name,String path,ArrayList<CameraImage> imageLists) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        Matrix matrix = new Matrix();
        Intent intent = imageGalleryActivity.getIntent();
        int lensFacing = intent.getIntExtra(CameraActivity.LENS_FACING,0);
        /* BackCamera = 1 , FrontCamera = 0. This rotation configuration for image display
        is based on our front and back camera test in actual devices)
         */
        if(lensFacing == 1) {
            matrix.postRotate(90);
        } else {
            matrix.postRotate(270);
        }
        // resizes bitmap
        bitmap = Bitmap.createScaledBitmap(bitmap,(bitmap.getWidth()) / 2,
                (bitmap.getHeight())/2,true);
        // rotates bitmap
        Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),
                matrix,true);
        CameraImage image = new CameraImage(imageIndex,name,rotatedBitmap,path);
        imageLists.add(image);
    }
}
