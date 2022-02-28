package fi.metropolia.herbreferenceguide.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 *  This classes uses SQLite commands to access, fetch and insert/delete Note data from database
 */


@Dao
public interface NoteDao {
    /**
     * Query to return all notes
     * @return All Notes available in Note activity
     */
    @Query("SELECT * FROM note")
    List<Note> getAllNote();

    /**
     * Query to update current notes in phone
     * @param title defines note title
     * @param description defines note description
     * @param id defines note id
     */
    @Query("UPDATE note SET noteTitle = :title, noteDescription = :description WHERE noteId = :id")
    void updateNote(String title, String description, int id);

    /**
     * Insert note function
     * @param note
     */
    @Insert
    void insertNote(Note note);

    /**
     * Delete note function
     * @param note
     */
    @Delete
    void delete(Note note);

}
