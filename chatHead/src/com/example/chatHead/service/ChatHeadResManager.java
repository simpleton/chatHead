package com.example.chatHead.service;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

public class ChatHeadResManager {
  private static ChatHeadResManager ins;
  private View.OnClickListener clickListener;
  private ImageView headImage;
  
  private ChatHeadResManager() {
  }
  
  public static ChatHeadResManager getInstance() {
    synchronized (ChatHeadResManager.class) {
      if (ins == null) {
        ins = new ChatHeadResManager();
      }
    }
    return ins;
  }
  
  public void setChatHeadClickListener(View.OnClickListener l) {
    this.clickListener = l;
  }
  
  public View.OnClickListener getChatHeadClickListener() {
    return this.clickListener;
  }
  
  public void addChatHeadImage(Bitmap bitmap) {
    headImage.setImageBitmap(bitmap);
  }
  
  public void removeHeadImage(Bitmap bitmap) {
    //TODO:
    throw new IllegalArgumentException("NO IMPLEMENTATION");
  }
  
  public View getChatHeadViewGroup() {
    return headImage;
  }
}
