package com.uth.avapa2.proyecto.views.inicio;

import com.flowingcode.vaadin.addons.carousel.Carousel;
import com.flowingcode.vaadin.addons.carousel.Slide;
import com.uth.avapa2.proyecto.views.MainLayout;
import com.vaadin.flow.component.Component;
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
        addClassName("inicio-view");
        this.getElement().getStyle().set("height", "99%");
        this.getElement().getStyle().set("background-image",
                "url('https://blogs.iadb.org/integracion-comercio/wp-content/uploads/sites/14/2020/06/Aduanas.jpg')");
        setMargin(false);
        setPadding(false);
        add(CrearCarousel());
    }

    private Component CrearCarousel() {
        VerticalLayout ver = new VerticalLayout();
        ver.addClassName("vertical_layout");
        ver.setMargin(false);
        ver.setPadding(false);
        ver.setJustifyContentMode(JustifyContentMode.CENTER);
        ver.setAlignItems(Alignment.CENTER);
        
        VerticalLayout contenedor_carousel = new VerticalLayout();
        contenedor_carousel.setMargin(false);
        contenedor_carousel.setPadding(false);
        contenedor_carousel.getStyle().set("width","80%");
        contenedor_carousel.getStyle().set("height","80%");

        Slide s1 = new Slide(slideCustom("https://www.dropbox.com/s/o1sscybu5sktu37/slide2.jpg?dl=1"));
        Slide s2 = new Slide(slideCustom("https://www.dropbox.com/s/xphfcq68eaw6jwc/slide1.jpg?dl=1"));
        Slide s3 = new Slide(slideCustom("https://www.dropbox.com/s/uhjjjejmkap4dtm/slide3.jpg?dl=1"));
        Slide s4 = new Slide(slideCustom("https://www.dropbox.com/s/n3sgqz0dr5hvgnb/slide4.jpg?dl=1"));
        Slide s5 = new Slide(slideCustom("https://www.dropbox.com/s/4td0o1x94jiqtc1/slide5.jpg?dl=1"));
        Slide s6 = new Slide(slideCustom("https://www.dropbox.com/s/xv74ozkp6gm7sbx/slide6.jpg?dl=1"));

        Carousel c =
        new Carousel(s1, s2, s3, s4, s5,s6)
            .withAutoProgress()
            .withSlideDuration(15)
            .withStartPosition(1)
            .withoutSwipe();
        
        contenedor_carousel.add(c);
        c.setWidth("100%");
        c.setSizeFull();
        c.setMinHeight("100%");
        ver.add(contenedor_carousel);
        return ver;
    }

    private Component slideCustom(String url_img) {
        HorizontalLayout h= new HorizontalLayout();
        VerticalLayout d = new VerticalLayout();
        d.getStyle().set("background-image","url("+url_img+")");
        d.getStyle().set("background-size","cover");
        d.getStyle().set("background-position","center center");
        d.getStyle().set("background-repeat","no-repeat");
        d.getStyle().set("background-color","#66999");
        d.setAlignItems(Alignment.CENTER);
        d.setSizeFull();
        return d;
    }

}
