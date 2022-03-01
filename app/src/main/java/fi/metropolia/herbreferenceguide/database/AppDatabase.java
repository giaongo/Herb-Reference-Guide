package fi.metropolia.herbreferenceguide.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


/**
 * Database entity for both Note and Plant class
 * This class informs android studio that a pre-populated database is added
 * @author Giao Ngo, Tai Nguyen, Shayne Kandagor
 * @version 1.0
 * @since 2022-03-01
 */
@Database(entities = {Note.class, Plant.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
        private static AppDatabase INSTANCE;
        public abstract NoteDao noteDao();
        public abstract PlantDao plantDao();

    /**
     * Gets instance of singleton AppDatabase. If instance does not exist, an instance is created
     * @param context Context
     * @return instance of AppDatabase
     */
    public synchronized static AppDatabase getINSTANCE(Context context) {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                        "app_database.db")
                        .createFromAsset("database/plant.db")
                        .allowMainThreadQueries()
                        .build();
            }
            return INSTANCE;
        }
}
