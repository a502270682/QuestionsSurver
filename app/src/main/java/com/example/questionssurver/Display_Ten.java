package com.example.questionssurver;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Display_Ten extends AppCompatActivity {

    RadioGroup Rdgroup;
    RadioButton SelectButton;
    int flag=0;
    String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__ten);
        Rdgroup=(RadioGroup) findViewById(R.id.RdGroup);
        Rdgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //checkId就是当前选中的RadioButton
                switch (checkedId) {
                    case R.id.Rd_Ten_B:
                        SelectButton = (RadioButton) findViewById(R.id.Rd_Ten_B);
                        answer = (String) SelectButton.getText();
                        break;
                    case R.id.Rd_Ten_A:
                        SelectButton = (RadioButton) findViewById(R.id.Rd_Ten_A);
                        answer = (String) SelectButton.getText();
                        break;
                    case R.id.Rd_Ten_C:
                        SelectButton = (RadioButton) findViewById(R.id.Rd_Ten_C);
                        answer = (String) SelectButton.getText();
                        break;
                    case R.id.Rd_Ten_D:
                        SelectButton = (RadioButton) findViewById(R.id.Rd_Ten_D);
                        answer = (String) SelectButton.getText();
                        break;
                }
                flag = 1;
            }
        });

    }


    public void btn_one_onclick(View view)
    {
        if(flag==1) {
            write(answer);
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(answer);
            builder.show();
            Intent intent1 = new Intent();
            intent1.setClass(Display_Ten.this, Display_Eleven.class);
            startActivity(intent1);
        }
        else {
            // 实例化AlertDailog.Builder对象
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please choose one of these options.");
            builder.show();
        }
    }
    //读取操作
    private String read() {
        try {

            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {

                File sdCardDir = Environment.getExternalStorageDirectory();

                FileInputStream fis = new FileInputStream(sdCardDir
                        .getCanonicalPath()
                        +"/answer.txt");

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        fis));
                StringBuilder sb = new StringBuilder("");
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //写入操作
    private void write(String content) {
        try {
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                File sdCardDir = Environment.getExternalStorageDirectory();
                File targetFile = new File(sdCardDir.getCanonicalPath()
                        + "/answer.txt");
                // File targetFile=new File("sdcard/"+"answer.txt");
                if(!targetFile.exists())
                {
                    try {
                        targetFile.createNewFile();
                    }catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }
                try{
                    BufferedWriter buf=new BufferedWriter(new FileWriter(targetFile,true));
                    buf.append(content);
                    buf.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
