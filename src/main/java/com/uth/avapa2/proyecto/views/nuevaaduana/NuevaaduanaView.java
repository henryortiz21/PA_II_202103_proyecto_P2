package com.uth.avapa2.proyecto.views.nuevaaduana;

import com.uth.avapa2.proyecto.data.entity.Aduana;
import com.uth.avapa2.proyecto.data.service.SamplePersonService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.uth.avapa2.proyecto.views.MainLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.dependency.Uses;

@PageTitle("Nueva aduana")
@Route(value = "nuevaAduana", layout = MainLayout.class)
@Uses(Icon.class)
public class NuevaaduanaView extends Div {

    private TextField nombre = new TextField("Nombre");
    private TextArea ubicación = new TextArea("Ubicación");
    private TextField via_acceso = new TextField("Vía de acceso terrestre");
    private ComboBox<String> categoria = new ComboBox<>("Categoria");
    private ComboBox<String> zona = new ComboBox<>("Zona");
    private TextField telefono = new TextField("Telefono");

    private Button bCancelar = new Button("Cancelar");
    private Button bGuardar = new Button("Guardar");

    private Binder<Aduana> binder = new Binder(Aduana.class);

    public NuevaaduanaView(SamplePersonService personService) {
        addClassName("nuevaaduana-view");
        categoria.setItems("Aerea","Terrestre","Portuaria");
        zona.setItems("Centro-Sur","Nor-Occidental","Nor-Oriental");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        clearForm();

        bCancelar.addClickListener(e -> clearForm());
        bGuardar.addClickListener(e -> {
            personService.update(binder.getBean());
//            Notification.show(binder.getBean().getClass().getSimpleName() + " details stored.");
            Notification.show("Detalle de aduana almacenado");
            clearForm();
        });
    }

    private void clearForm() {
        binder.setBean(new Aduana());
    }

    private Component createTitle() {
        return new H3("Nueva aduana");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(nombre, ubicación, via_acceso, categoria, zona, telefono);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        bGuardar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(bGuardar);
        buttonLayout.add(bCancelar);
        return buttonLayout;
    }
}
