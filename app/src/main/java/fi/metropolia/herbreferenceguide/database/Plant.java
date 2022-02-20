package fi.metropolia.herbreferenceguide.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class Plant implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int plantId;

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

    protected Plant(Parcel in) {
        plantId = in.readInt();
        plantName = in.readString();
        plantType = in.readString();
        plantNutrition = in.readString();
        plantHealthBenefit = in.readString();
        plantFoodSuggestion = in.readString();
        plantImgSrc = in.readString();
    }

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

    public int getPlantId() {
        return plantId;
    }

    public String getPlantType() {
        return plantType;
    }

    @NonNull
    public String getPlantName() {
        return plantName;
    }

    @NonNull
    public String getPlantNutrition() {
        return plantNutrition;
    }

    @NonNull
    public String getPlantHealthBenefit() {
        return plantHealthBenefit;
    }

    @NonNull
    public String getPlantFoodSuggestion() {
        return plantFoodSuggestion;
    }

    @NonNull
    public String getPlantImgSrc() {
        return plantImgSrc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

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
