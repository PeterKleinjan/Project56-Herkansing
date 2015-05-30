package com.BS4Project.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter on 21-5-2015.
 */
public class ProductList {
    private List<Product> productList;
    private List<ProductWinkel>productWinkelList;
    DataConverter data = null;

    public ProductList(){
        //System.out.println("ProductList wordt aangeroepen");
        data = new DataConverter();
        productList = data.getProducts();
        productWinkelList = data.getProductWinkels();
    }

    public List<Product> getProductList() {
        //System.out.println("Productlijst wordt vanaf de ProductList doorgegeven, aantal: "+ productList.size());
        //System.out.println(productList.get(0).getProductnaam());
        return productList;
    }

    public List<Product> getProductType(String Type){
        List<Product> result = new ArrayList<Product>();
        for(Product product : productList){
            if(product.getType().equals(Type))result.add(product);
        }
        return result;
    }

    public List<ProductWinkel> getProductWinkels(Product product) {
        List<ProductWinkel> result = new ArrayList<ProductWinkel>();
        for(ProductWinkel item : productWinkelList){
            if(item.product.equals(product)) result.add(item);
        }
        return result;
    }

    public Euroformatter getLowestPrice(Product product){
        Euroformatter result = null;
        for(ProductWinkel shop : getProductWinkels(product)){
            if(result == null)result = shop.getPrijs();
            else if(shop.getPrijs().compareTo(result)<0)result = shop.getPrijs();

        }
        return result;
    }

    public Product getProductByName(String name){
        for(Product product : productList){
            if(product.getProductnaam().equals(name))return product;
        }
        return null;
    }

}
