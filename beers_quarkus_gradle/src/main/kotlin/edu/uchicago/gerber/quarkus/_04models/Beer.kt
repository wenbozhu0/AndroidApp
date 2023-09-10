package edu.uchicago.gerber.quarkus._04models

import org.bson.types.ObjectId

class Beer{
    var id: ObjectId? = null // used by MongoDB for the _id field
    lateinit var idDrink:String
    lateinit var strDrink:String
    lateinit var strCategory:String
    lateinit var strGlass:String
    lateinit var strInstructions:String
    lateinit var strDrinkThumb:String
}
