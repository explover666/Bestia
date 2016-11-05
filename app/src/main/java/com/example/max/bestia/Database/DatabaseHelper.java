package com.example.max.bestia.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.example.max.bestia.R;
import com.example.max.bestia.Utility.DbBitmapUtility;


public class DatabaseHelper extends SQLiteOpenHelper {


    Bitmap bitmap;
    Cursor c;
    private DatabaseHelper dbHelper;
    private byte[] image=null;
    private final Context context;
    private SQLiteDatabase db;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "database_name";
    private static final String DB_TABLE = "table_image";
    public static final String COLUMN_ID = "_id";
    private static final String KEY_NAME = "image_name";
    private static final String KEY_IMAGE = "image_data";
    private static final String CREATE_TABLE_IMAGE = "CREATE TABLE " + DB_TABLE + "("+ COLUMN_ID + " integer primary key autoincrement, " + KEY_NAME + " TEXT," + KEY_IMAGE + " BLOB);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_IMAGE);
        setImage(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        // create new table
        onCreate(db);
    }

    private void setImage(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        Resources res = context.getResources();
        TypedArray image_poster = res.obtainTypedArray(R.array.image_poster);
        String[] name_poster = res.getStringArray(R.array.name_poster);
        int length = name_poster.length;
        for (int i = 0; i<length; i++) {
            cv.put(KEY_NAME, name_poster[i]);
            Drawable drawable = image_poster.getDrawable(i);
            byte [] convert = DbBitmapUtility.getBytes(drawable);
            cv.put(KEY_IMAGE, convert);
            db.insert(DB_TABLE, null, cv);
        }
    }

    public Bitmap PutImage(int cv) {
        dbHelper = new DatabaseHelper(context);
        db=dbHelper.getWritableDatabase();
        c=db.rawQuery("select * from " + DatabaseHelper.DB_TABLE + " where " + DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(cv)});
        if(c!=null){
            c.moveToFirst();
            do{
                image=c.getBlob(c.getColumnIndex(KEY_IMAGE));
            }
            while(c.moveToNext());
            bitmap =DbBitmapUtility.getImage(image);
        }
        return bitmap;
    }
}
