package com.company;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.company.JsonReader.readJsonFromUrl;


public class Main {

    public static void main(String[] args) throws IOException, JSONException {

        // units of liquids e.g. oz
        // finish the grams to blocks
        // users data

        /*reverse the food block.
          how many grames of the food do you need to reach a block goal.
          e.g. I want to blocks of a food, how many grams of that food do I need.
         */

/*
        JSONObject json = readJsonFromUrl("https://api.nal.usda.gov/ndb/nutrients/?format=json&api_key=" + "x5qM9v8PkjZrTf2cVSHzoK7y4GsSBgoQEmJsbwqV" + "&nutrients=203&nutrients=205&nutrients=204&nutrients=208&nutrients=269&ndbno=" + "45107871");

        JSONObject report = json.getJSONObject("report");

        JSONArray foods = new JSONArray();
        foods = report.getJSONArray("foods");

      //  System.out.println("Database number: " + foods.getJSONObject(0).get("ndbno"));
    //    System.out.println("measure: " + foods.getJSONObject(0).get("measure"));
        System.out.println("name: " + foods.getJSONObject(0).get("name"));
     //   System.out.println("weight: " + foods.getJSONObject(0).get("weight"));

        JSONArray nutrients = foods.getJSONObject(0).getJSONArray("nutrients");
      //  System.out.println(nutrients.length());

        for(int i = 0; i < nutrients.length();i++) {
          //  System.out.println(nutrients.getJSONObject(i));
            System.out.println(nutrients.getJSONObject(i).get("unit"));
            System.out.println(nutrients.getJSONObject(i).get("nutrient"));
            System.out.println(nutrients.getJSONObject(i).get("gm"));
            System.out.println(nutrients.getJSONObject(i).get("value"));
        }
*/

        //breast gives array issues
        USDA usda = new USDA("beef");

        ArrayList<FoodData> listOfFOods = new ArrayList<>();
        listOfFOods = usda.getFoodObjects();

        for (int i = 0; i < listOfFOods.size(); i++) {
            System.out.println("Name: " + listOfFOods.get(i).getName());
            System.out.println("Calories: " + listOfFOods.get(i).getCaloriesFor100Grams() + "Kcal");
            System.out.println("Protein: " + listOfFOods.get(i).getProteinFor100Grams() + listOfFOods.get(i).getProteinFor100GramUnits());
            System.out.println("Protein blocks: " + listOfFOods.get(i).getProteinBlocks());
            System.out.println("Carbs: " + listOfFOods.get(i).getCarbohydratesFor100Grams() + listOfFOods.get(i).getCarbohydratesFor100GramUnits());
            System.out.println("Fats: " + listOfFOods.get(i).getFatsFor100Grams() + listOfFOods.get(i).getFatFor100GramsUnits());
        }










/*
        ArrayList<FoodData> listOfFOods = new ArrayList<>();


        ArrayList<String> idOfFoods = new ArrayList<>();
        idOfFoods =  usda.getIDsOfFood("chicken");


        for(int i = 0; i < idOfFoods.size();i++)
        {
            System.out.println(idOfFoods.get(i));
            System.out.println(i);
        }*/

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
