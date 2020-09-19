package com.example.mrnuritionist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class BodyCalorieCalculatorActivity extends AppCompatActivity {
    private Spinner activityLevelSpinner;
    private TextView tvDailyIntake;
    private TextView tvDays;
    private EditText etDeficit;
    private EditText etGoal;
    private double weight;
    private double goal;
    private int deficit;
    private int result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_calorie_calulator);


    }


    public void SetDailyIntake(int spinnerPosition){
        String calories;
        Weight lastWeight = HomeActivity.get_recent_weight();

        if (lastWeight == null){
            tvDailyIntake.setText(getText(R.string.stats_error));
            return;
        }

        weight = lastWeight.get_weight();
        int height = PreferenceManager.get_height();
        int age = PreferenceManager.get_age();
        String gender = PreferenceManager.get_gender();

        calories = String.valueOf(Calc.MaintenanceCalories(weight, height, age, gender, spinnerPosition));
        String calorieText = getText(R.string.maintenence_calories).toString();
        tvDailyIntake.setText(String.format(Locale.US, calorieText, calories));
    }

    public void CalculateDays(View v){
        String strGoal = etGoal.getText().toString();
        String strDeficit = etDeficit.getText().toString();

        if (strGoal.equals("") || strDeficit.equals("")){
            Toast.makeText(this,R.string.body_calc_null, Toast.LENGTH_LONG).show();
            return;
        }

        goal = Double.parseDouble(etGoal.getText().toString());
        deficit = Integer.parseInt(etDeficit.getText().toString());
        result = Calc.DaysToReachGoalWeight(deficit,weight,goal);
        tvDays.setText(String.format(Locale.US,getText(R.string.days_result).toString(),result));
    }

}