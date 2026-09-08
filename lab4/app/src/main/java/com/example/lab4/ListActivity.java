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

        html.append("<html><head><meta charset='UTF-8'>")
                .append("<meta name='viewport' content='width=device-width, initial-scale=1'>")
                .append("<style>").append(css()).append("</style></head><body>");

        if (employees.isEmpty()) {
            html.append("<p class='empty'>").append(getString(R.string.empty_list)).append("</p>");
        } else {
            html.append("<p class='total'>")
                    .append(getString(R.string.total_employees, employees.size()))
                    .append("</p>");

            // co dinh be rong tung cot de bang khong tran ra ngoai man hinh
            html.append("<table><colgroup>")
                    .append("<col class='w-name'><col class='w-dob'><col class='w-gender'>")
                    .append("<col class='w-hobby'><col class='w-elevel'>")
                    .append("</colgroup>")
                    .append("<tr><th>name</th><th>dob</th><th>gender</th><th>hobby</th><th>elevel</th></tr>");

            for (Employee e : employees) {
                html.append("<tr>")
                        .append("<td>").append(TextUtils.htmlEncode(e.getName())).append("</td>")
                        .append("<td class='center nowrap'>").append(e.getDob()).append("</td>")
                        .append("<td class='center'>")
                        .append(getString(e.isGender() ? R.string.male : R.string.female)).append("</td>")
                        .append("<td>").append(e.getHobby()).append("</td>")
                        .append("<td class='center'>").append(e.getElevel()).append("</td>")
                        .append("</tr>");
            }
            html.append("</table>");
        }

        return html.append("</body></html>").toString();
    }

    private String css() {
        return "*{box-sizing:border-box;}"
                + "body{font-family:sans-serif;margin:0;padding:6px;color:#1E293B;background:#FFFFFF;}"
                + ".total{margin:0 0 10px 2px;font-size:13px;color:#64748B;}"
                + ".empty{margin-top:28px;text-align:center;color:#94A3B8;font-size:14px;}"
                + "table{width:100%;table-layout:fixed;border-collapse:separate;border-spacing:0;"
                + "border:1px solid #E2E8F0;border-radius:10px;overflow:hidden;font-size:12px;}"
                + "th{background:#0F766E;color:#FFFFFF;text-align:left;font-weight:600;padding:10px 5px;}"
                + "td{padding:10px 5px;border-top:1px solid #E2E8F0;vertical-align:middle;"
                + "word-break:break-word;}"
                + "tr:nth-child(even) td{background:#F8FAFC;}"
                + ".center{text-align:center;}"
                + ".nowrap{white-space:nowrap;}"
                + ".w-name{width:26%;}.w-dob{width:23%;}.w-gender{width:12%;}"
                + ".w-hobby{width:20%;}.w-elevel{width:19%;}";
    }
}
