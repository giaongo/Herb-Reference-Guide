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

    @Query("UPDATE note SET noteTitle = :title, noteDescription = :description WHERE noteId = :id")
    void updateNote(String title, String description,int id);

    @Insert
    void insertNote(Note note);

    @Delete
    void delete(Note note);

}
