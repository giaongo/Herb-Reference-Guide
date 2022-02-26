package fi.metropolia.herbreferenceguide.camera;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class CameraImage implements Parcelable {
    private int imageId;
    private String imageName;
    private Bitmap bitmap;

    public CameraImage(int imageId, String imageName, Bitmap bitmap) {
        this.imageId = imageId;
        this.imageName = imageName;
        this.bitmap = bitmap;
    }

    protected CameraImage(Parcel in) {
        imageId = in.readInt();
        imageName = in.readString();
        bitmap = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<CameraImage> CREATOR = new Creator<CameraImage>() {
        @Override
        public CameraImage createFromParcel(Parcel in) {
            return new CameraImage(in);
        }

        @Override
        public CameraImage[] newArray(int size) {
            return new CameraImage[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(imageId);
        parcel.writeString(imageName);
        parcel.writeParcelable(bitmap, i);
    }
}
