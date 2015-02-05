package com.kevin.arrange;

import java.util.List;

import com.kevin.arrange.store.sqllite.DBManager;
import com.kevin.arrange.store.sqllite.entity.DailyLogEntity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AllLogsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_logs);
		
		List<DailyLogEntity> logs = new DBManager(this).findDailyLogBetweenDays("2015-01-01", "2016-01-01");
		if (logs != null) {
			StringBuilder sb = new StringBuilder();
			for (DailyLogEntity e : logs) {
				sb.append(e.getArragenEvent() + "--" + e.getStatus() + "--" + e.getSubmitDay());
			}
			
			TextView tv = (TextView)this.findViewById(R.id.tvAllLogs);
			tv.setText(sb.toString());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.all_logs, menu);
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
}
