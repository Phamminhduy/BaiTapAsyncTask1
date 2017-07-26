package com.example.phamm.httpurlconnection_with_asynctask_demo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView txtView;
    private Button btnimg,btnjson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        checkInternetConnection();
        btnimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean networkOk = checkInternetConnection();
               if(!networkOk){
                   return ;
               }
                String imageUrl = "https://techmaster.vn/fileman/Uploads/ImageBlog/hoc-lap-trinh-android-kiem-tien-30042016-1.jpg";
                DownLoadImage task = new DownLoadImage(imageView);
                task.execute(imageUrl);
            }
        });

    }
    public void AnhXa(){
        imageView = (ImageView) findViewById(R.id.imageView);
        txtView   = (TextView) findViewById(R.id.textView);
        btnimg    = (Button) findViewById(R.id.buttonDownloand);
        btnjson   = (Button) findViewById(R.id.buttondownloandJson);
    }

    public boolean checkInternetConnection(){
        //lấy bộ quản lý kết nối
        ConnectivityManager connManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        //thông tin mạng đang kích hoạt
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if(networkInfo == null){
            Toast.makeText(this, "No default network is currently active ", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!networkInfo.isConnected()){
            Toast.makeText(this, "Network is not connected", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!networkInfo.isAvailable()){
            Toast.makeText(this, "Network not available", Toast.LENGTH_SHORT).show();
            return false;
        }
        Toast.makeText(this, "Network OK", Toast.LENGTH_SHORT).show();
        return true;
    }
}
