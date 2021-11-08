package com.uth.avapa2.proyecto.views.nuevousuario;

import com.uth.avapa2.proyecto.data.entity.Usuario;
import com.uth.avapa2.proyecto.data.service.UsuarioService;
import com.uth.avapa2.proyecto.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Nuevo usuario")
@Route(value = "nuevoUsuario", layout = MainLayout.class)
@Uses(Icon.class)
public class NuevousuarioView extends Div {

    private TextField nombre=new TextField("Nombre");
    private TextField apellido=new TextField("Apellido");
    private TextField telefono=new TextField("Telefono");
    private EmailField email=new EmailField("Correo electronico");
    private PasswordField pass1= new PasswordField("Contraseña");
    private PasswordField pass2= new PasswordField("Repetir contraseña");


    private Button bCancelar = new Button("Cancelar");
    private Button bGuardar = new Button("Guardar");

    private Binder<Usuario> binder = new Binder(Usuario.class);

    public NuevousuarioView(UsuarioService usuarioService) {
        addClassName("nuevousuario-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        clearForm();

        bCancelar.addClickListener(e -> clearForm());
        bGuardar.addClickListener(e -> {
            usuarioService.update(binder.getBean());
            //Notification.show(binder.getBean().getClass().getSimpleName() + " details stored.");
            Notification.show("Nuevo usuario creado exitosamente");
            clearForm();
        });
    }

    private void clearForm() {
        binder.setBean(new Usuario());
        pass1.setValue("");
        pass2.setValue("");
    }

    private Component createTitle() {
        return new H3("Nuevo usuario");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Por favor introduzca una direccion de email válida.");
        formLayout.add(nombre,apellido,telefono,email,pass1,pass2);
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
