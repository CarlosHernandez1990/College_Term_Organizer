package com.example.mobiledevelopment.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiledevelopment.R;

import java.util.List;

import Dao.CourseDao;
import Database.Repository;
import Entities.Courses;
import Entities.Term;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{


    class CourseViewHolder extends RecyclerView.ViewHolder{
        private final TextView courseTitle;
        private final TextView courseStart;
        private final TextView courseEnd;
        private final TextView courseNotes;
        private final TextView courseStatus;
        private final TextView courseDescription;
        private final TextView courseInstructor;
        private final TextView instructorPhone;
        private final TextView instructorEmail;

        public CourseViewHolder(View itemView){
            super(itemView);
            courseTitle =itemView.findViewById(R.id.courseTitleText);
            courseStart=itemView.findViewById(R.id.courseStartText);
            courseEnd=itemView.findViewById(R.id.courseEndText);
            courseNotes=itemView.findViewById(R.id.courseNotesText);
            courseStatus=itemView.findViewById(R.id.courseStatusText);
            courseDescription=itemView.findViewById(R.id.courseDescriptionText);
            courseInstructor=itemView.findViewById(R.id.courseInstructorText);
            instructorPhone=itemView.findViewById(R.id.courseInstructorPhone);
            instructorEmail=itemView.findViewById(R.id.courseInstructorEmail);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    final Courses current=mCourses.get(position);
                    Intent intent = new Intent(context, AddCourse.class);
                    intent.putExtra("courseID", current.getCourseId());
                    intent.putExtra("courseTitle", current.getTitle());
                    intent.putExtra("courseStart", current.getStart());
                    intent.putExtra("courseEnd", current.getEnd());
                    intent.putExtra("courseNotes", current.getNotes());
                    intent.putExtra("courseStatus", current.getStatus());
                    intent.putExtra("courseInfo", current.getDescription());
                    intent.putExtra("courseTerm",current.getTermName());
                    intent.putExtra("courseInstructor",current.getInstructor());
                    intent.putExtra("courseInstructorPhone",current.getInstructorPhone());
                    intent.putExtra("courseInstructorEmail",current.getInstructorEmail());
                    context.startActivity(intent);
                }
            });
        }
    }
    private List<Courses> mCourses;
    private final Context context;
    private LayoutInflater mInflater;



    public CourseAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.course_list, parent, false);


        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {

        if (mCourses != null) {
            final Courses current = mCourses.get(position);
            holder.courseTitle.setText(current.getTitle());
            holder.courseStart.setText(current.getStart());
            holder.courseEnd.setText(current.getEnd());
            holder.courseNotes.setText(current.getNotes());
            holder.courseDescription.setText(current.getDescription());
            holder.courseInstructor.setText(current.getInstructor());
            holder.instructorPhone.setText(current.getInstructorPhone());
            holder.instructorEmail.setText(current.getInstructorEmail());
            holder.courseStatus.setText(current.getStatus());

        } else {

        }
    }
    public void setCourse(List<Courses> courses){
        mCourses=courses;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }
}

