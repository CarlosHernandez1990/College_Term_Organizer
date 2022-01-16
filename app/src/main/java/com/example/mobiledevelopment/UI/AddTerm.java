package com.example.mobiledevelopment.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

import java.util.Calendar;

import Database.Repository;
import Entities.Courses;
import Entities.Term;

public class AddTerm extends AppCompatActivity {
public int termID = 0;
private DatePickerDialog datePickerDialog;
public DatePickerDialog.OnDateSetListener dateSetListener;
public DatePickerDialog.OnDateSetListener dateSetListenerEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);
        String term_title = getIntent().getStringExtra("termTitle");
        String term_start = getIntent().getStringExtra("termStart");
        String term_end = getIntent().getStringExtra("termEnd");
        EditText termTitle = findViewById(R.id.termTitleEdit);
        Button termStart = findViewById(R.id.termStartEdit);
        Button termEnd = findViewById(R.id.termEndEdit);
        termTitle.setText(term_title);
        termStart.setText(term_start);
        termEnd.setText(term_end);
        termID = getIntent().getIntExtra("termId",0);
        System.out.println(term_title);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.grad);
        actionBar.setTitle("     Modify Term");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);

        datePickerDialog = new DatePickerDialog(this, dateSetListener,year,month,dayOfMonth);
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicked,  int year, int month, int dayOfMonth) {

                String date = month+1 + "/" + dayOfMonth + "/" + year;
                Button termStart = findViewById(R.id.termStartEdit);
                termStart.setText(date);


            }
        };



        dateSetListenerEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicked, int year, int month, int dayOfMonth) {

                String date = month+1 + "/" + dayOfMonth + "/" + year;
                Button termEnd = findViewById(R.id.termEndEdit);
                termEnd.setText(date);
            }
        };

        if(term_title == null)
        {
            termTitle.setFocusable(true);
        }
        else {
            termTitle.setFocusable(false);
        }

    }




    public void openDatePickerStart(View view)
    {
        datePickerDialog.setOnDateSetListener(dateSetListener);
        datePickerDialog.show();
    }

    public void openDatePickerEnd(View view) {
        datePickerDialog.setOnDateSetListener(dateSetListenerEnd);
        datePickerDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        menu.getItem(1).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
EditText term = findViewById(R.id.termTitleEdit);
Button start = findViewById(R.id.termStartEdit);
Button end = findViewById(R.id.termEndEdit);
        switch (item.getItemId())
        {
            case R.id.refresh:
                Intent refresh = new Intent(getApplicationContext(), AddTerm.class);
                startActivity(refresh);
                break;

            case R.id.homeButton:
                Intent intent = new Intent(this, ViewTerms.class);
                startActivity(intent);
                break;
            case R.id.shareButton:
                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
                share.putExtra(Intent.EXTRA_TEXT, term.getText().toString() + " will begin on " +
                start.getText().toString() + " and will end on " + end.getText().toString() + ".");
                share.setType("text/plain");
                startActivity(share);
                break;

        }

        return super.onOptionsItemSelected(item);
    }



    public void saveTerm(View view) {
        boolean newTerm = true;
        EditText termTitle = findViewById(R.id.termTitleEdit);
        Button termStart = findViewById(R.id.termStartEdit);
        Button termEnd = findViewById(R.id.termEndEdit);
        termID = getIntent().getIntExtra("termId",0);
        Repository repository = new Repository(getApplication());
        String entered = termTitle.getText().toString();
            for(Term term : repository.getAllTerms())
            {
                String existing = term.getTitle().toString();

                if(entered.equalsIgnoreCase(existing)){
                    newTerm=false;
                }
            }

            if(termID == 0) {

                if(newTerm) {
                    if(termTitle.getText().toString().isEmpty() || termStart.getText().toString().isEmpty() ||
                    termEnd.getText().toString().isEmpty()) {
                        emptyAlert();
                    }
                    else {

                        Term term = new Term(termID, termStart.getText().toString(), termEnd.getText().toString(), termTitle.getText().toString());
                        repository.insertTerms(term);
                        String newAlert = termTitle.getText().toString() + " has been successfully added.";
                        alert(newAlert);
                        Intent intent = new Intent(this, ViewTerms.class);

                        startActivity(intent);
                    }
                }
            }
            else {

                    Term term = new Term(termID, termStart.getText().toString(), termEnd.getText().toString(), termTitle.getText().toString());
                    repository.updateTerms(term);
                    String updateAlert = termTitle.getText().toString() + " has been successfully updated";
                    alert(updateAlert);

            }
        }

    public void deleteTerm(View view) {

        termID = getIntent().getIntExtra("termId",0);
        EditText termTitle = findViewById(R.id.termTitleEdit);
        String termName = termTitle.getText().toString();
        Repository repository = new Repository(getApplication());
        Boolean hasTerms = false;
        Boolean isIn = false;
        for(Courses c : repository.getAllCourses()){
            if(c.getTermName() != null){
                if(c.getTermName().toString().equalsIgnoreCase(termName)) {
                    hasTerms = true;
                    break;
                }
            }
        }
        Term termFound = null;
        for(Term term : repository.getAllTerms()){
            if(termName.equalsIgnoreCase(term.getTitle())){
                termFound=term;
                isIn = true;
                break;
            }
        }

        if(isIn){
            if (!hasTerms){
                repository.deleteTerms(termFound);
                alert(termName + " has been successfully deleted,");
            }
            else{
                termHasCourses("Please remove all courses in order to delete term.");
            }
        }
        else{
            alert("Please select a valid term to delete.");
        }

        }




    public void viewCourses(View view) {
        Repository repository = new Repository(getApplication());
        EditText termTitle = findViewById(R.id.termTitleEdit);
        Intent intent = new Intent(this, ViewCourses.class);
        String termName = termTitle.getText().toString();
        intent.putExtra("termTitle", termName);
        boolean isIn = false;
        for(Term term:repository.getAllTerms()){
            String termNamed = term.getTitle().toString();
            if(termNamed.equalsIgnoreCase(termTitle.getText().toString()))
            {
                isIn = true;
            break;
            }
        }
        System.out.println(isIn);
                if(isIn)
                {startActivity(intent);}
                else{
                    alert("A valid term must be selected in order to view courses");
                };


    }

    public void alert(String alertMessage){
        Intent intent = new Intent(this, ViewTerms.class);
        AlertDialog alt = new AlertDialog.Builder(this)
                .setTitle("Message")
                .setMessage(alertMessage)
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

    public void emptyAlert(){

        AlertDialog alt = new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("All fields must be filled out in order to save a term.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                })
                .create();
        alt.show();
    }

    public void termHasCourses(String alertMessage){
        AlertDialog alt = new AlertDialog.Builder(this)
                .setTitle("Message")
                .setMessage(alertMessage)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();


                    }
                })
                .create();
        alt.show();
    }



}