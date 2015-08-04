package com.loonies.exercisemanager.Service;

/**
 * Created by ������ on 2015/8/4.
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

public class AlarmService extends Service { // �Զ���Service �����ڲ������Լ�������
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
                // ���ֿ�ʼ������
                try {
                    mMediaplayer = new MediaPlayer();
                    mMediaplayer.reset();
                    AssetFileDescriptor afd = getAssets().openFd("Skrillex - Make It Bun Dem.mp3"); // ��ȡassets�е������ļ�
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
                                    Log.e("media", "��������");
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

        // ���öԻ�����ʾ����ǰ���񶥶�-->�ö�
        Builder build = new Builder(getApplicationContext());
        build.setTitle("ʱ�䵽��");
        build.setMessage("ʱ�䵽�ˣ�\n �Ͻ���ȡ��~");
        build.setPositiveButton("ȡ��", new OnClickListener() {

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
