package com.loonies.exercisemanager;

import java.util.Date;
import android.app.Activity;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.loonies.exercisemanager.data.ItemHelper;

import java.text.SimpleDateFormat;
import java.util.Date;



public class StrnPage extends Fragment implements OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private SQLiteDatabase db;
    private ImageButton btnCancel;
    private ImageButton btnSave;
    private ImageButton btnSetDate;
    private EditText itemName;
    private EditText weightToDo;
    private EditText numberToDo;
    private EditText groupsToDo;


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
                    Time t=new Time();
                    t.setToNow();
                    String now_time=t.year+"-"+t.month+"-"+t.monthDay;
                    String sql="insert into ItemDb(item,hours,minutes,groups,datetime,isdone) values('"+item+"','"+weight+"','"+number+"','"+groups+"','"+now_time+"','"+4+"')";
                    db.execSQL(sql);
                    Toast.makeText(this.getActivity(), "saved", Toast.LENGTH_LONG).show();
                    itemName.setText(null);
                    weightToDo.setText(null);
                    numberToDo.setText(null);
                    groupsToDo.setText(null);
                }
                break;
            case R.id.strn_set_date:
                if(mListener!=null){
                }
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
        return view;
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
