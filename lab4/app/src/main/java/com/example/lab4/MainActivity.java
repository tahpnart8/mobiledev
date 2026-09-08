package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtDob;
    CompoundButton swGender;
    Spinner spHobby;
    CheckBox cbHigh, cbMedium, cbLow;

    // danh sach nhan vien da dang ky, gui sang man hinh 2 qua Intent
    ArrayList<Employee> employees = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        edtDob = findViewById(R.id.edtDob);
        swGender = findViewById(R.id.swGender);
        spHobby = findViewById(R.id.spHobby);
        cbHigh = findViewById(R.id.cbHigh);
        cbMedium = findViewById(R.id.cbMedium);
        cbLow = findViewById(R.id.cbLow);

        // dropdown so thich, dung layout rieng cho chu to hon
        ArrayAdapter<CharSequence> hobbyAdapter = ArrayAdapter.createFromResource(
                this, R.array.hobby_array, R.layout.spinner_item);
        hobbyAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spHobby.setAdapter(hobbyAdapter);

        // go so lien tuc, tu chen dau / thanh dd/MM/yyyy
        edtDob.addTextChangedListener(new TextWatcher() {
            boolean editing = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editing) return;
                editing = true;

                String digits = s.toString().replaceAll("[^0-9]", "");
                if (digits.length() > 8) {
                    digits = digits.substring(0, 8);
                }
                StringBuilder text = new StringBuilder();
                for (int i = 0; i < digits.length(); i++) {
                    if (i == 2 || i == 4) {
                        text.append('/');
                    }
                    text.append(digits.charAt(i));
                }

                s.replace(0, s.length(), text.toString());
                edtDob.setSelection(text.length());
                editing = false;
            }
        });

        swGender.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                swGender.setText(getString(isChecked ? R.string.male : R.string.female));
            }
        });

        // 3 checkbox trinh do chi duoc tick 1 o
        cbHigh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbMedium.setChecked(false);
                    cbLow.setChecked(false);
                }
            }
        });

        cbMedium.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbHigh.setChecked(false);
                    cbLow.setChecked(false);
                }
            }
        });

        cbLow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbHigh.setChecked(false);
                    cbMedium.setChecked(false);
                }
            }
        });

        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearForm();
            }
        });

        findViewById(R.id.btnShow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra(ListActivity.EXTRA_EMPLOYEES, employees);
                startActivity(intent);
            }
        });
    }

    // ngay sinh go tay nen phai kiem tra dung dd/MM/yyyy va co that (chan 31/02/2000)
    private boolean isValidDob(String dob) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        format.setLenient(false);
        try {
            format.parse(dob);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private String getElevel() {
        if (cbHigh.isChecked()) return "High";
        if (cbMedium.isChecked()) return "Medium";
        if (cbLow.isChecked()) return "Low";
        return "";
    }

    private void register() {
        String name = edtName.getText().toString().trim();
        String dob = edtDob.getText().toString().trim();
        String elevel = getElevel();

        if (name.isEmpty()) {
            toast(R.string.msg_name_empty);
            return;
        }
        if (dob.isEmpty()) {
            toast(R.string.msg_dob_empty);
            return;
        }
        if (!isValidDob(dob)) {
            toast(R.string.msg_dob_invalid);
            return;
        }
        if (elevel.isEmpty()) {
            toast(R.string.msg_elevel_empty);
            return;
        }

        String hobby = spHobby.getSelectedItem().toString();
        employees.add(new Employee(name, dob, swGender.isChecked(), hobby, elevel));
        toast(R.string.msg_added);
        clearForm();
    }

    private void clearForm() {
        edtName.setText("");
        edtDob.setText("");
        swGender.setChecked(true);
        spHobby.setSelection(0);
        cbHigh.setChecked(false);
        cbMedium.setChecked(false);
        cbLow.setChecked(false);
    }

    private void toast(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }
}
