package com.example.chatHead.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.example.chatHead.service.GestureListener.OnSingleClickListener;

public class ChatHeadLauncher {
  private ChatHeadService mChatHeadService;
  private boolean isStarted = false;

  private static ChatHeadLauncher ins;

  public static ChatHeadLauncher getInstance() {
    if (ins == null) {
      synchronized (ChatHeadLauncher.class) {
        ins = new ChatHeadLauncher();
      }
    }
    return ins;
  }
  public ChatHeadLauncher() {
  }
  
  public ChatHeadLauncher setClickListener(final OnSingleClickListener l) {
    ChatHeadResManager.getInstance().setChatHeadClickListener(l);
    return this;
  }

  private ServiceConnection mConnection = new ServiceConnection() {
    public void onServiceConnected(ComponentName className, IBinder service) {
      mChatHeadService = ((ChatHeadService.LocalBinder) service).getService();
    }

    public void onServiceDisconnected(ComponentName className) {
      mChatHeadService = null;
    }
  };

  public void start(Context context) {
    context.bindService(new Intent(context, ChatHeadService.class), mConnection, Context.BIND_AUTO_CREATE);
    isStarted = true;
  }

  public void stop(Context context) {
    if (isStarted) {
      context.unbindService(mConnection);
      isStarted = false;
    }
  }

  public synchronized ChatHeadService getService() {
    return mChatHeadService;
  }
}