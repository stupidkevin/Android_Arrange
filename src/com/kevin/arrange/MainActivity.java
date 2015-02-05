package com.kevin.arrange;

import java.util.ArrayList;
import java.util.List;

import com.kevin.arrange.store.sqllite.DBManager;
import com.kevin.arrange.store.sqllite.entity.DailyLogEntity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity {
	
	private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mainLayout = (LinearLayout)this.findViewById(R.id.layoutMain);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void text_btnSave_onClick(View v) {
		List<DailyLogEntity> entityList = new ArrayList<DailyLogEntity>();
    	for (int i = 0; i < mainLayout.getChildCount(); i++) {
    		View child = mainLayout.getChildAt(i);
    		if (child instanceof CheckBox) {
    			
    			CheckBox cb = (CheckBox)child;
    			
    			DailyLogEntity e = new DailyLogEntity();
    			e.setArragenEvent(cb.getText().toString());
    			if (cb.isChecked()) {
    				e.setStatus(1);
    			} else {
    				e.setStatus(2);
    			}
    			
    			entityList.add(e);
    		}
    	}
    	
    	new DBManager(this).addOrUpdateDailyLog(entityList);
    	
    }
    
    public void text_btnShowAll_onClick(View v) {
    	Intent intent = new Intent();
    	
    	intent.setClass(this, AllLogsActivity.class);
    	
    	this.startActivity(intent);
    }
    
}
