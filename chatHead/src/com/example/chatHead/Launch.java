package com.example.chatHead;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.chatHead.service.ChatHeadStarter;


public class Launch extends Activity{
  private static final String TAG = "Launch";
  
  @Override protected void onResume() {
    super.onResume();
    new ChatHeadStarter(this).setClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        Log.d(TAG, "chat head onclick");
        
      }
    }).start();
  }
}
