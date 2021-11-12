package com.uth.avapa2.proyecto.views.listadeusuarios;
import java.util.Optional;

import com.uth.avapa2.proyecto.data.entity.Usuario;
import com.uth.avapa2.proyecto.data.service.UsuarioService;
import com.uth.avapa2.proyecto.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.artur.helpers.CrudServiceDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.component.textfield.TextField;

@PageTitle("Lista de usuarios")
@Route(value = "listausuarios/:usuarioID?/:action?(edit)", layout = MainLayout.class)
public class ListadeusuariosView extends Div implements BeforeEnterObserver {

    private final String USUARIO_ID = "usuarioID";
    private final String USUARIO_EDIT_ROUTE_TEMPLATE = "listausuarios/%d/edit";

    private Grid<Usuario> grid = new Grid<>(Usuario.class, false);

    private TextField nombre;
    private TextField apellido;
    private TextField telefono;
    private TextField email;

    private Button bCancelar = new Button("Cancelar");
    private Button bGuardar = new Button("Guardar");

    private BeanValidationBinder<Usuario> binder;

    private Usuario usuario;

    private UsuarioService usuarioService;

    public ListadeusuariosView(@Autowired UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        addClassNames("listadeusuarios-view", "flex", "flex-col", "h-full");
        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("nombre").setAutoWidth(true);
        grid.addColumn("apellido").setAutoWidth(true);
        grid.addColumn("telefono").setAutoWidth(true);
        grid.addColumn("email").setAutoWidth(true);
        grid.setDataProvider(new CrudServiceDataProvider<>(usuarioService));
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(USUARIO_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
            } else {
                clearForm();
                UI.getCurrent().navigate(ListadeusuariosView.class);
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(Usuario.class);

        // Bind fields. This where you'd define e.g. validation rules

        binder.bindInstanceFields(this);

        bCancelar.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        bGuardar.addClickListener(e -> {
            try {
                if (this.usuario == null) {
                    this.usuario = new Usuario();
                }
                binder.writeBean(this.usuario);

                usuarioService.update(this.usuario);
                clearForm();
                refreshGrid();
                Notification.show("Usuario details stored.");
                UI.getCurrent().navigate(ListadeusuariosView.class);
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the usuario details.");
            }
        });

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Integer> usuarioId = event.getRouteParameters().getInteger(USUARIO_ID);
        if (usuarioId.isPresent()) {
            Optional<Usuario> usuarioFromBackend = usuarioService.get(usuarioId.get());
            if (usuarioFromBackend.isPresent()) {
                populateForm(usuarioFromBackend.get());
            } else {
                Notification.show(String.format("The requested usuario was not found, ID = %d", usuarioId.get()), 3000,
                        Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(ListadeusuariosView.class);
            }
        }
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("flex flex-col");
        editorLayoutDiv.setWidth("400px");

        Div editorDiv = new Div();
        editorDiv.setClassName("p-l flex-grow");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        nombre = new TextField("Nombre");
        apellido = new TextField("Apellido");
        telefono = new TextField("Telefono");
        email = new TextField("Email");
        Component[] fields = new Component[]{nombre, apellido, telefono, email};

        for (Component field : fields) {
            ((HasStyle) field).addClassName("full-width");
        }
        formLayout.add(fields);
        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("w-full flex-wrap bg-contrast-5 py-s px-l");
        buttonLayout.setSpacing(true);
        bCancelar.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        bGuardar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(bGuardar, bCancelar);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Usuario value) {
        this.usuario = value;
        binder.readBean(this.usuario);

    }
}
