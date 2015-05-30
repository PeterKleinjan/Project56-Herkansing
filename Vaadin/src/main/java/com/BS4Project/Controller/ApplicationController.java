package com.BS4Project.Controller;

import com.BS4Project.Model.Product;
import com.BS4Project.Model.ProductList;
import com.BS4Project.Model.ProductWinkel;
import com.BS4Project.View.IndexView;
import com.BS4Project.View.ProductView;
import com.BS4Project.View.ProductlistView;
import com.vaadin.data.Property;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

/**
 * Created by Peter on 21-5-2015.
 */
public class ApplicationController {

    ProductList productList;

    public ApplicationController(){
       // System.out.println("Controller wordt aangemaakt");
       productList = new ProductList();
    }

    public VerticalLayout Index(){
        VerticalLayout layout = new IndexView();
        return layout;
    }

    public VerticalLayout Products(){
        return new ProductlistView(productList, productList.getProductList(), "Alle onderdelen");
    }
    public VerticalLayout Products(String Type){
        return new ProductlistView(productList, productList.getProductType(Type), Type);
    }

    public Property.ValueChangeListener valueChangeListener(final Table table){
        Property.ValueChangeListener changeListener = new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                if(table.getValue() != null){
                    Product product = productList.getProductByName(table.getValue().toString());
                    List<ProductWinkel> shops = productList.getProductWinkels(product);
                    ProductView detailScreen = new ProductView(product, shops, productList.getLowestPrice(product));

                    UI.getCurrent().addWindow(detailScreen);
                }
            }
        };

        return changeListener;
    }


}
