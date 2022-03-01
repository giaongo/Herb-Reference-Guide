package fi.metropolia.herbreferenceguide.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 *  This classes uses SQLite commands to access, fetch and insert/delete Note data from database
 * @author Giao Ngo
 * @version 1.0
 * @since 2022-03-01
 */
@Dao
public interface NoteDao {
    /**
     * Query to return all notes
     * @return List <Note> </Note>All Notes available in Note activity
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
     * @param note Note
     */
    @Insert
    void insertNote(Note note);

    /**
     * Delete note function
     * @param note Note
     */
    @Delete
    void delete(Note note);

}
