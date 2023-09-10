package edu.uchicago.gerber.quarkus._04models

import net.datafaker.Faker
import org.bson.types.ObjectId

object Faked {
    //static prop
    val faker = Faker()
    val FAKE_ID = "5063114bd386d8fadbd6b004"

     fun genRawEntity(): Beer {

         val beer = Beer()
         //we allow mongo to generate the id's for us
         //beer.id = ObjectId.get()
         beer.idDrink = "testId"
         beer.strDrink = "testDrink"
         beer.strCategory = "testCategory"
         beer.strInstructions = "testInstructions"
         beer.strGlass = "testGlass"
         beer.strDrinkThumb = "https://www.thecocktaildb.com/images/media/drink/9179i01503565212.jpg"
         return beer
    }

    fun genFakerBeers(num: Int): List<Beer>{
        val list = mutableListOf<Beer>()
        repeat(num){ list.add(genRawEntity()) }
        return list
    }

    //use a string such as "5063114bd386d8fadbd6b004"
    fun genTestBeer(hash: String): Beer{
         val beer = genRawEntity()
         beer.id = ObjectId(hash)
        return  beer
    }

}
