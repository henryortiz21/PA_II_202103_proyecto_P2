package com.uth.avapa2.proyecto.views.listadeaduanas;

import java.util.Arrays;
import java.util.List;

import com.uth.avapa2.proyecto.data.entity.Aduana;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.uth.avapa2.proyecto.views.MainLayout;

@PageTitle("Lista de aduanas")
@Route(value = "aduanas", layout = MainLayout.class)
public class ListadeaduanasView extends Div implements AfterNavigationObserver {

  Grid<Aduana> grid = new Grid<>();

  public ListadeaduanasView() {
    addClassName("listadeaduanas-view");
    setSizeFull();
    grid.setHeight("100%");
    grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
    grid.addComponentColumn(aduana -> createCard(aduana));
    add(grid);
  }

  private HorizontalLayout createCard(Aduana aduana) {
    HorizontalLayout card = new HorizontalLayout();
    card.addClassName("card");
    card.setSpacing(false);
    card.getThemeList().add("spacing-s");

    Image image = new Image();
    image.setHeight("100%");
    image.setSrc(aduana.getImage());
    VerticalLayout ubicacion = new VerticalLayout();
    ubicacion.addClassName("ubicacion");
    ubicacion.setSpacing(false);
    ubicacion.setPadding(false);

    HorizontalLayout header = new HorizontalLayout();
    header.addClassName("header");
    header.setSpacing(false);
    header.getThemeList().add("spacing-s");

    Span name = new Span(aduana.getNombre());
    name.addClassName("nombre");
    Span date = new Span(aduana.getCategoria());
    date.addClassName("categoria");
    header.add(name, date);

    Span post = new Span(aduana.getTelefono());
    post.addClassName("telefono");

    HorizontalLayout actions = new HorizontalLayout();
    actions.addClassName("actions");
    actions.setSpacing(false);
    actions.getThemeList().add("spacing-s");

    ubicacion.add(header, post, actions);
    card.add(image, ubicacion);
    return card;
  }

  @Override
  public void afterNavigation(AfterNavigationEvent event) {

    List<Aduana> aduanas =
        Arrays.asList(
            createAduana(
                "Toncontin",
                "https://revistasumma.com/wp-content/uploads/2017/02/Palmerola.jpg",
                "La Aduana de Toncontín está ubicada en la zona central del país; en Tegucigalpa, Departamento de Francisco Morazán.",
                "Vía de acceso terrestre: Corredor Logístico 391 km",
                "Aerea Terrestre",
                "Zona Centro Sur",
                "2550-4567"));

    grid.setItems(aduanas);
  }

  private static Aduana createAduana(
      String nombre,
      String image,
      String ubicacion,
      String via_acceso,
      String categoria,
      String zona,
      String telefono) {
    Aduana p = new Aduana();
    p.setNombre(nombre);
    p.setImage(image);
    p.setUbicacion(ubicacion);
    p.setVia_acceso(via_acceso);
    p.setCategoria(categoria);
    p.setZona(zona);
    p.setTelefono(telefono);
    return p;
  }
}
