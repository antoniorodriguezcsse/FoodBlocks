package com.company;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.company.JsonReader.readJsonFromUrl;

public class USDA {
    private String apiKey = "x5qM9v8PkjZrTf2cVSHzoK7y4GsSBgoQEmJsbwqV";
    private String type = "b";
    private String format = "json";
    private String nameOfFood;

    private ArrayList<String> JSONFoodReportArray = new ArrayList<>();
    private ArrayList<String> idOfFoods = new ArrayList<>();
    private ArrayList<FoodData> listOfFoodObjects = new ArrayList<>();


    // constructor
    USDA(String nameOfFoodToSearchFor) {
        setFoodObjects(nameOfFoodToSearchFor);
    }

    public ArrayList<FoodData> getFoodObjects() {
        return listOfFoodObjects;
    }

    // Sets the ArrayList(listOfFoodObjects) with foodObjects
    private void setFoodObjects(String nameOfFoodToSearchFor) {
        idOfFoods = getIDsOfFood(nameOfFoodToSearchFor);

        for (int i = 0; i < idOfFoods.size(); i++) {
            FoodData foodData = new FoodData();

            //search for food by ID
            foodData = setFoodObject(idOfFoods.get(i));

           // System.out.println("Name: " + foodData.getName());
            listOfFoodObjects.add(foodData);
        }
    }

    // Sets One object for the food data.
    private FoodData setFoodObject(String foodID) {
        JSONObject json = null;
        try {

            //  System.out.println("Food id: " +  foodID);
            //  System.out.println("Code: " +  45188431);
            //  foodID = "45107994";
            json = readJsonFromUrl("https://api.nal.usda.gov/ndb/nutrients/?format=json&api_key=" + "x5qM9v8PkjZrTf2cVSHzoK7y4GsSBgoQEmJsbwqV" + "&nutrients=203&nutrients=205&nutrients=204&nutrients=208&nutrients=269&ndbno=" + foodID);

            //   JSONArray jObject = json.getJSONObject("report").getJSONArray("foods");
            //System.out.println("hello" + jObject.getJSONObject(0).getJSONArray("nutrients").getJSONObject(4).get("gm"));
            FoodData foodData = new FoodData();

            JSONObject report = json.getJSONObject("report");
            JSONArray foods = new JSONArray();
            foods = report.getJSONArray("foods");

            // System.out.println("name: " + foods.getJSONObject(0).get("name"));
            foodData.setName("name: " + foods.getJSONObject(0).get("name"));
          //  System.out.println(foodData.getName());

            JSONArray nutrients = foods.getJSONObject(0).getJSONArray("nutrients");
            //  System.out.println(nutrients.length());

            for (int i = 0; i < nutrients.length(); i++) {
                // System.out.println(nutrients.getJSONObject(i));

                if (nutrients.getJSONObject(i).get("nutrient").equals("Total lipid (fat)")) {
                    foodData.setFatsFor100Grams(nutrients.getJSONObject(i).get("gm").toString());
                    foodData.setFatFor100GramsUnits(nutrients.getJSONObject(i).get("unit").toString());
                }

                if (nutrients.getJSONObject(i).get("nutrient").equals("Protein")) {
                    foodData.setProteinFor100Grams(nutrients.getJSONObject(i).get("gm").toString());
                    foodData.setProteinFor100GramUnits(nutrients.getJSONObject(i).get("unit").toString());
                }

                if (nutrients.getJSONObject(i).get("nutrient").equals("Carbohydrate, by difference")) {
                    foodData.setCarbohydratesFor100Grams(nutrients.getJSONObject(i).get("gm").toString());
                    foodData.setCarbohydratesFor100GramUnits(nutrients.getJSONObject(i).get("unit").toString());
                }

                if(nutrients.getJSONObject(i).get("nutrient").equals("Energy")){
                    foodData.setCaloriesFor100Grams(nutrients.getJSONObject(i).get("gm").toString());
                   // System.out.println("Calorkes: " + nutrients.getJSONObject(i).get("gm").toString() + nutrients.getJSONObject(i).get("unit").toString() );
                }

            }

          //  System.out.println("report: " + json.getJSONObject("report").get("foods"));

            /*
            System.out.println("Protein for 100g: " + foodData.getProteinFor100Grams() + foodData.getProteinFor100GramUnits());
            System.out.println("Carbs for 100g: " + foodData.getCarbohydratesFor100Grams() + foodData.getCarbohydratesFor100GramUnits());
            System.out.println("Fat for 100g: " + foodData.getFatsFor100Grams() + foodData.getFatFor100GramsUnits());
*/
            return foodData;
            //     System.out.println(json.getJSONObject("report").get("foods"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    // return ID of All Foods that have the search term in an array. e.g. "beef" is the search term
    private ArrayList<String> getIDsOfFood(String nameOfFood) {

        //Connects to the USDA and stores the JSON result in to JSONFoodReportArray
        setDataBaseNumbersOfFood(nameOfFood);
        return getDataBaseNumbersOfFood();
    }

    private void setDataBaseNumbersOfFood(String nameOfFood) {
        String foodToSearchFor = nameOfFood;

        //https://api.nal.usda.gov/ndb/search/?format=json&q=butter&sort=n&max=25&offset=0&api_key=DEMO_KEY
        //"http://api.nal.usda.gov/ndb/reports/?ndbno=" + ndbno + "&type=" + type + "&format=" + format + "&api_key=" + apiKey;

        String url1 = "https://api.nal.usda.gov/ndb/search/?format=json&q=" + foodToSearchFor + "&sort=n&max=25&offset=0&api_key=x5qM9v8PkjZrTf2cVSHzoK7y4GsSBgoQEmJsbwqV";

        try {

            URL url = new URL(url1);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                JSONFoodReportArray.add(output);
            }

            getDataBaseNumbersOfFood();
            conn.disconnect();


        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    private ArrayList<String> getDataBaseNumbersOfFood() {
        ArrayList<String> nutritinalDatabaseNumbers = new ArrayList<>();

        for (int i = 0; i < JSONFoodReportArray.size(); i++) {
            if (JSONFoodReportArray.get(i).contains("ndbno")) {
                String nutritinalDatabaseNumber = JSONFoodReportArray.get(i).replace("\"ndbno\": \"", "");
                nutritinalDatabaseNumber = nutritinalDatabaseNumber.replace("\",", "");
                nutritinalDatabaseNumber = nutritinalDatabaseNumber.replaceAll("\\s+", "");
                nutritinalDatabaseNumbers.add(nutritinalDatabaseNumber);
            }
        }

        return nutritinalDatabaseNumbers;
    }
}
