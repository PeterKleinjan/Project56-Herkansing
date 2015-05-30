package com.BS4Project.View;

import com.BS4Project.Controller.ApplicationController;
import com.BS4Project.Model.Euroformatter;
import com.BS4Project.Model.Product;
import com.BS4Project.Model.ProductList;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

/**
 * Created by Peter on 21-5-2015.
 */
public class ProductlistView extends VerticalLayout {

    public ProductlistView(ProductList productList, List<Product> products, String titel) {
        setWidth("100%");
        setSizeFull();

        // Create table for components
        Table table = new Table(titel);

        // Set properties for component table.
        table.setWidth("98%");
        table.setSelectable(true);
        table.setImmediate(true);

        table.addContainerProperty("EAN", String.class, null);
        table.addContainerProperty("Productnaam", String.class, null);
        table.addContainerProperty("Merk", String.class, null);
        table.addContainerProperty("Laagste prijs", Euroformatter.class, null);

        for (Product product : products) {
            //System.out.println(product.getProductnaam());


            table.addItem(new Object[]{product.getEAN(), product.getProductnaam(), product.getMerk(), productList.getLowestPrice(product)}, product.getProductnaam());
        }

        //Add valuechangelistener
        table.addValueChangeListener(new ApplicationController().valueChangeListener(table));

        // Lastly, add the table to the main layout (Most Important!!)
        addComponent(table);
    }

}