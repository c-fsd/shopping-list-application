package ca.georgebrown.comp3074.myapplication;

import android.provider.BaseColumns;

public final class ListTable {

    private ListTable(){}

    public static class ListItem

    implements BaseColumns
    {
        public static final String TABLE_NAME = "itemList";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "item";

        public static final String SQL_CREATE = "CREATE TABLE "+ TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT )";

        public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

}
