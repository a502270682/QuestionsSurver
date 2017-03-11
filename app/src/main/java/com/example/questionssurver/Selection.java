package com.example.questionssurver;

/**
 * Created by 啦啦啦 on 2017/3/10.
 */

public class Selection {
    //答案id
    private String s_answerId;
    //答案主体
    private String s_answer_content;

    public String getS_answerId()
    {
        return s_answerId;
    }
    public void setS_answerId(String answerId)
    {
        this.s_answerId=answerId;
    }
    public String getS_answer_content()
    {
        return s_answer_content;
    }
    public void setS_answer_content(String answer_content)
    {
        this.s_answer_content=answer_content;
    }
}
