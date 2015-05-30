package com.BS4Project.View;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Peter on 21-5-2015.
 */
public class IndexView extends VerticalLayout{

    public IndexView(){
        // Create layouts
        HorizontalLayout layout = new HorizontalLayout();
        VerticalLayout l = new VerticalLayout();
        VerticalLayout r = new VerticalLayout();

        layout.addComponent(l);
        layout.addComponent(r);
        // set demensions for layouts
        l.setWidth(500.0f, Sizeable.Unit.PIXELS);
        r.setWidth(500.0f, Sizeable.Unit.PIXELS);

        VerticalLayout verticalLayout = new VerticalLayout();
        // add components to left layout
        verticalLayout.addComponent(new Label("Welkom op de website van Build my PC."));
        verticalLayout.addComponent(new Label("Deze website bied mogelijkheden om computercomponenten uit te zoeken, een builder om uw eigen PC samen te stellen." +
                "En om het af te maken kunt u prijzen van zes winkels vergelijken. De winkelwagen, die u voor uw boodschappenlijstje kunt gebruiken, helpt u daar ook nog eens bij. \n \n " +
                "Dus zoekt u losse onderdelen of wilt u uw nieuwe computer zelf samenstellen, kunt u links in het menu direct aan de slag" +
                " \n \n Heeft u vragen aan ons kunt u contact met ons opnemen via het contactforumier op de 'over ons'-pagina."));

        l.addComponent(verticalLayout);
        l.setComponentAlignment(verticalLayout, Alignment.MIDDLE_CENTER);

        addComponent(layout);
    }
}
