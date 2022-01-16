package Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "courseTable")
public class Courses {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "courseID")
    public int  courseId;


    @ColumnInfo(name = "courseTitle")
    public String title;

    @ColumnInfo(name = "courseStart")
    public String start;

    @ColumnInfo(name = "courseEnd")
    public String end;

    @ColumnInfo(name = "courseStatus")
    public String status;

    @ColumnInfo(name = "courseNotes")
    public String notes;

    @ColumnInfo(name = "courseInfo")
    public String description;

    @ColumnInfo(name = "courseInstructor")
    public String instructor;

    @ColumnInfo(name = "courseInstructorPhone")
    public String instructorPhone;

    @ColumnInfo(name = "courseInstructorEmail")
    public String instructorEmail;

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    @ColumnInfo(name = "courseTerm")
    public String termName;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.instructorPhone = instructorPhone;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public Courses(int courseId, String title, String start, String end, String status, String notes, String description, String instructor, String instructorPhone, String instructorEmail, String termName) {
        this.courseId = courseId;
        this.title = title;
        this.start = start;
        this.end = end;
        this.status = status;
        this.notes = notes;
        this.description = description;
        this.instructor = instructor;
        this.instructorPhone = instructorPhone;
        this.instructorEmail = instructorEmail;
        this.termName = termName;
    }
}
