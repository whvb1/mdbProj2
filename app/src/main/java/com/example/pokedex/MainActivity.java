package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.pokedex.model.Pokemon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton imgbtnStart;
    TextView txtTypes;
    //18 different types
    Button btnNormal;
    Button btnFire;
    Button btnWater;
    Button btnElectric;
    Button btnGrass;
    Button btnIce;
    Button btnFighting;
    Button btnPoison;
    Button btnGround;
    Button btnFlying;
    Button btnPsychic;
    Button btnBug;
    Button btnRock;
    Button btnGhost;
    Button btnDragon;
    Button btnDark;
    Button btnSteel;
    Button btnFairy;

    //Button[] btnTypes = new Button[]{btnNormal, btnFire, btnWater, btnElectric, btnGrass, btnIce, btnFighting, btnPoison, btnGround, btnFlying, btnBug, btnPsychic, btnRock, btnGhost, btnDragon, btnDark, btnSteel, btnFairy};


    boolean normal = false;
    boolean fire = false;
    boolean water = false;


    ArrayList<String> types = new ArrayList<>();
    ArrayList<Pokemon> pokemen = new ArrayList<>();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgbtnStart = findViewById(R.id.imgbtnStart);
        txtTypes = findViewById(R.id.txtTypes);

        btnNormal = findViewById(R.id.btnNormal);
        btnFire = findViewById(R.id.btnFire);
        btnWater = findViewById(R.id.btnWater);
        btnElectric = findViewById(R.id.btnElectric);
        btnGrass = findViewById(R.id.btnGrass);
        btnIce = findViewById(R.id.btnIce);
        btnFighting = findViewById(R.id.btnFighting);
        btnPoison = findViewById(R.id.btnPoison);
        btnGround = findViewById(R.id.btnGround);
        btnFlying = findViewById(R.id.btnFlying);
        btnPsychic = findViewById(R.id.btnPsychic);
        btnBug = findViewById(R.id.btnBug);
        btnRock = findViewById(R.id.btnRock);
        btnGhost = findViewById(R.id.btnGhost);
        btnDragon = findViewById(R.id.btnDragon);
        btnDark = findViewById(R.id.btnDark);
        btnSteel = findViewById(R.id.btnSteel);
        btnFairy = findViewById(R.id.btnFairy);

        txtTypes.setText("");
        imgbtnStart.setOnClickListener(this);
        btnNormal.setOnClickListener(this);
        btnFire.setOnClickListener(this);
        btnWater.setOnClickListener(this);
        btnElectric.setOnClickListener(this);
        btnGrass.setOnClickListener(this);
        btnIce.setOnClickListener(this);
        btnFighting.setOnClickListener(this);
        btnPoison.setOnClickListener(this);
        btnGround.setOnClickListener(this);
        btnFlying.setOnClickListener(this);
        btnPsychic.setOnClickListener(this);
        btnBug.setOnClickListener(this);
        btnRock.setOnClickListener(this);
        btnGhost.setOnClickListener(this);
        btnDragon.setOnClickListener(this);
        btnDark.setOnClickListener(this);
        btnSteel.setOnClickListener(this);
        btnFairy.setOnClickListener(this);









    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgbtnStart:
                Intent startIntent = new Intent(this, ListActivity.class);
                startIntent.putExtra("types",types);
                this.startActivity(startIntent);
                break;
            case R.id.btnNormal:
                if(normal) {
                    System.out.println("removing: normal");
                    removeStr("normal");
                    normal = false;
                }
                else if(types.size()<2) {
                    normal = true;
                    types.add("normal");
                }
                break;
            case R.id.btnFire:
                if(fire){
                    System.out.println("removing: fire");
                    removeStr("fire");
                    fire = false;
                }
                else if(types.size()<2) {
                    fire = true;
                    types.add("fire");
                }
                break;
            case R.id.btnWater:
                if(water){
                    System.out.println("removing: water");
                    removeStr("water");
                    water = false;
                }
                else if(types.size()<2) {
                    water = true;
                    types.add("water");
                }
                break;



            default:
                break;
        }
        update();


    }

    public void update(){
        txtTypes.setText(types.toString());
    }


    //FIXME: buggy...concurrent modification exception?
    public void removeStr(String str){
        System.out.println("Types to remove ["+str+"] from: "+types.toString());
        for(String obj : types){
            if(obj == str){
                System.out.println("removed: "+obj);
                types.remove(obj);
            }
        }

    }



}
