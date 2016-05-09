package com.psnotify;

import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBCReceiver extends BroadcastReceiver {
	
	private String url = "http://10.0.2.2/psnotify/getNewMessages.php";

	@Override
	public void onReceive(final Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        //params.put("lastTimeChecked", lastTimeChecked);
        
        client.post(url, params, new AsyncHttpResponseHandler() {
        	
        	@Override
        	public void onSuccess(String content) {
        		// TODO Auto-generated method stub
        		try {
					JSONObject response = new JSONObject(content);
					if(response.getInt("new_msgs") != 0) {
						//Toast.makeText(context, "You have "+response.getInt("msgs")+" new mgs", Toast.LENGTH_SHORT).show();
						
						final Intent intent = new Intent(context, MyService.class);
                    	// Set unsynced count in intent data
                    	intent.putExtra("new_msgs", "You have "+response.getInt("new_msgs")+" new mgs");
                    	// Call MyService
                    	context.startService(intent);
                    	
					}else{
                    	//Toast.makeText(context, "You have no new messages...!", Toast.LENGTH_SHORT).show();
                    }
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	
        	@Override
        	public void onFailure(int statusCode, Throwable error, String content) {
        		// TODO Auto-generated method stub
        		Toast.makeText(context, "getNewMessages() failure : "+String.valueOf(statusCode), Toast.LENGTH_SHORT).show();
        	}
        	
        });
		
	}

}
