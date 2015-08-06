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

import java.util.ArrayList;
import java.util.List;


public class PlanPage extends Activity {

    private List<ListItemTextView> data,dataAll;
    private ListView listview;
    private SQLiteDatabase db;
    private BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_page);
        listview=(ListView)findViewById(R.id.list_view);
        db=new ItemHelper(this).getWritableDatabase();
        listview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                if(dataAll.get(findPosition(data,dataAll,arg2)).getIsDone()<3){
                    Intent intent= new Intent(PlanPage.this,EditOxyPage.class);
                    intent.putExtra("id", dataAll.get(findPosition(data, dataAll, arg2)).getId());
                    intent.putExtra("name",dataAll.get(findPosition(data, dataAll, arg2)).getItem());
                    intent.putExtra("hours", dataAll.get(findPosition(data, dataAll, arg2)).getHoursOrWeights());
                    intent.putExtra("minutes",dataAll.get(findPosition(data, dataAll, arg2)).getMinutesOrNumber());
                    intent.putExtra("groups",dataAll.get(findPosition(data, dataAll, arg2)).getGroups());
                    intent.putExtra("timeEdit", dataAll.get(findPosition(data, dataAll, arg2)).getWrTime());
                    startActivity(intent);
                }
                else if(dataAll.get(findPosition(data, dataAll, arg2)).getIsDone()>3){
                    Intent intent= new Intent(PlanPage.this,EditStrnPage.class);
                    intent.putExtra("id",dataAll.get(findPosition(data, dataAll, arg2)).getId());
                    intent.putExtra("name",dataAll.get(findPosition(data, dataAll, arg2)).getItem());
                    intent.putExtra("weights", dataAll.get(findPosition(data, dataAll, arg2)).getHoursOrWeights());
                    intent.putExtra("numbers",dataAll.get(findPosition(data, dataAll, arg2)).getMinutesOrNumber());
                    intent.putExtra("groups",dataAll.get(findPosition(data, dataAll, arg2)).getGroups());
                    intent.putExtra("timeEdit",dataAll.get(findPosition(data,dataAll,arg2)).getWrTime());
                    startActivity(intent);
                }
            }
        });
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
    protected void onResume(){
        super.onResume();
        data=new ArrayList<ListItemTextView>() ;
        dataAll=new ArrayList<ListItemTextView>() ;
        Time t=new Time();
        t.setToNow();
        String now_time=t.year+"-"+(t.month+1)+"-"+t.monthDay;
        String sqlo="select * from ItemDb order by datetime desc ";
        Cursor cursor=db.rawQuery(sqlo, null);
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
    public void addPlan(View view){
        Intent intent=new Intent(this,AddAnItemToPlanPage.class);
        startActivity(intent);
        finish();
    }
    public void review(View view){
        Intent intent=new Intent(this,ExcerciseByPlanPage.class);
        startActivity(intent);
    }
}
