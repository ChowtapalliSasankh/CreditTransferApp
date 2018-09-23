package com.example.home.token;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.home.token.R.id.bt3;

public class transferpage extends AppCompatActivity {
    SQLiteDatabase db;
    ListView listView;
    Button b;
    EditText e;
    listviewadapter adapter;
    static int a;
    String[] title;
    String[] size;
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> emails = new ArrayList<String>();
    ArrayList<Model> arrayList = new ArrayList<Model>();
    ArrayList<Integer> amounts = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferpage);
        listView = (ListView)findViewById(R.id.list);
        b = (Button)findViewById(bt3);
        e = (EditText) findViewById(R.id.et1);
        Bundle bundle = getIntent().getExtras();
        final String p = bundle.getString("position");
        final String s2 = bundle.getString("amount");
        final String s1 = bundle.getString("name");
        a = Integer.parseInt(s2);
        db = openOrCreateDatabase("token", Context.MODE_ENABLE_WRITE_AHEAD_LOGGING,null);
        String ct = "create table if not exists customer(name varchar(20),email varchar(100),amount integer)";
        db.execSQL(ct);
        setlistitems("customer");
        title = names.toArray(new String[names.size()]);
        size  = emails.toArray(new String[emails.size()]);
        for(int i=0;i<title.length;i++)
        {
            if(i != Integer.parseInt(p))
            {
                Model model = new Model(title[i],size[i]);
                arrayList.add(model);
            }
        }
        adapter = new listviewadapter(this,arrayList,amounts,1);
        listView.setAdapter(adapter);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int amt = 0;
                if(e.getText().toString().matches(""))
                {
                    amt = 0;
                }
                else
                {
                     amt = Integer.parseInt(e.getText().toString());
                }
                String d = String.valueOf(amt);
                //Toast.makeText(transferpage.this,d,Toast.LENGTH_LONG).show();
                String rec = adapter.getSel();
                if(transferpage.a < amt)
                {
                    Toast.makeText(transferpage.this,"Not Enough Balance To Transfer",Toast.LENGTH_LONG).show();
                }
                else if(amt == 0)
                {
                    Toast.makeText(transferpage.this,"Please Enter The Amount To Be Transferred",Toast.LENGTH_LONG).show();
                }
                else if(rec.matches(""))
                {
                    Toast.makeText(transferpage.this,"Select The Reciepient Of The Amount",Toast.LENGTH_LONG).show();
                }
                else
                {
                    int t = 0;
                    String ds;
                    db.execSQL("update customer set amount = "+(transferpage.a-amt)+" where name ='"+s1+"'");
                    Cursor c = db.rawQuery("Select * From customer", null);
                    while (c.moveToNext())
                    {
                        ds = c.getString(0);
                        if (ds.equals(rec))
                        {
                            t = c.getInt(2);
                            break;
                        }
                    }
                    db.execSQL("update customer set amount = "+(t+amt)+" where name ='"+rec+"'");
                    db.execSQL("create table if not exists trans"+rec+"(fname varchar(20),tname varchar(20),amount varchar(20))");
                    db.execSQL("Insert into trans"+rec+"(fname,tname,amount)values('"+s1+"','"+rec+"','"+String.valueOf(amt)+"')");
                    db.execSQL("create table if not exists trans"+s1+"(fname varchar(20),tname varchar(20),amount varchar(20))");
                    db.execSQL("Insert into trans"+s1+"(fname,tname,amount)values('"+rec+"','"+s1+"','"+String.valueOf(amt)+"')");
                    adapter.setSel("");
                    Intent intent = new Intent(transferpage.this,HomeActivity.class);
                    Toast.makeText(transferpage.this,"Transfer Successful of "+String.valueOf(amt)+" from "+s1+" to "+rec,Toast.LENGTH_LONG).show();
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
    private void setlistitems(String table) {
        try {
            Cursor c = db.rawQuery("Select * From " + table, null);
                while (c.moveToNext()) {
                    names.add(c.getString(0));
                    emails.add(c.getString(1));
                    amounts.add(c.getInt(2));
                }
        } catch (Exception e) {
            Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
