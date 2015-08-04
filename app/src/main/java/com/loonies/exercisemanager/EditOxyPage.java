package com.loonies.exercisemanager;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.loonies.exercisemanager.data.ItemHelper;

public class EditOxyPage extends Activity implements OnClickListener {
    private SQLiteDatabase db;
    private EditText blaknk_name;
    private EditText blank_hours;
    private EditText blank_minutes;
    private EditText blank_groups;
    private TextView time_edited;
    private int id;
    private Button btn_save;
    private Button btn_cancel;
    private Button btn_del;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit_oxy_page);
        blaknk_name=(EditText)findViewById(R.id.item_name_edit_oxy);
        blank_hours=(EditText)findViewById(R.id.item_houur_edit_oxy);
        blank_minutes=(EditText)findViewById(R.id.item_minute_edit_oxy);
        blank_groups=(EditText)findViewById(R.id.item_groups_edit_oxy);
        time_edited=(TextView)findViewById(R.id.edit_time_show);
        btn_save=(Button)findViewById(R.id.btn_edit_save_oxy);
        btn_cancel=(Button)findViewById(R.id.btn_edit_cancel_oxy);
        btn_del=(Button)findViewById(R.id.btn_edit_del_oxy);

        Intent intent=getIntent();
        id=intent.getIntExtra("id",1);
        String name=intent.getStringExtra("name");
        String houors=intent.getStringExtra("hours");
        String minutes=intent.getStringExtra("minutes");
        String gtoups=intent.getStringExtra("groups");
        String timeEdit=intent.getStringExtra("timeEdit");

        time_edited.setText(timeEdit);
        blank_groups.setText(gtoups);
        blank_minutes.setText(minutes);
        blank_hours.setText(houors);
        blaknk_name.setText(name);

        btn_cancel.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        btn_del.setOnClickListener(this);
    }
    public void onClick(View v){
        db=new ItemHelper(this).getWritableDatabase();
        switch(v.getId()){
            case R.id.btn_edit_save_oxy:
                String name=blaknk_name.getText().toString().trim();
                String houors=blank_hours.getText().toString().trim();
                String minutes=blank_minutes.getText().toString().trim();
                String gtoups=blank_groups.getText().toString().trim();
                String sql="update ItemDb set item='"+name+"',hours='"+houors+"',minutes='"+minutes+"',groups='"+gtoups+"'where id='"+id+"'";
                db.execSQL(sql);
                Toast.makeText(EditOxyPage.this, "saved..", Toast.LENGTH_LONG).show();
                finish();
                break;
            case R.id.btn_edit_cancel_oxy:
                finish();
                break;
            case R.id.btn_edit_del_oxy:
                String sql2="delete from ItemDb where id="+Integer.toString(id);
                db.execSQL(sql2);
                Toast.makeText(EditOxyPage.this, "deleted", Toast.LENGTH_LONG).show();
                finish();
                break;
        }
    }
}
