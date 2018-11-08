package com.digitalcreative.warungskripsi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.digitalcreative.warungskripsi.Boundary.BookingHere;
import com.digitalcreative.warungskripsi.Boundary.Page_Login;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Page_Login fragment =  new Page_Login();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, fragment).commit();

    }
}
