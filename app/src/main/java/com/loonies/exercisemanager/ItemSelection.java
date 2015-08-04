package com.loonies.exercisemanager;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class ItemSelection extends Fragment implements OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnPressListener mListener;

    private ImageButton btnL;
    private ImageButton btnR;

    public interface OnPressListener {
        public void onPressL();
        public void onPressR();
    }


    public static ItemSelection newInstance(String param1, String param2) {
        ItemSelection fragment = new ItemSelection();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ItemSelection() {
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
        View view=inflater.inflate(R.layout.fragment_item_selection, container, false);
        btnL=(ImageButton) view.findViewById(R.id.id_btn_strn);
        btnR=(ImageButton) view.findViewById(R.id.id_btn_oxy);
        btnR.setBackgroundResource(R.drawable.oxy_btn_onclick);
        btnL.setOnClickListener(this);
        btnR.setOnClickListener(this);
        return view;
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.id_btn_oxy:
                if(mListener!=null) {
                    mListener.onPressR();
                    btnL.setBackgroundResource(R.drawable.strn_btn);
                    btnR.setBackgroundResource(R.drawable.oxy_btn_onclick);
                }
                break;
            case R.id.id_btn_strn:
                if(mListener!=null) {
                    mListener.onPressL();
                    btnL.setBackgroundResource(R.drawable.strn_btn_onclick);
                    btnR.setBackgroundResource(R.drawable.oxy_btn);
                }
                break;
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void setPressListener(OnPressListener mListener) {
        if (mListener != null) {
            this.mListener=mListener;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnPressListener) activity;
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
