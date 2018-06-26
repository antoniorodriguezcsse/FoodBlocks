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


    USDA(String nameOfFoodToSearchFor){

         setFoodObjects(nameOfFoodToSearchFor);

    }

    private void setFoodObjects(String nameOfFoodToSearchFor)
    {
        idOfFoods = getIDsOfFood(nameOfFoodToSearchFor);

        for(int i = 0; i < idOfFoods.size();i++)
        {
            FoodData foodData = new FoodData();
          //  System.out.println(idOfFoods.get(i));
            //search for food by ID
            foodData = searchFoodBasedOnID(idOfFoods.get(i));
        }
    }

    public ArrayList<FoodData> getFoodObjects(){


        return listOfFoodObjects;

    }

    private FoodData searchFoodBasedOnID(String foodID){
        JSONObject json = null;
        try {
          //  System.out.println("Food id: " +  foodID);
          //  System.out.println("Code: " +  45188431);
            json = readJsonFromUrl("https://api.nal.usda.gov/ndb/nutrients/?format=json&api_key=" + "x5qM9v8PkjZrTf2cVSHzoK7y4GsSBgoQEmJsbwqV" + "&nutrients=203&nutrients=205&nutrients=204&nutrients=208&nutrients=269&ndbno=" +  foodID);

            JSONArray jObject = json.getJSONObject("report").getJSONArray("foods");
            //System.out.println("hello" + jObject.getJSONObject(0).getJSONArray("nutrients").getJSONObject(4).get("gm"));
            FoodData foodData = new FoodData();
            foodData.setName((String) jObject.getJSONObject(0).get("name"));
            foodData.setCaloriesFor100Grams((Double) jObject.getJSONObject(0).getJSONArray("nutrients").getJSONObject(0).get("gm"));
            foodData.setProteinFor100Grams((Double) jObject.getJSONObject(0).getJSONArray("nutrients").getJSONObject(1).get("gm"));
            foodData.setCarbohydratesFor100Grams((Double) jObject.getJSONObject(0).getJSONArray("nutrients").getJSONObject(4).get("gm"));
          //  foodData.setFatsFor100Grams((Double) jObject.getJSONObject(0).getJSONArray("nutrients").getJSONObject(3).get("gm"));

            System.out.println( "blah" + jObject.getJSONObject(0).getJSONArray("nutrients").getJSONObject(3).get("gm"));
            double blah = Double.valueOf((Double) jObject.getJSONObject(0).getJSONArray("nutrients").getJSONObject(3).get("gm"));



            System.out.println("Name: " + foodData.getName());
            System.out.println("Calories per 100g: " + foodData.getCaloriesFor100Grams());
            System.out.println("Protein per 100g: " + foodData.getProteinFor100Grams());
            System.out.println("Carbohydrate per 100g: " + foodData.getCarbohydratesFor100Grams());
            System.out.println("Fat per 100g: " + foodData.getFatsFor100Grams());

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
                nutritinalDatabaseNumber = nutritinalDatabaseNumber.replaceAll("\\s+","");
                nutritinalDatabaseNumbers.add(nutritinalDatabaseNumber);
            }
        }

        return nutritinalDatabaseNumbers;
    }
}
