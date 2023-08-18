package com.dimqa.serialization;

import java.util.ArrayList;

public class IngredientData {
    private boolean success;
    private ArrayList<Ingredient> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<Ingredient> getData() {
        return data;
    }

    public void setData(ArrayList<Ingredient> data) {
        this.data = data;
    }
}
