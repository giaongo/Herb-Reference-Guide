package fi.metropolia.herbreferenceguide.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

/**
 * This class defines data variables that can be fetched from database
 * @author Tai Nguyen
 * @version 1.0
 * @since 2022-03-01
 */
@Entity
public class Plant implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private final int plantId;

    @NotNull
    private final String plantName;

    @NotNull
    private final String plantType;

    @NotNull
    private final String plantNutrition;

    @NotNull
    private final String plantHealthBenefit;

    @NotNull
    private final String plantFoodSuggestion;

    @NotNull
    private final String plantImgSrc;

    /**
     * Constructor for plant class
     * @param plantId defines plant ID
     * @param plantName defines plant name
     * @param plantType defines plant type
     * @param plantNutrition defines plant nutrition value
     * @param plantHealthBenefit defines plant health benefits
     * @param plantFoodSuggestion defines plant food suggestions
     * @param plantImgSrc defines plant image source
     */
    public Plant(int plantId, @NotNull String plantName, @NotNull String plantType,
                 @NotNull String plantNutrition, @NotNull String plantHealthBenefit,
                 @NotNull String plantFoodSuggestion, @NotNull String plantImgSrc) {
        this.plantId = plantId;
        this.plantName = plantName;
        this.plantType = plantType;
        this.plantNutrition = plantNutrition;
        this.plantHealthBenefit = plantHealthBenefit;
        this.plantFoodSuggestion = plantFoodSuggestion;
        this.plantImgSrc = plantImgSrc;
    }

    /**
     * Constructor to initialize Plant with data from parcel
     * @param in parcel
     * @see <a href="https://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents">
     * Stack Overflow: How to send an object from one Android Activity to another using Intents? </a>
     */
    protected Plant(Parcel in) {
        plantId = in.readInt();
        plantName = in.readString();
        plantType = in.readString();
        plantNutrition = in.readString();
        plantHealthBenefit = in.readString();
        plantFoodSuggestion = in.readString();
        plantImgSrc = in.readString();
    }

    /**
     * Regenerates a Plant object from parcel.
     * Study source and reference from Android Studio and Stack Overflow
     * @see <a href="https://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents">
     * Stack Overflow: How to send an object from one Android Activity to another using Intents? </a>
     */
    public static final Creator<Plant> CREATOR = new Creator<Plant>() {
        @Override
        public Plant createFromParcel(Parcel in) {
            return new Plant(in);
        }

        @Override
        public Plant[] newArray(int size) {
            return new Plant[size];
        }
    };

    /**
     * Gets plant type
     * @return String plant type
     */
    @NotNull
    public String getPlantType(){return plantType;}

    /**
     * Gets plant id
     * @return int id
     */
    @NotNull
    public int getPlantId(){ return  plantId;}

    /**
     * Gets plant name
     * @return String plant name
     */
    @NonNull
    public String getPlantName() {
        return plantName;
    }

    /**
     * Gets plant nutrition
     * @return String plant nutrition
     */
    @NonNull
    public String getPlantNutrition() {
        return plantNutrition;
    }

    /**
     * Gets plant health benefit
     * @return String plant health benefit
     */
    @NonNull
    public String getPlantHealthBenefit() {
        return plantHealthBenefit;
    }

    /**
     * Gets plant food suggestion
     * @return String plant food suggestion
     */
    @NonNull
    public String getPlantFoodSuggestion() {
        return plantFoodSuggestion;
    }

    /**
     * Gets plant image source
     * @return String plant image source
     */
    @NonNull
    public String getPlantImgSrc() {
        return plantImgSrc;
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
        parcel.writeInt(plantId);
        parcel.writeString(plantName);
        parcel.writeString(plantType);
        parcel.writeString(plantNutrition);
        parcel.writeString(plantHealthBenefit);
        parcel.writeString(plantFoodSuggestion);
        parcel.writeString(plantImgSrc);
    }
}
