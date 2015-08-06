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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OxyPage.OxyFragmentListener} interface
 * to handle interaction events.
 * Use the {@link OxyPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OxyPage extends Fragment implements OnClickListener,DatePickerDialog.OnDateSetListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Calendar c;
    private Dialog dialog;
    private String mParam1;
    private String mParam2;
    private SQLiteDatabase db;
    private ImageButton btnCancel;
    private ImageButton btnSave;
    private ImageButton btnSetDate;
    private EditText itemName;
    private EditText timeToLastH;
    private EditText timeToLastM;
    private EditText groupsTodo;
    private String now_time;


    public interface OxyFragmentListener {
        public void oxyPageInteractionSave();
        public void oxyPageInteractionCancel();
    }
    private OxyFragmentListener mListener;

    public void setOnPressListener(OxyFragmentListener mListener){
        if(mListener!=null)
            this.mListener=mListener;
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.oxy_cancel_btn:
                if(mListener!=null) {
                    mListener.oxyPageInteractionCancel();
                    itemName.setText(null);
                    timeToLastM.setText(null);
                    timeToLastH.setText(null);
                    groupsTodo.setText(null);
                }
                break;
            case R.id.oxy_save_btn:
                if(mListener!=null){
                    String item=itemName.getText().toString().trim();
                    String hours=timeToLastH.getText().toString().trim();
                    String minutes=timeToLastM.getText().toString().trim();
                    String groups=groupsTodo.getText().toString().trim();
                    //SimpleDateFormat format=new SimpleDateFormat("yyyy��MM��dd�� hh:mm:ss");
                    //String now_time=format.format(new Date());
                    String sql="insert into ItemDb(item,hours,minutes,groups,datetime,isdone) values('"+item+"','"+hours+"','"+minutes+"','"+groups+"','"+this.now_time+"','"+0+"')";
                    db.execSQL(sql);
                    Toast.makeText(this.getActivity(), "saved", Toast.LENGTH_LONG).show();
                    mListener.oxyPageInteractionSave();
                    itemName.setText(null);
                    timeToLastM.setText(null);
                    timeToLastH.setText(null);
                    groupsTodo.setText(null);
                }
                break;
            case R.id.oxy_set_date:
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
    public void onDateSet(DatePicker dp, int year,int month, int dayOfMonth) {
        this.now_time=year+"-"+(month+1)+"-"+dayOfMonth;
        Toast.makeText(this.getActivity(),this.now_time, Toast.LENGTH_LONG).show();
    }
    public static OxyPage newInstance(String param1, String param2) {
        OxyPage fragment = new OxyPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public OxyPage() {
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
        View view= inflater.inflate(R.layout.fragment_oxy_page, container, false);
        btnCancel=(ImageButton)view.findViewById(R.id.oxy_cancel_btn);
        btnSave=(ImageButton)view.findViewById(R.id.oxy_save_btn);
        btnSetDate=(ImageButton)view.findViewById(R.id.oxy_set_date);
        itemName=(EditText)view.findViewById(R.id.text_item);
        timeToLastH=(EditText)view.findViewById(R.id.text_hour);
        timeToLastM=(EditText)view.findViewById(R.id.text_minute);
        groupsTodo=(EditText)view.findViewById(R.id.text_groups_oxy);
        db=new ItemHelper(this.getActivity()).getWritableDatabase();
        btnSetDate.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        Time t=new Time();
        t.setToNow();
        this.now_time=t.year+"-"+(t.month+1)+"-"+t.monthDay;
        return view;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OxyFragmentListener) activity;
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
