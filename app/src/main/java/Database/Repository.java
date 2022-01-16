package Database;

import android.app.Application;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Dao.AssessmentDao;
import Dao.CourseDao;
import Dao.TermDao;
import Entities.Assessments;
import Entities.Courses;
import Entities.Term;

public class Repository {
    private AssessmentDao mAssessmentDao;
    private CourseDao mCourseDao;
    private TermDao mTermDao;
    private List<Assessments> mAllAssessments;
    private List<Courses> mAllCourses;
    private List<Term> mAllTerms;
    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        SchoolDatabase dataBuilder = SchoolDatabase.getDatabase(application);
        mAssessmentDao = dataBuilder.assessmentDao();
        mCourseDao = dataBuilder.courseDao();
        mTermDao = dataBuilder.termDao();
    }
    public List<Assessments> getAllAssessments() {
        databaseExecutor.execute(() -> {
            mAllAssessments = mAssessmentDao.getAssessments();
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllAssessments;
    }

    public String assessmentCourse = null;
    public List<Assessments> getAllAssessmentsByTerm(String assessmentCourse) {
        databaseExecutor.execute(()->{
            mAllAssessments = mAssessmentDao.getAssessmentByTerm(assessmentCourse);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllAssessments;
    }

    public void updateAssessments(Assessments assessments){
        databaseExecutor.execute(()->{
            mAssessmentDao.updateAssessment(assessments);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void insertAssessments(Assessments assessments){
        databaseExecutor.execute(()->{
            mAssessmentDao.insertAssessment(assessments);
        });
    try{
            Thread.sleep(1000);
        }
    catch (InterruptedException e){
        e.printStackTrace();
    }
    }
    public void deleteAssessments(Assessments assessments){
        databaseExecutor.execute(()->{
            mAssessmentDao.deleteAssessment(assessments);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    public List<Courses> getAllCourses() {
        databaseExecutor.execute(() -> {
            mAllCourses = mCourseDao.getCourses();
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllCourses;
    }
    public String termName = null;
    public List<Courses> getAllCoursesByTerm(String termName) {
        databaseExecutor.execute(()->{
            mAllCourses = mCourseDao.getCourseByTerm(termName);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllCourses;
    }

    public void updateCourses(Courses courses){
        databaseExecutor.execute(()->{
            mCourseDao.updateCourse(courses);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void insertCourse(Courses courses){
        databaseExecutor.execute(()->{
            mCourseDao.insertCourse(courses);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void deleteCourses(Courses courses){
        databaseExecutor.execute(()->{
            mCourseDao.deleteCourse(courses);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public List<Term> getAllTerms() {
        databaseExecutor.execute(() -> {
            mAllTerms = mTermDao.getTerms();
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllTerms;
    }


    public void updateTerms(Term term){
        databaseExecutor.execute(()->{
            mTermDao.updateTerm(term);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void insertTerms(Term term){
        databaseExecutor.execute(()->{
            mTermDao.insertTerm(term);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void deleteTerms(Term term){
        databaseExecutor.execute(()->{
            mTermDao.deleteTerm(term);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }



}
