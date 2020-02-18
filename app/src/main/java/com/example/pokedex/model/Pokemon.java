package com.example.pokedex.model;

import java.util.Arrays;

public class Pokemon {
    private String name;
    private int id;
    private int Attack;
    private int Defense;
    private String FlavorText;
    private int HP;
    private int spAtk;
    private int spDef;
    private String Species;
    private int Speed;
    private int Total;
    private String[] Type;
    private String imageUrl;

    public Pokemon() {
    }

    public Pokemon(String name, int id, int attack, int defense, String flavorText, int HP, int spAtk, int spDef, String species, int speed, int total, String[] type) {
        this.name = name;
        this.id = id;
        Attack = attack;
        Defense = defense;
        FlavorText = flavorText;
        this.HP = HP;
        this.spAtk = spAtk;
        this.spDef = spDef;
        Species = species;
        Speed = speed;
        Total = total;
        Type = type;
        imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/"+id+".png";
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", Attack=" + Attack +
                ", Defense=" + Defense +
                ", FlavorText='" + FlavorText + '\'' +
                ", HP=" + HP +
                ", spAtk=" + spAtk +
                ", spDef=" + spDef +
                ", Species='" + Species + '\'' +
                ", Speed=" + Speed +
                ", Total=" + Total +
                ", Type=" + Arrays.toString(Type) +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAttack() {
        return Attack;
    }

    public int getDefense() {
        return Defense;
    }

    public String getFlavorText() {
        return FlavorText;
    }

    public int getHP() {
        return HP;
    }

    public int getSpAtk() {
        return spAtk;
    }

    public int getSpDef() {
        return spDef;
    }

    public String getSpecies() {
        return Species;
    }

    public int getSpeed() {
        return Speed;
    }

    public int getTotal() {
        return Total;
    }

    public String[] getType() {
        return Type;
    }

    public String getType1() {
        return Type[0];
    }

    public String getType2() {
        if(Type.length>1)
            return Type[1];
        return "";
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAttack(int attack) {
        Attack = attack;
    }

    public void setDefense(int defense) {
        Defense = defense;
    }

    public void setFlavorText(String flavorText) {
        FlavorText = flavorText;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setSpAtk(int spAtk) {
        this.spAtk = spAtk;
    }

    public void setSpDef(int spDef) {
        this.spDef = spDef;
    }

    public void setSpecies(String species) {
        Species = species;
    }

    public void setSpeed(int speed) {
        Speed = speed;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public void setType(String[] type) {
        Type = type;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setImageUrl(int id) {
        this.imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/"+id+".png";
    }

}
