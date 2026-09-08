package com.example.lab4;

import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        WebView webView = findViewById(R.id.webView);
        webView.loadDataWithBaseURL(null, buildHtml(), "text/html", "UTF-8", null);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    // Dung chuoi HTML dang bang tu danh sach nhan vien
    private String buildHtml() {
        List<Employee> employees = EmployeeManager.getAll();
        StringBuilder html = new StringBuilder();
        html.append("<html><head><meta charset='UTF-8'><style>")
                .append("body{font-family:sans-serif;margin:0;padding:8px;color:#1E293B;}")
                .append("table{border-collapse:collapse;width:100%;}")
                .append("th,td{border:1px solid #CBD5E1;padding:8px;text-align:left;font-size:14px;}")
                .append("th{background:#0F766E;color:#FFFFFF;}")
                .append("tr:nth-child(even){background:#F1F5F9;}")
                .append("p{color:#64748B;text-align:center;margin-top:24px;}")
                .append("</style></head><body>");

        if (employees.isEmpty()) {
            html.append("<p>").append(getString(R.string.empty_list)).append("</p>");
        } else {
            html.append("<table><tr>")
                    .append("<th>name</th><th>dob</th><th>gender</th><th>hobby</th><th>elevel</th>")
                    .append("</tr>");
            for (Employee e : employees) {
                html.append("<tr>")
                        .append("<td>").append(TextUtils.htmlEncode(e.getName())).append("</td>")
                        .append("<td>").append(e.getDob()).append("</td>")
                        .append("<td>").append(getString(e.isGender() ? R.string.male : R.string.female)).append("</td>")
                        .append("<td>").append(e.getHobby()).append("</td>")
                        .append("<td>").append(e.getElevel()).append("</td>")
                        .append("</tr>");
            }
            html.append("</table>");
        }

        return html.append("</body></html>").toString();
    }
}
