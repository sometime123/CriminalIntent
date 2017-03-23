package cn.edu.niit.criminalintent.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by liaozq on 2017/2/28. 模型层
 */

public class Crime {


    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public  Crime(){

        mId = UUID.randomUUID();      //生成惟一的标识符
        mDate = new Date();          //使用默认的Date构造方法初始化Date变量，值为当前日期（util.date类）
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
