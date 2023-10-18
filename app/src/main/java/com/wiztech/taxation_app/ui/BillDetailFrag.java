package com.wiztech.taxation_app.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wiztech.taxation_app.Entities.User;
import com.wiztech.taxation_app.LoginSession;
import com.wiztech.taxation_app.MainActivity;
import com.wiztech.taxation_app.R;

public class BillDetailFrag extends Fragment {

    private TextView etPhone,etEmail,etAddress,etPostalAddress,tv_name_value;
    private ImageView ivBack,ivLogout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //http://localhost:4200/app/bill/water-bill/33/true/false
        View view= inflater.inflate(R.layout.fragment_bill_detail, container, false);
        etPhone=view.findViewById(R.id.etPhone);
        etEmail=view.findViewById(R.id.etEmail);
        etAddress=view.findViewById(R.id.etAddress);
        etPostalAddress=view.findViewById(R.id.etPostalAddress);
        tv_name_value=view.findViewById(R.id.tv_name_value);
        ivBack=view.findViewById(R.id.ivBack);
        ivLogout=view.findViewById(R.id.ivLogout);

        User user= LoginSession.getUser(getActivity());
        tv_name_value.setText(""+user.getFullName());
        etEmail.setText(""+user.getEmail());
        etPhone.setText(""+user.getContact());
        etAddress.setText(""+user.getCnic());
        etPostalAddress.setText(""+user.getGenders());

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).onBackPressed();
            }
        });
        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).showAlert();
            }
        });


        return view;
    }




}