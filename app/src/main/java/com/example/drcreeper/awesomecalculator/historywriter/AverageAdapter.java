package com.example.drcreeper.awesomecalculator.historywriter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.drcreeper.awesomecalculator.R;
import com.example.drcreeper.awesomecalculator.activities.AverageActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AverageAdapter extends RecyclerView.Adapter<AverageAdapter.AverageHolder> {

    List<Double> list;
    public void setList(List list) {
        this.list = list;
    }
    public AverageAdapter(List<Double> list){
        super();
        this.list = list;
    }
    @NonNull
    @Override
    public AverageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.avg_list,parent,false);
        return new AverageHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AverageHolder holder, int position) {
        holder.text.setText(AverageActivity.toFormat(list.get(position)));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    class AverageHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.num)
        TextView text;
        public AverageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
