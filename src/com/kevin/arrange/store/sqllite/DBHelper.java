package com.kevin.arrange.store.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "kevin.db";  
    private static final int DATABASE_VERSION = 1;  

    public static final String TABLENAME_DAILYLOG = "KV_DailyLog";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLENAME_DAILYLOG + " (Id INTEGER PRIMARY KEY AUTOINCREMENT, ArragenEvent TEXT, Status INTEGER, SubmitDay TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
