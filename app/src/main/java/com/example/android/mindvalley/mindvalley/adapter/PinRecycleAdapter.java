package com.example.android.mindvalley.mindvalley.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.mindvalley.R;
import com.example.android.mindvalley.mindvalley.model.Pin;
import com.example.android.mindvalley.mindvalley.model.ProfileImage;
import com.example.android.mindvalley.mindvalley.model.Urls;
import com.example.android.mindvalley.mindvalley.model.User;
import com.example.android.mindvalley.mindvalley.ui.PinterestDetail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PinRecycleAdapter extends RecyclerView.Adapter<PinRecycleAdapter.PinViewHolder>{

    private Context mContext;
    private List<Pin> pins;

    public PinRecycleAdapter(Context context, ArrayList<Pin> list){
        mContext= context;
        pins = list;
    }

    @NonNull
    @Override
    public PinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pin_cover, parent, false);
        return new PinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PinViewHolder holder, int position) {
        final Pin pin = pins.get(position);

        final String pinId = pin.getPinId();
        User user = pin.getUser();
        Urls urls = pin.getUrls();

        final String name = user.getUserName();
        ProfileImage profileImage = user.getProfileImage();
        final String smallImage = profileImage.getSmall();

        final String regularPin = urls.getRegular();

        Picasso.get()
                .load(regularPin)
                .error(R.drawable.cover)
                .placeholder(R.drawable.cover)
                .into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PinterestDetail.class);
                intent.putExtra(PinterestDetail.ID, pinId);
                intent.putExtra(PinterestDetail.NAME, name);
                intent.putExtra(PinterestDetail.PROFILE, smallImage);
                intent.putExtra(PinterestDetail.IMAGE, regularPin);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (pins == null) { return  0;}
        return pins.size();
    }

    public void bindData(ArrayList<Pin> pin){
        pins = pin;
        notifyDataSetChanged();
    }

    class PinViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;

        public PinViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.favorite_pin);
        }
    }
}