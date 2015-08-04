package com.loonies.exercisemanager;

import android.app.Activity;
import android.app.Fragment;
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
    private Fragment content;

    public void setDefaultFragment(){
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        strnPage=new StrnPage();
        oxypage=new OxyPage();
        content=oxypage;
        strnPage.setOnPressListner(this);
        oxypage.setOnPressListener(this);
        transaction.add(R.id.id_content, oxypage);
        transaction.commit();
    }
    public void onPressL(){
        if(content!=strnPage) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction tx = fm.beginTransaction();
            content = strnPage;
            if(!strnPage.isAdded()){
                tx.hide(oxypage);
                tx.add(R.id.id_content, strnPage);
            }
            else{
                tx.hide(oxypage);
                tx.show(strnPage);
            }
            tx.commit();
        }
    }

    public void onPressR(){
        if(content!=oxypage) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction tx = fm.beginTransaction();
            content = oxypage;
            if(!oxypage.isAdded()){
                tx.hide(strnPage);
                tx.add(R.id.id_content, oxypage);
            }
            else{
                tx.hide(strnPage);
                tx.show(oxypage);
            }
            tx.commit();
        }
    }
    public void oxyPageInteractionSave(){
        Intent intent=new Intent(this,AddingPlanDialog.class);
        startActivity(intent);
        finish();
    }
    public void oxyPageInteractionCancel(){
        Intent intent=new Intent(this,AddingPlanDialog.class);
        startActivity(intent);
        finish();
    }
    public void strnPageInteractionSave(){
        Intent intent=new Intent(this,AddingPlanDialog.class);
        startActivity(intent);
        finish();
    }
    public void strnPageInteractionCancel(){
        Intent intent=new Intent(this,AddingPlanDialog.class);
        startActivity(intent);
        finish();
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
