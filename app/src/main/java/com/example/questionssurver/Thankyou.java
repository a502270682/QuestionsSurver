package com.example.questionssurver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Thankyou extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);
    }
    public void btn_cancel_onclick(View view)
    {
        finish();
    }
}
