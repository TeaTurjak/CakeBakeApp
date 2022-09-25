package com.example.myapp;

public class ListItem {
    private String cakeId;
    private String difficulty;
    private String ingredients;
    private String recipe;
    private String imageUrl;

    public ListItem(String cakeid, String difficulty, String ingredients, String recipe, String imageUrl) {
        this.cakeId = cakeid;
        this.difficulty = difficulty;
        this.ingredients=ingredients;
        this.recipe=recipe;
        this.imageUrl=imageUrl;
    }

    public String getCakeId() {
        return cakeId;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getRecipe() {
        return recipe;
    }
}
