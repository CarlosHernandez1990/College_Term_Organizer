package com.example.mobiledevelopment.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mobiledevelopment.R;

import java.util.List;

import Database.Repository;
import Entities.Assessments;
import Entities.Courses;
import Entities.Term;

public class ViewAssessments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_assessments);
        ActionBar actionBar = getSupportActionBar();
        String course = getIntent().getStringExtra("course");
        actionBar.setIcon(R.drawable.grad);
        actionBar.setTitle("   " + course + " Assessments");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        System.out.println(course + " This is from the assessments page");
        Repository repository = new Repository(getApplication());
        List<Assessments> allAssessments =repository.getAllAssessmentsByTerm(course);
        RecyclerView recyclerView = findViewById(R.id.AssessmentRecyclerView);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdapter.setAssessments(allAssessments);

        if(course == null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
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
        String courseName = getIntent().getStringExtra("course");
        Repository repository = new Repository(getApplication());
        Intent refresh = new Intent(getApplicationContext(), ViewAssessments.class);
        String term = null;
        for(Courses courses: repository.getAllCourses())
        {
            if(courses.getTitle().equalsIgnoreCase(courseName)){
                term = courses.getTermName();
                break;
            }
        }
        switch (item.getItemId())
        {
            case android.R.id.home:
                Intent intent = new Intent(getApplicationContext(), ViewCourses.class);
                intent.putExtra("termTitle", term);
                startActivity(intent);
                break;
            case R.id.refresh:
                refresh.putExtra("course", courseName);
                startActivity(refresh);
                break;
            case R.id.homeButton:
                Intent intents = new Intent(this, MainActivity.class);
                startActivity(intents);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addAssessment(View view) {
        String course = getIntent().getStringExtra("course");
        Intent intent = new Intent(this, AddAssessment.class);
        intent.putExtra("course", course);
        startActivity(intent);
    }
}