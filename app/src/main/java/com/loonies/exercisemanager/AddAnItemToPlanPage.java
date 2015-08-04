package com.loonies.exercisemanager;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loonies.exercisemanager.data.ItemHelper;


public class AddAnItemToPlanPage extends Activity implements ItemSelection.OnPressListener,OxyPage.OxyFragmentListener,StrnPage.StrnPageListener {
    private OxyPage oxypage;
    private StrnPage strnPage;
    private ItemSelection itemSelection;
    private SQLiteDatabase db;

    public void setDefaultFragment(){
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        oxypage=new OxyPage();
        oxypage.setOnPressListener(this);
        transaction.add(R.id.id_content, oxypage);
        transaction.commit();
    }
    public void onPressL(){
        if(strnPage==null)
            strnPage=new StrnPage();
        strnPage.setOnPressListner(this);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.replace(R.id.id_content, strnPage);
        tx.addToBackStack(null);
        tx.commit();
    }

    public void onPressR(){
        if(oxypage==null)
            oxypage=new OxyPage();
        oxypage.setOnPressListener(this);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.replace(R.id.id_content, oxypage);
        tx.addToBackStack(null);
        tx.commit();
    }
    public void oxyPageInteractionSave(){
        Intent intent=new Intent(this,AddingPlanDialog.class);
        startActivity(intent);
    }
    public void oxyPageInteractionCancel(){
        Intent intent=new Intent(this,AddingPlanDialog.class);
        startActivity(intent);
    }
    public void strnPageInteractionSave(){
        Intent intent=new Intent(this,AddingPlanDialog.class);
        startActivity(intent);
    }
    public void strnPageInteractionCancel(){
        Intent intent=new Intent(this,AddingPlanDialog.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_an_item_to_plan_page);
        itemSelection=(ItemSelection)getFragmentManager().findFragmentById(R.id.id_title_fragment);
        itemSelection.setPressListener(this);
        setDefaultFragment();
    }
}
