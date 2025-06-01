package edu.ijse.powerhouse.dto;

public class MealListDto {

    private String meal_id;
    private String diet_plan_id;
    private String name;
    private String description;
    private String meal_time;
    private int calories;
    private int protein;
    private int carbs;
    private int fat;
    private String recipe;

    public MealListDto() {
    }

    public MealListDto(String meal_id, String diet_plan_id, String name, String description, String meal_time, int calories, int protein, int carbs, int fat, String recipe) {
        this.meal_id = meal_id;
        this.diet_plan_id = diet_plan_id;
        this.name = name;
        this.description = description;
        this.meal_time = meal_time;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.recipe = recipe;
    }

    public String getMeal_id() {
        return meal_id;
    }

    public void setMeal_id(String meal_id) {
        this.meal_id = meal_id;
    }

    public String getDiet_plan_id() {
        return diet_plan_id;
    }

    public void setDiet_plan_id(String diet_plan_id) {
        this.diet_plan_id = diet_plan_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMeal_time() {
        return meal_time;
    }

    public void setMeal_time(String meal_time) {
        this.meal_time = meal_time;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }
}
