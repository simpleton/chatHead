package com.example.chatHead;

import android.app.Activity;
import android.content.Intent;

import com.example.chatHead.service.chatHeadService;

public class Launch extends Activity{
  @Override protected void onResume() {
    super.onResume();
    startService(new Intent(Launch.this, chatHeadService.class));
  }
}
