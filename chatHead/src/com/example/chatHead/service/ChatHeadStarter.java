package com.example.chatHead.service;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class ChatHeadStarter {
  private Context context;
  
  public ChatHeadStarter(Context context) {
    this.context = context;
  }
  
  public ChatHeadStarter setClickListener(final View.OnClickListener l) {
    ChatHeadResManager.getInstance().setChatHeadClickListener(l);
    return this;
  }

  public void start() {
    Intent intent = new Intent(this.context, ChatHeadService.class);
    
    this.context.startService(intent);
  }
}