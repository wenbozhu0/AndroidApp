package edu.uchicago.gerber.quarkus._03repositories


import edu.uchicago.gerber.quarkus._04models.Beer
import io.quarkus.mongodb.panache.kotlin.PanacheQuery


interface BeerRepoInterface {

    fun _create(beer: Beer)
    fun _create(beers: List<Beer>)
    fun _readById(id:String): Beer
    fun _readAll(): List<Beer>
    fun _update(updatedBeer: Beer)
    fun _deleteById(id:String)
    fun _deleteAll()
    fun _count() : Long
    fun _findAll(): PanacheQuery<Beer>?

}
