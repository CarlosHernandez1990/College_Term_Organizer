package com.example.mobiledevelopment.UI;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.example.mobiledevelopment.R;
import com.google.android.material.navigation.NavigationView;

import Dao.AssessmentDao;
import Database.Repository;
import Entities.Assessments;
import Entities.Courses;
import Entities.Term;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setIcon(R.drawable.grad);
        actionBar.setTitle("     Term Scheduler");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Repository repository = new Repository(getApplication());


        if(repository.getAllTerms().isEmpty()) {

            Term term1 = new Term(0, "01/01/2022", "06/29/2022", "Term 1");
            Term term2 = new Term(0, "07/01/2022", "12/29/2022", "Term 2");
            Term term3 = new Term(0, "01/01/2023", "06/29/2023", "Term 3");
            Term term4 = new Term(0, "07/01/2023", "12/29/2023", "Term 4");
            repository.insertTerms(term1);
            repository.insertTerms(term2);
            repository.insertTerms(term3);
            repository.insertTerms(term4);
        }

if(repository.getAllCourses().isEmpty()) {

    Courses course1 = new Courses(0, "C200", "01/01/2022", "02/29/2022", "In Progress", "I love this course. Best class so far.", "This course is a survey of Theatre Arts, theatre history, playwrights, " +
            "practitioners, genres, production methods, dramatic structure, " +
            "performance style, plays, terminology, history, criticism, and stagecraft. " +
            "Students will develop an appreciation for the theatre arts through lectures, play reading, viewing, critiquing, and participating in college " +
            "productions.", "George", "929-347-2475", "george@wgu.edu", "Term 1");
    Courses course2 = new Courses(0, "C201", "03/01/2022", "04/29/2022", "Plan to take", " I am looking forward to this class.", "This is a survey course in trends and developments of 20th Century theatre. Major playwrights " +
            "(Ibsen, Chekhov, Miller), personalities (Craig, Artaud), and theatre innovators (Brecht) of this century will be " +
            "examined. Mainstream and radical influences as well as the impact of " +
            "technology on plays and performances will be discussed.", "Jeff", "909-343-7634", "jeff@wgu.edu", "Term 1");
    Courses course3 = new Courses(0, "C202", "05/01/2022", "06/29/2022", "Plan to take", "I am not sure about this course but I need it for my degree.", "This is a survey course of Theatre History emphasizing cultural, historic, " +
            "and international theatre from its origins through the 17th Century. It " +
            "includes exploration of experience, imagination, and expression of " +
            "dramatic art forms throughout the world. Topics include historical " +
            "relevance and context, text analysis, acting style, theme, language, " +
            "diction, set, audience, gender issues, special effects, cultural " +
            "significance, and production stylization", "Janice", "365-888-9211", "janice@wgu.edu", "Term 1");
    Courses course4 = new Courses(0, "C203", "07/01/2022", "08/29/2022", "Plan to take", " This may be a tough one.", "This a survey course of Theatre History emphasizing cultural, historic, " +
            "and contemporary theatre from 1700 to the present. It includes " +
            "exploration of experience, imagination, and expression in dramatic art " +
            "forms throughout the world. Topics include historical development and " +
            "context, text analysis, acting style, theme, language, diction, set,", "Charlotte", "209-342-4776", "charlotte@wgu.edu", "Term 2");
    Courses course5 = new Courses(0, "C204", "09/01/2022", "10/29/2022", "Plan to take", " This class should be a easy one", "This course prepares a student to apply basic acting theory to " +
            "performance and develops the skills of interpretation of drama through " +
            "acting. Special attention is paid to skills for performance: memorization, " +
            "stage movement, vocal production, and interpretation of text. (", "Greg", "317-893-2352", "greg@wgu.edu", "Term 2");
    Courses course6 = new Courses(0, "C205", "011/01/2022", "12/29/2022", "Plan to take", "This is the class I am the most interested in.", "This course prepares a student to apply basic acting theory to " +
            "performance and develops the skills of interpretation of drama through " +
            "acting. Special attention is paid to skills for performance: memorization, " +
            "stage movement, vocal production, and interpretation of text.", "Miguel", "407-234-6332", "miguel@wgu.edu", "Term 2");
    Courses course7 = new Courses(0, "C206", "01/01/2023", "02/29/2023", "Plan to take", " This should help get close to my degree.", "This laboratory course follows Acting I and Acting II and continues the " +
            "exploration of theories and techniques used in preparation for the " +
            "interpretation of drama through acting. The emphasis will be placed on " +
            "deepening the understanding of the acting process through character " +
            "analysis, monologues, and scenes", "Henry", "254-235-6545", "henry@wgu.edu", "Term 3");
    Courses course8 = new Courses(0, "C207", "03/01/2023", "04/29/2023", "Plan to take", "I've heard very good things about this instructor.", "A course that focuses on the rehearsal and performance of a major play " +
            "or musical. Activities may include acting, stage management, backstage " +
            "operations, costuming, stagecraft and front of house operations. Play " +
            "selections vary each time this course is taught. Students may enroll " +
            "more than once for this course until reaching the maximum number of 6 " +
            "total units", "Megan", "707-545-2363", "megan@wgu.edu", "Term 3");
    Courses course9 = new Courses(0, "C208", "05/01/2023", "06/29/2023", "Plan to take", " This course is going to be absolutely great.", "This course is designed to introduce the student to the background, " +
            "function and techniques of the stage director. Included in the course will " +
            "be an investigation of the principles involved in script selection and " +
            "interpretation, the fundamentals of casting, rehearsal techniques, " +
            "blocking, aims and conduct, rehearsal scheduling, and the preparation " +
            "of a director's prompt book. Students should have previous experience " +
            "in theatre performance and production.", "Ashley", "530-624-3623", "ashley@wgu.edu", "Term 3");
    Courses course10 = new Courses(0, "C209", "07/01/2023", "08/29/2023", "Plan to take", " I heard this course is pretty dry.", "This course focuses on the technical principles of theatrical productions. " +
            "Subjects covered include the use of basic power tools, the design, " +
            "construction and painting of scenery, hanging and operating lighting " +
            "instruments, basic stage management and understanding backstage " +
            "operations. Students will learn how to interpret theatrical construction " +
            "diagrams, floor plans for stage sets, and light plots.", "Margret", "709-343-2543", "margret@wgu.edu", "Term 4");
    Courses course11 = new Courses(0, "C210", "09/01/2023", "10/29/2023", "Plan to take", " I plan to take this one as of now.", "This course is designed to introduce the student to the principles and " +
            "practical application of stage makeup. Emphasis will be given to facial " +
            "structure, character analysis, makeup selection, application, facial " +
            "modeling, three-dimensional techniques, false hair, character and " +
            "corrective makeup. The student will demonstrate his/her understanding " +
            "through actual application in the classroom and as a member of a ", "Rob", "325-879-2744", "rob@wgu.edu", "Term 4");
    Courses course12 = new Courses(0, "C212", "11/01/2023", "12/29/2023", "Plan to take", "This course has a huge assessment at the end of it. I might need an extension for this one", "This lab course is designed to develop the student’s skills introduced in " +
            "Theatre 34, Makeup. Emphasis will be given to corrective character " +
            "analysis, makeup selection and application techniques. The student will " +
            "demonstrate his/her understanding through actual application in the " +
            "classroom and as a member of a makeup crew for a specific play " +
            "production, special exercise, or project.", "Jessie", "462-825-3386", "jessie@wgu.edu", "Term 4");
    repository.insertCourse(course1);
    repository.insertCourse(course2);
    repository.insertCourse(course3);
    repository.insertCourse(course4);
    repository.insertCourse(course5);
    repository.insertCourse(course6);
    repository.insertCourse(course7);
    repository.insertCourse(course8);
    repository.insertCourse(course9);
    repository.insertCourse(course10);
    repository.insertCourse(course11);
    repository.insertCourse(course11);
}


