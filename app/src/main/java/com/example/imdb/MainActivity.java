package com.example.imdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String DATABASE_PATH =
            "/data/data/com.example.imdb/databases/";
    private static final String DATABASE_PATH2 =
            "/data/data/com.example.imdb/databases"; // no at end of path !!!
    private static final String DATABASE_NAME = "SQLite_Lily_Collins.db";
    private static final String LOG_TAG = "SQLite_Lily_Collins.db";
    Context ctx;

    OpenDatabase sqh;
    SQLiteDatabase sqdb;


    Button ShowAllRecordsButton;
    Button SearchByTitleButtton;
    Button SearchByYearButton;
    Button UpdateRecordButton;
    Button HelpButton;
    Button AboutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpDatabase();

        InitDataBase();

        setupControls();

    }

    public void setupControls(){

        ShowAllRecordsButton = findViewById(R.id.ShowAllRecordsButton);
        ShowAllRecordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ShowAllRecordsActivity.class);
                startActivity(intent);
            }
        });

        SearchByYearButton = findViewById(R.id.SearchByYearButton);
        SearchByYearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SearchByYearAvtivity.class);
                startActivity(intent);
            }
        });

        UpdateRecordButton = findViewById(R.id.UpdateRecordButton);
        UpdateRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), UpdateRecordActivity.class);
                startActivity(intent);
            }
        });

        HelpButton = findViewById(R.id.HelpButton);
        HelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), HelpActivity.class);
                startActivity(intent);
            }
        });

        AboutButton = findViewById(R.id.AboutButton);
        AboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AboutActivity.class);
                startActivity(intent);
            }
        });


    }

    public void setUpDatabase()
    {
        ctx = this.getBaseContext();
        try
        {
            CopyDataBaseFromAsset();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    } // public void setUpDatabase()


    public void CopyDataBaseFromAsset() throws IOException
    {
        // Get the sqlite databse in the assets folder
        InputStream in = ctx.getAssets().open(DATABASE_NAME);
        Log.w( LOG_TAG , "Starting copying...");
        String outputFileName = DATABASE_PATH + DATABASE_NAME;
        File databaseFolder = new File( DATABASE_PATH2 );

        // databases folder exists ? No - Create it and copy !!!
        if ( !databaseFolder.exists() )
        {
            databaseFolder.mkdir();
            OutputStream out = new
                    FileOutputStream(outputFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ( (length = in.read(buffer)) > 0 )
            {
                out.write(buffer,0,length);
            } // while ( (length = in.read(buffer)) > 0 )
            out.flush();
            out.close();
            in.close();
            Log.w(LOG_TAG, "Completed.");

        } // if ( !databaseFolder.exists() )

    } // public void CopyDataBaseFromAsset() throws IOException

    public void InitDataBase()
    {
        // Init the SQLite Helper Class
        sqh = new OpenDatabase(this);
        // RETRIEVE A READABLE AND WRITEABLE DATABASE
        sqdb = sqh.getWritableDatabase();
    } // public void InitDataBase()

    @Override
    protected void onStart()
    {
        super.onStart();
        // The activity is about to become visible.

        //  DisplayMessage("onStart");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        // The activity has become visible (it is now "resumed").

        //  DisplayMessage("onResume");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        // Another activity is taking focus (pause coming "paused").

        //  DisplayMessage("onPause");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")

        // DisplayMessage("onStop");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        // The activity is about to be destroyed.

        // DisplayMessage("onDestroy");
    }


}
