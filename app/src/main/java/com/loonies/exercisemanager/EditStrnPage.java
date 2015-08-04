package com.loonies.exercisemanager;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.loonies.exercisemanager.data.ItemHelper;


public class EditStrnPage extends Activity implements OnClickListener {

    private SQLiteDatabase db;
    private EditText blaknk_name;
    private EditText blank_weight;
    private EditText blank_number;
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
        setContentView(R.layout.activity_edit_strn_page);
        blaknk_name=(EditText)findViewById(R.id.item_name_edit);
        blank_weight=(EditText)findViewById(R.id.item_weight_edit);
        blank_number=(EditText)findViewById(R.id.item_number_edit);
        blank_groups=(EditText)findViewById(R.id.item_groups_edit);
        time_edited=(TextView)findViewById(R.id.edit_time_show_2);
        btn_save=(Button)findViewById(R.id.btn_edit_save_strn);
        btn_cancel=(Button)findViewById(R.id.btn_edit_cancel_strn);
        btn_del=(Button)findViewById(R.id.btn_edit_del_strn);

        Intent intent=getIntent();
        id=intent.getIntExtra("id",1);
        String name=intent.getStringExtra("name");
        String houors=intent.getStringExtra("weights");
        String minutes=intent.getStringExtra("numbers");
        String gtoups=intent.getStringExtra("groups");
        String timeEdit=intent.getStringExtra("timeEdit");

        time_edited.setText(timeEdit);
        blank_groups.setText(gtoups);
        blank_number.setText(minutes);
        blank_weight.setText(houors);
        blaknk_name.setText(name);

        btn_cancel.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        btn_del.setOnClickListener(this);
    }
    public void onClick(View v) {
        db = new ItemHelper(this).getWritableDatabase();
        switch (v.getId()) {
            case R.id.btn_edit_save_strn:
                String name = blaknk_name.getText().toString().trim();
                String houors = blank_weight.getText().toString().trim();
                String minutes = blank_number.getText().toString().trim();
                String gtoups = blank_groups.getText().toString().trim();
                String sql = "update ItemDb set item='" + name + "',hours='" + houors + "',minutes='" + minutes + "',groups='" + gtoups + "'where id='" + id + "'";
                db.execSQL(sql);
                Toast.makeText(EditStrnPage.this, "saved..", Toast.LENGTH_LONG).show();
                finish();
                break;
            case R.id.btn_edit_cancel_strn:
                finish();
                break;
            case R.id.btn_edit_del_strn:
                String sql2 = "delete from ItemDb where id=" + Integer.toString(id);
                db.execSQL(sql2);
                Toast.makeText(EditStrnPage.this, "deleted..", Toast.LENGTH_LONG).show();
                finish();
                break;
        }
    }
}
