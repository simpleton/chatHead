package com.example.chatHead.ui;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class CanvasThread extends Thread {

    private static final String TAG = "CanvasThread";
    
    private final SurfaceHolder surfaceHolder;
    private final MainPanel panel;
    
    private boolean running;
    synchronized public void setRunning(boolean running) {
      this.running = running;
    }
    
    public CanvasThread(SurfaceHolder holder, MainPanel panel) {
      super();
      this.surfaceHolder = holder;
      this.panel = panel;
    }
    
    @Override public void run() {
      Canvas canvas;
      Log.d(TAG, "Start Canvas Loop");
      while (this.running) {
        canvas = null;
        try {
          canvas = this.surfaceHolder.lockCanvas();
          if (canvas != null) {
            synchronized (this.surfaceHolder) {
              this.panel.render(canvas);
            }
          }
        } finally {
          if (canvas != null) {
            this.surfaceHolder.unlockCanvasAndPost(canvas);
          }
        }
      }
      super.run();
    }
}
