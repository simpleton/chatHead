package com.example.chatHead;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.chatHead.service.ChatHeadResManager;
import com.example.chatHead.service.ChatHeadStarter;
import com.example.chathead.R;


public class Launch extends Activity{
  private static final String TAG = "Launch";
  
  @Override protected void onResume() {
    super.onResume();
    Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
    ChatHeadResManager.getInstance().addChatHeadImage(bm, this);
    new ChatHeadStarter(this).setClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        Log.d(TAG, "chat head onclick");
        
      }
    }).start();
  }
}
