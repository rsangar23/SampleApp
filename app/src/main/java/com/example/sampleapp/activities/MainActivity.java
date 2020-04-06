package com.example.sampleapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.sampleapp.utils.GetData;
import com.example.sampleapp.R;

public class MainActivity extends AppCompatActivity {

   // public static TextView textView;
    public static GridView simpleGrid;

//   public static ImageView icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // textView = findViewById(R.id.txt);
        simpleGrid = findViewById(R.id.simpleGridView);

//        icon = findViewById(R.id.icon); // get the reference of ImageView
	    
        GetData get = new GetData(this);

	    get.execute();

    }
}
