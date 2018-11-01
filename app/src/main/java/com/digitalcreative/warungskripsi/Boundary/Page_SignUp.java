package com.digitalcreative.warungskripsi.Boundary;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.digitalcreative.warungskripsi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class Page_SignUp extends Fragment {
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    EditText regUsername, regEmail, regNama, regAlamat, regPassword;
    Button btn_register, btn_login;
    String getUsername, getEmail, getNama, getAlamat, getPassword;

    public Page_SignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page_sign_up, container, false);
        //Init
        sayHelloboys(view);

        //Set Value
        //talkTothem();

        //Get Value
        //getFromThem();

        //Actions
        doitBoys();
        return view;
    }

    private void doitBoys() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectScreen();
            }
        });
    }

    private void sayHelloboys(View view) {
        //Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        //Variable
        regUsername = view.findViewById(R.id.input_username);
        regEmail = view.findViewById(R.id.input_email);
        regNama = view.findViewById(R.id.input_nama);
        regAlamat = view.findViewById(R.id.input_alamat);
        regPassword = view.findViewById(R.id.input_password);

        //Button
        btn_register = view.findViewById(R.id.btn_register2);
        btn_login = view.findViewById(R.id.btn_login2);
    }

    private void redirectScreen() {
        Log.d(TAG, "Redirecting to login screen.");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new Page_Login())
                .addToBackStack(null).commit();
    }

}
