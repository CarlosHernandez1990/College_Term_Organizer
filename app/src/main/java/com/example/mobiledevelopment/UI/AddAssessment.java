package com.example.mobiledevelopment.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
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

public class AddAssessment extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    public DatePickerDialog.OnDateSetListener dateSetListener;
    public DatePickerDialog.OnDateSetListener dateSetListenerEnd;
    private AlertHelper notificationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment);
        EditText title = findViewById(R.id.assessmentTitleEdit);
        title.setText(getIntent().getStringExtra("assessmentTitle"));
        Button start = findViewById(R.id.assessmentStartEdit);
        start.setText(getIntent().getStringExtra("assessmentStart"));
        Button end = findViewById(R.id.assessmentEndEdit);
        end.setText(getIntent().getStringExtra("assessmentEnd"));
        EditText type = findViewById(R.id.assessmentTypeEdit);
        type.setText(getIntent().getStringExtra("assessmentType"));
        EditText info = findViewById(R.id.assessmentInfoEdit);
        info.setText(getIntent().getStringExtra("assessmentInfo"));

        notificationHelper = new AlertHelper(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.grad);
        actionBar.setTitle(" Modify Assessment");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, dayOfMonth);
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicked, int year, int month, int dayOfMonth) {

                String date = month+1 +"/" + dayOfMonth + "/" + year;
                start.setText(date.toString());
            }
        };

        dateSetListenerEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicked, int year, int month, int dayOfMonth) {

                String date = month+1 + "/" + dayOfMonth + "/" + year;
                end.setText(date);
            }
        };


        if(!title.getText().toString().isEmpty())
        {
            title.setFocusable(false);
        }
    }

    private void startAlarm(Date cal){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MyReceiver.class);
        intent.putExtra("Date", "Your assessment is now available.");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        Long start = cal.getTime();
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, start, pendingIntent);
    }

    private void endAlarm(Date cal){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MyReceiver.class);
        intent.putExtra("Date", "Your assessment is now closed.");
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

    public void openDatePickerStartAssessment(View view)
    {
        datePickerDialog.setOnDateSetListener(dateSetListener);
        datePickerDialog.show();
    }

    public void openDatePickerEndAssessment(View view) {
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
        EditText name = findViewById(R.id.assessmentTitleEdit);
        Button start = findViewById(R.id.assessmentStartEdit);
        Button end =findViewById(R.id.assessmentEndEdit);
        String course = getIntent().getStringExtra("course");
        switch (item.getItemId())
        {
            case android.R.id.home:

                Intent intent1 = new Intent(this, ViewAssessments.class);
                intent1.putExtra("course", course);
                break;
            case R.id.refresh:
                Intent refresh = new Intent(getApplicationContext(), AddAssessment.class);
                refresh.putExtra("course", course);
                startActivity(refresh);
                break;

            case R.id.homeButton:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.shareButton:
                Intent share2 = new Intent();
                share2.setAction(Intent.ACTION_SEND);
                share2.putExtra(Intent.EXTRA_TEXT, name.getText().toString() + " will be available on " +
                        start.getText().toString() + " and will be due by " + end.getText().toString() + ".");
                share2.setType("text/plain");
                startActivity(share2);
                break;
            case R.id.setAlarm:

                String dateConversion = "MM/dd/yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateConversion, Locale.US);
                Date date = null;
                try {
                    date = simpleDateFormat.parse(start.getText().toString());
                Date date1 = simpleDateFormat.parse(end.getText().toString());

                sendToChannel1(name.getText().toString(), " This assessment will be available on " + start.getText().toString() + ". " +
                        " Alert has been set.");
                startAlarm(date);
                sendToChannel2(name.getText().toString(), " This assessment will be due by " + end.getText().toString() + ". " +
                        "Alert has been set.");
                endAlarm(date1);
                break;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveAssessment(View view) throws ParseException {
        EditText title = findViewById(R.id.assessmentTitleEdit);
        Button start = findViewById(R.id.assessmentStartEdit);
        Button end = findViewById(R.id.assessmentEndEdit);
        EditText type = findViewById(R.id.assessmentTypeEdit);
        EditText info = findViewById(R.id.assessmentInfoEdit);
        String course = getIntent().getStringExtra("course");
        int id = getIntent().getIntExtra("assessmentID", 0);

        Repository repository = new Repository(getApplication());
        boolean isIn = false;
        for(Assessments assessments1 : repository.getAllAssessmentsByTerm(course)){
            if (assessments1.getTitle().toString().equalsIgnoreCase(title.getText().toString())){
                isIn = true;
            }
        }

        if(title.getText().toString().isEmpty() || start.getText().toString().isEmpty() || end.getText()
        .toString().isEmpty() || type.getText().toString().isEmpty() ||  info.getText().toString().isEmpty())
        {
            emptyAlert();
        }
        else {

            if ((id == 0)&&(!isIn))
            {
                Assessments assessments = new Assessments(id, type.getText().toString(), title.getText().toString(),
                        start.getText().toString(), end.getText().toString(), info.getText().toString(), course);
                repository.insertAssessments(assessments);
                alert(title.getText().toString() + " has been successfully saved. Alarms have been set.");
                title.setFocusable(false);
            }
            else
            {
                String getTitle = getIntent().getStringExtra("assessmentTitle");
                Assessments assessments = new Assessments(id, type.getText().toString(), getTitle,
                        start.getText().toString(), end.getText().toString(), info.getText().toString(), course);
                repository.updateAssessments(assessments);
                alert(title.getText().toString() + " has been successfully updated. Alarms have been set.");
                title.setFocusable(false);
            }
        }

    }

    public void emptyAlert(){

        AlertDialog alt = new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("All fields must be filled out in order to save a assessment.")
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

    public void deleteAlert(String message){
    String course = getIntent().getStringExtra("course");

        Intent intent = new Intent(this, ViewAssessments.class);
        intent.putExtra("course", course);
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

    public void deleteAssessment(View view) {
        EditText title = findViewById(R.id.assessmentTitleEdit);
        Repository repository = new Repository(getApplication());
        boolean isIn = false;
        Assessments assessment = null;
        for (Assessments assessments : repository.getAllAssessments()) {
            if (assessments.getTitle().toString().equalsIgnoreCase(title.getText().toString())) {
                isIn = true;
                assessment = assessments;
                break;
            }
        }

        if (isIn){

            repository.deleteAssessments(assessment);
            deleteAlert(title.getText().toString() + " has been successfully deleted");
            }


    }

    public void backToAssessments(View view) {
        String course = getIntent().getStringExtra("course");
        Intent intent = new Intent(this, ViewAssessments.class);
        intent.putExtra("course", course);
        System.out.println(course + "                          back to assessments");
        startActivity(intent);

    }

    public void backToViewCourses(View view) {
        Repository repository = new Repository(getApplication());
        String course = getIntent().getStringExtra("course");
        String getTerm = null;
        for(Courses courses : repository.getAllCourses()){
            if(course.equalsIgnoreCase(courses.getTitle())){
                getTerm = courses.getTermName();

            }
        }
        Intent intent = new Intent(this, ViewCourses.class);
        intent.putExtra("termTitle", getTerm);
        startActivity(intent);

    }
}