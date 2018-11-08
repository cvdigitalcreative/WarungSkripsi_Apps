package com.digitalcreative.warungskripsi.Boundary;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.digitalcreative.warungskripsi.ModelData.Mahasiswa;
import com.digitalcreative.warungskripsi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
                goRegister();
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
        myRef = firebaseDatabase.getReference("mahasiswa");

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

    private void goRegister(){
        getEmail = regEmail.getText().toString();
        getPassword= regPassword.getText().toString();
        getNama = regNama.getText().toString();
        getAlamat = regAlamat.getText().toString();

        if(TextUtils.isEmpty(getEmail)){
            Toast.makeText(getContext(),"Silahkan masukkan username anda!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(getPassword)){
            Toast.makeText(getContext(),"Silahkan masukkan kata sandi dengan benar!", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(getEmail, getPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(),"Register Berhasil!", Toast.LENGTH_SHORT).show();

                            String uid = firebaseAuth.getUid();

                            Mahasiswa mahasiswa = new Mahasiswa();
                            mahasiswa.setNama(getNama);
                            mahasiswa.setEmail(getEmail);
                            mahasiswa.setAlamat(getAlamat);

                            myRef.child(uid).child("informasi").setValue(mahasiswa);

                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.container, new Page_Login())
                                    .addToBackStack(null).commit();
                        }
                        else{
                            Toast.makeText(getContext(),"Register Gagal!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
