package com.example.phamm.httpurlconnection_with_asynctask_demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by phamm on 7/25/2017.
 */

public class DownLoadImage extends AsyncTask<String,Void,Bitmap> {
    private ImageView imageView;
    public DownLoadImage(ImageView imageView){
        this.imageView = imageView;
    }
    @Override
    protected Bitmap doInBackground(String... strings) {
        String imageUrl = strings[0];
        InputStream in = null;
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            in = httpConn.getInputStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap !=null){
            imageView.setImageBitmap(bitmap);
        }else{
            Log.e("MyMessage", "Failed to fetch data!");
        }
    }
}
