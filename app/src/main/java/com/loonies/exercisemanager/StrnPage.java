package com.loonies.exercisemanager;

import java.util.Calendar;
import java.util.Date;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.loonies.exercisemanager.data.ItemHelper;

import java.text.SimpleDateFormat;
import java.util.Date;



public class StrnPage extends Fragment implements OnClickListener,DatePickerDialog.OnDateSetListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Calendar c;
    private Dialog dialog;
    private SQLiteDatabase db;
    private ImageButton btnCancel;
    private ImageButton btnSave;
    private ImageButton btnSetDate;
    private EditText itemName;
    private EditText weightToDo;
    private EditText numberToDo;
    private EditText groupsToDo;
    private String now_time;

    public interface StrnPageListener {
        public void strnPageInteractionSave();
        public void strnPageInteractionCancel();
    }
    private StrnPageListener mListener;

    public void onClick(View v){
        switch(v.getId()){
            case R.id.strn_cancel_btn:
                if(mListener!=null) {
                    mListener.strnPageInteractionCancel();
                    itemName.setText(null);
                    weightToDo.setText(null);
                    numberToDo.setText(null);
                    groupsToDo.setText(null);
                }
                break;
            case R.id.strn_save_btn:
                if(mListener!=null){
                    mListener.strnPageInteractionSave();
                    String item=itemName.getText().toString().trim();
                    String weight=weightToDo.getText().toString().trim();
                    String number=numberToDo.getText().toString().trim();
                    String groups=groupsToDo.getText().toString().trim();

                    String sql="insert into ItemDb(item,hours,minutes,groups,datetime,isdone) values('"+item+"','"+weight+"','"+number+"','"+groups+"','"+this.now_time+"','"+4+"')";
                    db.execSQL(sql);
                    Toast.makeText(this.getActivity(), "saved", Toast.LENGTH_LONG).show();
                    itemName.setText(null);
                    weightToDo.setText(null);
                    numberToDo.setText(null);
                    groupsToDo.setText(null);
                }
                break;
            case R.id.strn_set_date:
                c = Calendar.getInstance();
                dialog=new DatePickerDialog(
                        this.getActivity(),this,
                        c.get(Calendar.YEAR), // 传入年份
                        c.get(Calendar.MONTH), // 传入月份
                        c.get(Calendar.DAY_OF_MONTH) // 传入天数
                );
                dialog.show();
                break;
        }
    }
    public void setOnPressListner(StrnPageListener mListener){
        if(mListener!=null)
            this.mListener=mListener;
    }
    public static StrnPage newInstance(String param1, String param2) {
        StrnPage fragment = new StrnPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public StrnPage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_strn_page, container, false);
        btnCancel=(ImageButton)view.findViewById(R.id.strn_cancel_btn);
        btnSave=(ImageButton)view.findViewById(R.id.strn_save_btn);
        btnSetDate=(ImageButton)view.findViewById(R.id.strn_set_date);
        itemName=(EditText)view.findViewById(R.id.text_item_strn);
        weightToDo=(EditText)view.findViewById(R.id.text_weight);
        numberToDo=(EditText)view.findViewById(R.id.text_number);
        groupsToDo=(EditText)view.findViewById(R.id.text_groups_strn);
        db=new ItemHelper(this.getActivity()).getWritableDatabase();
        btnCancel.setOnClickListener(this);
        btnSetDate.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        Time t=new Time();
        t.setToNow();
        this.now_time=t.year+"-"+(t.month+1)+"-"+t.monthDay;
        return view;
    }
    public void onDateSet(DatePicker dp, int year,int month, int dayOfMonth) {
        this.now_time=year+"-"+(month+1)+"-"+dayOfMonth;
        Toast.makeText(this.getActivity(),this.now_time, Toast.LENGTH_LONG).show();
    }
    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (StrnPageListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
