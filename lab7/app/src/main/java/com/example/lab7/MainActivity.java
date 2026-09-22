package com.example.lab7;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Article> articles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Lab 8 - Bài viết");
        setSupportActionBar(toolbar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main),
                new OnApplyWindowInsetsListener() {
                    @Override
                    public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                        Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                        v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
                        return insets;
                    }
                });

        createArticles();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ArticleAdapter(this, articles));
    }

    // du lieu mau khoi tao thu cong, view ban dau = 0
    private void createArticles() {
        articles.add(new Article(
                "Không khí lạnh tăng cường, miền Bắc trời rét về đêm và sáng",
                "Một đợt không khí lạnh tăng cường được dự báo tràn xuống trong vài ngày tới, "
                        + "khiến nền nhiệt các tỉnh miền Bắc giảm sâu, trời rét về đêm và sáng sớm. "
                        + "Người dân được khuyến cáo giữ ấm, hạn chế ra ngoài vào khung giờ nhiệt độ "
                        + "xuống thấp nhất và theo dõi sát các bản tin thời tiết để chủ động ứng phó.",
                R.drawable.cover_weather));

        articles.add(new Article(
                "Nhiều tuyến đường nội đô ùn tắc giờ cao điểm do lượng xe tăng",
                "Lượng phương tiện đổ về khu vực trung tâm tăng mạnh vào giờ cao điểm khiến một số "
                        + "tuyến đường thường xuyên ùn ứ kéo dài. Lực lượng chức năng đã bố trí thêm "
                        + "người điều tiết giao thông tại các nút giao trọng điểm, đồng thời khuyến "
                        + "khích người dân sắp xếp giờ di chuyển hợp lý hoặc sử dụng phương tiện công cộng.",
                R.drawable.cover_traffic));

        articles.add(new Article(
                "Bác sĩ khuyến cáo tăng cường vận động khi thời tiết giao mùa",
                "Thời điểm giao mùa là lúc cơ thể dễ mệt mỏi và giảm sức đề kháng. Các bác sĩ khuyến "
                        + "cáo nên duy trì vận động nhẹ nhàng mỗi ngày, ăn uống đủ chất, ngủ đúng giờ "
                        + "và giữ ấm cơ thể để phòng tránh cảm cúm cùng các bệnh về đường hô hấp "
                        + "thường gặp trong giai đoạn chuyển mùa.",
                R.drawable.cover_health));
    }
}
