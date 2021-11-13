package com.uth.avapa2.proyecto.views.inicio;

import com.uth.avapa2.proyecto.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;


@PageTitle("Inicio")
@Route(value = "inicio", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class InicioView extends HorizontalLayout {


    public InicioView() {
        this.getElement().getStyle().set("height","99%");
        this.getElement().getStyle().set("background-image" ,
                "url('https://blogs.iadb.org/integracion-comercio/wp-content/uploads/sites/14/2020/06/Aduanas.jpg')" );
        setMargin(false);
        add(labels());
    }

    private Component labels() {
        VerticalLayout lver=new VerticalLayout();
        lver.setJustifyContentMode(JustifyContentMode.CENTER);
        lver.setAlignItems(Alignment.CENTER);
        lver.setMargin(false);
        H1 h1= new H1("ESTA ES LA PAGINA DE INICIO");
        h1.getStyle().set("border-radius", "20px");
        lver.setWidth("100%");
        lver.setWidthFull();
        lver.add(h1);
        return lver;
    }

}
