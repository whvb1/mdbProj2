package com.example.pokedex.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.pokedex.R;
import com.example.pokedex.model.Pokemon;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private ArrayList<Pokemon> mData;
    RequestOptions option;

    public RecyclerViewAdapter(Context mContext, ArrayList<Pokemon> mData) {
        this.mContext = mContext;
        this.mData = mData;

        //Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background);

    }

    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //TODO: grid layout manager
        //View v = LayoutInflater.from(parent.getContext()).inflate(isProductViewAsList ? R.layout.product_row_layout_list : R.layout.product_row_layout_grid, null);

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pokemon_row_item,parent,false);
        //System.out.println("created view holder");
        MyViewHolder viewHolder = new MyViewHolder(view);
        //System.out.println("should have called MyViewHolder(view)");
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        //System.out.println(position);
        //System.out.println(mData.get(position).getAttack());
        //System.out.println(holder.txtAttack);
        holder.txtAttack.setText(Integer.toString(mData.get(position).getAttack()));
        //holder.txtAttackTitle.setText(mData.get(position).getAttack());
        holder.txtDefense.setText(Integer.toString(mData.get(position).getDefense()));
        holder.txtHP.setText(Integer.toString(mData.get(position).getHp()));
        holder.txtSpecies.setText(mData.get(position).getSpecies());
        holder.txtName.setText(mData.get(position).getName());
        holder.txtType1.setText(mData.get(position).getType1());
        holder.txtType2.setText(mData.get(position).getType2());
        //holder.txtType1.setBackgroundTintList(R.color.);


        Glide.with(mContext).load(String.format("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/%s.png", mData.get(position).getId())).apply(option).into(holder.imgThumbnail);


    }

    @Override
    public int getItemCount() { return mData.size(); }

    public void setData(ArrayList<Pokemon> pokemen) {
        this.mData = pokemen;
        this.notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtAttack;
        TextView txtDefense;
        TextView txtAttackTitle;
        TextView txtDefenseTitle;
        TextView txtHP;
        TextView txtHPTitle;
        TextView txtSpecies;
        TextView txtSpeciesTitle;

        TextView txtName;
        TextView txtType1;
        TextView txtType2;


        ImageView imgThumbnail;


        public MyViewHolder(View itemView) {
            super(itemView);
            //System.out.println("instantiating TextViews");
            //System.out.println(itemView.findViewById(R.id.txtAttack));

            txtAttack = itemView.findViewById(R.id.txtAttack);
            txtDefense = itemView.findViewById(R.id.txtDefense);
            txtAttackTitle = itemView.findViewById(R.id.txtAttTitle);
            txtDefenseTitle = itemView.findViewById(R.id.txtDefTitle);
            txtHP = itemView.findViewById(R.id.txtHP);
            txtHPTitle = itemView.findViewById(R.id.txtHPTitle);
            txtSpecies = itemView.findViewById(R.id.txtSpecies);
            txtSpeciesTitle = itemView.findViewById(R.id.txtSpeciesTitle);
            txtName = itemView.findViewById(R.id.txtName);
            txtType1 = itemView.findViewById(R.id.txtType1);
            txtType2 = itemView.findViewById(R.id.txtType2);

            imgThumbnail = itemView.findViewById(R.id.imgThumbnail);

        }
    }



}
