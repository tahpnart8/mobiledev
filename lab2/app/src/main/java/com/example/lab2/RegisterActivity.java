package com.example.lab2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Man hinh Register (Form dang ky) - chi lam layout, chua xu ly su kien nut bam.
 * Giao dien duoc khai bao trong res/layout/activity_register.xml, dung GridLayout.
 */
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}
