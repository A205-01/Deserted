package com.loonies.exercisemanager;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class EditOxyPage extends Activity {
    private EditText blaknk_name;
    private EditText blank_hours;
    private EditText blank_minutes;
    private EditText blank_groups;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_oxy_page);
    }
}
