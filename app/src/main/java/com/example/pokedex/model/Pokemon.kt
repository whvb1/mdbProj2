package com.example.pokedex.model

import java.util.Arrays

class Pokemon {
    var name: String? = null
    var id: String? = null
    var attack: Int = 0
    var defense: Int = 0
    var flavorText: String? = null
    var hp: Int = 0
    var spAtk: Int = 0
    var spDef: Int = 0
    var species: String? = null
    var speed: Int = 0
    var total: Int = 0
    var type: ArrayList<String>? = null
    private var imageUrl: String? = null




    val type1: String
        get() = if (type != null) type!![0] else "no type"

    val type2: String
        get() = if (type != null && type!!.size > 1) type!![1] else ""

    constructor() {}

    constructor(name: String, id: String, attack: Int, defense: Int, flavorText: String, HP: Int, spAtk: Int, spDef: Int, species: String, speed: Int, total: Int, type: ArrayList<String>) {
        this.name = name
        this.id = id
        this.attack = attack
        this.defense = defense
        this.flavorText = flavorText
        this.hp = HP
        this.spAtk = spAtk
        this.spDef = spDef
        this.species = species
        this.speed = speed
        this.total = total
        this.type = type
        this.imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/$id.png"
    }

    override fun toString(): String {
        return  "Name: '" + name + '\''.toString() +
                "\n ID: " + id +
                "\n Attack: " + attack +
                "\n Defense: " + defense +
                "\n FlavorText: " + flavorText +
                "\n HP: " + hp +
                "\n Special Atk: " + spAtk +
                "\n Special Def: " + spDef +
                "\n Species: " + species +
                "\n Speed: " + speed +
                "\n Total: " + total +
                "\n Primary Type: " + type1 +
                "\n Secondary Type: " + type2

    }

    fun toStringArr(): ArrayList<String?> {
        var list: ArrayList<String?> = ArrayList<String?>()
        list.add(name)
        list.add(id)
        list.add(attack.toString())
        list.add(defense.toString())
        list.add(flavorText)
        list.add(hp.toString())
        list.add(spAtk.toString())
        list.add(spDef.toString())
        list.add(species)
        list.add(speed.toString())
        list.add(total.toString())
        list.add(type1)
        list.add(type2)
        list.add(imageUrl)

        return list
    }


    fun setImageUrl(id: String) {
        this.imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/$id.png"
    }


}
