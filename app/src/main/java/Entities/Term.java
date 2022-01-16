package Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "termTable")
public class Term {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "termId")
    public int termID;

    @ColumnInfo(name = "termStart")
    public String start;

    @ColumnInfo(name = "termEnd")
    public String end;


    @ColumnInfo(name = "termTitle")
    @NonNull
    public String title;

    public String toString(){
        return "Term{" +
                "termId=" + termID +
                ", termStart=" + start +
                ", termEnd=" + end +
                ", termTitle" + title +
                "}";

    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }





    public Term(int termID, String start, String end, String title) {
        this.termID = termID;
        this.start = start;
        this.end = end;
        this.title = title;

    }


}
