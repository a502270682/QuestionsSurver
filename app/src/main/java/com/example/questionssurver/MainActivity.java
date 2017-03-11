package com.example.questionssurver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button btn_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start=(Button)findViewById(R.id.btn_Start);
    }

    public void btn_start_onclick(View view)
    {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, Display_One.class);
        startActivity(intent);
    }
}
