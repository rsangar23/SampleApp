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
    private String data = "";
    private String image_url;
    private String name;
    private GridItem gridItem;
    Context context;

    ArrayList<GridItem> gridList;

    public GetData(Context context) {
        this.context = context;
    }


    @Override
    protected Void doInBackground(Void... voids) {

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

                name = jsonObject.getString("author");

                image_url = "https://i.picsum.photos/id/"+Integer.toString(jsonObject.getInt("id"))+"/300/300.jpg";

                Log.d("img", "doInBackground: " + image_url);

                gridItem = new GridItem();
                gridItem.setImage(image_url);
                gridItem.setAuthor(name);
                gridList.add(gridItem);

                Log.d("author", "doInBackground: " + jsonObject.getString("author"));

            }

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

        GridAdapter gridAdapter = new GridAdapter(gridList, context);

        MainActivity.simpleGrid.setAdapter(gridAdapter);

    }
}
