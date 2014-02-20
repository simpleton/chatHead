package com.example.chatHead.service;

import android.content.Context;
import android.content.Intent;

import com.example.chatHead.service.GestureListener.OnSingleClickListener;

public class ChatHeadStarter {
  private Context context;
  
  public ChatHeadStarter(Context context) {
    this.context = context;
  }
  
  public ChatHeadStarter setClickListener(final OnSingleClickListener l) {
    ChatHeadResManager.getInstance().setChatHeadClickListener(l);
    return this;
  }

  public void start() {
    Intent intent = new Intent(this.context, ChatHeadService.class);
    
    this.context.startService(intent);
  }
}