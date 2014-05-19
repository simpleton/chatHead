package com.example.chatHead.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import com.example.chatHead.service.GestureListener.OnSingleClickListener;

public class ChatHeadResManager {
  private static ChatHeadResManager ins;
  private OnSingleClickListener clickListener;
  private ImageView headImage;
  private Bitmap bitmap;
  private final WindowManager.LayoutParams wholeScrennParams;

  @SuppressWarnings("deprecation")
  private ChatHeadResManager() {
    wholeScrennParams = new WindowManager.LayoutParams(
        WindowManager.LayoutParams.FILL_PARENT,
        WindowManager.LayoutParams.FILL_PARENT,
        WindowManager.LayoutParams.TYPE_PHONE,
        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
        PixelFormat.TRANSLUCENT);
    wholeScrennParams.gravity = Gravity.TOP | Gravity.LEFT;
    wholeScrennParams.x = 0;
    wholeScrennParams.y = 0;
  }
  
  public static ChatHeadResManager getInstance() {
    synchronized (ChatHeadResManager.class) {
      if (ins == null) {
        ins = new ChatHeadResManager();
      }
    }
    return ins;
  }
  
  public WindowManager.LayoutParams getWholeScrennParams() {
    return wholeScrennParams;
  }

  public WindowManager.LayoutParams getChatHeadParams() {
    return new WindowManager.LayoutParams(
        this.bitmap.getWidth(),
        this.bitmap.getHeight(),
        WindowManager.LayoutParams.TYPE_PHONE,
        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
        PixelFormat.TRANSLUCENT);
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
