package com.example.sampleapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.sampleapp.utils.GetData;
import com.example.sampleapp.R;

public class MainActivity extends AppCompatActivity {

    public static GridView simpleGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleGrid = findViewById(R.id.simpleGridView);

        GetData get = new GetData(this);

	    get.execute();

    }
}
