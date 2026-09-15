package com.example.lab5;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StudentDetailActivity extends AppCompatActivity {

    public static final String EXTRA_STUDENT = "student";

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        Student student = (Student) getIntent().getSerializableExtra(EXTRA_STUDENT);
        if (student != null) {
            ((TextView) findViewById(R.id.tvId)).setText(student.getId());
            ((TextView) findViewById(R.id.tvName)).setText(student.getName());
            ((TextView) findViewById(R.id.tvAge)).setText(String.valueOf(student.getAge()));
        }

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
