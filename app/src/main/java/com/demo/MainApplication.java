package com.demo;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.demo.di.component.DaggerAppComponent;
import com.demo.util.CommonUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MainApplication extends Application implements HasActivityInjector {
    private static Activity mContext;
    private String signData = "";
    private String imagePath = "";
    private String audioPath = "";
    private String videoPath = "";
    private String filePath = "";
    private String imageName = "";
    private String barCode="";
    private String questionId="";
    private Map<String,String> groupSectionImage = new HashMap<>();
    private static boolean isScreenOff=false;
    private static ScheduledThreadPoolExecutor intractiveThread;
    private Timer timer;
    private static boolean isNotIntract=false;
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;



    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    private MediaPlayer mediaPlayer;

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getSignData() {
        return signData;
    }

    public void setSignData(String signData) {
        this.signData = signData;
    }


    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    public Map<String, String> getGroupSectionImage() {
        return groupSectionImage;
    }

    public void setGroupSectionImage(Map<String, String> groupSectionImage) {
        this.groupSectionImage = groupSectionImage;
    }
    public void resetData() {
        this.audioPath = "";
        this.videoPath = "";
        this.filePath = "";
        this.imageName = "";
        this.signData = "";
        this.imagePath = "";
    }


    public void startUserInactivityDetectThread() {
        intractiveThread = new ScheduledThreadPoolExecutor(1);
        intractiveThread.schedule(runnable1,20, TimeUnit.SECONDS);


    }

    private Runnable runnable1 = new Runnable() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void run() {
            stopIntractiveThread();
            mContext.sendBroadcast(new Intent("interact_user"));
        }
    };



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void stopIntractiveThread(){
        if (intractiveThread != null){
            intractiveThread.setRemoveOnCancelPolicy(true);
            intractiveThread.shutdownNow();
        }

    }



    private Handler handler = new Handler();

    private Runnable runnable =new Runnable() {
        @Override
        public void run() {
            startUserInactivityDetectThread();
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void stopHandler(){
        stopIntractiveThread();
        if (handler != null){
            handler.removeCallbacks(runnable);
        }
    }

    public void startHandler(){
        if (handler != null){
            handler.postDelayed(runnable,1000);
        }
    }

    /*private class InteractiveBroadCast extends BroadcastReceiver{


        public InteractiveBroadCast(){
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("interact_user");
            registerReceiver(this,intentFilter);
        }



        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("interact_user")){
                openDialogToReEnterPass(mContext);
            }
        }
    }*/


    public void openDialogToReEnterPass(Activity mContext) {
        Dialog dialog = new Dialog(mContext,android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.re_enter_password,null);
        dialog.setContentView(view);
        EditText editText = dialog.findViewById(R.id.etReEnterPass);
        TextView tvDone = dialog.findViewById(R.id.tvDone);

        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override()
            public void onClick(View view) {

                if (editText.getText().toString().isEmpty()){
                    CommonUtils.showToast(mContext,"Please enter password.");
                }else {
//                    UserTable userTable = new UserTable(mContext);
//                    if (userTable.isValidPassword(new AppSession(mContext).getUserName(),editText.getText().toString().trim())){
//                        dialog.dismiss();
//                        new AppSession(mContext).setInteract(false);
//                        CommonUtils.hide_keyboard(mContext);
//                        stopIntractiveThread();
//                        startUserInactivityDetectThread();
////                        new ScreenReceiver();
//                    }else {
//                        CommonUtils.showToast(mContext,"Please enter valid password.",true);
//                    }

                }

            }
        });

        dialog.show();
    }
}
