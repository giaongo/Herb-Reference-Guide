package fi.metropolia.herbreferenceguide.camera;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Defines CameraImage class
 * @author Giao Ngo
 * @version 1.0
 * @since 2022-03-01
 */
public class CameraImage implements Parcelable {
    private final int imageId;
    private final String imageName;
    private final Bitmap bitmap;
    private String path;

    /**
     * Defines constructor that initializes the instance of this class
     * @param imageId int
     * @param imageName String
     * @param bitmap Bitmap
     * @param path String
     */
    public CameraImage(int imageId, String imageName, Bitmap bitmap,String path) {
        this.imageId = imageId;
        this.imageName = imageName;
        this.bitmap = bitmap;
        this.path = path;
    }

    /**
     * Constructor to initialize CameraImage with data from parcel
     * @param in parcel
     * @see <a href="https://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents">
     * Stack Overflow: How to send an object from one Android Activity to another using Intents? </a>
     */
    protected CameraImage(Parcel in) {
        imageId = in.readInt();
        imageName = in.readString();
        bitmap = in.readParcelable(Bitmap.class.getClassLoader());
    }

    /**
     * Regenerates a CameraImage object from parcel.
     * Study source and reference from Android Studio and Stack Overflow
     * @see <a href="https://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents">
     * Stack Overflow: How to send an object from one Android Activity to another using Intents? </a>
     */
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

    /**
     * Gets bitmap
     * @return Bitmap
     */
    public Bitmap getBitmap() {
        return bitmap;
    }

    /**
     * Gets image path
     * @return String path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets image path
     * @param path String
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable instance's marshaled representation.
     * @return int 0
     * @see <a href="https://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents">
     * Stack Overflow: How to send an object from one Android Activity to another using Intents? </a>
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     *  Writes object data to a parcel
     * @param parcel parcel to send
     * @param i flags
     * @see <a href="https://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents">
     * Stack Overflow: How to send an object from one Android Activity to another using Intents? </a>
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(imageId);
        parcel.writeString(imageName);
        parcel.writeParcelable(bitmap, i);
    }
}
