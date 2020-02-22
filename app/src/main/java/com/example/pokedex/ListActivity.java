package com.example.pokedex;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.icu.text.Edits;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.pokedex.adapters.RecyclerViewAdapter;
import com.example.pokedex.model.Pokemon;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

import java.util.Iterator;

import static com.example.pokedex.adapters.RecyclerViewAdapter.SPAN_COUNT_ONE;
import static com.example.pokedex.adapters.RecyclerViewAdapter.SPAN_COUNT_THREE;
import static com.example.pokedex.adapters.RecyclerViewAdapter.VIEW_TYPE_BIG;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton flbtnSwitch;
    Button btnSearch;
    TextView txtInfo;

    String toGrid = "Grid";
    String toList = "List";
    String type_one;
    String type_two;
    String none_type = "none";
    ArrayList<String> types;
    String name_filter;

    boolean isProductViewAsList = true;
    int minAtk;
    int minDef;

    ArrayList<Pokemon> pokemen = new ArrayList<>();

    RecyclerView recyclePokemon;
    RecyclerViewAdapter pokemonAdapter;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //importing stuff from filter
        types = getIntent().getStringArrayListExtra("types");
        minAtk = (int)getIntent().getIntExtra("minAtk",0);
        minDef = (int)getIntent().getIntExtra("minDef",0);
        name_filter = getIntent().getStringExtra("name");
        parseTypes();
        toPokemon1(getJson(), pokemen);
        // Set the adapter for the recycler view.
        gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT_ONE);
        pokemonAdapter = new RecyclerViewAdapter(this, pokemen, gridLayoutManager);
        recyclePokemon = findViewById(R.id.recyclePokemon);
        recyclePokemon.setAdapter(pokemonAdapter);
        recyclePokemon.setLayoutManager(gridLayoutManager);
        // Set the layout of the recycler manager. This will determine how the
        // rows will be displayed. LinearLayout will set them to be vertically
        // linear (i.e one after the other, on top of each other).
        btnSearch = findViewById(R.id.btnSearch);
        flbtnSwitch = findViewById(R.id.flbtnSwitch);
        flbtnSwitch.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        txtInfo = findViewById(R.id.txtInfo);
        flbtnSwitch.setImageResource((R.drawable.grid));
        //getting data from search

    }

    @Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.btnSearch:
                Intent startIntent = new Intent(this, MainActivity.class);
                this.startActivity(startIntent);
                break;
            case R.id.flbtnSwitch:
                switchLayout();
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
    public void toPokemon1(JSONObject jsonObject, ArrayList<Pokemon> pokemen) {
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
                    pokemon.setAttack(Integer.parseInt((String)obj.get("Attack")));
                    pokemon.setDefense(Integer.parseInt((String)obj.get("Defense")));
                    pokemon.setHp(Integer.parseInt((String)obj.get("HP")));
                    pokemon.setSpAtk(Integer.parseInt((String)obj.get("Sp. Atk")));
                    pokemon.setSpDef(Integer.parseInt((String)obj.get("Sp. Def")));
                    pokemon.setFlavorText((String)obj.get("FlavorText"));
                    pokemon.setSpeed(Integer.parseInt((String)obj.get("Speed")));
                    pokemon.setSpecies((String)obj.get("Species"));
                    //System.out.println((obj.get("Type")));
                    ArrayList<String> list = new ArrayList<>();
                    JSONArray jsonArray = (JSONArray)obj.get("Type");
                    if (jsonArray != null) {
                        int len = jsonArray.length();
                        for (int i=0;i<len;i++){
                            list.add(jsonArray.get(i).toString());
                        }
                    }

                    pokemon.setType(list);
                    addToPokemen(pokemon);

                }
            }
        } catch (JSONException e) {
        }
    }

    private void addToPokemen(Pokemon pokemon) {
        String type1 = pokemon.getType1().toLowerCase();
        String type2 = pokemon.getType2().toLowerCase();
        String name = pokemon.getName();
        type_one = type_one.toLowerCase();
        type_two = type_two.toLowerCase();
        //System.out.println(String.format("type_one: %s; type1: %s; type2: %s",type_one,type1,type2));
        //System.out.println(type_one.equals(type1) || type_one.equals("none") || type_one.equals(type2));
        boolean type1test = (type_one.equals(type1) || type_one.equals("none") || type_one.equals(type2));
        boolean type2test = (type_two.equals(type2) || type_two.equals("none") || type_two.equals(type1));
        //System.out.println(String.format("Min: %d; Attack: %d",minAtk,pokemon.getAttack()));
        //System.out.println(minAtk < pokemon.getAttack());
        boolean atktest = minAtk < pokemon.getAttack();
        boolean deftest = minDef < pokemon.getDefense();
        System.out.println(name+" || "+name_filter.substring(1));
        boolean nameTest = name.toLowerCase().contains(name_filter.substring(1).toLowerCase());

        if(name.contains("( Mega")) {
            int nameEnd = name.length();
            for (int i = 0; i < name.length(); i += 1) {
                if (name.charAt(i) == '(') {
                    nameEnd = i;
                }
            }
            pokemon.setName(name.substring(0, nameEnd) + "(Mega)");

        }
        if(type1test && type2test && atktest && deftest && nameTest) {
            System.out.println("Adding Pokemon -----------------");
            pokemen.add(pokemon);
        }
    }

    private void switchLayout() {
        if(gridLayoutManager.getSpanCount() == SPAN_COUNT_ONE) {
            flbtnSwitch.setImageResource((R.drawable.list));
            gridLayoutManager.setSpanCount(SPAN_COUNT_THREE);
        } else {
            flbtnSwitch.setImageResource((R.drawable.grid));
            gridLayoutManager.setSpanCount(SPAN_COUNT_ONE);
        }
        pokemonAdapter.notifyItemRangeChanged(0,pokemonAdapter.getItemCount());
    }

    private void parseTypes() {
        if(types.size()>1) {
            type_two = types.get(1);
            type_one = types.get(0);
        } else if (types.size() == 1) {
            type_one = types.get(0);
            type_two = none_type;
        } else {
            System.out.println("no types selected");
            type_one = none_type;
            type_two = none_type;
            System.out.println(String.format("type_one: %s; type_two: %s",type_one,type_two));
        }
    }
}