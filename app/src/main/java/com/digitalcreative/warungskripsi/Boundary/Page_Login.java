package com.digitalcreative.warungskripsi.Boundary;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.digitalcreative.warungskripsi.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class Page_Login extends Fragment {
    FirebaseAuth firebaseAuth;
    EditText email, pass;
    Button btn_registrasi, btn_login;
    String getemail, getpass;

    public Page_Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_page__login, container, false);

        //Init
        sayHelloboys(view);

        //Set Value
        //talkTothem();

        //Get Value
        getFromThem();

        //Actions
        doitBoys();

        return view;
    }

    private void doitBoys() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check Auth Here
                checkemail_instance();
            }
        });

        btn_registrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, new Page_SignUp())
                        .addToBackStack(null).commit();
            }
        });
    }

    private void getFromThem() {
        //get Edittext
        getemail = email.getText().toString();
        getpass = pass.getText().toString();
    }

    private void sayHelloboys(View view) {
        //Edittext
        email = view.findViewById(R.id.username);
        pass = view.findViewById(R.id.password);

        //Button
        btn_registrasi =  view.findViewById(R.id.btn_register);
        btn_login = view.findViewById(R.id.btn_login);

        //Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void checkemail_instance() {
        //Check Auth Here
        firebaseAuth.signInWithEmailAndPassword(getemail, getpass);
    }

}
