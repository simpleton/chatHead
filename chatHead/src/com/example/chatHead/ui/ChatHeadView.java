package com.example.chatHead.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.example.chatHead.service.ChatHeadLauncher;

public class ChatHeadView implements OnCHTouchListener{
  private Bitmap bitmap;
  public int x;
  public int y;
  private boolean isTouched;
  
  public ChatHeadView(Bitmap bm, int x, int y) {
    this.bitmap = bm;
    this.x = x;
    this.y = y;
  }
  
  public boolean isTouched() {
    return this.isTouched;
  }
  
  public void setTouched(boolean touched) {
    this.isTouched = touched;
    if (touched) {
      ChatHeadLauncher.getInstance().getService().MaximalPannel();
    } else {
      ChatHeadLauncher.getInstance().getService().MinimalPannel(x, y);
    }
  }
  public void draw(Canvas canvas) {
    canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null);
  }

  @Override public void onDown(int eventX, int eventY) {
    if (eventX >= (x - bitmap.getWidth() / 2) && eventX <= (x + bitmap.getWidth() / 2)
        && eventY >= (y - bitmap.getHeight() / 2) && eventY <= (y + bitmap.getHeight() / 2)) {
      setTouched(true);
    } else {
      setTouched(false);
    }
  }

  @Override public void onMove(int eventX, int eventY) {
    if (isTouched()) {
      this.x = eventX;
      this.y = eventY;
    }
  }

  @Override public void onUp(int eventX, int eventY) {
    if (isTouched()) {
      setTouched(false);
    }    
  }

  public int getWidth() {
    return bitmap.getWidth();
  }

  public int getHeight() {
    return bitmap.getHeight();
  }
}
