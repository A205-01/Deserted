package com.loonies.exercisemanager;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class EditInfoDialog extends Activity implements OnClickListener {
    private SharedPreferences prefs;
    private EditText height;
    private EditText weight;
    private Button btn_save;
    private Button btn_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit_info_dialog);
        height=(EditText)findViewById(R.id.height);
        weight=(EditText)findViewById(R.id.weight);
        btn_save=(Button)findViewById(R.id.btn_edit_cancel_person);
        btn_cancel=(Button)findViewById(R.id.btn_edit_save_person);
        btn_save.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        prefs = this.getSharedPreferences("PERSONNALFILE",0);
        boolean isNotInied=prefs.getBoolean("isNotInied", true);
        if(!isNotInied){
            int height_blank=prefs.getInt("height",0);
            int weight_blank=prefs.getInt("weight",1);
            height.setText(Integer.toString(height_blank));
            weight.setText(Integer.toString(weight_blank));
        }

    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_edit_cancel_person:
                finish();
                break;
            case R.id.btn_edit_save_person:
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("isNotInied", false);
                editor.putInt("weight",Integer.parseInt(weight.getText().toString()));
                editor.putInt("height",Integer.parseInt(height.getText().toString()));
                editor.commit();
                Toast.makeText(EditInfoDialog.this, "saved..", Toast.LENGTH_LONG).show();
                finish();
                break;
        }
    }
}
