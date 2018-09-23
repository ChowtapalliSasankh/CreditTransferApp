package com.example.home.token;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;

/**
 * Created by home on 9/20/2018.
 */
public class listviewadapter extends BaseAdapter
{
    Context mContext;
    LayoutInflater inflater;
    List<Model> modellist;
    ArrayList<Model> arrayList;
    String sel;
    public String getSel() {
        return sel;
    }

    public void setSel(String sel) {
        this.sel = sel;
    }

    ArrayList<Integer> amount;
    TextView mname,memail;
    int x;
    public listviewadapter(Context context, List<Model> modellist,ArrayList<Integer> amount,int code) {
        mContext = context;
        this.modellist = modellist;
        this.amount = amount;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<Model>();
        this.arrayList.addAll(modellist);
        x = code;
    }
    @Override
    public int getCount() {
        return modellist.size();
    }

    @Override
    public Object getItem(int position) {
        return modellist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View view, ViewGroup parent)
    {
        view = inflater.inflate(R.layout.row,null);
        mname = (TextView) view.findViewById(R.id.cname);
        memail = (TextView) view.findViewById(R.id.cemail);
        mname.setText(modellist.get(position).getName());
        memail.setText(modellist.get(position).getEmail());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(x == 0)
                {
                    try {
                        Intent intent = new Intent(mContext, amountpage.class);
                        String temp = modellist.get(position).getName();
                        String temp1 = String.valueOf(amount.get(position));
                        String pos = String.valueOf(position);
                        intent.putExtra("amount", temp1);
                        intent.putExtra("name", temp);
                        intent.putExtra("position", pos);
                        mContext.startActivity(intent);
                        ((Activity)mContext).finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if(x == 1)
                {
                    setSel(modellist.get(position).getName());
                    Toast.makeText(mContext,modellist.get(position).getName()+" is selected",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
