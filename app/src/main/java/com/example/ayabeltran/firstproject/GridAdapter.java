package com.example.ayabeltran.firstproject;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.*;
import java.util.Base64;

/**
 * Created by ayabeltran on 01/02/2018.
 */


public class GridAdapter extends RecyclerView.Adapter <GridAdapter.MyViewHolder> {

    private ArrayList<ImgRepo> places = new ArrayList<>();
    private Context context;

    public GridAdapter(ArrayList<ImgRepo> places, Context context) {
        this.places = places;
        this.context = context;
    }
    public ArrayList<ImgRepo> getPlaces() {
        return this.places;
    }


    @Override
    public GridAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater gInflater = LayoutInflater.from(context);
        View view1 = gInflater.inflate(R.layout.activity_grid_view_layout, parent, false);
        GridAdapter.MyViewHolder holder = new GridAdapter.MyViewHolder(view1);
        return holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView des;
        ImageView photo;
        ImgRepo selectedPlace;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.photo = itemView.findViewById(R.id.imageViewGrid);
            this.name = itemView.findViewById(R.id.textViewGrid);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent preview = new Intent(context, ListDisplay.class);
//                    preview.putExtra("Key", selectedPlace.getImgname());
//                    preview.putExtra("Key2", selectedPlace.getDesc());
//                    preview.putExtra("Key3", selectedPlace.getImgstring());
//                    context.startActivity(preview);
//                }
//            });
        }
    }

    @Override
    public void onBindViewHolder(GridAdapter.MyViewHolder holder, int position) {

        //        getting the original photo from the list
        String originalPhoto = places.get(position).getImgstring();

//        converting the photo bytes to usable image
//        Bitmap decodedPhoto = BitmapFactory.decodeByteArray(originalPhoto, 0, originalPhoto.length);

        final String pureBase64Encoded = originalPhoto.substring(originalPhoto.indexOf(",")  + 1);
        final byte[] decodedBytes = android.util.Base64.decode(pureBase64Encoded, android.util.Base64.DEFAULT);


//        holder.photo.setImageBitmap(decodedBytes);
        holder.name.setText(places.get(position).getImgname());
//        holder.des.setText(places.get(position).getDesc());
        holder.selectedPlace = places.get(position);
        Glide.with(context).load(decodedBytes).into(holder.photo);
//        Toast.makeText(context, places.get(position).getPhoto().toString(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return places.size();
    }

}