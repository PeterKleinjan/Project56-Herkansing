package com.BS4Project.View;

import com.BS4Project.Model.Euroformatter;
import com.BS4Project.Model.Product;
import com.BS4Project.Model.ProductWinkel;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;

import java.util.List;

/**
 * Created by Peter on 26-5-2015.
 */
public class ProductView extends Window {

    public ProductView(Product product, List<ProductWinkel> shops, Euroformatter lowestPrice){
        // Set the properties for this given window
        this.setStyleName("Productinfo");
        this.setWidth("75%");
        this.setHeight("75%");
        this.center();
        this.setResizable(false);
        this.setModal(true);
        this.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent closeEvent) {
                try {
                    this.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });

        // Create a layout for inside the window
        HorizontalLayout mainWindowLayout = new HorizontalLayout();
        mainWindowLayout.setWidth("100%");
        //Left, right
        VerticalLayout productWindowLayout = new VerticalLayout();
        VerticalLayout prijsWindowLayout = new VerticalLayout();
        productWindowLayout.setWidth("80%");
        prijsWindowLayout.setWidth("50%");
        //Add them to main
        mainWindowLayout.addComponent(productWindowLayout);
        mainWindowLayout.addComponent(prijsWindowLayout);
        // set the content of the window to display the vertical layout earlier made in the ValueChangeListener
        this.setContent(mainWindowLayout);
        // Set style for prijs and info parts
        productWindowLayout.setStyleName("Productinfotext");
        prijsWindowLayout.setStyleName("Productinfotext");

        Label leftSide = new Label("<html><h2 style='margin-bottom=4px;'>"+ product.getMerk()+" "+
        product.getProductnaam() +"</h2>" +
        "<b>Specificaties:</b><br/><hr/>"+product.getSpecifications()+"</html>", ContentMode.HTML);

        productWindowLayout.addComponent(leftSide);

        String winkelprijzen = "";

        for(final ProductWinkel shop : shops){
            String prijs = shop.getPrijs().toString();
            if(shop.getPrijs().toString().equals(lowestPrice.toString())) prijs = "<span style='color: #31781F'>"+prijs+"</span>";
            winkelprijzen += "<h3><b>"+ prijs + "</b>  " + shop.getWinkel().getNaam() + "</h3>  " +
                    "<form action='"+shop.getURL()+"' target='_blank'>" +
                    "    <input type='submit' value='Bekijk het product bij "+shop.getWinkel().getNaam()+"'>" +
                    "</form>" +
                    ""+"<br><hr>";
        }
        Label rightSide = new Label(winkelprijzen, ContentMode.HTML);
        prijsWindowLayout.addComponent(rightSide);


        //prijsWindowLayout.addComponent(addLinks(product));


        // Add the window to the browser screen
        // UI.getCurrent().addWindow(productWindow);
    }

}
