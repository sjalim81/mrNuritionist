package com.example.mrnuritionist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public
class HomeActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private static ArrayList<Weight> _weights;

    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }

    public static Weight get_recent_weight(){
        if (_weights.size() > 0){ return _weights.get(_weights.size()-1); }
        else {return null;}
    }

}
