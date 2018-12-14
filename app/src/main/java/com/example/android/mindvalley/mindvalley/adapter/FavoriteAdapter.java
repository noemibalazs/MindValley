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
import com.example.android.mindvalley.mindvalley.room.PinEntity;
import com.example.android.mindvalley.mindvalley.ui.FavoriteDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private Context mContext;
    private List<PinEntity> entities;

    public FavoriteAdapter(Context context, List<PinEntity> entities){
        this.mContext = context;
        this.entities = entities;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pin_cover, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {

        PinEntity entity = entities.get(position);

        final String id = entity.getId();
        final String name = entity.getName();
        final String profile = entity.getProfile();
        final String cover = entity.getImage();

        Picasso.get()
                .load(cover)
                .error(R.drawable.cover)
                .placeholder(R.drawable.cover)
                .into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, FavoriteDetail.class);
                intent.putExtra(FavoriteDetail.ID, id);
                intent.putExtra(FavoriteDetail.NAME, name);
                intent.putExtra(FavoriteDetail.PROFILE, profile);
                intent.putExtra(FavoriteDetail.COVER, cover);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (entities == null){
            return 0;
        }
        return entities.size();
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;

        public FavoriteViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.favorite_pin);
        }
    }
}
