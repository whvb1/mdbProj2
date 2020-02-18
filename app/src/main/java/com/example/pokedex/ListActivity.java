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

import java.io.IOException;
import java.io.InputStream;
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
    Pokemon sample1 = new Pokemon("Name",123,1,1,"flavore",10, 15, 15, "Species", 3, 111, new String[]{"type1", "type2"});

    ArrayList<Pokemon> pokemen;

    RecyclerView recyclePokemon;
    RecyclerViewAdapter pokemonAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dummyArrayList.add(sample1);

        pokemonAdapter = new RecyclerViewAdapter(this, dummyArrayList);
        recyclePokemon = findViewById(R.id.recyclePokemon);

        //toPokemon1(getJson(), pokemen);
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
                    //System.out.println(key);
                    pokemon.setName(key);
                    //System.out.println(pokemon);
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
}