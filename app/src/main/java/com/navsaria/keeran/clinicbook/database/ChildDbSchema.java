package com.navsaria.keeran.clinicbook.database;

/**
 * Created by keeran on 2017/11/29.
 */

public class ChildDbSchema {

    public static final class ChildTable {
        public static final String NAME = "children";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String FIRST_NAME = "name";
            public static final String SURNAME = "surname";
            public static final String GENDER = "gender";
            public static final String DATE = "date";
        }// End of Cols
    }// End of Child Table

}
