package com.loonies.exercisemanager.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.loonies.exercisemanager.R;

import java.util.List;

/**
 * Created by Õı∫∆÷€ on 2015/8/6.
 */
public class ListViewAdapterB extends BaseAdapter {
    private Context context ;
    private List<ListItemTextView> data ;
    private List<ListItemTextView> dataAll;

    public ListViewAdapterB(android.content.Context context,List<ListItemTextView> data){
        this.context=context;
        this.data=data;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return data.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return data.get(arg0).getId();
    }
    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2){
        View view;
        if(null==arg1){
            view= LayoutInflater.from(context).inflate(R.layout.activity_base_item2, null);
        }else{
            view=arg1;
        }
        TextView item_name=(TextView) view.findViewById(R.id.base_item_name2);
        TextView item_time=(TextView) view.findViewById(R.id.base_item_time2);
        TextView item_wOrH=(TextView) view.findViewById(R.id.textView2);
        TextView item_wOrHW=(TextView) view.findViewById(R.id.textView3);
        TextView item_nOrM=(TextView) view.findViewById(R.id.textView4);
        TextView item_nOrMW=(TextView) view.findViewById(R.id.textView7);
        TextView item_gp=(TextView) view.findViewById(R.id.textView6);
        ImageView item_done=(ImageView) view.findViewById(R.id.base_item_done2);
        ListItemTextView myltv=data.get(arg0);
        item_name.setText(myltv.getItem());
        item_time.setText(myltv.getWrTime());
        if(myltv.getIsDone()<3){
            item_wOrH.setText(myltv.getHoursOrWeights());
            item_wOrHW.setText("H");
            item_nOrM.setText(myltv.getMinutesOrNumber());
            item_nOrMW.setText("Min");
            item_gp.setText(myltv.getGroups());
        }
        else if(myltv.getIsDone()>3){
            item_wOrH.setText(myltv.getHoursOrWeights());
            item_wOrHW.setText("KG");
            item_nOrM.setText(myltv.getMinutesOrNumber());
            item_nOrMW.setText("*");
            item_gp.setText(myltv.getGroups());
        }
        if(myltv.getIsDone()==0||myltv.getIsDone()==4)
            item_done.setImageResource(R.drawable.unfinish);
        if(myltv.getIsDone()==1||myltv.getIsDone()==5)
            item_done.setImageResource(R.drawable.unfished);
        if(myltv.getIsDone()==2||myltv.getIsDone()==6)
            item_done.setImageResource(R.drawable.finish);
        return view;
    }
}
