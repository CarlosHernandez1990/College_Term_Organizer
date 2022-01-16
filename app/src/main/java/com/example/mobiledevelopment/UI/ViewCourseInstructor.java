package com.example.mobiledevelopment.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.mobiledevelopment.R;

import Dao.CourseDao;
import Database.Repository;
import Entities.Courses;

public class ViewCourseInstructor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course_instructor);

        String courseInstructor = getIntent().getStringExtra("courseInstructor");
        String courseInstructorPhone = getIntent().getStringExtra("courseInstructorPhone");
        String courseInstructorEmail = getIntent().getStringExtra("courseInstructorEmail");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.grad);
        actionBar.setTitle("         Instructor");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        EditText instructor = findViewById(R.id.instructorNameEdit);
        instructor.setText(courseInstructor);
        EditText instructorPhone = findViewById(R.id.instructorPhoneEdit);
        instructorPhone.setText(courseInstructorPhone);
        EditText instructorEmail = findViewById(R.id.instructorEmailEdit);
        instructorEmail.setText(courseInstructorEmail);
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

            case R.id.homeButton:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveInstructor(View view) {
        String courseTitle = getIntent().getStringExtra("courseTitle");
        String courseStart = getIntent().getStringExtra("courseStart");
        String courseEnd = getIntent().getStringExtra("courseEnd");
        String courseStatus = getIntent().getStringExtra("courseStatus");
        String courseNotes = getIntent().getStringExtra("courseNotes");
        String courseDescription = getIntent().getStringExtra("courseInfo");
        String courseTerm = getIntent().getStringExtra("courseTerm");
        int courseID = getIntent().getIntExtra("courseID", 0);

        EditText instructor = findViewById(R.id.instructorNameEdit);
        EditText instructorPhone = findViewById(R.id.instructorPhoneEdit);
        EditText instructorEmail = findViewById(R.id.instructorEmailEdit);
        Repository repository = new Repository(getApplication());
        if(courseID == 0){
            courseID = repository.getAllCourses().size();
            System.out.println(courseID);
        }

        Courses courses = new Courses(courseID,courseTitle,courseStart,courseEnd,courseStatus,courseNotes,
                courseDescription,instructor.getText().toString(),instructorPhone.getText().toString(),
                instructorEmail.getText().toString(),courseTerm);

        if(instructor.getText().toString().isEmpty() || instructorPhone.getText().toString().isEmpty() ||
        instructorEmail.getText().toString().isEmpty())
        {
            emptyAlert();
        }
        else {
            repository.updateCourses(courses);
            Intent intent = new Intent(this, ViewCourses.class);
            intent.putExtra("termTitle", courseTerm);
            startActivity(intent);
        }



    }

    public void emptyAlert(){

        AlertDialog alt = new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("All fields must be filled out in order to proceed")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                })
                .create();
        alt.show();
    }

    public void alert(String message){
        Intent intent = new Intent(this, ViewCourses.class);
        AlertDialog alt = new AlertDialog.Builder(this)
                .setTitle("Info")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startActivity(intent);
                    }
                })
                .create();
        alt.show();
    }

    public void backToCourses(View view) {
        Intent intent = new Intent(this, ViewCourses.class);
        String courseTerm = getIntent().getStringExtra("courseTerm");
        System.out.println(courseTerm + "             wwwwwwwwwwwatch this");
        intent.putExtra("termTitle", courseTerm);
        EditText instructor = findViewById(R.id.instructorNameEdit);
        EditText instructorPhone = findViewById(R.id.instructorPhoneEdit);
        EditText instructorEmail = findViewById(R.id.instructorEmailEdit);
        if(instructor.getText().toString().isEmpty() || instructorEmail.getText().toString().isEmpty()
        || instructorPhone.getText().toString().isEmpty()){
            alert("All empty fields will labeled unassigned.");

        }
        else {
            startActivity(intent);
        }
    }
}