package com.example.mobiledevelopment.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.mobiledevelopment.R;

import java.util.List;

import Database.Repository;
import Entities.Courses;
import Entities.Term;

public class ViewCourses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_courses);
        String termName = getIntent().getStringExtra("termTitle");
        Repository repository = new Repository(getApplication());
        List<Courses> allCourses =repository.getAllCoursesByTerm(termName);
        RecyclerView recyclerView = findViewById(R.id.courseRecyclerView);
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter.setCourse(allCourses);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("     " + termName + " Courses");
        actionBar.setIcon(R.drawable.grad);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView underlineNote = findViewById(R.id.underlineNote);
        if (termName == null){
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
        Intent refresh = new Intent(getApplicationContext(), ViewCourses.class);
        switch (item.getItemId())
        {
            case android.R.id.home:
                String termName = getIntent().getStringExtra("termTitle");
                break;
            case R.id.refresh:
                termName = getIntent().getStringExtra("termTitle");
                refresh.putExtra("termTitle", termName);
                startActivity(refresh);
                break;

            case R.id.homeButton:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addCourse(View view) {
        Intent intent = new Intent(this,AddCourse.class);
        String termName = getIntent().getStringExtra("termTitle");
        intent.putExtra("courseTerm",termName);
        startActivity(intent);
    }
}