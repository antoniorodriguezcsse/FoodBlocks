package com.company;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.company.JsonReader.readJsonFromUrl;

public class Main {

    public static void main(String[] args) {

        USDA usda = new USDA();

        ArrayList<FoodData> listOfFOods = new ArrayList<>();


        ArrayList<String> idOfFoods = new ArrayList<>();
        idOfFoods =  usda.getIDsOfFood("beef");


        for(int i = 0; i < idOfFoods.size();i++)
        {
            System.out.println(idOfFoods.get(i));
            System.out.println(i);
        }
        /*

        JSONObject json = null;
        try {

            json = readJsonFromUrl("https://api.nal.usda.gov/ndb/nutrients/?format=json&api_key=" + "x5qM9v8PkjZrTf2cVSHzoK7y4GsSBgoQEmJsbwqV" + "&nutrients=203&nutrients=205&nutrients=204&nutrients=208&nutrients=269&ndbno=" + "45053485");

            JSONArray jObject = json.getJSONObject("report").getJSONArray("foods");
            //System.out.println("hello" + jObject.getJSONObject(0).getJSONArray("nutrients").getJSONObject(4).get("gm"));
            FoodData foodData = new FoodData();
            foodData.setName((String) jObject.getJSONObject(0).get("name"));
            foodData.setCaloriesFor100Grams((Double) jObject.getJSONObject(0).getJSONArray("nutrients").getJSONObject(0).get("gm"));
            foodData.setProteinFor100Grams((Double) jObject.getJSONObject(0).getJSONArray("nutrients").getJSONObject(1).get("gm"));
            foodData.setCarbohydratesFor100Grams((Double) jObject.getJSONObject(0).getJSONArray("nutrients").getJSONObject(4).get("gm"));
            foodData.setFatsFor100Grams((Double) jObject.getJSONObject(0).getJSONArray("nutrients").getJSONObject(3).get("gm"));

            System.out.println("Name: " + foodData.getName());
            System.out.println("Calories per 100g: " + foodData.getCaloriesFor100Grams());
            System.out.println("Protein per 100g: " + foodData.getProteinFor100Grams());
            System.out.println("Carbohydrate per 100g: " + foodData.getCarbohydratesFor100Grams());
            System.out.println("Fat per 100g: " + foodData.getFatsFor100Grams());

            //     System.out.println(json.getJSONObject("report").get("foods"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }
}
