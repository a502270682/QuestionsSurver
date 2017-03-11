package com.example.questionssurver;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.app.AlertDialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.BaseAdapter;
import android.widget.TextView;




public class Display_Eleven extends AppCompatActivity {
    Button btn_ele;
    ListView lv;
    List<Selection> selection = new ArrayList<Selection>();
    Context mContext;
    MyListAdapter adapter;
    List<Integer> listItemID = new ArrayList<Integer>();
    String answer;
   //选项内容
    String[] options={"Project Phoenix","The sense of accomplishment when you pass or upgrade","There are classmates or friends playing the same game",
            "Can make more friends","Player Killing","Role play, such as head of a faction","make money","The online games make me relax and happy"
            ,"others" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__eleven);
        mContext = getApplicationContext();
        btn_ele = (Button)findViewById(R.id.btn_Ele);
        lv = (ListView)findViewById(R.id.lvperson);

        initSelectionData();
        adapter = new MyListAdapter(selection);
        lv.setAdapter(adapter);

        btn_ele.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                listItemID.clear();
                for(int i=0;i<adapter.mChecked.size();i++){
                    if(adapter.mChecked.get(i)){
                        listItemID.add(i);
                    }
                }

                if(listItemID.size()==0){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Display_Eleven.this);
                    builder1.setMessage("没有选中任何记录");
                    builder1.show();
                }else{
                    StringBuilder sb = new StringBuilder();
                    StringBuilder sb_text=new StringBuilder();
                    for(int i=0;i<listItemID.size();i++){
                        sb.append("The choice:"+listItemID.get(i)+" . ");
                        sb_text.append(options[listItemID.get(i)]+",");
                    }
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(Display_Eleven.this);
                    builder2.setMessage(sb.toString());
                    builder2.show();
                    //跳转
                    Intent intent1 = new Intent();
                    intent1.setClass(Display_Eleven.this, Display_Twelve.class);
                    startActivity(intent1);
                    answer=sb_text.toString();
                    write(answer);
                }

            }
        });

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

        File targetFile=new File("sdcard/"+"answer.txt");
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
     //问题内容初始化
    private void initSelectionData(){
        Selection mSelection;

            mSelection = new Selection();
        for(int i=0;i<=8;i++){
            mSelection = new Selection();
            mSelection.setS_answer_content(options[i]);
            selection.add(mSelection);
        }

    }
    //自定义ListView适配器
    class MyListAdapter extends BaseAdapter {
        List<Boolean> mChecked;
        List<Selection> listSelction;
        HashMap<Integer, View> map = new HashMap<Integer, View>();

        public MyListAdapter(List<Selection> list) {
            listSelction= new ArrayList<Selection>();
            listSelction = list;

            mChecked = new ArrayList<Boolean>();
            for (int i = 0; i < list.size(); i++) {
                mChecked.add(false);
            }
        }

        @Override
        public int getCount() {
            return listSelction.size();
        }

        @Override
        public Object getItem(int position) {
            return listSelction.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder holder = null;

            if (map.get(position) == null) {
                Log.e("MainActivity", "position1 = " + position);

                LayoutInflater mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = mInflater.inflate(R.layout.listitem, null);
                holder = new ViewHolder();
                holder.selected = (CheckBox) view.findViewById(R.id.list_select);
                holder.context = (TextView) view.findViewById(R.id.list_name);
                //holder.address = (TextView) view.findViewById(R.id.list_address);
                final int p = position;
                map.put(position, view);
                holder.selected.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        mChecked.set(p, cb.isChecked());
                    }
                });
                view.setTag(holder);
            } else {
                Log.e("MainActivity", "position2 = " + position);
                view = map.get(position);
                holder = (ViewHolder) view.getTag();
            }

            holder.selected.setChecked(mChecked.get(position));
            holder.context.setText(listSelction.get(position).getS_answer_content());
            //holder.address.setText(listSelction.get(position).getAddress());

            return view;
        }

    }

    class ViewHolder {
        CheckBox selected;
        TextView context;
        //TextView address;
    }
}


