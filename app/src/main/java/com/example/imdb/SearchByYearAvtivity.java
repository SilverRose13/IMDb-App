package com.example.imdb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchByYearAvtivity extends AppCompatActivity {



    Button SearchByYearActivityButton;
    Button BackSearchByYearActivityButton;

    EditText SearchByYearEditText;

    OpenDatabase sqh;
    SQLiteDatabase sqdb;

    String recordId;
    String searchYear;


    ListView ListSearchByYear;
    ArrayList<String> list;
    ArrayAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_record);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            recordId = extras.getString("id");
            Log.w("PUTEXTRA","value = " + recordId);
        }

        InitDataBase();

        SetupControls();

    }



    public void InitDataBase()
    {
        // Init the SQLite Helper Class
        sqh = new OpenDatabase(this);

        // RETRIEVE A READABLE AND WRITEABLE DATABASE
        sqdb = sqh.getWritableDatabase();

    } // public void InitDataBase()

    public void SetupControls(){

        SearchByYearEditText = findViewById(R.id.SearchByYearEditText);
        ListSearchByYear = findViewById(R.id.ListSearchByYear);

        SearchByYearActivityButton = findViewById(R.id.SearchByYearActivityButton);
        SearchByYearActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchYear = SearchByYearEditText.getText().toString();

                /*list = new ArrayList<String>();
                list = sqh.SearchByYear(sqdb, searchYear);
                adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
                ListSearchByYear.setAdapter(adapter);*/
            }


        });




        BackSearchByYearActivityButton = findViewById(R.id.BackSearchByYearActivityButton);
        BackSearchByYearActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
