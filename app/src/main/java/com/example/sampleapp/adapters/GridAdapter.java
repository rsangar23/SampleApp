package com.example.sampleapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sampleapp.models.GridItem;
import com.example.sampleapp.utils.ImageLoadTask;
import com.example.sampleapp.R;
import com.example.sampleapp.activities.SampleCell;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    ArrayList<GridItem> gridItems;
    Context context;

    public GridAdapter(ArrayList<GridItem> gridItems, Context context) {
        this.gridItems = gridItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return gridItems.size();
    }

    @Override
    public Object getItem(int position) {
        return gridItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder{
        TextView author;
        ImageView img;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grid_item, parent, false);

            viewHolder.author = convertView.findViewById(R.id.txt);
            viewHolder.img = convertView.findViewById(R.id.icon);

            convertView.setTag(viewHolder);

            final GridItem gridItem = gridItems.get(position);

            viewHolder.author.setText(gridItem.getAuthor());

            ImageLoadTask imageLoadTask = new ImageLoadTask(gridItem.getImage(), viewHolder.img);
            imageLoadTask.execute();

            viewHolder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, SampleCell.class);
                    i.putExtra("image", gridItem.getImage());
                    i.putExtra("author", gridItem.getAuthor());
                    context.startActivity(i);
                }
            });

            viewHolder.author.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, SampleCell.class);
                    i.putExtra("image", gridItem.getImage());
                    i.putExtra("author", gridItem.getAuthor());
                    context.startActivity(i);
                }
            });

        }

        return convertView;
    }
}
