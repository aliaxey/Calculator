package com.example.drcreeper.awesomecalculator.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.drcreeper.awesomecalculator.R;
import com.example.drcreeper.awesomecalculator.historywriter.AverageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AverageActivity extends AppCompatActivity {
    @BindView(R.id.number)
    EditText number;
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.sum)
    TextView sumView;
    @BindView(R.id.quantity)
    TextView quantityView;
    @BindView(R.id.avg)
    TextView averageView;
    @BindView(R.id.list)
    RecyclerView recyclerView;

    AverageAdapter adapter;
    List<Double> list = new ArrayList<>();
    double sum;
    int quantity;
    double avg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_average);
        ButterKnife.bind(this);
        adapter = new AverageAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        update();
    }
    @OnClick(R.id.add)
    void addNumber(){
        if(number.getText().toString().isEmpty()){
            return;
        }
        double num = Double.parseDouble(number.getText().toString());
        list.add(num);
        number.setText("");
        adapter.setList(list);
        adapter.notifyDataSetChanged();
        update();
    }
    @OnClick(R.id.clear)
    void clear(){
        list.clear();
        update();
    }
    void update(){
        adapter.setList(list);
        adapter.notifyDataSetChanged();
        if(list.size() == 0){
            sum = 0;
            quantity = 0;
            avg = 0;
        }else {
            sum = 0;
            for(double num : list){
                sum += num;
            }
            quantity = list.size();
            avg = sum / quantity;
        }
        sumView.setText(toFormat(sum));
        quantityView.setText(toFormat(quantity));
        averageView.setText(toFormat(avg));
    }
    public static String toFormat(double d){
        String result;
        if(d == (long)d){
            result = String.format("%d",(long)d);
        }else {
            result = String.format("%.3f",d);
        }
        return result;
    }
}
