package com.example.sampleapp.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.sampleapp.models.GridItem;
import com.example.sampleapp.activities.MainActivity;
import com.example.sampleapp.adapters.GridAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetData extends AsyncTask<Void, Void, Void> {
   String data = "";
    int id;
    String image_url;
    String name;
    GridItem gridItem;
   // String str = "";
   String getData = "";
   Context context;
//    ArrayList<String> images;
//    ArrayList<String> author;
    ArrayList<GridItem> gridList;

    public GetData(Context context) {
        this.context = context;
    }


    @Override
    protected Void doInBackground(Void... voids) {

//        images = new ArrayList<>();
//        author = new ArrayList<>();

        try {
            URL url = new URL(WebServer.BASE_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONArray jsonArray = new JSONArray(data);
            gridList = new ArrayList<GridItem>();
            for(int i=0; i<jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                // str = "Id :" + jsonObject.get("id") + "\n" +
                //         "Author :" + jsonObject.get("author");

                id = jsonObject.getInt("id");
                name = jsonObject.getString("author");

//                author.add(jsonObject.getString("author"));

                image_url = "https://i.picsum.photos/id/"+Integer.toString(jsonObject.getInt("id"))+"/300/300.jpg";

//                images.add(image_url);
                Log.d("img", "doInBackground: " + image_url);

                gridItem = new GridItem();
                gridItem.setImage(image_url);
                gridItem.setAuthor(name);
                gridList.add(gridItem);

                Log.d("author", "doInBackground: " + jsonObject.getString("author"));

//                getData = getData + image_url;
            }



//            for (GridItem item: gridList) {
//                item = gridItem;
//
//            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

//        MainActivity.textView.setText(getData);
//        downloadfile(getData, MainActivity.icon);


        GridAdapter gridAdapter = new GridAdapter(gridList, context);

        MainActivity.simpleGrid.setAdapter(gridAdapter);

    }
}
