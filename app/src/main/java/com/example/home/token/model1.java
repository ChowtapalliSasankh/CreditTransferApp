package com.example.home.token;

/**
 * Created by home on 9/22/2018.
 */

public class model1
{
    String tname;
    String fname;
    String amt;
    public model1(String tname,String fname,String amt)
    {
        this.tname = tname;
        this.fname = fname;
        this.amt = amt;
    }

    public String getTname() {
        return tname;
    }

    public String getFname() {
        return fname;
    }

    public String getAmt() {
        return amt;
    }
}
