package com.loonies.exercisemanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.loonies.exercisemanager.Service.AlarmService;

import java.util.Calendar;


public class MainPage extends Activity implements OnClickListener {

    Calendar c;
    private AlarmManager alarmManager;
    private PendingIntent pdintent;
    private Intent intent;
    private int hours, minutes;
    private long longtime;
    private ImageButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        btn=(ImageButton)findViewById(R.id.notify_btn);
        c=Calendar.getInstance();
        btn.setOnClickListener(this);
    }
    public void goExercising(View view){
        Intent intent=new Intent(this,ExerciseDialog.class);
        startActivity(intent);
    }
    public void startToPlanPage(View view){
        Intent intent=new Intent(this,PlanPage.class);
        startActivity(intent);
    }
    public void onClick(View v){
        if(v.getId()==R.id.notify_btn)
            alarm();
    }
    public void alarm(){
        timeDialog();
        setAlarm();
    }
    private void setAlarm(){

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(MainPage.this, AlarmService.class); // 启动服务
        pdintent = PendingIntent.getService(MainPage.this,100, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis()
                + longtime * 60 * 1000, pdintent);
    }
    private void timeDialog() { // 显示时间对话框
        AlertDialog dg = new TimePickerDialog(MainPage.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker arg0, int arg1, int arg2) {

                        hours = arg1; // 设置当前选择的时间
                        minutes = arg2;
                        long time1, time2;
                        time1 = hours * 60 + arg2;
                        time2 = c.get(Calendar.HOUR_OF_DAY) * 60
                                + c.get(Calendar.MINUTE);
                        if (time1 >= time2) {
                            longtime = time1 - time2;
                        } else {
                            longtime = 24 * 60 - time2 + time1;
                        }
                        Toast.makeText(MainPage.this, "" + longtime,
                                Toast.LENGTH_LONG).show();
                    }
                }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true);
        dg.setTitle("alarm：");
        dg.show();
    }
}
