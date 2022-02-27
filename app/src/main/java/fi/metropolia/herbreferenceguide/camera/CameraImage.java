package fi.metropolia.herbreferenceguide.camera;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class CameraImage implements Parcelable {
    private final int imageId;
    private final String imageName;
    private final Bitmap bitmap;
    private String path;

    public CameraImage(int imageId, String imageName, Bitmap bitmap,String path) {
        this.imageId = imageId;
        this.imageName = imageName;
        this.bitmap = bitmap;
        this.path = path;
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

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
