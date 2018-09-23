package com.example.home.token;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by home on 9/22/2018.
 */

public class tlistviewadapter extends BaseAdapter
{
    Context mContext;
    LayoutInflater inflater;
    List<model1> modellist;
    ArrayList<model1> arrayList;
    TextView ftxt,ttxt,atxt;
    int x;
    public tlistviewadapter(Context context, List<model1> modellist)
    {
        mContext = context;
        this.modellist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<model1>();
        this.arrayList.addAll(modellist);
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
        view = inflater.inflate(R.layout.row1,null);
        ftxt = (TextView) view.findViewById(R.id.tfname);
        ttxt = (TextView) view.findViewById(R.id.ttname);
        atxt = (TextView) view.findViewById(R.id.tamt);
        ftxt.setText(modellist.get(position).getFname());
        ttxt.setText(modellist.get(position).getTname());
        atxt.setText(modellist.get(position).getAmt());
        return view;
    }
}
