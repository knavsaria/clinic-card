package com.navsaria.keeran.clinicbook.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.navsaria.keeran.clinicbook.database.ChildDbSchema.*;


/**
 * Created by keeran on 2017/12/08.
 */

public class VaccineBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "vaccineBase.db";

    public VaccineBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + VaccineTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                VaccineTable.Cols.UUID + ", " +
                VaccineTable.Cols.AGE_GROUP + ", " +
                VaccineTable.Cols.BATCH_NUMBER + ", " +
                VaccineTable.Cols.VACCINE + ", " +
                VaccineTable.Cols.SITE + ", " +
                VaccineTable.Cols.DATE +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}


}
