package com.company;

public class FoodData {
    private String name;
    private String caloriesFor100Grams;
    private String proteinFor100Grams;
    private String carbohydratesFor100Grams;
    private String carbohydratesFor100GramUnits;
    private String fatsFor100Grams;
    private String fatFor100GramsUnits;

    public String getProteinBlocks()
    {
        double d =  (Double.parseDouble(proteinFor100Grams) / 10);
        d = Math.round(d * 1.0) / 1.0;
        return String.valueOf(d);
    }

    public String getProteinFor100GramUnits() {
        return proteinFor100GramUnits;
    }

    public void setProteinFor100GramUnits(String proteinFor100GramUnits) {
        this.proteinFor100GramUnits = proteinFor100GramUnits;
    }

    private String proteinFor100GramUnits;

    public String getFatsFor100Grams() {
        return fatsFor100Grams;
    }

    public void setFatsFor100Grams(String fatsFor100Grams) {
        if(fatsFor100Grams.equals("--"))
        {
            this.fatsFor100Grams = "0.00";
        }
        else{
            this.fatsFor100Grams = fatsFor100Grams;
        }
    }


    public String getCarbohydratesFor100Grams() {
        return carbohydratesFor100Grams;
    }

    public void setCarbohydratesFor100Grams(String carbohydratesFor100Grams) {
        this.carbohydratesFor100Grams = carbohydratesFor100Grams;
    }

    public String getProteinFor100Grams() {
        return proteinFor100Grams;
    }

    public void setProteinFor100Grams(String proteinFor100Grams) {
        this.proteinFor100Grams = proteinFor100Grams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaloriesFor100Grams() {
        return caloriesFor100Grams;
    }

    public void setCaloriesFor100Grams(String caloriesFor100Grams) {
        this.caloriesFor100Grams = caloriesFor100Grams;
    }

    public String getFatFor100GramsUnits() {
        return fatFor100GramsUnits;
    }

    public void setFatFor100GramsUnits(String fatFor100GramsUnits) {
        this.fatFor100GramsUnits = fatFor100GramsUnits;
    }

    public String getCarbohydratesFor100GramUnits() {
        return carbohydratesFor100GramUnits;
    }

    public void setCarbohydratesFor100GramUnits(String carbohydratesFor100GramUnits) {
        this.carbohydratesFor100GramUnits = carbohydratesFor100GramUnits;
    }
}
