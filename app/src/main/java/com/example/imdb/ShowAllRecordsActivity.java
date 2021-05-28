package com.example.imdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class ShowAllRecordsActivity extends AppCompatActivity {



    OpenDatabase sqh;
    SQLiteDatabase sqdb;



    ListView ShowAllRecordsList;
    ArrayList<String> list;
    ArrayAdapter adapter;


    Button BackShowAllRecordsButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_records);

        InitDataBase();

        setupControls();

    }



    public void setupControls(){

        ShowAllRecordsList = findViewById(R.id.ShowAllRecordsList);

        list = sqh.DisplayRecords(sqdb);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        ShowAllRecordsList.setAdapter(adapter);

        BackShowAllRecordsButton = findViewById(R.id.BackShowAllRecordsButton);
        BackShowAllRecordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public ArrayList<String> DisplayRecords()
    {
        ArrayList<String> arrayList = new ArrayList<String>();
        String result = "";


        Cursor c = sqdb.rawQuery("SELECT * FROM songtable", null);
        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    result = "";
                    String id = c.getString(0);
                    result += id + ", ";
                    String title = c.getString(1);
                    result += title + ", ";
                    String year = c.getString(2);
                    result += year + ", ";
                    String role = c.getString(3);
                    result += role + ", ";
                    String director = c.getString(4);
                    result += director + ", ";

                    arrayList.add(result);

                    Log.w("LILY COLLINS DATABASE", "ID = " + id + " Songtitle = " +
                            title);
                } while (c.moveToNext());
            }
        }
        c.close();

        return arrayList;
    } // public void DisplayRecords()


    public void InitDataBase()
    {
        // Init the SQLite Helper Class
        sqh = new OpenDatabase(this);
        // RETRIEVE A READABLE AND WRITEABLE DATABASE
        sqdb = sqh.getWritableDatabase();
    } // public void InitDataBase()




}
