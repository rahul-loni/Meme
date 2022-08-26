package com.example.meme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.meme.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getMeme();
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              getMeme();
            }
        });
        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 shareMeme();
            }
        });
    }

    private void getMeme() {
        String url = "https://meme-api.herokuapp.com/gimme";
        binding.progress.setVisibility(View.VISIBLE);
        binding.memeImage.setVisibility(View.GONE);
        RequestQueue que= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String imgUrl=response.getString("url");
                            Glide.with(getApplicationContext()).load(imgUrl).into(binding.memeImage);
                            binding.progress.setVisibility(View.GONE);
                            binding.memeImage.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        que.add(jsonObjectRequest);
    }

    private void shareMeme() {
//        Bitmap image=getBitmapFromView(binding.memeImage);
//        sharImageAndText(image);
        Toast.makeText(this, "Bistare aaunxa", Toast.LENGTH_SHORT).show();
    }

//    private void sharImageAndText(Bitmap image) {
//        Uri uri=getImageToShare(image);
//        Intent intent=new Intent(Intent.ACTION_SEND);
//        intent.putExtra(Intent.EXTRA_STREAM,uri);
//        intent.setType("image/gif");
//        startActivity(Intent.createChooser(intent,"Share image Via"));
//    }
//
//    private Uri getImageToShare(Bitmap image) {
//        File imageFolder=new File(getCacheDir(),"images");
//        Uri uri=null;
//        try {
//            imageFolder.mkdir();
//            File file=new File(imageFolder,"birthday image gif");
//            FileOutputStream outputStream=new FileOutputStream(file);
//            image.compress(Bitmap.CompressFormat.PNG,100,outputStream);
//            outputStream.flush();
//            outputStream.close();
//            uri= FileProvider.getUriForFile(this,"com.example.meme.fileProvider",file);
//        } catch (FileNotFoundException e) {
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return uri;
//    }
//
//    private Bitmap getBitmapFromView(ImageView memeImage) {
//        Bitmap returnedBitmap= Bitmap.createBitmap(memeImage.getWidth(),memeImage.getHeight(),Bitmap.Config.ARGB_8888);
//        Canvas canvas= new Canvas(returnedBitmap);
//        Drawable background=memeImage.getBackground();
//        if (background !=null){
//            background.draw(canvas);
//        }else {
//            canvas.drawColor(Color.WHITE);
//
//        }
//        memeImage.draw(canvas);
//        return returnedBitmap;
//    }
}