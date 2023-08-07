package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result_textview, solution_textview;
    MaterialButton button_c, button_open_bracket, button_close_bracket;
    MaterialButton button_divide, button_mul, button_add, button_sub, button_equals;
    MaterialButton button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9;
    MaterialButton button_ac, button_decimal;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result_textview = findViewById(R.id.result_textview);
        solution_textview = findViewById(R.id.solution_textview);

        assignId(button_c, R.id.button_c);
        assignId(button_open_bracket, R.id.button_open_bracket);
        assignId(button_close_bracket, R.id.button_close_bracket);
        assignId(button_divide, R.id.button_divide);
        assignId(button_7, R.id.button_7);
        assignId(button_9, R.id.button_9);
        assignId(button_8, R.id.button_8);
        assignId(button_6, R.id.button_6);
        assignId(button_5, R.id.button_5);
        assignId(button_4, R.id.button_4);
        assignId(button_3, R.id.button_3);
        assignId(button_2, R.id.button_2);
        assignId(button_1, R.id.button_1);
        assignId(button_0, R.id.button_0);
        assignId(button_ac, R.id.button_ac);
        assignId(button_mul, R.id.button_multiply);
        assignId(button_add, R.id.button_addition);
        assignId(button_sub, R.id.button_minus);
        assignId(button_equals, R.id.button_equals_to);
        assignId(button_decimal, R.id.button_decimal);

    }

    void assignId(MaterialButton btn, int id ){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution_textview.getText().toString();

        if(buttonText.equals("AC")){
            solution_textview.setText("");
            result_textview.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solution_textview.setText(result_textview.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length()-1);
        } else{
            dataToCalculate = dataToCalculate + buttonText;
        }

        solution_textview.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            result_textview.setText(finalResult);
        }
    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e){
            return "Err";
        }
    }

}