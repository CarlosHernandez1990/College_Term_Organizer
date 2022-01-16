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
public interface AssessmentDao {
    @Query("SELECT * FROM assessmentTable ORDER BY assessmentID")
    public List<Assessments> getAssessments();

    @Query("SELECT * FROM assessmentTable WHERE assessmentCourse = :termName")
    public List<Assessments> getAssessmentByTerm(String termName);

    @Insert(onConflict = OnConflictStrategy.FAIL)
    public void insertAssessment(Assessments assessments);

    @Update
    public void updateAssessment(Assessments assessments);

    @Delete
    public void deleteAssessment(Assessments assessments);
}
