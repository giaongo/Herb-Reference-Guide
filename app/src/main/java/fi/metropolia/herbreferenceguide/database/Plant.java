package fi.metropolia.herbreferenceguide.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class Plant {

    @PrimaryKey (autoGenerate = true)
    private int plantId;

    @NotNull
    private String plantName;

    @NotNull
    private String plantType;

    @NotNull
    private String plantNutrition;

    @NotNull
    private String plantHealthBenefit;

    @NotNull
    private String plantFoodSuggestion;

    @NotNull
    private String plantImgSrc;

    public Plant(@NotNull int plantId, @NotNull String plantName, @NotNull String plantType,
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

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public String getPlantNutrition() {
        return plantNutrition;
    }

    public void setPlantNutrition(String plantNutrition) {
        this.plantNutrition = plantNutrition;
    }

    public String getPlantHealthBenefit() {
        return plantHealthBenefit;
    }

    public void setPlantHealthBenefit(String plantHealthBenefit) {
        this.plantHealthBenefit = plantHealthBenefit;
    }

    public String getPlantFoodSuggestion() {
        return plantFoodSuggestion;
    }

    public void setPlantFoodSuggestion(String plantFoodSuggestion) {
        this.plantFoodSuggestion = plantFoodSuggestion;
    }

    public String getPlantImgSrc() {
        return plantImgSrc;
    }

    public void setPlantImgSrc(String plantImgSrc) {
        this.plantImgSrc = plantImgSrc;
    }
}
