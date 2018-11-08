package com.digitalcreative.warungskripsi.Boundary;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.digitalcreative.warungskripsi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class Page_Login extends Fragment {
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
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

        cek_login_user();

        //Set Value
        //talkTothem();

        //Actions
        doitBoys();

        return view;
    }

    private void cek_login_user(){
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Toast.makeText(getContext(),"Autentikasi berhasil!", Toast.LENGTH_SHORT).show();

                    Bundle bundle = new Bundle();
                    bundle.putString("id_mahasiswa", user.getUid());

                    BookingHere getData = new BookingHere();
                    getData.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container, getData)
                            .addToBackStack(null)
                            .commit();

                }
                else{
                    Toast.makeText(getContext(),"Autentikasi gagal!", Toast.LENGTH_SHORT).show();
                }
            }
        };
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

        if(TextUtils.isEmpty(getemail)){
            Toast.makeText(getContext(),"Silahkan masukkan email anda!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(getpass)){
            Toast.makeText(getContext(),"Silahkan masukkan kata sandi dengan benar!", Toast.LENGTH_SHORT).show();
            return;
        }
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
        //Get Value
        getFromThem();

        //Check Auth Here
        firebaseAuth.signInWithEmailAndPassword(getemail, getpass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(),"Autentikasi berhasil!", Toast.LENGTH_SHORT).show();

                            FirebaseUser user = firebaseAuth.getCurrentUser();

                            Bundle bundle = new Bundle();
                            bundle.putString("id_mahasiswa", user.getUid());

                            BookingHere getData = new BookingHere();
                            getData.setArguments(bundle);

                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.container, getData)
                                    .addToBackStack(null)
                                    .commit();
                        }else{
                            Toast.makeText(getContext(),"Autentikasi gagal!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mAuthListener != null){
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
