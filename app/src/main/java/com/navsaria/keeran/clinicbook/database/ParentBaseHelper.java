package com.navsaria.keeran.clinicbook.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.navsaria.keeran.clinicbook.database.ChildDbSchema.*;

/**
 * Created by keeran on 2017/12/07.
 */

public class ParentBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "parentBase.db";

    public ParentBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + ParentTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                ParentTable.Cols.UUID + ", " +
                ParentTable.Cols.FIRST_NAME + ", " +
                ParentTable.Cols.SURNAME + ", " +
                ParentTable.Cols.GENDER + ", " +
                ParentTable.Cols.DATE + ", " +
                ParentTable.Cols.ID_NUMBER + ", " +
                ParentTable.Cols.NO_OF_BIRTHS + ", " +
                ParentTable.Cols.NO_OF_ALIVE +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
