package com.example.pokedex;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pokedex.adapters.RecyclerViewAdapter;
import com.example.pokedex.model.Pokemon;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import org.json.JSONException;
import org.json.JSONObject;
//import org.json.simple.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

import java.util.Iterator;


public class ListActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton flbtnSwitch;
    Button btnSearch;
    TextView txtInfo;

    String toGrid = "Grid";
    String toArrayList = "ArrayList";
    String type_one;
    String type_two;
    ArrayList<String> types;

    ArrayList<Pokemon> dummyArrayList = new ArrayList<>();
    Pokemon sample1 = new Pokemon("Name","001",1,1,"flavore",10, 15, 15, "Species", 3, 111, new String[]{"type1", "type2"});
    Pokemon sample2 = new Pokemon("Name","123",1,1,"flavore",10, 15, 15, "Species", 3, 111, new String[]{"type1", "type2"});
    Pokemon sample3 = new Pokemon("Name","222",1,1,"flavore",10, 15, 15, "Species", 3, 111, new String[]{"type1", "type2"});

    ArrayList<Pokemon> pokemen = new ArrayList<>();

    RecyclerView recyclePokemon;
    RecyclerViewAdapter pokemonAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dummyArrayList.add(sample1);
        dummyArrayList.add(sample2);
        dummyArrayList.add(sample3);
        toPokemon1(getJson(), pokemen);

        pokemonAdapter = new RecyclerViewAdapter(this, pokemen);
        recyclePokemon = findViewById(R.id.recyclePokemon);


        // Set the adapter for the recycler view.
        recyclePokemon.setAdapter(pokemonAdapter);
        recyclePokemon.setLayoutManager(new LinearLayoutManager(this));
        // Set the layout of the recycler manager. This will determine how the
        // rows will be displayed. LinearLayout will set them to be vertically
        // linear (i.e one after the other, on top of each other).


        btnSearch = findViewById(R.id.btnSearch);
        flbtnSwitch = findViewById(R.id.flbtnSwitch);
        flbtnSwitch.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        txtInfo = findViewById(R.id.txtInfo);
        //getting data from search
        types = getIntent().getStringArrayListExtra("types");
    }

    @Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.btnSearch:
                Intent startIntent = new Intent(this, MainActivity.class);
                this.startActivity(startIntent);
                break;
            case R.id.flbtnSwitch:
                if(txtInfo.getText() == toGrid) {
                    System.out.println("calling self-made replace on GRID");
                    txtInfo.setText(toArrayList);
                }else{
                    System.out.println("calling self-made replace on ArrayList");
                    //replaceFragment(new pokemenArrayList());
                    txtInfo.setText(toGrid);
                }
                break;
            default:
                break;


        }

    }

    public JSONObject getJson() {
        try {
            System.out.println("trying to get pokeData");
            InputStream is = this.getAssets().open("pokeData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String myJson = new String(buffer, "UTF-8");
            JSONObject obj = new JSONObject(myJson);
            return obj;

        } catch (JSONException e) {

        } catch (IOException e) {

        }
        return null;
    }
    public void toPokemon1(JSONObject jsonObject,ArrayList<Pokemon> pokemen) {
        ArrayList<Pokemon> pokemons;
        Iterator<String> keys = jsonObject.keys();
        try {
            while(keys.hasNext()) {
                String key = keys.next();
                if (jsonObject.get(key) instanceof JSONObject) {
                    Pokemon pokemon = new Pokemon();
                    pokemon.setName(key);
                    JSONObject obj = ((JSONObject) jsonObject.get(key));
                    pokemon.setId(((String)obj.get("#")));
                    pokemon.setImageUrl(((String)obj.get("#")));
                    System.out.println("Image URL: "+pokemon.getImageUrl());
                    pokemon.setAttack(Integer.parseInt((String)obj.get("Attack")));
                    pokemon.setDefense(Integer.parseInt((String)obj.get("Defense")));
                    pokemon.setHP(Integer.parseInt((String)obj.get("HP")));
                    pokemon.setSpAtk(Integer.parseInt((String)obj.get("Sp. Atk")));
                    pokemon.setSpDef(Integer.parseInt((String)obj.get("Sp. Def")));
                    pokemon.setFlavorText((String)obj.get("FlavorText"));
                    pokemon.setSpeed(Integer.parseInt((String)obj.get("Speed")));
                    pokemon.setSpecies((String)obj.get("Species"));
                    System.out.println((obj.get("Type")));
                    //pokemon.setType(jsonArraytoString((JSONArray)obj.get("Type"),2));

                    pokemen.add(pokemon);
                }
            }
        } catch (JSONException e) {
        }
    }
    private void setuprecycler(ArrayList<Pokemon> lstPok) {
        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,lstPok);
        recyclePokemon.setLayoutManager(new LinearLayoutManager(this));
        recyclePokemon.setAdapter(myadapter);
    }
    /*
    private String[] jsonArraytoString(JSONArray jsonArray,int size) {
        Iterator<String> iterator = jsonArray.iterator();
        String[] list = new String[size];
        int i = 0;
        while(i<size) {
            list[i] = iterator.next();
            i += 1;
        }
        return list;

    }

     */

}