package ca.georgebrown.comp3074.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "itemList.db";

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ListTable.ListItem.SQL_CREATE);
        Log.d("DATABASE", "Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ListTable.ListItem.SQL_DROP);
        Log.d("DATABASE", "Database dropped");
        onCreate(db);
    }

    public boolean insertItem(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ListTable.ListItem.COLUMN_NAME, item);
        long result = db.insert(ListTable.ListItem.TABLE_NAME, ListTable.ListItem.COLUMN_ID , contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getID(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + ListTable.ListItem.COLUMN_ID + " FROM " + ListTable.ListItem.TABLE_NAME + " WHERE " +
                ListTable.ListItem.COLUMN_NAME + " = '" + item + "'";
        Cursor c = db.rawQuery(query, null);
        return c;
    }

    public Cursor getName(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + ListTable.ListItem.TABLE_NAME;

        Cursor c = db.rawQuery(query, null);
        return c;
    }

    public void removeItem(int id, String item){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM " + ListTable.ListItem.TABLE_NAME + " WHERE "
                + ListTable.ListItem.COLUMN_ID + " = '" + id + "'" +
                " AND " + ListTable.ListItem.COLUMN_NAME + " = '" + item + "'";

        db.execSQL(query);
    }

}