if(repository.getAllAssessments().isEmpty()) {

    Assessments assessment1 = new Assessments(0, "performance", "c200 pa", "01/01/2022", "02/29/2022", "The performance assessment for c200", "C200");
    Assessments assessment2 = new Assessments(0, "performance", "c201 pa", "03/01/2022", "04/29/2022", "The performance assessment for c201", "C201");
    Assessments assessment3 = new Assessments(0, "performance", "c202 pa", "05/01/2022", "06/29/2022", "The performance assessment for c202", "C202");
    Assessments assessment4 = new Assessments(0, "performance", "c203 pa", "07/01/2022", "08/29/2022", "The performance assessment for c203", "C203");
    Assessments assessment5 = new Assessments(0, "Objective", "c204 Obj", "09/01/2022", "10/29/2022", "The Objective assessment for c204", "C204");
    Assessments assessment6 = new Assessments(0, "performance", "c205 pa", "11/01/2022", "12/29/2022", "The performance assessment for c205", "C205");
    Assessments assessment7 = new Assessments(0, "performance", "c206 pa", "01/01/2022", "02/29/2023", "The performance assessment for c206", "C206");
    Assessments assessment8 = new Assessments(0, "performance", "c207 pa", "03/01/2022", "04/29/2023", "The performance assessment for c207", "C207");
    Assessments assessment9 = new Assessments(0, "Objective", "c208 Obj", "05/01/2022", "06/29/2023", "The Objective assessment for c208", "C208");
    Assessments assessment10 = new Assessments(0, "performance", "c209 pa", "07/01/2022", "08/29/2023", "The performance assessment for c209", "C209");
    Assessments assessment11 = new Assessments(0, "performance", "c210 pa", "09/01/2022", "10/29/2023", "The performance assessment for c210", "C210");
    Assessments assessment12 = new Assessments(0, "Objective", "c211 Obj", "11/01/2022", "12/29/2023", "The Objective assessment for c211", "C211");
    repository.insertAssessments(assessment1);
    repository.insertAssessments(assessment2);
    repository.insertAssessments(assessment3);
    repository.insertAssessments(assessment4);
    repository.insertAssessments(assessment5);
    repository.insertAssessments(assessment6);
    repository.insertAssessments(assessment7);
    repository.insertAssessments(assessment8);
    repository.insertAssessments(assessment9);
    repository.insertAssessments(assessment10);
    repository.insertAssessments(assessment11);
    repository.insertAssessments(assessment12);

}



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.main, menu);
        menu.getItem(1).setVisible(false);
       menu.getItem(2).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {

            case R.id.refresh:
                Intent refresh = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(refresh);
                break;

            case R.id.homeButton:
                Intent intent = new Intent(this, ViewTerms.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void enterIn(View view) {
        Intent intent = new Intent(MainActivity.this, ViewTerms.class);
        startActivity(intent);

    }


}