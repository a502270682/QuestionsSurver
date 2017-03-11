package com.example.questionssurver;

/**
 * Created by 啦啦啦 on 2017/3/10.
 */

public class Question {
    private String q_num;
    //单选多选标识
    private String q_type;
    //题目
    private String q_content;

    public String Getq_num()
    {
        return q_num;
    }
    public String getType() {
        return q_type;
    }
    public void setType(String type) {
        this.q_type = type;
    }
    public String getContent() {
        return q_content;
    }
    public void setContent(String content) {
        this.q_content = content;
    }
}
