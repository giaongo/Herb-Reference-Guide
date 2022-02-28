package fi.metropolia.herbreferenceguide.database;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;


/**
 * This classes uses SQLite commands to access and fetch plant data from prepopulate database
 */
@Dao
public interface PlantDao {

    /**
     * Query to select all from plant database with selected type
     * @param type
     * @return data from plant database with selected type
     */
    @Query("SELECT * FROM plant WHERE plantType = :type")
    List<Plant> getPlantByType(String type);


    /**
     * Query to select all from plant database with selected type
     * @param plantName
     * @return database from plant database with selected plant name
     */
    @Query("SELECT * FROM plant WHERE plantName = :plantName")
    Plant getPlantByName(String plantName);
}
