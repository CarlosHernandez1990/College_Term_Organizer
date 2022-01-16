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

import Entities.Term;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder>{

    class TermViewHolder extends RecyclerView.ViewHolder{
        private final TextView termTitle;
        private final TextView termStart;
        private final TextView termEnd;

        public TermViewHolder(View itemView){
            super(itemView);
            termTitle =itemView.findViewById(R.id.termTitleView);
            termStart=itemView.findViewById(R.id.termStartDateView);
            termEnd=itemView.findViewById(R.id.termEndDateView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    final Term current=mTerms.get(position);
                    Intent intent = new Intent(context, AddTerm.class);
                    intent.putExtra("termId", current.getTermID());
                    intent.putExtra("termTitle", current.getTitle());
                    intent.putExtra("termStart", current.getStart());
                    intent.putExtra("termEnd", current.getEnd());
                    context.startActivity(intent);
                }
            });
        }
    }
    private List<Term> mTerms;
    private final Context context;
    private LayoutInflater mInflater;



    public TermAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.terms_list, parent, false);

        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
            if (mTerms != null) {
                final Term current = mTerms.get(position);
                holder.termTitle.setText(current.getTitle());
                holder.termStart.setText(current.getStart());
                holder.termEnd.setText(current.getEnd());
//                Term current = mTerms.get(position);
//                int id = current.getTermID();
 //               holder.termItemView.setText(current.getTitle());
         //       holder.termItemView2.setText(Integer.toString(current.getTermID()));
            } else {
                holder.termTitle.setText("No Term Name");
                holder.termStart.setText("No Term Start");
                holder.termEnd.setText("No term End");
            }
        }
    public void setTerms(List<Term> terms){
        mTerms=terms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mTerms.size();
    }
}
