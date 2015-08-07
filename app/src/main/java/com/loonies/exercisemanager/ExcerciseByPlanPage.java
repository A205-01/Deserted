package com.loonies.exercisemanager;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.loonies.exercisemanager.data.ItemHelper;
import com.loonies.exercisemanager.data.ListItemTextView;
import com.loonies.exercisemanager.data.ListViewAdapterA;

import java.util.ArrayList;
import java.util.List;


public class ExcerciseByPlanPage extends Activity {

    private List<ListItemTextView> dataAll;
    private ListView listview;
    private SQLiteDatabase db;
    private BaseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_by_plan_page);
        listview=(ListView)findViewById(R.id.list_view2);
        db=new ItemHelper(this).getWritableDatabase();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                if (dataAll.get(arg2).getIsDone() < 3) {
                    Intent intent = new Intent(ExcerciseByPlanPage.this, EditOxyPage.class);
                    intent.putExtra("id", dataAll.get(arg2).getId());
                    intent.putExtra("name", dataAll.get(arg2).getItem());
                    intent.putExtra("hours", dataAll.get(arg2).getHoursOrWeights());
                    intent.putExtra("minutes", dataAll.get(arg2).getMinutesOrNumber());
                    intent.putExtra("groups", dataAll.get(arg2).getGroups());
                    intent.putExtra("timeEdit", dataAll.get(arg2).getWrTime());
                    startActivity(intent);
                } else if (dataAll.get(arg2).getIsDone() > 3) {
                    Intent intent = new Intent(ExcerciseByPlanPage.this, EditStrnPage.class);
                    intent.putExtra("id", dataAll.get(arg2).getId());
                    intent.putExtra("name", dataAll.get(arg2).getItem());
                    intent.putExtra("weights", dataAll.get(arg2).getHoursOrWeights());
                    intent.putExtra("numbers", dataAll.get(arg2).getMinutesOrNumber());
                    intent.putExtra("groups", dataAll.get(arg2).getGroups());
                    intent.putExtra("timeEdit", dataAll.get(arg2).getWrTime());
                    startActivity(intent);
                }
            }
        });
    }
    protected void onResume(){
        super.onResume();
        dataAll=new ArrayList<ListItemTextView>() ;
        Time t=new Time();
        t.setToNow();
        String now_time=t.year+"-"+t.month+"-"+t.monthDay;
        String sqlo="select * from ItemDb order by datetime desc ";
        Cursor cursor=db.rawQuery(sqlo, null);
        while(cursor.moveToNext()){
            ListItemTextView litv=new ListItemTextView(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),
                    cursor.getString(4),cursor.getString(5),cursor.getInt(6));
            dataAll.add(litv);
        }
        cursor.close();
        adapter=new ListViewAdapterA(this,dataAll,this);
        listview.setAdapter(adapter);
    }

    public void startExc(View view){
        finish();
    }
}
