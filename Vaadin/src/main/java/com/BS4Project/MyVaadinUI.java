package com.BS4Project;

import com.BS4Project.Controller.ApplicationController;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;


@Theme("BS4Theme")
@SuppressWarnings("serial")
@Title("Build my PC")
public class MyVaadinUI extends UI
{
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "com.BS4Project.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
        @Override
        public void init(ServletConfig servletConfig) throws ServletException {
            super.init(servletConfig);
        }
    }
    ApplicationController applicationController = null;
    private VerticalLayout main;
    private VerticalLayout content;
    HorizontalLayout top, mid, bot;

    Label PageTitle;


    @Override
    protected void init(VaadinRequest request) {
        main = new VerticalLayout();

        applicationController = new ApplicationController();
        initTop();
        initMid();
        initBot();

        setContent(main);

    }

    private void initTop() {
        top = new HorizontalLayout();
        main.addComponent(top);

        top.setSizeFull();
        top.setHeight("75px");

        Label label = new Label("Build my PC");
        label.setStyleName("h2");
        label.setWidthUndefined();
        top.addComponent(label);
        top.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
    }

    private void initMid() {
        mid = new HorizontalLayout();
        mid.setHeightUndefined();

        final Object[][] treeitem = new Object[][]{
                new Object[]{"Startpagina"},
                new Object[]{"Onderdelen", "Processors", "Behuizingen", "Casefans", "Videokaarten", "Geheugenmodules", "Moederborden", "Voedingen", "Geluidskaarten", "Harddisks/SSD's", "Optische drives"},

        };

        final Tree tree = new Tree();
        tree.setWidth("210px");

        for (Object[] aTreeitem : treeitem) {
            String menuTree = (String) (aTreeitem[0]);
            tree.addItem(menuTree);

            if (aTreeitem.length == 1) {
                // The menuItems has no submenus so make it a leaf.
                tree.setChildrenAllowed(menuTree, false);
            } else {
                // Add children under the MenuItems.
                for (int j = 1; j < aTreeitem.length; j++) {
                    String subTree = (String) aTreeitem[j];

                    // Add the item as a regular item.
                    tree.addItem(subTree);

                    // Set it to be a child.
                    tree.setParent(subTree, menuTree);

                    // Make the subitem look like leaves.
                    tree.setChildrenAllowed(subTree, false);
                }
            }
        }
        tree.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                if(tree.getValue() != null){

                    setCurrentPage(tree.getValue().toString());
                }
            }
        });

        mid.addComponent(tree);

        content = new VerticalLayout();
        content.addComponent(applicationController.Index());
        content.setWidth((int) (Page.getCurrent().getBrowserWindowWidth() - tree.getWidth())+"px");
        content.setStyleName("contentStyle");
        mid.addComponent(content);

        main.addComponent(mid);

    }


    private void initBot() {
        bot = new HorizontalLayout();
        main.addComponent(bot);

        bot.setHeight("50px");
        bot.setWidth("100%");

    }


    private void setCurrentPage (String page){
        if(page.equals("Startpagina")){
            content.removeAllComponents();
            content.addComponent(applicationController.Index());
        }else if(page.equals("Onderdelen")){
            content.removeAllComponents();
            content.addComponent(applicationController.Products());
        }else {
            content.removeAllComponents();
            content.addComponent(applicationController.Products(page));
        }
    }


}

