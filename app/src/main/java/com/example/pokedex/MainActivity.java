package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    EditText txtMinAtk;
    EditText txtMinDef;

    //Button[] btnTypes = new Button[]{btnNormal, btnFire, btnWater, btnElectric, btnGrass, btnIce, btnFighting, btnPoison, btnGround, btnFlying, btnBug, btnPsychic, btnRock, btnGhost, btnDragon, btnDark, btnSteel, btnFairy};


    boolean normal = false;
    boolean fire = false;
    boolean water = false;
    boolean electric = false;
    boolean grass = false;
    boolean ice = false;
    boolean fighting = false;
    boolean poison = false;
    boolean ground = false;
    boolean flying  = false;
    boolean psychic = false;
    boolean bug  = false;
    boolean rock = false;
    boolean ghost = false;
    boolean dragon  = false;
    boolean dark = false;
    boolean steel = false;
    boolean fairy = false;


    ArrayList<String> types = new ArrayList<>();
    ArrayList<Pokemon> pokemen = new ArrayList<>();
    int minAtk = 0;
    int minDef = 0;






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
        txtMinAtk = findViewById(R.id.txtMinAtk);
        txtMinDef = findViewById(R.id.txtMinDef);

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
                minAtk = Integer.parseInt(txtMinAtk.getText().toString());
                minDef = Integer.parseInt(txtMinDef.getText().toString());
                startIntent.putExtra("minAtk",minAtk);
                startIntent.putExtra("minDef",minDef);
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
            case R.id.btnElectric:
                if(electric){
                    removeStr("electric");
                    electric = false;
                }
                else if(types.size()<2) {
                    electric = true;
                    types.add("electric");
                }
                break;
            case R.id.btnGrass:
                if(grass){
                    removeStr("grass");
                    grass = false;
                }
                else if(types.size()<2) {
                    grass = true;
                    types.add("grass");
                }
                break;
            case R.id.btnIce:
                if(ice){
                    removeStr("ice");
                    ice = false;
                }
                else if(types.size()<2) {
                    ice = true;
                    types.add("ice");
                }
                break;
            case R.id.btnFighting:
                if(fighting){
                    removeStr("fighting");
                    fighting = false;
                }
                else if(types.size()<2) {
                    fighting = true;
                    types.add("fighting");
                }
                break;
            case R.id.btnPoison:
                if(poison){
                    removeStr("poison");
                    poison = false;
                }
                else if(types.size()<2) {
                    poison = true;
                    types.add("poison");
                }
                break;
            case R.id.btnGround:
                if(ground){
                    removeStr("ground");
                    ground = false;
                }
                else if(types.size()<2) {
                    ground = true;
                    types.add("ground");
                }
                break;
            case R.id.btnFlying:
                if(flying){
                    removeStr("flying");
                    flying = false;
                }
                else if(types.size()<2) {
                    flying = true;
                    types.add("flying");
                }
                break;
            case R.id.btnPsychic:
                if(electric){
                    removeStr("psychic");
                    psychic = false;
                }
                else if(types.size()<2) {
                    psychic = true;
                    types.add("psychic");
                }
                break;
            case R.id.btnBug:
                if(bug){
                    removeStr("bug");
                    bug = false;
                }
                else if(types.size()<2) {
                    bug = true;
                    types.add("bug");
                }
                break;
            case R.id.btnRock:
                if(rock){
                    removeStr("rock");
                    rock = false;
                }
                else if(types.size()<2) {
                    rock = true;
                    types.add("rock");
                }
                break;
            case R.id.btnGhost:
                if(ghost){
                    removeStr("ghost");
                    ghost = false;
                }
                else if(types.size()<2) {
                    ghost = true;
                    types.add("ghost");
                }
                break;
            case R.id.btnDragon:
                if(dragon){
                    removeStr("dragon");
                    dragon = false;
                }
                else if(types.size()<2) {
                    dragon = true;
                    types.add("dragon");
                }
                break;
            case R.id.btnDark:
                if(dark){
                    removeStr("dark");
                    dark = false;
                }
                else if(types.size()<2) {
                    dark = true;
                    types.add("dark");
                }
                break;
            case R.id.btnSteel:
                if(steel){
                    removeStr("steel");
                    steel = false;
                }
                else if(types.size()<2) {
                    steel = true;
                    types.add("steel");
                }
                break;
            case R.id.btnFairy:
                if(fairy){
                    removeStr("fairy");
                    fairy = false;
                }
                else if(types.size()<2) {
                    fairy = true;
                    types.add("fair");
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
