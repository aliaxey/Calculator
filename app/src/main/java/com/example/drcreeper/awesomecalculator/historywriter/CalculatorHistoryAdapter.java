package com.example.drcreeper.awesomecalculator.historywriter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.drcreeper.awesomecalculator.R;
import com.example.drcreeper.awesomecalculator.math.CalculatorHistoryItem;

import java.util.List;

public class CalculatorHistoryAdapter extends ArrayAdapter<CalculatorHistoryItem> {
    public CalculatorHistoryAdapter(Context context, List<CalculatorHistoryItem> list){
        super(context, R.layout.history_item,list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.history_item, parent, false);
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.firstOperand.setText(getItem(position).toString());
        return convertView;
    }
}
