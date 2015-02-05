package com.kevin.arrange.store.sqllite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kevin.arrange.store.sqllite.entity.DailyLogEntity;

public class DBManager {
	private DBHelper helper;
    private SQLiteDatabase db;
    
    public DBManager(Context context) {
    	helper = new DBHelper(context);
    	db = helper.getWritableDatabase();
    }
    
    public void addOrUpdateDailyLog(List<DailyLogEntity> logs) {
    	String day = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    	
    	List<DailyLogEntity> entityList = findDailyLogByDay(day);
    	if (entityList == null || entityList.size() <= 0) {
    		for (DailyLogEntity log : logs) {
    			log.setSubmitDay(day);
    			insertDailyLog(log);
    		}
    	} else {
    		for (DailyLogEntity newLog : logs) {
    			for (DailyLogEntity oldLog : entityList) {
    				if (newLog.getArragenEvent().equals(oldLog.getArragenEvent()) && ! (newLog.getStatus() == oldLog.getStatus())) {
    					newLog.setSubmitDay(day);
    					newLog.setId(oldLog.getId());
    					updateDailyLog(newLog);
    				}
    			}
    		}
    	}
    	
    }
    
    public List<DailyLogEntity> findDailyLogByDay(String day) {
    	
    	List<DailyLogEntity> entityList = new ArrayList<DailyLogEntity>();
    	
    	Cursor c = db.rawQuery("SELECT * FROM " + DBHelper.TABLENAME_DAILYLOG + " WHERE SubmitDay=?", new String[]{ day });
    	
    	if (c != null) {
    		while (c.moveToNext()) {
    			DailyLogEntity e = new DailyLogEntity();
    			e.setId(c.getInt(c.getColumnIndex("Id")));
    			e.setArragenEvent(c.getString(c.getColumnIndex("ArragenEvent")));
    			e.setStatus(c.getInt(c.getColumnIndex("Status")));
    			e.setSubmitDay(c.getString(c.getColumnIndex("SubmitDay")));
    			entityList.add(e);
    		}
    	}
    	
    	c.close();
    	
    	return entityList;
    }
    
    public List<DailyLogEntity> findDailyLogBetweenDays(String dayStart, String dayEnd) {

    	List<DailyLogEntity> entityList = new ArrayList<DailyLogEntity>();
    	
    	Cursor c = db.rawQuery("SELECT * FROM " + DBHelper.TABLENAME_DAILYLOG + " WHERE SubmitDay >= ? AND SubmitDay <= ?", new String[]{ dayStart, dayEnd });
    	
    	if (c != null) {
    		while (c.moveToNext()) {
    			DailyLogEntity e = new DailyLogEntity();
    			e.setId(c.getInt(c.getColumnIndex("Id")));
    			e.setArragenEvent(c.getString(c.getColumnIndex("ArragenEvent")));
    			e.setStatus(c.getInt(c.getColumnIndex("Status")));
    			e.setSubmitDay(c.getString(c.getColumnIndex("SubmitDay")));
    			entityList.add(e);
    		}
    	}
    	
    	c.close();
    	
    	return entityList;
    }
    
    public void insertDailyLog(DailyLogEntity e) {
    	if (e == null)
    		return;
    	
    	db.execSQL("INSERT INTO " + DBHelper.TABLENAME_DAILYLOG + " (ArragenEvent, Status, SubmitDay) VALUES(?, ?, ?);", new Object[] {e.getArragenEvent(), e.getStatus(), e.getSubmitDay()});
    }
    
    public void updateDailyLog(DailyLogEntity e) {
    	if (e == null)
    		return;
    	
    	db.execSQL("UPDATE " + DBHelper.TABLENAME_DAILYLOG + " SET Status=? WHERE Id=?;", new Object[] {e.getStatus(), e.getId()});
    	
    }
}
