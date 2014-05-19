package com.example.chatHead;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.chatHead.service.ChatHeadLauncher;
import com.example.chatHead.service.ChatHeadResManager;
import com.example.chatHead.service.GestureListener;
import com.example.chathead.R;


public class Launch extends Activity{
  private static final String TAG = "Launch";
  
  @Override protected void onResume() {
    super.onResume();
    Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
    ChatHeadResManager.getInstance().addChatHeadImage(bm, this);
    ChatHeadLauncher.getInstance().setClickListener(new GestureListener.OnSingleClickListener() {
      
      @Override public void onClick() {
        Log.d(TAG, "launch app");
      }
    }).start(this);
  }
}
