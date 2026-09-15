package com.example.lab6;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    LinearLayout classList;
    TextView tvClassName, tvTeacher;
    TableLayout studentTable;

    ArrayList<ClassInfo> classes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        classList = findViewById(R.id.classList);
        tvClassName = findViewById(R.id.tvClassName);
        tvTeacher = findViewById(R.id.tvTeacher);
        studentTable = findViewById(R.id.studentTable);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Lab 6 - Danh sách lớp");
        setSupportActionBar(toolbar);

        // nut 3 gach cua Navigation Drawer, tu doi hinh khi ngan keo mo ra
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(ContextCompat.getColor(this, R.color.white));

        // chua noi dung xuong duoi thanh trang thai
        ViewCompat.setOnApplyWindowInsetsListener(drawerLayout, new OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
                return insets;
            }
        });

        createData();
        buildClassList();

        // mo len chon san lop dau tien
        showClass(0);
    }

    private void createData() {
        ArrayList<Student> a01 = new ArrayList<>();
        a01.add(new Student("SV01", "Nguyễn Văn An"));
        a01.add(new Student("SV02", "Lê Thị Bình"));
        a01.add(new Student("SV03", "Trần Văn Cường"));
        classes.add(new ClassInfo("A01", "Nguyễn Văn Minh", a01));

        ArrayList<Student> a02 = new ArrayList<>();
        a02.add(new Student("SV04", "Phạm Văn Dũng"));
        a02.add(new Student("SV05", "Võ Thị Hoa"));
        classes.add(new ClassInfo("A02", "Trần Thị Lan", a02));

        ArrayList<Student> a03 = new ArrayList<>();
        a03.add(new Student("SV06", "Đặng Văn Khoa"));
        a03.add(new Student("SV07", "Nguyễn Thị Mai"));
        a03.add(new Student("SV08", "Lê Văn Nam"));
        a03.add(new Student("SV09", "Hoàng Thị Yến"));
        classes.add(new ClassInfo("A03", "Lê Văn Thành", a03));

        ArrayList<Student> a04 = new ArrayList<>();
        a04.add(new Student("SV10", "Trần Văn Phúc"));
        a04.add(new Student("SV11", "Nguyễn Thị Thu"));
        classes.add(new ClassInfo("A04", "Phạm Văn Hùng", a04));
    }

    // do danh sach lop vao ngan keo
    private void buildClassList() {
        for (int i = 0; i < classes.size(); i++) {
            final int position = i;

            TextView row = (TextView) getLayoutInflater()
                    .inflate(R.layout.item_class, classList, false);
            row.setText(classes.get(i).getName());
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showClass(position);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            });

            classList.addView(row);
        }
    }

    // do thong tin lop dang chon ra khung noi dung
    private void showClass(int position) {
        ClassInfo classInfo = classes.get(position);

        tvClassName.setText("Lớp " + classInfo.getName());
        tvTeacher.setText("Chủ nhiệm: " + classInfo.getTeacher()
                + "  •  Sĩ số: " + classInfo.getStudents().size());

        // to sang lop dang chon trong ngan keo
        for (int i = 0; i < classList.getChildCount(); i++) {
            classList.getChildAt(i).setActivated(i == position);
        }

        // xoa cac dong cu, giu lai dong tieu de dau bang
        if (studentTable.getChildCount() > 1) {
            studentTable.removeViews(1, studentTable.getChildCount() - 1);
        }

        ArrayList<Student> students = classInfo.getStudents();
        for (int i = 0; i < students.size(); i++) {
            addStudentRow(students.get(i), i);
        }
    }

    private void addStudentRow(Student student, int position) {
        TableRow row = (TableRow) getLayoutInflater()
                .inflate(R.layout.item_student_row, studentTable, false);

        ((TextView) row.findViewById(R.id.tvStudentId)).setText(student.getId());
        ((TextView) row.findViewById(R.id.tvStudentName)).setText(student.getName());

        // dong chan to nen nhat cho de doc
        if (position % 2 == 1) {
            row.setBackgroundColor(0xFFF8FAFC);
        }

        studentTable.addView(row);
    }

    // bam Back khi ngan keo dang mo thi dong ngan keo truoc
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
