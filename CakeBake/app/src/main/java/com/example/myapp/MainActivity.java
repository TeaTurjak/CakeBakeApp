package com.example.myapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public Button button_enter;
    public Button button_exit_app_first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable= new ColorDrawable(Color.parseColor("#CE88A5"));
        actionBar.setBackgroundDrawable(colorDrawable);
        setContentView(R.layout.activity_main);

        button_enter=(Button) findViewById(R.id.btn_first);

        button_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, TimerActivity.class);
                startActivity(intent);
            }
        });

        button_exit_app_first = (Button) findViewById(R.id.btn_first_exit);

        button_exit_app_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

    }
}