package com.uth.avapa2.proyecto.views.inicio;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.uth.avapa2.proyecto.views.MainLayout;
import com.vaadin.flow.router.RouteAlias;


@PageTitle("Inicio")
@Route(value = "inicio", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class InicioView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;

    public InicioView() {
        //setJustifyContentMode(JustifyContentMode.CENTER);
        //setVerticalComponentAlignment(Alignment.CENTER);
        setMargin(true);
/*        setMargin(true);
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        add(name, sayHello);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });
 */
        add(labels());
    }

    private Component labels() {
        VerticalLayout lver=new VerticalLayout();
        lver.setJustifyContentMode(JustifyContentMode.CENTER);
        lver.setAlignItems(Alignment.CENTER);
        lver.getStyle().set("border", "1px solid #9E9E9E");
        lver.getStyle().set("border-radius", "20px");
        lver.setMargin(true);
        H1 h1= new H1("ESTA ES LA PAGINA DE INICIO");
        h1.getStyle().set("border-radius", "20px");
        lver.setWidth("100%");
        lver.setWidthFull();
        lver.add(h1);
        return lver;
    }


}
