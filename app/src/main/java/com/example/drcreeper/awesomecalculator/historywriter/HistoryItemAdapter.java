package com.example.drcreeper.awesomecalculator.historywriter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.drcreeper.awesomecalculator.R;
import com.example.drcreeper.awesomecalculator.math.CalculatorHistoryItem;

import java.util.ArrayList;
import java.util.List;

public class HistoryItemAdapter extends ArrayAdapter<CalculatorHistoryItem> {

    private List<Integer> pos = new ArrayList<>();

    public List<Integer> getPos() {
        return pos;
    }

    public void setPos(List<Integer> pos) {
        this.pos = pos;
    }

    private int tmpId;

    public HistoryItemAdapter(Context context,List<CalculatorHistoryItem> list){
        super(context, R.layout.history_item,list);
        tmpId = R.layout.history_item;
    }

    public HistoryItemAdapter(Context context,int id,List<CalculatorHistoryItem> list){
        super(context, R.layout.history_item,list);
        tmpId = id;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        ViewHolder viewHolder;
        CalculatorHistoryItem history = getItem(position);
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(tmpId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.setFirstOperand((TextView)convertView.findViewById(R.id.first_operand_text));
            viewHolder.setSecondOperand((TextView)convertView.findViewById(R.id.second_operand_text));
            viewHolder.setOperator((TextView)convertView.findViewById(R.id.operator_text));
            viewHolder.setResult((TextView)convertView.findViewById(R.id.result_text));
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.getFirstOperand().setText(Double.toString(history.getFirstOperand()));
        viewHolder.getSecondOperand().setText(Double.toString(history.getSecondOperand()));
        viewHolder.getOperator().setText(history.getOperatorSymbol());
        viewHolder.getResult().setText(Double.toString(history.getResult()));

        if(tmpId == R.layout.history_item_selectable){ //maybe not best solution
            CheckBox c = (CheckBox)convertView.findViewById(R.id.checkBox);
            Integer i = new Integer(position);
            if (pos.contains(i)){
                c.setChecked(true);
            }
            convertView.setOnClickListener((d)->{
                if(pos.contains(i)){
                    pos.remove(i);
                    c.setChecked(false);
                }else {
                    pos.add(i);
                    c.setChecked(true);
                }

            });
        }
        return convertView;
    }
}
