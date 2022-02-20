package com.example.final_finaaaal;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CaptionImagesAdapter extends RecyclerView.Adapter<CaptionImagesAdapter.ViewHolder>{
    List<Room> rooms;
    Context context;
    public CaptionImagesAdapter(List<Room> rooms,Context context){
        this.rooms=rooms;
        this.context=context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v=(CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new ViewHolder(v);
    }

  /*  @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        CardView cardView=holder.cardView;
        Button btn = (Button)cardView.findViewById(R.id.btn);
        TextView txt=(TextView)cardView.findViewById(R.id.card_view_textView);
        txt.setText(rooms[position].getName());
        TextView txt2=(TextView)cardView.findViewById(R.id.card_view_textView2);
        txt2.setText(rooms[position].description);

    }*/
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Room room= rooms.get(position);
        CardView cardView=holder.cardView;
        TextView txt=(TextView)cardView.findViewById(R.id.card_view_textView);
        txt.setText(rooms.get(position).room_type);
        TextView txt2=(TextView)cardView.findViewById(R.id.card_view_textView2);
        txt2.setText(rooms.get(position).descreption);
        cardView.setOnClickListener(v -> {

           Intent intent=new Intent(context,Userinfo_rec.class);
            String strRoomId=room.room_id+"";
            intent.putExtra("roomId",strRoomId);
            intent.putExtra("price",room.price);


          context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        public ViewHolder (CardView cardView){
            super(cardView);
            this.cardView=cardView;
        }
    }
}
