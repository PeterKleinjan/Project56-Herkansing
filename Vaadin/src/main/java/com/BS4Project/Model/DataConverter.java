package com.BS4Project.Model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter on 26-5-2015.
 */
public class DataConverter {

    private static final ArrayList<String> exclude = new ArrayList<String>(){{
        add("_id");
    }};
    //Database is niet optimaal opgezet, hierom niet mogelijk direct uit te lezen.
    private static final ArrayList<String> allShopsLinks = new ArrayList<String>(){{
        add("link-4Allshop");
        add("link-Afuture");
        add("link-Alternate");
        add("link-Azerty");
        add("link-CD-Rom-Land");
        add("link-Informatique");
    }};

    DatabaseContext db;
    DBCursor rawData;

    List<Product> products;
    List<Winkel> shops = new ArrayList<Winkel>(){{
        add(new Winkel("4Allshop"));
        add(new Winkel("Afuture"));
        add(new Winkel("Alternate"));
        add(new Winkel("Azerty"));
        add(new Winkel("CD-Rom-Land"));
        add(new Winkel("Informatique"));
    }};
    List<ProductWinkel> productWinkels;

    public DataConverter(){

        //System.out.println("Dataconverter wordt aangeroepen");
        products = new ArrayList<Product>();
       // shops = new ArrayList<Winkel>();
        productWinkels = new ArrayList<ProductWinkel>();

        //Open database connection
        db = new DatabaseContext();
        //Get data with iterator ie DBCursor
        rawData = db.getDataCursor();

        //Iterate the BasicDBObjects
        try {
            while(rawData.hasNext()) {
                BasicDBObject current = (BasicDBObject)rawData.next();

                //Convert to this Application's models and fill the arraylists
                convertObjectToModels(current);

            }
        } finally {
            rawData.close();
        }

        //Close database connection
        db.disconnectDB();
    }

    //Convert en splits een BasicDBObject naar een Product, een winkel en een ProductWinkel
    private void convertObjectToModels(BasicDBObject object){

        //Variables for new Product
        String EAN = object.getString("EAN");
        String Type = object.getString("Type");
        String Merk = object.getString("Merk");
        String Productnaam = object.getString("Productnaam");
        String Specifications = "";

        //ArrayList to store all storenames where product is available
        ArrayList<String> productShops = new ArrayList<String>();
        ArrayList<String> productShopsUrl = new ArrayList<String>();

        //Get all attribute-keys
        Object[] props = object.keySet().toArray();

        //Loop properties
        for(Object detail : props){
            //Exclude mongoDB _id
            if(exclude.contains(detail))continue;

            //Exclude shops from loop and saves keys to ArrayList for later use
            if(getShopNames().contains(detail.toString())){
                productShops.add(detail.toString());
                continue;}
            //Exclude shopsurls from loop and saves keys to ArrayList for later use
            if(allShopsLinks.contains(detail.toString())){
                productShopsUrl.add(detail.toString());
                continue;}

            Specifications += detail.toString() +": "+ object.get(detail.toString())+"</br>";

        }
        //Create Product
        Product product = new Product(EAN, Type, Merk, Productnaam, Specifications);
        //Add to productlist
        products.add(product);

       // if(productShops.size() != productShopsUrl.size())System.out.println("Winkelprijs mist de url");
        for(int i = 0; i < productShops.size()-1; i++){
            //Gather additional data and refs
            Winkel winkel = getShop(productShops.get(i));
            String url = null;
            if(i<productShopsUrl.size()-1)url=object.get(productShopsUrl.get(i)).toString();
            Euroformatter prijs = new Euroformatter(object.getDouble(winkel.getNaam()));

            //Create a productWinkel
            ProductWinkel productWinkel = new ProductWinkel(winkel, product, prijs, url);
            productWinkels.add(productWinkel);

           // System.out.println(winkel.getNaam()+", "+url+"\n"+prijs);
        }
       // System.out.println("Aantal winkels: "+productShops.size());
        //return product;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Winkel> getShops() {
        return shops;
    }

    public Winkel getShop(String name){
        for(Winkel winkel : shops){
            if(winkel.getNaam().equals(name)){
                return winkel;
            }
        }
        return null;
    }
    public List<ProductWinkel> getProductWinkels() {
        return productWinkels;
    }

    private List<String> getShopNames(){
        List result = new ArrayList<String>();
        for(Winkel winkel : shops){
            result.add(winkel.getNaam());
        }
        return result;
    }

}

 /*

        String AzertyURL = null;
        Double AzertyPrice = null;
        String AlternateURL = null;
        Double AlternatePrice = null;
        String AfutureURL = null;
        Double AfuturePrice = null;


        if(current.getString("link-Azerty")!=null&&current.getString("Azerty")!=null) {
            AzertyURL = current.getString("link-Azerty");
            AzertyPrice = current.getDouble("Azerty");
        }
        if(current.getString("link-Alternate")!=null&&current.getString("Alternate")!=null) {
            AlternateURL = current.getString("link-Alternate");
            AlternatePrice = current.getDouble("Alternate");
        }
        if(current.getString("link-Afuture")!=null&&current.getString("Afuture")!=null) {
            AfutureURL = current.getString("link-Afuture");
            AfuturePrice = current.getDouble("Afuture");
        }
*/
// Product product = new Product(EAN, Type,Merk,Productnaam);
//      if(AfuturePrice!=null&&AfutureURL!=null) product.setAfuturePrice(AfuturePrice, AfutureURL);
//    if(AlternatePrice!=null&&AlternateURL!=null) product.setAlternatePrice(AlternatePrice,AlternateURL);
//   if(AzertyPrice!=null&&AzertyURL!=null) product.setAzertyPrice(AzertyPrice,AzertyURL);
//  return Product;