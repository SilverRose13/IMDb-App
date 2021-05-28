package com.example.imdb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class OpenDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SQLite_Lily_Collins.db";

    // TOGGLE THIS NUMBER FOR UPDATING TABLES AND DATABASE
    private static final int DATABASE_VERSION = 1;


    OpenDatabase(Context context){
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }




    //method for displaying all records
    public ArrayList<String> DisplayRecords(SQLiteDatabase sqdb)
    {
        ArrayList<String> arrayList = new ArrayList<String>();
        String result = "";

        Cursor c = sqdb.rawQuery("SELECT * FROM Lily_Collins", null);
        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    result = "";
                    String id = c.getString(0);
                    result += id + ", ";
                    String name = c.getString(1);
                    result += name + ", ";
                    String year = c.getString(2);
                    result += year + ", ";
                    String role = c.getString(3);
                    result += role + ", ";
                    String director = c.getString(4);
                    result += director;

                    arrayList.add(result);

                    Log.w("SONG_TABLE", "ID = " + id + " Songtitle = " +
                            name);

                } while (c.moveToNext());
            }
        }
        c.close();

        return arrayList;
    } // public void DisplayRecords()


    //method for searching records by title
    public ArrayList<String> SearchByTitle(SQLiteDatabase sqdb, String searchTitle)
    {
        ArrayList<String> arrayList = new ArrayList<String>();
        String result = "";

        Cursor c = sqdb.rawQuery("SELECT * FROM Lily_Collins WHERE year = ''" + searchTitle + "'", null);
        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    result = "";
                    String id = c.getString(0);
                    result += id + ", ";
                    String name = c.getString(1);
                    result += name + ", ";
                    String year = c.getString(2);
                    result += year + ", ";
                    String role = c.getString(3);
                    result += role + ", ";
                    String director = c.getString(4);
                    result += director;

                    arrayList.add(result);

                    Log.w("SONG_TABLE", "ID = " + id + " Songtitle = " +
                            name);

                } while (c.moveToNext());
            }
        }
        c.close();

        return arrayList;
    }


    //method for searching records by year
    public ArrayList<String> SearchByYear(SQLiteDatabase sqdb, String searchYear)
    {
        ArrayList<String> arrayList = new ArrayList<String>();
        String result = "";

        Cursor c = sqdb.rawQuery("SELECT * FROM Lily_Collins WHERE year LIKE '" + searchYear + "'", null);
        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    result = "";
                    String id = c.getString(0);
                    result += id + ", ";
                    String name = c.getString(1);
                    result += name + ", ";
                    String year = c.getString(2);
                    result += year + ", ";
                    String role = c.getString(3);
                    result += role + ", ";
                    String director = c.getString(4);
                    result += director;

                    arrayList.add(result);

                        Log.w("LILY_COLLINS", "ID = " + id + " Title = " +
                            name);

                } while (c.moveToNext());
            }
        }
        c.close();

        return arrayList;
    } // public void SearchByYear()



    public String ReturnRecord( SQLiteDatabase sqdb, String searchId )
    {
        String result = "";

        Cursor c = sqdb.rawQuery("SELECT * FROM Lily_Collins WHERE id = " + searchId, null);
        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    String id = c.getString(0);
                    result = result + id + ", ";
                    String title = c.getString(1);
                    result = result + title + ", ";
                    String year = c.getString(2);
                    result = result + year + ", ";
                    String role = c.getString(3);
                    result = result + role + ", ";
                    String director = c.getString(4);
                    result = result + director;
                    Log.w("Lily_Collins", "ID = " + id + " Title = " + title);
                } while (c.moveToNext());
            }
        }
        c.close();

        return result;

    } // public void DisplayRecords()


    public void UpdateRecord(SQLiteDatabase sqdb, String id, String title, String year, String role, String director)
    {
        String updateQuery = "UPDATE Lily_Collins SET Name = '" + title + "', ReleaseYear = '" + year + "', Role = '" + role + "', Director = '" + director + "' ";
        updateQuery = updateQuery + " WHERE id = " + id ;

        Log.w("UPDATE", "updateQuery = " + updateQuery);

        sqdb.execSQL(updateQuery);

    }   // public void UpdateRecord

}
