package com.loonies.exercisemanager;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;


public class AddingPlanDialog extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_adding_plan_dialog);
    }
    public void onContinue(View view){
        Intent intent=new Intent(this,AddAnItemToPlanPage.class);
        startActivity(intent);
        AddingPlanDialog.this.finish();
    }
    public void onDone(View view){
        Intent intent=new Intent(this,MainPage.class);
        startActivity(intent);
        AddingPlanDialog.this.finish();
    }
}
