package com.BS4Project.Model;

/**
 * Created by Peter on 22-5-2015.
 */
public class ProductWinkel {
    Winkel winkel;
    Product product;
    Euroformatter prijs;
    String Url;

    public ProductWinkel(Winkel winkel, Product product, Euroformatter prijs, String Url){
        this.winkel = winkel;
        this.product = product;
        this.prijs = prijs;
        this.Url = Url;
    }

    public Winkel getWinkel() {
        return winkel;
    }

    public Product getProduct() {
        return product;
    }

    public Euroformatter getPrijs() {
        return prijs;
    }

    public String getURL() {
        return Url;
    }
}
