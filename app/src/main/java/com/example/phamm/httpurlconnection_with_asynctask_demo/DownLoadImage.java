package com.example.phamm.httpurlconnection_with_asynctask_demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by phamm on 7/25/2017.
 */

public class DownLoadImage extends AsyncTask<String,Void,Bitmap> {
    MainActivity activity = new MainActivity() ;
    private ImageView imageView;
    Bitmap bitmap = null;
    public DownLoadImage(ImageView imageView){
        this.imageView = imageView;
    }
    @Override
    protected Bitmap doInBackground(String... strings) {
        String imageUrl = strings[0];
        InputStream in = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            in = httpConn.getInputStream();
            bitmap = BitmapFactory.decodeStream(in);
            updateImage();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }
    private void updateImage(){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(bitmap);
            }
        });
    }
}

