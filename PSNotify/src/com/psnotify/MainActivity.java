package com.psnotify;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	private SharedPreferences sp;
	private Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
		editor = sp.edit();
        editor.putString("lastTimeChecked", "2015-06-07 11:29:07");
        Toast.makeText(this, "Start lastTimeChecked = 0", Toast.LENGTH_SHORT).show();
        editor.commit();*/

		// Start BroadCast Receiver
		Intent alarmIntent = new Intent(getApplicationContext(), MyBCReceiver.class);

		// Pending Intent Object
		PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		// Alarm Manager Object
		AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);

		// Alarm Manager calls BroadCast for every Ten seconds (10 * 1000), 
		// BroadCast further calls service to check if new records are inserted in Remote MySQL DB
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis() + 5000, 10 * 1000, pendingIntent);

	}

}
