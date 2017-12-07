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
            public static final String ID_NUMBER = "idNum";
            public static final String MOTHER_UUID = "motherUuid";
            public static final String FATHER_UUID = "fatherUuid";
            public static final String BIRTH_FACILITY = "birthFacility";
            public static final String CHILD_STAYING_WITH = "childStayingWith";
            public static final String ADDRESS = "address";
            public static final String TWIN = "twin";
            public static final String DISABILITY = "disability";
            public static final String MOTHER_SUPPORT = "motherSupport";



        }// End of Cols
    }// End of Child Table

    public static final class ParentTable {
        public static final String NAME = "parents";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String FIRST_NAME = "name";
            public static final String SURNAME = "surname";
            public static final String GENDER = "gender";
            public static final String DATE = "date";
            public static final String ID_NUMBER = "idNum";
            public static final String NO_OF_BIRTHS = "noOfBirths";
            public static final String NO_OF_ALIVE = "noOfAlive";
            public static final String BIRTH_FACILITY = "birthFacility";
            public static final String CHILD_STAYING_WITH = "childStayingWith";
            public static final String ADDRESS = "address";



        }// End of Cols
    }// End of Parent Table



}
