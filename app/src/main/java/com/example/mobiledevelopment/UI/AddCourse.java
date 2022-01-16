package com.example.mobiledevelopment.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.mobiledevelopment.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import Database.Repository;
import Entities.Assessments;
import Entities.Courses;
import Entities.Term;

public class AddCourse extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    public DatePickerDialog.OnDateSetListener dateSetListener;
    public DatePickerDialog.OnDateSetListener dateSetListenerEnd;
    private AlertHelper notificationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        String courseTitle = getIntent().getStringExtra("courseTitle");
        String courseStart = getIntent().getStringExtra("courseStart");
        String courseEnd = getIntent().getStringExtra("courseEnd");
        String courseStatus = getIntent().getStringExtra("courseStatus");
        String courseNotes = getIntent().getStringExtra("courseNotes");
        String courseDescription = getIntent().getStringExtra("courseInfo");
        EditText courseTitleEdit = findViewById(R.id.courseTitleEdit);
        Button courseStartEdit = findViewById(R.id.courseStartEdit);
        Button courseEndEdit = findViewById(R.id.courseEndEdit);
        EditText courseStatusEdit = findViewById(R.id.courseStatusEdit);
        EditText courseNotesEdit = findViewById(R.id.courseNotesEdit);
        EditText courseDescriptionEdit = findViewById(R.id.courseDescriptionEdit);
        courseTitleEdit.setText(courseTitle);
        courseStartEdit.setText(courseStart);
        courseEndEdit.setText(courseEnd);
        courseStatusEdit.setText(courseStatus);
        courseNotesEdit.setText(courseNotes);
        courseDescriptionEdit.setText(courseDescription);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);

        datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, dayOfMonth);
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicked, int year, int month, int dayOfMonth) {

                String date = month+1 +"/" + dayOfMonth + "/" + year;
                courseStartEdit.setText(date);
            }
        };

        dateSetListenerEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicked, int year, int month, int dayOfMonth) {

                String date = month+1 + "/" + dayOfMonth + "/" + year;
                courseEndEdit.setText(date);
            }
        };



        notificationHelper = new AlertHelper(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.grad);
        actionBar.setTitle("   Modify Course");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        if(courseTitle == null){
            courseTitleEdit.setFocusable(true);
        }
        else{
            courseTitleEdit.setFocusable(false);
        }

    }

    private void startAlarm(Date cal){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MyReceiver.class);
        intent.putExtra("Date", "Your course is beginning.");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        Long start = cal.getTime();
        System.out.println(start.toString() + "print this out to see if alarm is working");
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, start, pendingIntent);
    }

    private void endAlarm(Date cal){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MyReceiver.class);
        intent.putExtra("Date", "Your course has ended.");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 2, intent, 0);
        Long end = cal.getTime();
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, end, pendingIntent);
    }

    public void sendToChannel1(String title , String message){
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification(title, message);
        notificationHelper.getNotificationManager().notify(1, nb.build());
    }


    public void sendToChannel2(String title , String message){
        NotificationCompat.Builder nb2 = notificationHelper.getChannelNotification(title, message);
        notificationHelper.getNotificationManager().notify(2, nb2.build());
    }


    public void openDatePickerStartCourse(View view)
    {
        datePickerDialog.setOnDateSetListener(dateSetListener);
        datePickerDialog.show();
    }

    public void openDatePickerEndCourse(View view) {
        datePickerDialog.setOnDateSetListener(dateSetListenerEnd);
        datePickerDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        EditText course = findViewById(R.id.courseTitleEdit);
        Button start = findViewById(R.id.courseStartEdit);
        Button end = findViewById(R.id.courseEndEdit);
        EditText notes = findViewById(R.id.courseNotesEdit);
        String courseTerm = getIntent().getStringExtra("courseTerm");
        switch (item.getItemId())
        {
            case android.R.id.home:

                Intent intent1 = new Intent(this, ViewCourses.class);
                intent1.putExtra("termTitle", courseTerm);
                break;
            case R.id.refresh:
                Intent refresh = new Intent(getApplicationContext(), AddCourse.class);
                refresh.putExtra("courseTerm", courseTerm);
                startActivity(refresh);
                break;
            case R.id.homeButton:
                Intent intent = new Intent(this, ViewTerms.class);
                startActivity(intent);
                break;
            case R.id.shareButton:
                Intent share2 = new Intent();
                share2.setAction(Intent.ACTION_SEND);
                share2.putExtra(Intent.EXTRA_TEXT, course.getText().toString() + " will begin on " +
                        start.getText().toString() + " and will end on " + end.getText().toString() + ". Notes: " +
                         notes.getText().toString());
                share2.setType("text/plain");
                startActivity(share2);
                break;
            case R.id.setAlarm:
                EditText title = findViewById(R.id.courseTitleEdit);
                String courseStart = start.getText().toString();
                String courseEnd = end.getText().toString();
                String courseTitle = title.getText().toString();

                String dateConversion = "MM/dd/yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateConversion, Locale.US);
                Date date;
                try {
                    date = simpleDateFormat.parse(courseStart);

                Date date1 = simpleDateFormat.parse(courseEnd);

                if (courseStart.isEmpty() || courseEnd.isEmpty()){
                    alert("Please enter actual dates to set course alarms.");
                }
                else {
                    sendToChannel1(courseTitle, " This course will begin on " + courseStart +
                            ". Alert has been set.");
                    startAlarm(date);
                    sendToChannel2(courseTitle, " This course will end on " + courseEnd +
                            ". Alert has been set.");
                    endAlarm(date1);
                    break;
                }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveCourse(View view) throws ParseException {
        EditText courseTitleEdit = findViewById(R.id.courseTitleEdit);
        String courseTitle = courseTitleEdit.getText().toString();
        Button courseStartEdit = findViewById(R.id.courseStartEdit);
        String courseStart = courseStartEdit.getText().toString();
        Button courseEndEdit = findViewById(R.id.courseEndEdit);
        String courseEnd = courseEndEdit.getText().toString();
        EditText courseStatusEdit = findViewById(R.id.courseStatusEdit);
        String courseStatus = courseStatusEdit.getText().toString();
        EditText courseNotesEdit = findViewById(R.id.courseNotesEdit);
        String courseNotes = courseNotesEdit.getText().toString();
        EditText courseDescriptionEdit = findViewById(R.id.courseDescriptionEdit);
        String courseDescription = courseDescriptionEdit.getText().toString();
        String courseTerm = getIntent().getStringExtra("courseTerm");
        String courseInstructor = getIntent().getStringExtra("courseInstructor");
        String courseInstructorPhone = getIntent().getStringExtra("courseInstructorPhone");
        String courseInstructorEmail = getIntent().getStringExtra("courseInstructorEmail");
        int courseID = getIntent().getIntExtra("courseID", 0);

        System.out.println(courseTerm);
        Repository repository = new Repository(getApplication());
        boolean newCourse = true;
        for(Courses courses : repository.getAllCoursesByTerm(courseTerm))
        {

                String existing = courses.getTitle().toString();


            if(courseTitle.equalsIgnoreCase(existing)) {
                newCourse = false;
                break;
            }

        }
if(courseTitle.isEmpty() || courseStart.isEmpty() || courseEnd.isEmpty() || courseStatus.isEmpty() ||
courseNotes.isEmpty() || courseDescription.isEmpty()){
    emptyAlert();
}
else {


    if (courseID == 0) {

        if (newCourse) {

            Courses course = new Courses(courseID, courseTitle, courseStart, courseEnd, courseStatus, courseNotes,
                    courseDescription, "unassigned", "unassigned", "unassigned", courseTerm);
            repository.insertCourse(course);
            Intent intent = new Intent(this, ViewCourseInstructor.class);

            intent.putExtra("courseStart", courseStart);
            intent.putExtra("courseEnd", courseEnd);
            intent.putExtra("courseTitle", courseTitle);
            intent.putExtra("courseNotes", courseNotes);
            intent.putExtra("courseStatus", courseStatus);
            intent.putExtra("courseInfo", courseDescription);
            intent.putExtra("courseTerm", courseTerm);
            startActivity(intent);
            alert(courseTitle + " has been successfully saved.");
        }
    }  else {


        Courses course = new Courses(courseID, courseTitle, courseStart, courseEnd, courseStatus, courseNotes,
                courseDescription, courseInstructor, courseInstructorPhone, courseInstructorEmail, courseTerm);
        repository.updateCourses(course);
        alert(courseTitle + " has been successfully updated.");

    }

}



    }

    public void viewInstructor(View view) {
        Repository repository = new Repository(getApplication());
        EditText courseTitleEdit = findViewById(R.id.courseTitleEdit);
        String courseTitle = courseTitleEdit.getText().toString();
        String courseStart = getIntent().getStringExtra("courseStart");
        String courseEnd = getIntent().getStringExtra("courseEnd");
        String courseStatus = getIntent().getStringExtra("courseStatus");
        String courseNotes = getIntent().getStringExtra("courseNotes");
        String courseDescription = getIntent().getStringExtra("courseInfo");
        String courseTerm = getIntent().getStringExtra("courseTerm");
        String courseInstructor = getIntent().getStringExtra("courseInstructor");
        String courseInstructorPhone = getIntent().getStringExtra("courseInstructorPhone");
        String courseInstructorEmail = getIntent().getStringExtra("courseInstructorEmail");
        int courseID = getIntent().getIntExtra("courseID", 0);

        Intent intent = new Intent(this, ViewCourseInstructor.class);
        intent.putExtra("courseStart", courseStart);
        intent.putExtra("courseEnd", courseEnd);
        intent.putExtra("courseTitle", courseTitle);
        intent.putExtra("courseNotes", courseNotes);
        intent.putExtra("courseStatus", courseStatus);
        intent.putExtra("courseInfo", courseDescription);
        intent.putExtra("courseInstructor", courseInstructor);
        intent.putExtra("courseInstructorPhone", courseInstructorPhone);
        intent.putExtra("courseInstructorEmail", courseInstructorEmail);
        intent.putExtra("courseID",courseID);
        intent.putExtra("courseTerm", courseTerm);
        Boolean isIn = false;
        for (Courses courses : repository.getAllCourses())
        {
            if(courses.getTitle().toString().equalsIgnoreCase(courseTitleEdit.getText().toString()))
            {
                isIn = true;
            }
        }

        if(isIn) {
            startActivity(intent);
        }
        else {
             error("Please select an existing course to view Instructor information.");

        }


    }

    public void emptyAlert(){

        AlertDialog alt = new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("All fields must be filled out in order to save a course.")
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

        AlertDialog alt = new AlertDialog.Builder(this)
                .setTitle("Info")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                })
                .create();
        alt.show();
    }

    public void error(String message){
        String courseTerm = getIntent().getStringExtra("courseTerm");
        Intent intent1 = new Intent(this, ViewCourses.class);
        intent1.putExtra("termTitle", courseTerm);
        AlertDialog alt = new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();


                        startActivity(intent1);

                    }
                })
                .create();
        alt.show();
    }

    public void deleteAlert(String message){
        String courseTerm = getIntent().getStringExtra("courseTerm");
        Intent intent = new Intent(this, ViewCourses.class);
        intent.putExtra("termTitle", courseTerm);
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

    public void deleteCourse(View view) {
        Repository repository = new Repository(getApplication());
        EditText courseName = findViewById(R.id.courseTitleEdit);
        String courseTitle = courseName.getText().toString();
        boolean hasAssessments = false;
        boolean isIn = false;
        Courses foundCourse = null;
        for(Assessments assessments : repository.getAllAssessments()){
            if(assessments.getCourse().equalsIgnoreCase(courseTitle)){
                hasAssessments = true;
            }
        }

        for (Courses courses : repository.getAllCourses()) {
            if (courses.getTitle().equalsIgnoreCase(courseTitle)) {
                isIn = true;
                foundCourse = courses;
                break;
        }

        }
        if(isIn)
        {
        if (!hasAssessments)
        {
                    deleteAlert(courseTitle + " has been successfully deleted.");
            repository.deleteCourses(foundCourse);
        }
        else
            {
            alert("Please delete all corresponding assessments before deleting a course.");
            }

        }
        else
            {
            deleteAlert("Please select an existing course to delete.");
            }
    }

    public void viewAssessments(View view) {
        EditText course = findViewById(R.id.courseTitleEdit);
        String courseName = null;
        Repository repository = new Repository(getApplication());
        boolean isIn = false;
        for(Courses courses : repository.getAllCourses()) {
            if(course.getText().toString().equalsIgnoreCase(courses.getTitle())){
                courseName = courses.getTitle();
                isIn = true;
            }
        }
        if(isIn){
            Intent intent = new Intent(this, ViewAssessments.class);
            intent.putExtra("course", courseName);
            startActivity(intent);
        }
        else{
            deleteAlert("Please select a valid course to view Assessments");
        }
    }


}