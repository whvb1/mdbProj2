package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView imgInfo;
    TextView txtInfo;
    ImageButton btnWeb;

    String info;
    String id;
    String name;
    RequestOptions option;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        info = getIntent().getStringExtra("info");
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");


        imgInfo = findViewById(R.id.imgInfo);
        txtInfo = findViewById(R.id.txtInfo);
        btnWeb = findViewById(R.id.btnWeb);

        txtInfo.setText(info);
        option = new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background);
        Glide.with(this).load(String.format("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/%s.png", id)).apply(option).into(imgInfo);

        btnWeb.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnWeb:
                Uri webpage = Uri.parse(String.format("https://www.pokemon.com/us/pokedex/%s",first_name(name)));
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                this.startActivity(webIntent);

                break;

            default:
                break;
        }


    }

    private String first_name(String name) {
            int nameEnd = name.length();
            for (int i = 0; i < name.length(); i += 1) {
                if (name.charAt(i) == '(') {
                    nameEnd = i;
                }
            }
            return name.substring(0, nameEnd).toLowerCase();
    }
}
