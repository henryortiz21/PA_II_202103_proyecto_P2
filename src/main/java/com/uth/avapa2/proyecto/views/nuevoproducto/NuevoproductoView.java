package com.uth.avapa2.proyecto.views.nuevoproducto;

import com.uth.avapa2.proyecto.data.entity.Producto;
import com.uth.avapa2.proyecto.data.service.SamplePersonService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.uth.avapa2.proyecto.views.MainLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.dependency.Uses;

@PageTitle("Nuevo producto")
@Route(value = "nuevoProducto", layout = MainLayout.class)
@Uses(Icon.class)
public class NuevoproductoView extends Div {

    //private NumberField id = new NumberField("Id");
    private TextField codigo = new TextField("Codigo de producto");
    private TextArea descripcion = new TextArea("Descripcion");
    private ComboBox<String> cEstado= new ComboBox("Estado");
    private DatePicker fIngreso = new DatePicker("Fecha de ingreso");
    private TextField marca = new TextField("Marca");
    private TextField lugarorigen = new TextField("Lugar de origen");

    private Button bCancelar = new Button("Cancelar");
    private Button bGuardar = new Button("Guardar");

    private Binder<Producto> binder = new Binder(Producto.class);

    public NuevoproductoView(SamplePersonService personService) {
        addClassName("nuevoproducto-view");

        cEstado.setItems("Almacenado", "Retirado");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        clearForm();

        bCancelar.addClickListener(e -> clearForm());
        bGuardar.addClickListener(e -> {
//            personService.update(binder.getBean());
            Notification.show(binder.getBean().getClass().getSimpleName() + " details stored.");
            clearForm();
        });
    }

    private void clearForm() {
        binder.setBean(new Producto());
    }

    private Component createTitle() {
        return new H3("Nuevo producto");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        //email.setErrorMessage("Please enter a valid email address");
        //formLayout.add(id, codigo, descripcion,cEstado, fIngreso, marca, lugarorigen);
        formLayout.add(codigo, descripcion,cEstado, fIngreso, marca, lugarorigen);
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
