package com.loonies.exercisemanager;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import com.loonies.exercisemanager.data.ItemHelper;
import com.loonies.exercisemanager.data.ListItemTextView;
import com.loonies.exercisemanager.data.ListViewAdapterA;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import java.util.ArrayList;
import java.util.List;


public class ExerciseRecordingPage extends Activity {

    private List<ListItemTextView> data,dataAll;
    private ListView listview;
    private SQLiteDatabase db;
    private BaseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_recording_page);
        listview=(ListView)findViewById(R.id.list_view3);
        db=new ItemHelper(this).getWritableDatabase();
        listview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2,
                                    long arg3) {
                AlertDialog.Builder builder=new Builder(ExerciseRecordingPage.this);
                builder.setTitle(getText(R.string.recording));
                builder.setMessage(getText(R.string.WIP));
                builder.setPositiveButton(getText(R.string.done_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dataAll.get(arg2).getIsDone() < 3) {
                            String sqlFix = "update ItemDb set isDone='2'where id=" + Integer.toString(dataAll.get(arg2).getId());
                            db.execSQL(sqlFix);
                        }
                        else if (dataAll.get(arg2).getIsDone() > 3) {
                            String sqlFix = "update ItemDb set isDone='6'where id=" + Integer.toString(dataAll.get(arg2).getId());
                            db.execSQL(sqlFix);
                        }
                        onResume();
                    }
                });
                builder.setNegativeButton(getText(R.string.undone), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dataAll.get(arg2).getIsDone() < 3) {
                            String sqlFix = "update ItemDb set isDone='1'where id=" + Integer.toString(dataAll.get(arg2).getId());
                            db.execSQL(sqlFix);
                        }
                        else if (dataAll.get(arg2).getIsDone() > 3) {
                            String sqlFix = "update ItemDb set isDone='5'where id=" + Integer.toString(dataAll.get(arg2).getId());
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
        String now_time=t.year+"-"+t.month+"-"+t.monthDay;
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
        adapter=new ListViewAdapterA(this,data);
        listview.setAdapter(adapter);
    }
    public void finishExc(View view){
        Intent intent=new Intent(this,MainPage.class);
        startActivity(intent);
    }
}
