package fi.metropolia.herbreferenceguide.camera;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import java.util.ArrayList;

public class ImageDeletingThread implements Runnable {
    Message imageThreadMsg;
    Bundle imageThreadBundle = new Bundle();
    public static final String MSG_DELETE = "image deleted";
    int deletePosition;
    ArrayList<CameraImage> imageList;
    ImageGalleryActivity imageGalleryActivity;
    private final Handler objHandler;

    public ImageDeletingThread(int deletePosition, ArrayList<CameraImage> imageList, ImageGalleryActivity imageGalleryActivity,
    Handler objHandler) {
        this.deletePosition = deletePosition;
        this.imageList = imageList;
        this.imageGalleryActivity = imageGalleryActivity;
        this.objHandler = objHandler;
        imageThreadMsg = objHandler.obtainMessage();
    }

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
