package fi.metropolia.herbreferenceguide.camera;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import java.util.ArrayList;

/**
 * This background thread is defined for deleting images from MediaStore
 * @author Giao Ngo
 * @version 1.0
 * @since 2022-03-01
 */
public class ImageDeletingThread implements Runnable {
    Message imageThreadMsg;
    Bundle imageThreadBundle = new Bundle();
    public static final String MSG_DELETE = "image deleted";
    int deletePosition;
    ArrayList<CameraImage> imageList;
    ImageGalleryActivity imageGalleryActivity;
    private final Handler objHandler;

    /**
     * This constructor is used to initialise the instance of background thread
     * @param deletePosition int
     * @param imageList ArrayList<CameraImage>
     * @param imageGalleryActivity ImageGalleryActivity
     * @param objHandler Handler
     */
    public ImageDeletingThread(int deletePosition, ArrayList<CameraImage> imageList, ImageGalleryActivity imageGalleryActivity,
    Handler objHandler) {
        this.deletePosition = deletePosition;
        this.imageList = imageList;
        this.imageGalleryActivity = imageGalleryActivity;
        this.objHandler = objHandler;
        imageThreadMsg = objHandler.obtainMessage();
    }

    /**
     * Overrides run function, get called when the thread is started. This thread makes a delete
     * query for removing image upon user selection from MediaStore based on same image's path.
     * Updates the delete messages successfully to Bundle, sets that data to Message and sends that
     * message to Handler.
     * Code reference:
     * @see <a href="https://developer.android.google.cn/training/data-storage/shared/media?hl=en#java">
     * Access media files from shared storage: Remove an item </a>
     *  <a href="https://www.youtube.com/watch?v=IVFWC0rwfL4&ab_channel=CodingPursuits">
     *  Running android tasks in background thread</a>
     */
    @Override
    public void run() {
        Uri collection;
        String selection = MediaStore.Images.Media.DATA + " = ?";
        String imagePath = imageList.get(deletePosition).getPath();
        String[] selectionArgs = {imagePath};
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
        } else {
            collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }

        imageGalleryActivity.getContentResolver().delete(
                collection,
                selection,
                selectionArgs
        );
        imageThreadBundle.putString(MSG_DELETE, "Image is deleted");
        imageThreadMsg.setData(imageThreadBundle);
        objHandler.sendMessage(imageThreadMsg);
    }
}
