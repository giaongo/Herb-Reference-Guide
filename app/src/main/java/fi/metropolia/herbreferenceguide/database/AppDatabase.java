package fi.metropolia.herbreferenceguide.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class, Plant.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
        private static AppDatabase INSTANCE;
        public abstract NoteDao noteDao();
        public abstract PlantDao plantDao();
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
