package com.example.sampleapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sampleapp.utils.ImageLoadTask;
import com.example.sampleapp.R;

public class SampleCell extends AppCompatActivity {

    ImageView img;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_cell);

        img = findViewById(R.id.img_detail);
        tv = findViewById(R.id.txt_detail);

        Intent i = getIntent();

        String img_url = i.getStringExtra("image");

        ImageLoadTask imageLoadTask = new ImageLoadTask(img_url, img);
        imageLoadTask.execute();

        String txt = i.getStringExtra("author");
        tv.setText(txt);

    }
}
