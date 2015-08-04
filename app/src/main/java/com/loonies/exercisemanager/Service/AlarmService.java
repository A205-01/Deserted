package com.loonies.exercisemanager.Service;

/**
 * Created by 王浩舟 on 2015/8/4.
 */
import android.app.Dialog;

import android.app.Service;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;
import android.util.Log;
import android.view.WindowManager;

public class AlarmService extends Service { // 自定义Service ，用于播放我自己的音乐
    private MediaPlayer mMediaplayer;

    @Override
    public void onCreate() {
        mMediaplayer = new MediaPlayer();
        mMediaplayer.reset();

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread() {
            public void run() {
                // 音乐开始播放了
                try {
                    mMediaplayer = new MediaPlayer();
                    mMediaplayer.reset();
                    AssetFileDescriptor afd = getAssets().openFd("Skrillex - Make It Bun Dem.mp3"); // 读取assets中的音乐文件
                    mMediaplayer.reset();
                    mMediaplayer.setDataSource(afd.getFileDescriptor(),
                            afd.getStartOffset(), afd.getLength());
                    mMediaplayer.prepare();
                    mMediaplayer.start();
                    mMediaplayer
                            .setOnCompletionListener(new OnCompletionListener() {

                                @Override
                                public void onCompletion(MediaPlayer arg0) {
                                    // TODO Auto-generated method stub
                                    Log.e("media", "播放完了");
                                    mMediaplayer.stop();
                                    mMediaplayer.release();
                                    stopSelf();
                                }
                            });
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Log.e("Service", "OH~,Service err!!");
                }
            };
        }.start();

        // 设置对话框，显示到当前任务顶端-->置顶
        Builder build = new Builder(getApplicationContext());
        build.setTitle("时间到了");
        build.setMessage("时间到了！\n 赶紧点取消~");
        build.setPositiveButton("取消", new OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                mMediaplayer.stop();
                mMediaplayer.release();
                stopSelf();
            }
        });
        Dialog dialog = build.create();
        dialog.getWindow()
                .setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent arg0) {

        return null;
    }

}
