package com.wog.videoplayer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * Created by nhbao on 9/7/2016.
 */
public class VideoPlayerModule extends ReactContextBaseJavaModule implements ActivityEventListener,VideoClassListener.VideoListener {

    public final int VIDEO_CODE = 1;
    MediaSessionCompat mMediaSession;
    PlaybackStateCompat.Builder mStateBuilder;
    Promise videoPromise;



    public VideoPlayerModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "VideoPlayerManager";
    }



    @ReactMethod
    public void showVideoPlayer(String url,final Promise promise) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            VideoClassListener.getInstance().setListener(this);
            VideoClassListener.getInstance().videoState(false);
//            promise.resolve("videoEndreach");

            videoPromise = promise;
//            Intent videoIntent = new Intent(Intent.ACTION_VIEW);
//            videoIntent.setDataAndType(Uri.parse(url), "video/*");
//            currentActivity.startActivityForResult(videoIntent, VIDEO_CODE);
            Intent videoIntent = new Intent(getCurrentActivity(),ExoPlayer.class);
            videoIntent.putExtra("url",url);
           // videoIntent.setDataAndType(Uri.parse(url), "video/*");
            currentActivity.startActivityForResult(videoIntent, VIDEO_CODE);
        }else{
            promise.reject("ACTIVITY_NOT_FOUND", "Activity doesn't exist");
            return;
        }
    }

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {

        Log.e("requestCode",requestCode+"");
        if (requestCode == VIDEO_CODE) {
            getCurrentActivity().finish();
        }
    }

    @Override
    public void onNewIntent(Intent intent) {

    }

    @Override
    public void videoEndReach() {

        if(videoPromise!=null && VideoClassListener.getInstance().getVideoState() == true){
            Log.e("videoEndReach","callback called : "+VideoClassListener.getInstance().getVideoState());
            videoPromise.resolve("videoEndreach");
            videoPromise = null;
        }
    }
}
