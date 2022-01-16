package Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessmentTable")
public class Assessments {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "assessmentID")
    public int id;

    @ColumnInfo(name = "assessmentType")
    public String type;

    @ColumnInfo(name = "assessmentTitle")
    public String title;

    @ColumnInfo(name = "assessmentStart")
    public String start;

    @ColumnInfo(name = "assessmentEnd")
    public String end;

    @ColumnInfo(name = "assessmentInfo")
    public String description;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @ColumnInfo(name = "assessmentCourse")
    public String course;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Assessments(int id, String type, String title, String start, String end, String description, String course) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.course = course;
    }
}
