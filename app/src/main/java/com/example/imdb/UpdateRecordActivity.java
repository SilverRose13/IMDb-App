package com.example.imdb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateRecordActivity extends AppCompatActivity {

    EditText IDUpdateEditText;
    EditText TitleUpdateEditText;
    EditText YearUpdateEditText;
    EditText RoleUpdateEditText;
    EditText DirectorUpdateEditText;

    Button UpdateRecordActivityButton;
    Button BackUpdateActivityButton;

    OpenDatabase sqh;
    SQLiteDatabase sqdb;

    String recordId;




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

        PopulateEditTexts();
    }



    public void InitDataBase()
    {
        // Init the SQLite Helper Class
        sqh = new OpenDatabase(this);

        // RETRIEVE A READABLE AND WRITEABLE DATABASE
        sqdb = sqh.getWritableDatabase();

    } // public void InitDataBase()



    public void SetupControls()
    {
        IDUpdateEditText = findViewById(R.id.IDUpdateEditText);
        TitleUpdateEditText = findViewById(R.id.TitleUpdateEditText);
        YearUpdateEditText = findViewById(R.id.YearUpdateEditText);
        RoleUpdateEditText = findViewById(R.id.RoleUpdateEditText);
        DirectorUpdateEditText = findViewById(R.id.DirectorUpdateEditText);

        UpdateRecordActivityButton = findViewById(R.id.UpdateRecordActivityButton);
        UpdateRecordActivityButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sqh.UpdateRecord(sqdb, IDUpdateEditText.getText().toString(),
                        TitleUpdateEditText.getText().toString(),
                        YearUpdateEditText.getText().toString(),
                        RoleUpdateEditText.getText().toString(),
                        DirectorUpdateEditText.getText().toString());

                finish();
            }
        });

        BackUpdateActivityButton = findViewById(R.id.BackUpdateActivityButton);
        BackUpdateActivityButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

    }   //   public void SetupControls()







    public void PopulateEditTexts()
    {
        String match = sqh.ReturnRecord(sqdb, recordId);

        String subStrings[]= match.split(",");

        IDUpdateEditText.setText( subStrings[0] );
        TitleUpdateEditText.setText( subStrings[1] );
        YearUpdateEditText.setText( subStrings[2] );
        RoleUpdateEditText.setText( subStrings[3] );
        DirectorUpdateEditText.setText( subStrings[4] );

    }   //   public void PopulateEditTexts()*/
}
