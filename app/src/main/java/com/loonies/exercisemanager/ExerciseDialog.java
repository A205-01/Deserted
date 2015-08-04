package com.loonies.exercisemanager;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;


public class ExerciseDialog extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_exercise_dialog);
    }
    public void startToExcByPlan(View view){
        Intent intent=new Intent(this,ExerciseRecordingPage.class);
        startActivity(intent);
        ExerciseDialog.this.finish();//销毁当前页面
    }
    public void startToPlanPage(View view){
        Intent intent=new Intent(this,AddAnItemToPlanPage.class);
        startActivity(intent);
        ExerciseDialog.this.finish();//销毁当前页面
    }
}
