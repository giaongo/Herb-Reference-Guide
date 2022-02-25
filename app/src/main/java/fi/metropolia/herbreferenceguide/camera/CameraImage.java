package fi.metropolia.herbreferenceguide.camera;

import android.graphics.Bitmap;

public class CameraImage {
    private int imageId;
    private String imageName;
    private Bitmap bitmap;

    public CameraImage(int imageId, String imageName, Bitmap bitmap) {
        this.imageId = imageId;
        this.imageName = imageName;
        this.bitmap = bitmap;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
