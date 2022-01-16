package Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Entities.Courses;
import Entities.Term;

@Dao
public interface TermDao {


    @Query("SELECT * FROM termTable ORDER BY termId")
    public List<Term> getTerms();

    @Insert(onConflict = OnConflictStrategy.FAIL)
    public void insertTerm(Term term);

    @Update
    public void updateTerm(Term term);

    @Delete
    public void deleteTerm(Term term);
}
