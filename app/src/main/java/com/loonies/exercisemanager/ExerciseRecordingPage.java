package com.loonies.exercisemanager;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View.OnClickListener;
import com.loonies.exercisemanager.Service.RestTimer;
import com.loonies.exercisemanager.data.ItemHelper;
import com.loonies.exercisemanager.data.ListItemTextView;
import com.loonies.exercisemanager.data.ListViewAdapterA;
import com.loonies.exercisemanager.data.ListViewAdapterB;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ExerciseRecordingPage extends Activity implements OnClickListener {

    private SoundPool sp;
    private int soundId;
    private List<ListItemTextView> data,dataAll;
    private ListView listview;
    private SQLiteDatabase db;
    private BaseAdapter adapter;
    private Button timer;
    private Button finished;
    private RestTimer rRimer;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_recording_page);
        title=(TextView)findViewById(R.id.r_title);
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId=sp.load(this, R.raw.alright, 1);
        finished=(Button)findViewById(R.id.exc_done);
        timer=(Button)findViewById(R.id.set_timer_btn);
        rRimer=new RestTimer(timer,title,60,this);
        finished.setOnClickListener(this);
        timer.setOnClickListener(this);
        listview=(ListView)findViewById(R.id.list_view3);
        db=new ItemHelper(this).getWritableDatabase();
        listview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2,
                                    final long arg3) {
                AlertDialog.Builder builder = new Builder(ExerciseRecordingPage.this);
                builder.setTitle(getText(R.string.recording));
                builder.setMessage(getText(R.string.WIP));
                builder.setPositiveButton(getText(R.string.done_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dataAll.get(findPosition(data, dataAll, arg2)).getIsDone() < 3) {
                            String sqlFix = "update ItemDb set isDone='2'where id=" + Integer.toString(dataAll.get(findPosition(data, dataAll, arg2)).getId());
                            db.execSQL(sqlFix);
                        } else if (dataAll.get(findPosition(data, dataAll, arg2)).getIsDone() > 3) {
                            String sqlFix = "update ItemDb set isDone='6'where id=" + Integer.toString(dataAll.get(findPosition(data, dataAll,arg2)).getId());
                            db.execSQL(sqlFix);
                        }
                        onResume();
                    }
                });
                builder.setNegativeButton(getText(R.string.unfinished), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dataAll.get(findPosition(data, dataAll, arg2)).getIsDone() < 3) {
                            String sqlFix = "update ItemDb set isDone='1'where id=" + Integer.toString(dataAll.get(findPosition(data, dataAll, arg2)).getId());
                            db.execSQL(sqlFix);
                        } else if (dataAll.get(findPosition(data, dataAll,arg2)).getIsDone() > 3) {
                            String sqlFix = "update ItemDb set isDone='5'where id=" + Integer.toString(dataAll.get(findPosition(data,dataAll,arg2)).getId());
                            db.execSQL(sqlFix);
                        }
                        onResume();
                    }
                });
                builder.create().show();
            }
        });
    }
    protected void onResume(){
        super.onResume();
        Time t=new Time();
        t.setToNow();
        String now_time=t.year+"-"+(t.month+1)+"-"+t.monthDay;
        data=new ArrayList<ListItemTextView>() ;
        dataAll=new ArrayList<ListItemTextView>() ;
        String sql="select * from ItemDb order by datetime desc ";
        Cursor cursor=db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            ListItemTextView litv=new ListItemTextView(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),
                    cursor.getString(4),cursor.getString(5),cursor.getInt(6));
            dataAll.add(litv);
            if(now_time.equals(litv.getWrTime()))
                data.add(litv);
    }
        cursor.close();
        adapter=new ListViewAdapterB(this,data);
        listview.setAdapter(adapter);
    }
    protected void onPause(){
        super.onPause();
        if(rRimer.isRunning())
            rRimer.stop();
    }
    public void play() {
        //声音id 左声道 右声道 优先级
        //loop loop mode (0 = no loop, -1 = loop forever)
        //rate playback rate (1.0 = normal playback, range 0.5 to 2.0)
        sp.play(soundId, 1.0f, 0.3f, 0, 0, 2.0f);
    }
    public void indicate(){
        Toast.makeText(this,getText(R.string.time_up), Toast.LENGTH_LONG).show();
    }

    private int findPosition(List<ListItemTextView> data,List<ListItemTextView> dataAll,int arg2){
        int idCur=data.get(arg2).getId();
        int max=dataAll.size();
        int count=0;
        while(count<max){
            if(dataAll.get(count).getId()==idCur){
                return count;
            }
            count++;
        }
        return 0;
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.set_timer_btn:
                if(!rRimer.isRunning()){
                    rRimer.beginRun();
                }
                else if(rRimer.isRunning()){
                    rRimer.stop();
                }
                break;
            case R.id.exc_done:
                Intent intent=new Intent(this,MainPage.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
