package com.packtpub.dietplannerfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
*/

/**
 * Created by balbina on 26.06.17.
 */

class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "healthyDatabase.db";
    private static final int DATABASE_VERSION = 4;
    private static final String TABLE_USERINFO="userinfo";
    private static final String COLUMN_PERSON_ID="userid";
    private static final String COLUMN_PERSON_FIRSTNAME="userfirstname";
    private static final String COLUMN_PERSON_LASTNAME="userlastname";
    private static final String COLUMN_PERSON_PASSWORD="userpassword";
    private static final String TABLE_EATINFO="eatinfo";
    private static final String COLUMN_USERID="userid";
    private static final String COLUMN_FOOD="food_name";
    private static final String COLUMN_FAT="fat_take";
    private static final String COLUMN_CARBOHYDRATE="carbohydrate_take";
    private static final String COLUMN_CALORIE="calorie_take";
    private static final String TABLE_DIMINFO="diminfo";
    private static final String COLUMN_USER_INFO="user_id";
    private static final String COLUMN_WEIGHT="wt";
    private static final String COLUMN_HEIGHT="ht";
    private static final String COLUMN_SEX="s";
    private static final String COLUMN_AGE="a";
    private static final String COLUMN_LIFESTYLE="lifesty";
    private static final String COLUMN_P="_p";




    // the DAO object we use to access the SimpleData table
    //   private Dao<Ingredient, Integer> ingredientDao = null;
    // private RuntimeExceptionDao<Ingredient, Integer> ingredientRuntimeDao = null;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // sqLiteDatabase.execSQL("create table "+TABLE_USERINFO+" ( "+COLUMN_PERSON_ID+" integer primary key autoincrement, "+COLUMN_PERSON_FIRSTNAME+" varchar(30), "+COLUMN_PERSON_LASTNAME+" varchar(30),"+COLUMN_PERSON_PASSWORD+"varchar(30))");
        String query = "CREATE TABLE " + TABLE_USERINFO + "(" + COLUMN_PERSON_ID + " TEXT PRIMARY KEY," + COLUMN_PERSON_FIRSTNAME + " TEXT," + COLUMN_PERSON_LASTNAME +" TEXT," + COLUMN_PERSON_PASSWORD + " TEXT"+ ");";
        String query1="CREATE TABLE "+ TABLE_EATINFO+"("+
                COLUMN_USERID+" TEXT,"+
                COLUMN_CALORIE+" TEXT,"+
                COLUMN_FOOD+" TEXT,"+
                "FOREIGN KEY("+COLUMN_USERID+") REFERENCES "+TABLE_USERINFO+"("+COLUMN_PERSON_ID+")"+
                ");";
        String query2="CREATE TABLE "+ TABLE_DIMINFO+"("+
                COLUMN_USER_INFO+" TEXT,"+
                COLUMN_WEIGHT+" TEXT,"+
                COLUMN_HEIGHT+" TEXT,"+
                COLUMN_SEX+" TEXT,"+
                COLUMN_AGE+" TEXT,"+
                COLUMN_LIFESTYLE+" TEXT,"+
                COLUMN_P+" TEXT,"+
                "FOREIGN KEY("+COLUMN_USER_INFO+") REFERENCES "+TABLE_USERINFO+"("+COLUMN_PERSON_ID+")"+
                ");";


        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.execSQL(query1);
        sqLiteDatabase.execSQL(query2);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int ov, int nv) {
        // TODO something not right here
        sqLiteDatabase.execSQL("Drop table "+TABLE_USERINFO);
        onCreate(sqLiteDatabase);
    }

    public long addUserEntry(UserEntry user) {
        ContentValues value = new ContentValues();
        value.put(COLUMN_PERSON_ID, user.getId());
        value.put(COLUMN_PERSON_FIRSTNAME, user.getUserfirstname());
        value.put(COLUMN_PERSON_LASTNAME, user.getUserlastname());
        value.put(COLUMN_PERSON_PASSWORD, user.getUserpassword());

        SQLiteDatabase db = getWritableDatabase();
        long rowId=db.insert(TABLE_USERINFO, null, value);
        return rowId;
    }
    public long addEatInformation(EatEntry entry) {
        ContentValues value=new ContentValues();

        value.put(COLUMN_USERID, entry.getId());
        value.put(COLUMN_FOOD, entry.getFood_item());
        value.put(COLUMN_CALORIE, entry.getCalo_Intake());
        SQLiteDatabase db = getWritableDatabase();
        long rowId=db.insert(TABLE_EATINFO, null, value);
        return rowId;

    }
    public long addDimInformation(person_dimen entry) {
        ContentValues value=new ContentValues();

        value.put(COLUMN_USER_INFO, entry.getUser_id());
        value.put(COLUMN_WEIGHT, entry.getWeight());
        value.put(COLUMN_HEIGHT, entry.getHeight());
        value.put(COLUMN_SEX, entry.getSex());
        value.put(COLUMN_AGE, entry.getAge());
        value.put(COLUMN_P, entry.getP());
        SQLiteDatabase db = getWritableDatabase();
        long rowId=db.insert(TABLE_DIMINFO, null, value);
        return rowId;

    }




    public int checkEntry(String id,String pass)
    {

        SQLiteDatabase db = getWritableDatabase();
        String[] selectionArgs = new String[]{id, pass};
        SQLiteDatabase sqldb = getWritableDatabase();
        //String query = "SELECT * FROM " + TABLE_USERINFO+" where userid=? and userpassword=?", selectionArgs;
        Cursor c=sqldb.rawQuery("select * from userinfo where userid=? and userpassword=?", selectionArgs);
        c.moveToFirst();
        int i=c.getCount();
        return i;
        // Cursor c = db.rawQuery(query, null);





    }


    public void close() {
        super.close();
        // ingredientDao = null;
        //   ingredientRuntimeDao = null;
    }
}
