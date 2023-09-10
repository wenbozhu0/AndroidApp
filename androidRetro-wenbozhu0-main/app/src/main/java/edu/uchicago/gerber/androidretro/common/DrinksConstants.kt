package edu.uchicago.gerber.androidretro.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import edu.uchicago.gerber.androidretro.data.models.drinks.DrinksInfo
import edu.uchicago.gerber.androidretro.data.models.drinks.DrinksResponse

object DrinksConstants {
    val modifier = Modifier.padding(paddingValues = PaddingValues(all = 0.dp))

    var drinksUrl = "https://the-cocktail-db.p.rapidapi.com/"
    var drinksItem: DrinksInfo
    var drinksResponse: DrinksResponse

    init {
        val gson = Gson()
        val hardCodedResponse = """
            {
                "drinks": [
                    {
                        "idDrink": "13196",
                        "strDrink": "Long vodka",
                        "strDrinkAlternate": null,
                        "strTags": null,
                        "strVideo": null,
                        "strCategory": "Ordinary Drink",
                        "strIBA": null,
                        "strAlcoholic": "Alcoholic",
                        "strGlass": "Highball glass",
                        "strInstructions": "Shake a tall glass with ice cubes and Angostura, coating the inside of the glass. Por the vodka onto this, add 1 slice of lime and squeeze juice out of remainder, mix with tonic, stir and voila you have a Long Vodka",
                        "strInstructionsES": null,
                        "strInstructionsDE": "Schütteln Sie ein hohes Glas mit Eiswürfeln und Angostura und beschichten Sie so die Innenseite des Glases. Den Wodka darüber gießen, 1 Scheibe Limette hinzufügen und den Saft aus dem Rest herausquetschen, mit Tonic mischen, umrühren und voila, Sie haben einen langen Wodka.",
                        "strInstructionsFR": null,
                        "strInstructionsIT": "Agitare un bicchiere alto con cubetti di ghiaccio e angostura, ricoprendo l'interno del bicchiere.\r\nVersare la vodka su questo, aggiungere 1 fetta di lime e spremere il succo dal resto, mescolare con il tonico, mescolare e voilà hai una Long Vodka",
                        "strInstructionsZH-HANS": null,
                        "strInstructionsZH-HANT": null,
                        "strDrinkThumb": "https://www.thecocktaildb.com/images/media/drink/9179i01503565212.jpg",
                        "strIngredient1": "Vodka",
                        "strIngredient2": "Lime",
                        "strIngredient3": "Angostura bitters",
                        "strIngredient4": "Tonic water",
                        "strIngredient5": "Ice",
                        "strIngredient6": null,
                        "strIngredient7": null,
                        "strIngredient8": null,
                        "strIngredient9": null,
                        "strIngredient10": null,
                        "strIngredient11": null,
                        "strIngredient12": null,
                        "strIngredient13": null,
                        "strIngredient14": null,
                        "strIngredient15": null,
                        "strMeasure1": "5 cl ",
                        "strMeasure2": "1/2 ",
                        "strMeasure3": "4 dashes ",
                        "strMeasure4": "1 dl Schweppes ",
                        "strMeasure5": "4 ",
                        "strMeasure6": null,
                        "strMeasure7": null,
                        "strMeasure8": null,
                        "strMeasure9": null,
                        "strMeasure10": null,
                        "strMeasure11": null,
                        "strMeasure12": null,
                        "strMeasure13": null,
                        "strMeasure14": null,
                        "strMeasure15": null,
                        "strImageSource": null,
                        "strImageAttribution": null,
                        "strCreativeCommonsConfirmed": "No",
                        "dateModified": "2017-08-24 10:00:12"
                    },
                    {
                        "idDrink": "16967",
                        "strDrink": "Vodka Fizz",
                        "strDrinkAlternate": null,
                        "strTags": null,
                        "strVideo": null,
                        "strCategory": "Other / Unknown",
                        "strIBA": null,
                        "strAlcoholic": "Alcoholic",
                        "strGlass": "White wine glass",
                        "strInstructions": "Blend all ingredients, save nutmeg. Pour into large white wine glass and sprinkle nutmeg on top.",
                        "strInstructionsES": null,
                        "strInstructionsDE": "Alle Zutaten mischen. In ein großes Weißweinglas geben und mit Muskatnuss bestreuen.",
                        "strInstructionsFR": null,
                        "strInstructionsIT": "Frullare tutti gli ingredienti, tranne la noce moscata.\r\nVersare in un grande bicchiere da vino bianco e cospargere di noce moscata.",
                        "strInstructionsZH-HANS": null,
                        "strInstructionsZH-HANT": null,
                        "strDrinkThumb": "https://www.thecocktaildb.com/images/media/drink/xwxyux1441254243.jpg",
                        "strIngredient1": "Vodka",
                        "strIngredient2": "Half-and-half",
                        "strIngredient3": "Limeade",
                        "strIngredient4": "Ice",
                        "strIngredient5": "Nutmeg",
                        "strIngredient6": null,
                        "strIngredient7": null,
                        "strIngredient8": null,
                        "strIngredient9": null,
                        "strIngredient10": null,
                        "strIngredient11": null,
                        "strIngredient12": null,
                        "strIngredient13": null,
                        "strIngredient14": null,
                        "strIngredient15": null,
                        "strMeasure1": "2 oz ",
                        "strMeasure2": "2 oz ",
                        "strMeasure3": "2 oz ",
                        "strMeasure4": null,
                        "strMeasure5": null,
                        "strMeasure6": null,
                        "strMeasure7": null,
                        "strMeasure8": null,
                        "strMeasure9": null,
                        "strMeasure10": null,
                        "strMeasure11": null,
                        "strMeasure12": null,
                        "strMeasure13": null,
                        "strMeasure14": null,
                        "strMeasure15": null,
                        "strImageSource": null,
                        "strImageAttribution": null,
                        "strCreativeCommonsConfirmed": "No",
                        "dateModified": "2015-09-03 05:24:03"
                    }
                ]
            }
        """.trimIndent()

        drinksResponse =
            gson.fromJson<DrinksResponse>(hardCodedResponse, DrinksResponse::class.java)
        drinksItem = drinksResponse.drinks[0]
    }

}