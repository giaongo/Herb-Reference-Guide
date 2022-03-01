package fi.metropolia.herbreferenceguide.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


/**
 * This class defines Note that can be fetched from database
 * @author Giao Ngo
 * @version 1.0
 * @since 2022-03-01
 */
@Entity
public class Note {
    @PrimaryKey(autoGenerate = true)
    private final int noteId;
    private final String noteTitle;
    private final String noteDescription;

    /**
     * Constructor for Note class
     * @param noteId defines note Id
     * @param noteTitle defines note Title
     * @param noteDescription defines note Description
     */
    public Note(int noteId, String noteTitle, String noteDescription) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
    }

    /**
     * Gets not id
     * @return int note id
     */
    public int getNoteId() {
        return noteId;
    }

    /**
     * Gets note title
     * @return String note
     */
    public String getNoteTitle() {
        return noteTitle;
    }

    /**
     * Gets note description
     * @return String noteDescription
     */
    public String getNoteDescription() {
        return noteDescription;
    }

}
