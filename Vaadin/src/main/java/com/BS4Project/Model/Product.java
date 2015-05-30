package com.BS4Project.Model;

/**
 * Created by Peter on 21-5-2015.
 */
public class Product {
    private String EAN;
    private String Type;
    private String Merk;
    private String Productnaam;
    private String Specifications;


    //CONSTRUCTOR
    public Product(String EAN, String Type, String Merk, String Productnaam, String Specification) {
        this.EAN = EAN;
        this.Type = Type;
        this.Merk = Merk;
        this.Productnaam = Productnaam;
        this.Specifications = Specification;
    }

    //Getters
    public String getEAN() {
        return EAN;
    }

    public String getType() {
        return Type;
    }

    public String getMerk() {
        return Merk;
    }

    public String getProductnaam() {
        return Productnaam;
    }

    public String getSpecifications() {
        return Specifications;
    }

}
    /*
    //Setters
    public void setAzertyPrice(Double azertyPrice, String URL) {
        AzertyPrice = azertyPrice;
        AzertyURL = URL;
    }

    public void setAlternatePrice(Double alternatePrice, String URL) {
        AlternatePrice = alternatePrice;
        AlternateURL = URL;
    }

    public void setAfuturePrice(Double afuturePrice, String URL) {
        AfuturePrice = afuturePrice;
        AfutureURL = URL;
    }
*/

/*
    public Double getLowestPrice(){
        Double result = null;

        if (AfuturePrice != null) {
            result = AfuturePrice;
        }

        if (AlternatePrice != null) {
            if(result != null){
                if(result > AlternatePrice)result = AlternatePrice;
            }
        }

        if (AzertyPrice != null) {
            if(result != null){
                if(result > AzertyPrice)result = AzertyPrice;
            }
        }
        if(result == null){
            result = 0d;
        }
        return result;
    }
    */

