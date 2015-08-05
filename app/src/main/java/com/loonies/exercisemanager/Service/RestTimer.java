package com.loonies.exercisemanager.Service;

import android.app.Activity;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import com.loonies.exercisemanager.ExerciseRecordingPage;
import com.loonies.exercisemanager.R;

/**
 * Created by Õı∫∆÷€ on 2015/8/5.
 */
public class RestTimer implements Runnable {
    private int mSecond,primitiveMScond;
    private Button btnToSet;
    private Handler handler;
    private TextView title;
    private boolean isRunning=false;
    private ExerciseRecordingPage activity;
    public RestTimer(Button btnToSet,TextView title,int mSecond,ExerciseRecordingPage activity){
        this.btnToSet=btnToSet;
        this.activity=activity;
        this.mSecond=mSecond;
        primitiveMScond=mSecond;
        handler=new Handler();
        this.title=title;
    }
    public void setmSecond(int mSecond){
        this.mSecond=mSecond;
        primitiveMScond=mSecond;
    }
    public void beginRun(){
        isRunning=true;
        title.setText(activity.getText(R.string.resting));
        run();
    }
    public boolean isRunning(){
        if(isRunning)return true;
        else return false;
    }
    public void stop(){
        this.isRunning=false;
        mSecond=primitiveMScond;
        title.setText(activity.getText(R.string.exc_rcd_hnt));
    }
    private void computeTime(){
    mSecond--;
    }
    public void run(){
        if(isRunning){
            if(mSecond==1) {
                isRunning = false;
                activity.play();
            }
            computeTime();
            btnToSet.setText(Integer.toString(mSecond));
            handler.postDelayed(this,1000);
        }
        else {
            handler.removeCallbacks(this);
            btnToSet.setText(activity.getText(R.string.btn_rst_timer));
            title.setText(activity.getText(R.string.exc_rcd_hnt));
            mSecond=primitiveMScond;
        }
    }
}
