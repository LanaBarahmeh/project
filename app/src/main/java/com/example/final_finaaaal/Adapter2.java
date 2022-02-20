package com.example.final_finaaaal;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewHolder>{
    List<Room> rooms;
    Context context;


    //
    String dateIn;
    String dateOut;

    public Adapter2(List<Room> rooms,Context context,String dateIn,String dateOut){
        this.dateIn=dateIn;
        this.dateOut=dateOut;
        this.rooms=rooms;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v=(CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_search_cardiew,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Room room= rooms.get(position);
        System.out.println("kkkkkkkk: "+room.toString());
        CardView cardView=holder.cardView;
        ImageView imageView=(ImageView)cardView.findViewById(R.id.card_view_image);
        System.err.println("image view "+imageView);
        Glide.with(context).load(room.getRoom_photo()).into(imageView);



        TextView txt=(TextView)cardView.findViewById(R.id.card_view_textView);
        txt.setText(rooms.get(position).getRoom_type());
        cardView.setOnClickListener(v -> {

            Intent intent=new Intent(context,ReserveRoom.class);

            String strRoomId=room.room_id+"";
            intent.putExtra("roomId",strRoomId);

            intent.putExtra("roomtype",room.room_type);
            intent.putExtra("description",room.descreption);
            intent.putExtra("price",room.price);
            intent.putExtra("dateIn",dateIn);
            intent.putExtra("dateOut",dateOut);


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