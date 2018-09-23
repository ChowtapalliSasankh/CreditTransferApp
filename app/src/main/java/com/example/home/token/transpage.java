package com.example.home.token;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class transpage extends AppCompatActivity {
    SQLiteDatabase db;
    ListView listView;
    tlistviewadapter adapter;
    String[] fnms;
    String[] tnms;
    String[] amts;
    ArrayList<String> fnames = new ArrayList<String>();
    ArrayList<String> tnames = new ArrayList<String>();
    ArrayList<String> amounts = new ArrayList<String>();
    ArrayList<model1> arrayList = new ArrayList<model1>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transpage);
        listView = (ListView)findViewById(R.id.translist);
        Bundle bundle = getIntent().getExtras();
        final String s1 = bundle.getString("name");
        db = openOrCreateDatabase("token", Context.MODE_ENABLE_WRITE_AHEAD_LOGGING,null);
        setlistitems(s1);
        fnms = fnames.toArray(new String[fnames.size()]);
        tnms = tnames.toArray(new String[tnames.size()]);
        amts = amounts.toArray(new String[amounts.size()]);

        for(int i=0;i<fnms.length;i++)
        {
            model1 model = new model1(fnms[i],tnms[i],amts[i]);
            arrayList.add(model);
        }
        adapter = new tlistviewadapter(this,arrayList);
        listView.setAdapter(adapter);
    }
    private void setlistitems(String table) {
        try {
            Cursor c = db.rawQuery("Select * From trans" + table, null);
            if (c.getCount() == 0) {
                Toast.makeText(transpage.this, "database is empty", Toast.LENGTH_SHORT).show();
            } else {
                while (c.moveToNext()) {
                    fnames.add(c.getString(0));
                    tnames.add(c.getString(1));
                    amounts.add(c.getString(2));
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
