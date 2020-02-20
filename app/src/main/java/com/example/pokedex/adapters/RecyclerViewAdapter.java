package com.example.pokedex.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.pokedex.InfoActivity;
import com.example.pokedex.R;
import com.example.pokedex.model.Pokemon;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private ArrayList<Pokemon> mData;
    RequestOptions option;

    public static final int SPAN_COUNT_ONE = 1;
    public static final int SPAN_COUNT_THREE = 3;

    public static final int VIEW_TYPE_SMALL = 1;
    public static final int VIEW_TYPE_BIG = 2;

    private GridLayoutManager mLayoutManager;

    public RecyclerViewAdapter(Context mContext, ArrayList<Pokemon> mData, GridLayoutManager mLayoutManager) {
        this.mContext = mContext;
        this.mData = mData;
        this.mLayoutManager = mLayoutManager;

        //Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background);

    }

    @Override
    public int getItemViewType(int position) {
        int spanCount = mLayoutManager.getSpanCount();
        if (spanCount == SPAN_COUNT_ONE) {
            return VIEW_TYPE_BIG;
        } else {
            return VIEW_TYPE_SMALL;
        }
    }

    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //TODO: grid layout manager
        View view;
        if ( viewType == VIEW_TYPE_BIG) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_row_item, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_grid_item, parent, false);
        }
        return new MyViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        //big layout
        if (getItemViewType(position) == VIEW_TYPE_BIG) {
            holder.txtAttack.setText(Integer.toString(mData.get(position).getAttack()));
            holder.txtDefense.setText(Integer.toString(mData.get(position).getDefense()));
            holder.txtHP.setText(Integer.toString(mData.get(position).getHp()));
            holder.txtSpecies.setText(mData.get(position).getSpecies());
            holder.txtType1.setText(mData.get(position).getType1());
            holder.txtType2.setText(mData.get(position).getType2());
        }
        //small layout + big layout
        holder.txtName.setText(mData.get(position).getName());
        holder.btnInfo.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  Intent info = new Intent(holder.itemView.getContext(), InfoActivity.class);
                                                  info.putExtra("id",mData.get(position).getId());
                                                  info.putExtra("info",mData.get(position).toString());
                                                  holder.itemView.getContext().startActivity(info);
                                              }
                                          });

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

        ImageButton btnInfo;
        ImageView imgThumbnail;


        public MyViewHolder(View itemView, int viewType) {
            super(itemView);
            //System.out.println("instantiating TextViews");
            //System.out.println(itemView.findViewById(R.id.txtAttack));
            if (viewType == VIEW_TYPE_BIG) {
                txtAttack = itemView.findViewById(R.id.txtAttack);
                txtDefense = itemView.findViewById(R.id.txtDefense);
                txtAttackTitle = itemView.findViewById(R.id.txtAttTitle);
                txtDefenseTitle = itemView.findViewById(R.id.txtDefTitle);
                txtHP = itemView.findViewById(R.id.txtHP);
                txtHPTitle = itemView.findViewById(R.id.txtHPTitle);
                txtSpecies = itemView.findViewById(R.id.txtSpecies);
                txtSpeciesTitle = itemView.findViewById(R.id.txtSpeciesTitle);
                txtType1 = itemView.findViewById(R.id.txtType1);
                txtType2 = itemView.findViewById(R.id.txtType2);
                txtName = itemView.findViewById(R.id.txtName);
                btnInfo = itemView.findViewById(R.id.btnInfo);
                imgThumbnail = itemView.findViewById(R.id.imgThumbnail);
            } else {
                txtName = itemView.findViewById(R.id.txtname_small);
                btnInfo = itemView.findViewById(R.id.btnInfo_small);
                imgThumbnail = itemView.findViewById(R.id.imgThumbnail_small);

            }



        }
    }



}
