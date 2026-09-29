package thanhdnh.ueh.edu.article_app;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public class UserData {
  public static UserList data;
  private Context context;
  private GridView gridview;

  public UserData(Context context, GridView gridview) {
    this.context = context;
    this.gridview = gridview;
  }

  public static UserProfile getUserFromId(int id) {
    for (int i = 0; i < data.getUsers().size(); i++)
      if (data.getUsers().get(i).getId() == id)
        return data.getUsers().get(i);
    return null;
  }

  // tai danh sach user tu url, co thanh tien trinh (downloadWithProgress thay cho downloadFile)
  public void loadData(String url, ProgressBar progressBar) {
    Handler mainHandler = new Handler(Looper.getMainLooper());

    Downloader.downloadWithProgress(url, mainHandler, context.getCacheDir(), progressBar, new Downloader.DownloadListener() {
      @Override
      public void onFinished(File file) {
        Gson gson = new Gson();
        data = gson.fromJson(readText(file), (Type) UserList.class);
        UserAdapter adapter = new UserAdapter(data.getUsers(), context);
        gridview.setAdapter(adapter);
      }
    });
  }

  public String readText(File file) {
    BufferedReader reader = null;
    try {
      InputStream stream = new FileInputStream(file);
      reader = new BufferedReader(new InputStreamReader(stream));
      StringBuffer buffer = new StringBuffer();
      String line = "";
      while ((line = reader.readLine()) != null) {
        buffer.append(line + "\n");
      }
      return buffer.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return reader.toString();
  }
}
