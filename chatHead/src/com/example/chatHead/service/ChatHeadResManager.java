package com.example.chatHead.service;

import com.example.chatHead.service.GestureListener.OnSingleClickListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class ChatHeadResManager {
  private static ChatHeadResManager ins;
  private OnSingleClickListener clickListener;
  private ImageView headImage;
  private Bitmap bitmap;
  private final WindowManager.LayoutParams params;
  @SuppressWarnings("deprecation")
  private ChatHeadResManager() {
    params = new WindowManager.LayoutParams(
        WindowManager.LayoutParams.FILL_PARENT,
        WindowManager.LayoutParams.FILL_PARENT,
        WindowManager.LayoutParams.TYPE_PHONE,
        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
        PixelFormat.TRANSLUCENT);
  }
  
  public static ChatHeadResManager getInstance() {
    synchronized (ChatHeadResManager.class) {
      if (ins == null) {
        ins = new ChatHeadResManager();
      }
    }
    return ins;
  }
  
  public WindowManager.LayoutParams getWindowManagerParams() {
    return params;
  }
  
  /*packaged*/ void setChatHeadClickListener(OnSingleClickListener l) {
    this.clickListener = l;
  }
  
  public OnSingleClickListener getChatHeadClickListener() {
    return this.clickListener;
  }
  
  public void addChatHeadImage(Bitmap bitmap, Context context) {
    if (context == null) {
      throw new IllegalArgumentException("Context equals to NULL");
    }
    if (headImage == null) {
      headImage = new ImageView(context);
    }
    headImage.setImageBitmap(bitmap);
    this.bitmap = bitmap;
  }
  
  public void removeHeadImage(Bitmap bitmap) {
    //TODO:
    throw new IllegalArgumentException("NO IMPLEMENTATION");
  }
  
  public View getChatHeadViewGroup() {
    return headImage;
  }
  //TODO: array
  public Bitmap getChatHeadBitmap() {
    return this.bitmap;
  }
}
