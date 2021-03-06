package com.navsaria.keeran.clinicbook.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.navsaria.keeran.clinicbook.database.ChildDbSchema.ChildTable;

/**
 * Created by keeran on 2017/11/29.
 */

public class ChildBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "childBase.db";

    public ChildBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + ChildTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                ChildTable.Cols.UUID + ", " +
                ChildTable.Cols.FIRST_NAME + ", " +
                ChildTable.Cols.SURNAME + ", " +
                ChildTable.Cols.GENDER + ", " +
                ChildTable.Cols.DATE + ", " +
                ChildTable.Cols.ID_NUMBER + ", " +
                ChildTable.Cols.MOTHER_UUID + ", " +
                ChildTable.Cols.FATHER_UUID + ", " +
                ChildTable.Cols.BIRTH_FACILITY + ", " +
                ChildTable.Cols.CHILD_STAYING_WITH + ", " +
                ChildTable.Cols.ADDRESS + ", " +
                ChildTable.Cols.TWIN + ", " +
                ChildTable.Cols.DISABILITY + ", " +
                ChildTable.Cols.MOTHER_SUPPORT + ", " +
                ChildTable.Cols.VACCINES +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
