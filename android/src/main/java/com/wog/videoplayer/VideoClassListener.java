package com.wog.videoplayer;

/**
 * Created by anisderbel on 02/10/2018.
 */


public class VideoClassListener {

     private VideoListener videoListener;
     private static VideoClassListener videoClassInstance;
     private boolean isVideoreachend = false;


     private VideoClassListener() {}

     public static VideoClassListener getInstance() {
          if(videoClassInstance == null) {
               videoClassInstance = new VideoClassListener();
          }
          return videoClassInstance;
     }

     public interface VideoListener {
          void videoEndReach();
     }

     public void setListener(VideoListener listener) {
          videoListener = listener;
     }

     public void videoState(boolean isVideoreachend) {
          if(videoListener != null) {
               this.isVideoreachend = isVideoreachend;
               notifyStateChange();
          }
     }

     public boolean getVideoState() {
          return isVideoreachend;
     }

     private void notifyStateChange() {
          videoListener.videoEndReach();
     }
}
