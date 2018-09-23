package com.example.home.token;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    SQLiteDatabase db;
    ListView listView;
    listviewadapter adapter;
    String[] title;
    String[] size;
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> emails = new ArrayList<String>();
    ArrayList<Model> arrayList = new ArrayList<Model>();
    ArrayList<Integer> amounts = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = (ListView)findViewById(R.id.mainlist);
        db = openOrCreateDatabase("token", Context.MODE_ENABLE_WRITE_AHEAD_LOGGING,null);
        String ct = "create table if not exists customer(name varchar(20),email varchar(100),amount integer)";
        db.execSQL(ct);
        if(tableExists("customer") == false)
        {String ss = "Insert Into customer(name,email,amount)VALUES('customer1','email1',5000);";
        db.execSQL(ss);
        ss = "Insert Into customer(name,email,amount)VALUES('customer2','email2',5000);";
        db.execSQL(ss);
        ss = "Insert Into customer(name,email,amount)VALUES('customer3','email3',5000);";
        db.execSQL(ss);
        ss = "Insert Into customer(name,email,amount)VALUES('customer4','email4',5000);";
        db.execSQL(ss);
        ss = "Insert Into customer(name,email,amount)VALUES('customer5','email5',5000);";
        db.execSQL(ss);
        ss = "Insert Into customer(name,email,amount)VALUES('customer6','email6',5000);";
        db.execSQL(ss);
        ss = "Insert Into customer(name,email,amount)VALUES('customer7','email7',5000);";
        db.execSQL(ss);
        ss = "Insert Into customer(name,email,amount)VALUES('customer8','email8',5000);";
        db.execSQL(ss);
        ss = "Insert Into customer(name,email,amount)VALUES('customer9','email9',5000);";
        db.execSQL(ss);
        ss = "Insert Into customer(name,email,amount)VALUES('customer10','email10',5000);";
        db.execSQL(ss);
        }
        setlistitems("customer");
        title = names.toArray(new String[names.size()]);
        size  = emails.toArray(new String[emails.size()]);

        for(int i=0;i<title.length;i++)
        {
            Model model = new Model(title[i],size[i]);
            arrayList.add(model);
        }
        adapter = new listviewadapter(this,arrayList,amounts,0);
        listView.setAdapter(adapter);
    }
    private void setlistitems(String table) {
        try {
            Cursor c = db.rawQuery("Select * From " + table, null);
            if (c.getCount() == 0) {
                Toast.makeText(HomeActivity.this, "database is empty", Toast.LENGTH_SHORT).show();
            } else {
                while (c.moveToNext()) {
                    names.add(c.getString(0));
                    emails.add(c.getString(1));
                    amounts.add(c.getInt(2));
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    boolean tableExists(String table)
    {
            Cursor c = db.rawQuery("Select * From " + table, null);
            if (c.getCount() == 0)
            {
                return false;
            }
            else
            {
                return true;
            }
    }
}
