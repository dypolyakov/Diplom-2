package com.dimqa.generator;

import com.dimqa.serialization.Ingredient;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.Random;

public class IngredientGenerator {
    private ArrayList<Ingredient> ingredients;

    public IngredientGenerator(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public JsonArray random() {
        Random random = new Random();
        int ingredientCount = random.nextInt(ingredients.size()) + 1;
        JsonArray randomIngredients = new JsonArray();
        for (int i = 0; i < ingredientCount; i++) {
            int randomIngredient = random.nextInt(ingredients.size());
            randomIngredients.add(ingredients.get(randomIngredient).get_id());
        }
        return randomIngredients;
    }
}
