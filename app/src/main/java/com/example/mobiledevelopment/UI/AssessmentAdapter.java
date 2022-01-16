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

import Entities.Assessments;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder>{


    class AssessmentViewHolder extends RecyclerView.ViewHolder{
        private final TextView assessmentTitle;
        private final TextView assessmentStart;
        private final TextView assessmentEnd;
        private final TextView assessmentType;
        private final TextView assessmentInfo;



        public AssessmentViewHolder(View itemView){
            super(itemView);
            assessmentTitle=itemView.findViewById(R.id.assessmentTitleText);
            assessmentStart=itemView.findViewById(R.id.assessmentStartText);
            assessmentEnd=itemView.findViewById(R.id.assessmentEndText);
            assessmentInfo=itemView.findViewById(R.id.assessmentInfoText);
            assessmentType=itemView.findViewById(R.id.assessmentTypeText);





            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    final Assessments current=mAssessments.get(position);
                    Intent intent = new Intent(context, AddAssessment.class);
                    intent.putExtra("assessmentID", current.getId());
                    intent.putExtra("assessmentTitle", current.getTitle());
                    intent.putExtra("assessmentStart", current.getStart());
                    intent.putExtra("assessmentEnd", current.getEnd());
                    intent.putExtra("assessmentType", current.getType());
                    intent.putExtra("assessmentInfo", current.getDescription());
                    intent.putExtra("course",current.getCourse());

                    context.startActivity(intent);
                }
            });
        }
    }
    private List<Assessments> mAssessments;
    private final Context context;
    private LayoutInflater mInflater;



    public AssessmentAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.assessment_list, parent, false);


        return new AssessmentAdapter.AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {

        if (mAssessments != null) {
            final Assessments current = mAssessments.get(position);
            holder.assessmentTitle.setText(current.getTitle());
            holder.assessmentStart.setText(current.getStart());
            holder.assessmentEnd.setText(current.getEnd());
            holder.assessmentInfo.setText(current.getDescription());
            holder.assessmentType.setText(current.getType());

        } else {

        }
    }
    public void setAssessments(List<Assessments> assessments){
        mAssessments=assessments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mAssessments.size();
    }
}
