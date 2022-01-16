package Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Entities.Assessments;
import Entities.Courses;

@Dao
public interface CourseDao {

    public int termId = 0;
    @Query("SELECT * FROM courseTable ORDER BY courseID")
    public List<Courses> getCourses();

    @Query("SELECT * FROM courseTable WHERE courseTerm = :termName")
    public List<Courses> getCourseByTerm(String termName);

    @Insert(onConflict = OnConflictStrategy.FAIL)
    public void insertCourse(Courses courses);

    @Update
    public void updateCourse(Courses courses);

    @Delete
    public void deleteCourse(Courses courses);
}
