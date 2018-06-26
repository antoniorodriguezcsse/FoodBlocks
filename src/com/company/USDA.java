package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class USDA {
    private String apiKey = "x5qM9v8PkjZrTf2cVSHzoK7y4GsSBgoQEmJsbwqV";
    private String type = "b";
    private String format = "json";
    private String nameOfFood;

    private ArrayList<String> JSONFoodReportArray = new ArrayList<>();

    // return ID of All Foods that have the search term in an array. e.g. "beef" is the search term
    public ArrayList<String> getIDsOfFood(String nameOfFood) {

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
                nutritinalDatabaseNumbers.add(nutritinalDatabaseNumber);
            }
        }

        return nutritinalDatabaseNumbers;
    }

    public void searchFoodDataBaseNumber(String nameOfFood) {

    }
}
