package com.example.lab06;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private TextView classNameTextView;
    private TextView teacherTextView;
    private TextView studentCountTextView;
    private LinearLayout studentListLayout;

    private ArrayList<ClassInfo> classList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initializeViews();

        createClassData();

        setupNavigationDrawer();

        showClass(classList.get(0));
    }

    private void initializeViews() {

        drawerLayout =
                findViewById(R.id.drawerLayout);

        navigationView =
                findViewById(R.id.navigationView);

        classNameTextView =
                findViewById(R.id.classNameTextView);

        teacherTextView =
                findViewById(R.id.teacherTextView);

        studentCountTextView =
                findViewById(R.id.studentCountTextView);

        studentListLayout =
                findViewById(R.id.studentListLayout);
    }

    private void createClassData() {

        classList = new ArrayList<ClassInfo>();

        // Tạo sinh viên cho lớp A01
        ArrayList<Student> studentsA01 =
                new ArrayList<Student>();

        studentsA01.add(
                new Student(
                        "SV001",
                        "Nguyen Van An",
                        "Male",
                        20
                )
        );

        studentsA01.add(
                new Student(
                        "SV002",
                        "Tran Thi Binh",
                        "Female",
                        20
                )
        );

        studentsA01.add(
                new Student(
                        "SV003",
                        "Le Van Cuong",
                        "Male",
                        21
                )
        );

        // Tạo lớp A01
        ClassInfo classA01 =
                new ClassInfo(
                        "A01",
                        "Nguyen Van Minh",
                        studentsA01
                );

        classList.add(classA01);


        // Tạo sinh viên cho lớp A02
        ArrayList<Student> studentsA02 =
                new ArrayList<Student>();

        studentsA02.add(
                new Student(
                        "SV004",
                        "Pham Van Dung",
                        "Male",
                        20
                )
        );

        studentsA02.add(
                new Student(
                        "SV005",
                        "Vo Thi Hoa",
                        "Female",
                        21
                )
        );

        studentsA02.add(
                new Student(
                        "SV006",
                        "Dang Van Khoa",
                        "Male",
                        20
                )
        );

        // Tạo lớp A02
        ClassInfo classA02 =
                new ClassInfo(
                        "A02",
                        "Tran Thi Lan",
                        studentsA02
                );

        classList.add(classA02);


        // Tạo sinh viên cho lớp A03
        ArrayList<Student> studentsA03 =
                new ArrayList<Student>();

        studentsA03.add(
                new Student(
                        "SV007",
                        "Nguyen Thi Mai",
                        "Female",
                        20
                )
        );

        studentsA03.add(
                new Student(
                        "SV008",
                        "Le Van Nam",
                        "Male",
                        21
                )
        );

        // Tạo lớp A03
        ClassInfo classA03 =
                new ClassInfo(
                        "A03",
                        "Le Van Thanh",
                        studentsA03
                );

        classList.add(classA03);


        // Tạo sinh viên cho lớp A04
        ArrayList<Student> studentsA04 =
                new ArrayList<Student>();

        studentsA04.add(
                new Student(
                        "SV009",
                        "Tran Van Phuc",
                        "Male",
                        20
                )
        );

        studentsA04.add(
                new Student(
                        "SV010",
                        "Nguyen Thi Thu",
                        "Female",
                        20
                )
        );

        studentsA04.add(
                new Student(
                        "SV011",
                        "Pham Van Son",
                        "Male",
                        21
                )
        );

        studentsA04.add(
                new Student(
                        "SV012",
                        "Hoang Thi Yen",
                        "Female",
                        20
                )
        );

        // Tạo lớp A04
        ClassInfo classA04 =
                new ClassInfo(
                        "A04",
                        "Pham Van Hung",
                        studentsA04
                );

        classList.add(classA04);
    }

    private void setupNavigationDrawer() {

        Toolbar toolbar =
                findViewById(R.id.mainToolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(
                        this,
                        drawerLayout,
                        toolbar,
                        R.string.open_drawer,
                        R.string.close_drawer
                );

        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(
                            @NonNull MenuItem item) {

                        int itemId =
                                item.getItemId();

                        if (itemId == R.id.menu_class_a01) {

                            showClass(classList.get(0));

                        } else if (itemId == R.id.menu_class_a02) {

                            showClass(classList.get(1));

                        } else if (itemId == R.id.menu_class_a03) {

                            showClass(classList.get(2));

                        } else if (itemId == R.id.menu_class_a04) {

                            showClass(classList.get(3));
                        }

                        drawerLayout.closeDrawer(
                                GravityCompat.START
                        );

                        return true;
                    }
                }
        );
    }

    private void showClass(ClassInfo classInfo) {

        classNameTextView.setText(
                "Class " + classInfo.getClassName()
        );

        teacherTextView.setText(
                "Homeroom teacher: "
                        + classInfo.getHomeroomTeacher()
        );

        studentCountTextView.setText(
                "Student count: "
                        + classInfo.getStudentCount()
        );

        studentListLayout.removeAllViews();

        ArrayList<Student> students =
                classInfo.getStudents();

        for (int i = 0; i < students.size(); i++) {

            addStudentView(students.get(i));
        }
    }

    private void addStudentView(
            Student student) {

        View studentView =
                getLayoutInflater().inflate(
                        R.layout.item_student,
                        studentListLayout,
                        false
                );

        TextView studentIdTextView =
                studentView.findViewById(
                        R.id.studentIdTextView
                );

        TextView studentNameTextView =
                studentView.findViewById(
                        R.id.studentNameTextView
                );

        studentIdTextView.setText(
                student.getStudentId()
        );

        studentNameTextView.setText(
                student.getName()
        );

        studentView.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        showStudentDialog(student);
                    }
                }
        );

        studentListLayout.addView(studentView);
    }

    private void showStudentDialog(
            Student student) {

        String message =
                "Student ID: "
                        + student.getStudentId()
                        + "\n\n"
                        + "Name: "
                        + student.getName()
                        + "\n\n"
                        + "Gender: "
                        + student.getGender()
                        + "\n\n"
                        + "Age: "
                        + student.getAge();

        new AlertDialog.Builder(this)
                .setTitle("Student Information")
                .setMessage(message)
                .setPositiveButton(
                        "OK",
                        null
                )
                .show();
    }
}