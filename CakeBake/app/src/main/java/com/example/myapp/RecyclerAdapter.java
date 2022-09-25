package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public RecyclerAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem listItem=listItems.get(position);

        holder.textViewCakeId.setText(listItem.getCakeId());
        holder.textViewDifficulty.setText(listItem.getDifficulty());
        holder.textViewIngredients.setText(listItem.getIngredients());
        holder.textViewRecipe.setText(listItem.getRecipe());

        String imageString=listItem.getImageUrl();

        Picasso.get()
                .load(imageString)
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewCakeId;
        public TextView textViewDifficulty;
        public TextView textViewIngredients;
        public TextView textViewRecipe;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewCakeId=(TextView) itemView.findViewById(R.id.textViewCakeId);
            textViewDifficulty=(TextView) itemView.findViewById(R.id.textViewDifficulty);
            textViewIngredients=(TextView) itemView.findViewById(R.id.textViewIngredients);
            textViewRecipe=(TextView) itemView.findViewById(R.id.textViewRecipe);
            imageView=(ImageView) itemView.findViewById(R.id.ImageView);
        }
    }

}
