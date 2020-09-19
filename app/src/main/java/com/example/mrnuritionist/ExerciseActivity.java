package com.example.mrnuritionist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExerciseActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        button2=(Button)findViewById(R.id.button2Id);
        button2.setOnClickListener(this);

        button3=(Button)findViewById(R.id.button3Id);
        button3.setOnClickListener(this);

        button4=(Button)findViewById(R.id.button4Id);
        button4.setOnClickListener(this);

        button5=(Button)findViewById(R.id.button5Id);
        button5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.button3Id)
        {
            Intent button2Intent = new Intent(ExerciseActivity.this, Activity3.class);

            startActivity(button2Intent);
        }
        else if(v.getId()==R.id.button4Id)
        {
            Intent button3Intent = new Intent(ExerciseActivity.this, Activity4.class);

            startActivity(button3Intent);
        }
        else if(v.getId()==R.id.button5Id)
        {
            Intent button4Intent = new Intent(ExerciseActivity.this, Activity5.class);

            startActivity(button4Intent);
        }






    }
}