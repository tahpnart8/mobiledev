package com.example.lab5;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    GridLayout board;

    ArrayList<Student> students = new ArrayList<>();

    // nut SV dang o che do focus, dung de biet to mau cho nut nao
    Button focusedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Lab 5 - Student Board");
        setSupportActionBar(toolbar);

        // chua noi dung xuong duoi thanh trang thai
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main),
                new OnApplyWindowInsetsListener() {
                    @Override
                    public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                        Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                        v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
                        return insets;
                    }
                });

        board = findViewById(R.id.board);

        addStudent(new Student("SV001", "Nguyen Van An", 20));
        addStudent(new Student("SV002", "Tran Thi Binh", 21));
        addStudent(new Student("SV003", "Le Van Cuong", 20));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuNew) {
            showNewStudentDialog();
            return true;
        }
        if (id == R.id.menuSelectColor) {
            showColorPickerDialog();
            return true;
        }
        if (id == R.id.menuAbout) {
            showAboutDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // tao Button moi cho sinh vien roi gan vao Board
    private void addStudent(final Student student) {
        students.add(student);

        final Button button = new Button(this);
        button.setText(student.getId());
        button.setAllCaps(false);
        button.setTextSize(15);
        button.setTextColor(ContextCompat.getColor(this, R.color.textDark));
        button.setBackgroundResource(R.drawable.sv_btn_bg);
        button.setFocusableInTouchMode(true);

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = dp(56);
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.setMargins(dp(5), dp(5), dp(5), dp(5));
        button.setLayoutParams(params);

        // lay focus ngay luc cham xuong, de lan cham dau tien van mo duoc man chi tiet
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.requestFocus();
                }
                return false;
            }
        });

        button.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    focusedButton = button;
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudentDetailActivity.class);
                intent.putExtra(StudentDetailActivity.EXTRA_STUDENT, student);
                startActivity(intent);
            }
        });

        board.addView(button);
    }

    private void showNewStudentDialog() {
        View content = getLayoutInflater().inflate(R.layout.dialog_new_student, null);
        final EditText edtId = content.findViewById(R.id.edtId);
        final EditText edtName = content.findViewById(R.id.edtName);
        final EditText edtAge = content.findViewById(R.id.edtAge);
        edtId.setText(String.format(Locale.US, "SV%03d", students.size() + 1));

        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Thêm sinh viên")
                .setView(content)
                .setPositiveButton("Thêm", null)
                .setNegativeButton("Huỷ", null)
                .create();
        dialog.show();

        // tu xu ly nut Them de nhap sai thi dialog khong bi dong
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString().trim();
                String name = edtName.getText().toString().trim();
                String age = edtAge.getText().toString().trim();

                if (id.isEmpty() || name.isEmpty() || age.isEmpty()) {
                    toast("Vui lòng nhập đủ mã SV, họ tên và tuổi");
                    return;
                }
                addStudent(new Student(id, name, Integer.parseInt(age)));
                toast("Đã thêm " + id);
                dialog.dismiss();
            }
        });
    }

    private void showColorPickerDialog() {
        Button target = focusedButton;
        if (target == null && board.getChildCount() > 0) {
            // khong nut nao dang focus thi to cho nut cuoi cung
            target = (Button) board.getChildAt(board.getChildCount() - 1);
        }
        if (target == null) {
            toast("Chưa có nút SV nào, hãy bấm New để thêm");
            return;
        }
        final Button colorTarget = target;

        View content = getLayoutInflater().inflate(R.layout.dialog_color_picker, null);
        TextView tvTarget = content.findViewById(R.id.tvColorTarget);
        tvTarget.setText("Đang chọn màu cho nút " + colorTarget.getText());
        GridLayout grid = content.findViewById(R.id.colorGrid);

        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Select color")
                .setView(content)
                .setNegativeButton("Đóng", null)
                .create();

        int[] colors = {
                Color.parseColor("#FFFFFF"), Color.parseColor("#CCFBF1"),
                Color.parseColor("#BFDBFE"), Color.parseColor("#BBF7D0"),
                Color.parseColor("#FEF08A"), Color.parseColor("#FED7AA"),
                Color.parseColor("#FECACA"), Color.parseColor("#E9D5FF"),
                Color.parseColor("#E2E8F0"), Color.parseColor("#99F6E4"),
                Color.parseColor("#A5B4FC"), Color.parseColor("#F9A8D4")
        };

        for (int i = 0; i < colors.length; i++) {
            final int color = colors[i];
            View swatch = new View(this);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = dp(52);
            params.height = dp(52);
            params.setMargins(dp(6), dp(6), dp(6), dp(6));
            swatch.setLayoutParams(params);

            swatch.setBackgroundResource(R.drawable.color_swatch);
            swatch.setBackgroundTintList(ColorStateList.valueOf(color));

            swatch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    colorTarget.setBackgroundTintList(ColorStateList.valueOf(color));
                    dialog.dismiss();
                }
            });

            grid.addView(swatch);
        }

        dialog.show();
    }

    private void showAboutDialog() {
        new AlertDialog.Builder(this)
                .setView(getLayoutInflater().inflate(R.layout.dialog_about, null))
                .setPositiveButton("OK", null)
                .show();
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
