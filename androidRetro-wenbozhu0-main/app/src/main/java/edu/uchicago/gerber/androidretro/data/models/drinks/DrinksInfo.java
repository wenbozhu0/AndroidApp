package edu.uchicago.gerber.androidretro.data.models.drinks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DrinksInfo {
    @SerializedName("idDrink")
    @Expose
    private String idDrink;

    @SerializedName("strDrink")
    @Expose
    private String strDrink;

    private String strCategory;
    private String strGlass;

    private String strInstructions;
    private String strDrinkThumb;

    public String getStrGlass() {
        return strGlass;
    }

    public void setStrGlass(String strGlass) {
        this.strGlass = strGlass;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
    }

    public String getStrDrink() {
        return strDrink;
    }

    public void setStrDrink(String strDrink) {
        this.strDrink = strDrink;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrDrinkThumb() {
        return strDrinkThumb;
    }

    public void setStrDrinkThumb(String strDrinkThumb) {
        this.strDrinkThumb = strDrinkThumb;
    }
}
