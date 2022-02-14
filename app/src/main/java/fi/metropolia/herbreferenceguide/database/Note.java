package fi.metropolia.herbreferenceguide.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {
    @PrimaryKey (autoGenerate = true)
    public int noteId;

    @ColumnInfo (name = "note_title")
    public String note_title;

    @ColumnInfo (name = "note_description")
    public String note_description;
}
