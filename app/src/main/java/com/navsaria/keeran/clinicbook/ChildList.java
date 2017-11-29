package com.navsaria.keeran.clinicbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.navsaria.keeran.clinicbook.database.ChildBaseHelper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by keeran on 2017/11/23.
 */

public class ChildList {

    private static ChildList sChildList;
    private LinkedHashMap<UUID, Child> mChildren;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ChildList getChildList(Context context) {
        if ( sChildList == null) {
            sChildList = new ChildList(context);
        }
        return sChildList;

    }

    private ChildList(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new ChildBaseHelper(context)
                .getWritableDatabase();
        mChildren = new LinkedHashMap<>();
        Child firstChild = new Child();
        firstChild.setBoy(true);
        firstChild.setFirstName("Shakeel");
        firstChild.setLastName("Navsaria");

        Child secondChild = new Child();
        secondChild.setBoy(true);
        secondChild.setFirstName("Aaryan");
        secondChild.setLastName("Navsaria");

        mChildren.put(firstChild.getId(), firstChild);
        mChildren.put(secondChild.getId(), secondChild);
    }

    public List<Child> getChildren() {
        return new ArrayList<>(mChildren.values());
    }

    public void addChild(Child child) {
        mChildren.put(child.getId(), child);
    }

    public Child getChild(UUID id) {
        Child child = mChildren.get(id);
        return child;
    }

}
