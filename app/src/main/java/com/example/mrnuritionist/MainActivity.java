package com.example.mrnuritionist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;

public
class MainActivity extends AppCompatActivity {
    ImageButton bmiImageButton, bodyCalculatorImageButton, browserImageButton, exerciseImageButton, foodRecipeImageButton, homeImageButton;
    ImageButton stopWatchImageButton, reminderImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bmiImageButton = findViewById(R.id.bmi_imageButton);
        bodyCalculatorImageButton = findViewById(R.id.body_calculator_imageButton);
        browserImageButton = findViewById(R.id.browser_imageButton);
        exerciseImageButton = findViewById(R.id.exercise_imageButton);
        foodRecipeImageButton = findViewById(R.id.food_recipe_imageButton);
        homeImageButton = findViewById(R.id.home_imageButton);
        stopWatchImageButton = findViewById(R.id.stop_watch_imageButton);
        reminderImageButton = findViewById(R.id.reminder_imageButton);



        bmiImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BmiActivity.class);
                startActivity(intent);
            }
        });
        bodyCalculatorImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BodyCalorieCalculatorActivity.class);
                startActivity(intent);
            }
        });
        browserImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BrowserActivity.class);
                startActivity(intent);
            }
        });
        exerciseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ExerciseActivity.class);
                startActivity(intent);
            }
        });
        foodRecipeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FoodRecipeActivity.class);
                startActivity(intent);
            }
        });
        homeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
        stopWatchImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,StopWatchActivity.class);
                startActivity(intent);
            }
        });
        reminderImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReminderActivity.class);
                startActivity(intent);
            }
        });


    }

}
