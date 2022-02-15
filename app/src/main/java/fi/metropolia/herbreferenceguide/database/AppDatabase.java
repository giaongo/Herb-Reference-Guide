package fi.metropolia.herbreferenceguide.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
        private static AppDatabase INSTANCE;
        public abstract NoteDao noteDao();
        public static AppDatabase getINSTANCE(Context context) {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                        "app_database.db")
                        .allowMainThreadQueries()
                        .build();
            }
            return INSTANCE;
        }
}
