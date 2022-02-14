package fi.metropolia.herbreferenceguide.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note")
    List<Note> getAllNote();

    @Insert
    void insertNote(Note...notes);

    @Delete
    void delete(Note note);

}
