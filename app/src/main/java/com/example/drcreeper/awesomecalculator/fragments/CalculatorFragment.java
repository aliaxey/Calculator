package com.example.drcreeper.awesomecalculator.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drcreeper.awesomecalculator.R;
import com.example.drcreeper.awesomecalculator.activities.AverageActivity;
import com.example.drcreeper.awesomecalculator.activities.HistoryActivity;
import com.example.drcreeper.awesomecalculator.asynktasks.DeleteHistoryListAsyncTask;
import com.example.drcreeper.awesomecalculator.asynktasks.SaveOperationToHistoryAsyncTask;
import com.example.drcreeper.awesomecalculator.math.Calculator;
import com.example.drcreeper.awesomecalculator.math.CalculatorHistoryItem;
import com.example.drcreeper.awesomecalculator.math.Operator;
import com.example.drcreeper.awesomecalculator.propertywork.CalculatorPreferencesContract;
import com.example.drcreeper.awesomecalculator.propertywork.CalculatorWriter;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalculatorFragment extends Fragment {

    @BindView(R.id.main_textView)
    TextView mainTextView;
    @BindViews({
            R.id.num_0_button,
            R.id.num_1_button,
            R.id.num_2_button,
            R.id.num_3_button,
            R.id.num_4_button,
            R.id.num_5_button,
            R.id.num_6_button,
            R.id.num_7_button,
            R.id.num_8_button,
            R.id.num_9_button
    })
    Button[] numbersButtons;

    private Calculator calculator;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.calculator_fragment, container, false);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this, view);
        calculator = new Calculator();
        CalculatorWriter.restoreState(calculator, getActivity().getSharedPreferences(CalculatorPreferencesContract.FILE_NAME, Context.MODE_PRIVATE));
        refresh();
        for (final Button button : numbersButtons) {
            button.setOnClickListener((v) -> {
                calculator.numPress(button.getText().charAt(0));
                refresh();
            });
        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.open_history:
                Intent intent = new Intent(getActivity(),HistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.clear_history:
                DeleteHistoryListAsyncTask deleter = new DeleteHistoryListAsyncTask(getActivity());
                deleter.setOnCompleteListener(()->{
                    Toast.makeText(getContext(),R.string.delete_success,Toast.LENGTH_LONG).show();
                });
                DeleteDialogFragment dialog = new DeleteDialogFragment();
                dialog.setOnConfirm((arg0,arg1)->{
                    deleter.execute();
                    arg0.cancel();
                });
                dialog.show(getFragmentManager(),"delete_all");
                break;
            case R.id.average:
                startActivity(new Intent(getActivity(), AverageActivity.class));
        }
        return true;
    }

    @OnClick(R.id.num_dot_button)
    public void dotPress(){
        calculator.numPress('.');
        refresh();
    }

    @OnClick(R.id.add_button)
    public void onAddPress() {
        calculator.operatorPress(Operator.ADD);
        refresh();
    }

    @OnClick(R.id.sub_button)
    public void onSubButton() {
        calculator.operatorPress(Operator.SUB);
        refresh();
    }

    @OnClick(R.id.mul_button)
    public void onMulButton() {
        calculator.operatorPress(Operator.MUL);
        refresh();
    }

    @OnClick(R.id.div_button)
    public void onDivButton() {
        calculator.operatorPress(Operator.DIV);
        refresh();
    }

    @OnClick(R.id.slove_button)
    public void onSolveClick() {
        calculator.solvePress();
        refresh();
        SaveOperationToHistoryAsyncTask writer = new SaveOperationToHistoryAsyncTask();
        writer.setContext(getContext());
        writer.execute(new CalculatorHistoryItem[]{calculator.getHistory()});
    }

    @OnClick(R.id.clear_button)
    public void onClearClick() {
        calculator.clear();
        refresh();
    }

    @OnClick(R.id.erace_button)
    public void onEraseClick() {
        calculator.erasePress();
        refresh();
    }

    public void refresh() {
        mainTextView.setText(calculator.getCurrentText());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(CalculatorPreferencesContract.FILE_NAME,0);
        CalculatorWriter.saveState(calculator,sharedPreferences);
    }

}