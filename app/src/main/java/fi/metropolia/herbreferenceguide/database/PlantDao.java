package fi.metropolia.herbreferenceguide.database;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlantDao {
    @Query("SELECT * FROM plant")
    List<Plant> getAllPlant();

    @Query("SELECT plantName FROM plant WHERE plantType = :type")
    List<String> getPlantByType(String type);

    @Query("SELECT * FROM plant WHERE  plantId = :id")
    Plant getPlantById(int id);

    @Query("SELECT * FROM plant WHERE plantName = :plantName")
    Plant getPlantByName(String plantName);
}
