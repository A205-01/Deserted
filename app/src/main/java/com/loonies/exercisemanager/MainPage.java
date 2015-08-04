package com.loonies.exercisemanager;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }
    public void goExercising(View view){
        Intent intent=new Intent(this,ExerciseDialog.class);
        startActivity(intent);
    }
    public void startToPlanPage(View view){
        Intent intent=new Intent(this,PlanPage.class);
        startActivity(intent);
    }
}
