package fi.metropolia.herbreferenceguide.database;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;


/**
 * This classes uses SQLite commands to access and fetch plant data from prepopulate database
 */
@Dao
public interface PlantDao {

    @Query("SELECT * FROM plant WHERE plantType = :type")
    List<Plant> getPlantByType(String type);

    @Query("SELECT * FROM plant WHERE plantName = :plantName")
    Plant getPlantByName(String plantName);
}
