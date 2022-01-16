package Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import Dao.AssessmentDao;
import Dao.CourseDao;
import Dao.TermDao;
import Entities.Assessments;
import Entities.Courses;
import Entities.Term;

@Database(entities = {Assessments.class, Courses.class, Term.class}, version = 6, exportSchema = false)
public abstract class SchoolDatabase extends RoomDatabase {
    public abstract AssessmentDao assessmentDao();
    public abstract CourseDao courseDao();
    public abstract TermDao termDao();

    private static volatile SchoolDatabase INSTANCE;

    public static SchoolDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (SchoolDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SchoolDatabase.class,
                            "schoolDatabase.db").fallbackToDestructiveMigration().allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }

}
