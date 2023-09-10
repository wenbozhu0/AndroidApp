package edu.uchicago.gerber.androidretro.data.models.drinks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DrinksResponse {
    @SerializedName("drinks")
    @Expose
    private List<DrinksInfo> drinks;

    public List<DrinksInfo> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinksInfo> drinks) {
        this.drinks = drinks;
    }
}
