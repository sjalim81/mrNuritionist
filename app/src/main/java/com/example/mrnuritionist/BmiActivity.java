package com.example.mrnuritionist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BmiActivity extends AppCompatActivity {

    private EditText height, weight, bmi,comment;
    private Button button;
    String number1,number2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        height = findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        bmi = (EditText) findViewById(R.id.result);

        comment=(EditText) findViewById(R.id.comment);

        button = (Button) findViewById(R.id.btn);




        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                number1 = height.getText().toString();
                number2 = weight.getText().toString();

                double num1 = Double.parseDouble(number1);
                double num2 = Double.parseDouble(number2);





                double result = num2 / (num1 * num1);
                bmi.setText("BMI:" + result);


                //double result=num2/((num1)*(num1));
                if (result < 18.5) {
                    comment.setText("Underweight");
                }
                if (result >= 18.5) {
                    comment.setText("Normal");
                }
                if (result >= 25.0) {
                    comment.setText("Overweight");
                }
                if (result >= 30.0) {
                    comment.setText("Obese");
                }

            }
        });
    }

}