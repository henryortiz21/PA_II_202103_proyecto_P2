package com.uth.avapa2.proyecto.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.crud.CrudI18n;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.uth.avapa2.proyecto.views.MainLayout;
import com.uth.avapa2.proyecto.views.inicio.InicioView;
import com.uth.avapa2.proyecto.views.subasta.SubastaView;
import com.uth.avapa2.proyecto.views.listadeproductos.ListadeproductosView;
import com.uth.avapa2.proyecto.views.nuevoproducto.NuevoproductoView;
import com.uth.avapa2.proyecto.views.listadeusuarios.ListadeusuariosView;
import com.uth.avapa2.proyecto.views.nuevousuario.NuevousuarioView;
import com.uth.avapa2.proyecto.views.listadeaduanas.ListadeaduanasView;
import com.uth.avapa2.proyecto.views.nuevaaduana.NuevaaduanaView;
import com.vaadin.flow.component.avatar.Avatar;

/** The main view is a top-level placeholder for other views. */
@PWA(name = "AduaSoft", shortName = "AduaSoft", enableInstallPrompt = false)
@Theme(themeFolder = "aduasoft", variant = Lumo.DARK)
@PageTitle("Main")
public class MainLayout extends AppLayout {

  public static class MenuItemInfo {

    private String text;
    private String iconClass;
    private Class<? extends Component> view;

    public MenuItemInfo(String text, String iconClass, Class<? extends Component> view) {
      this.text = text;
      this.iconClass = iconClass;
      this.view = view;
    }

    public String getText() {
      return text;
    }

    public String getIconClass() {
      return iconClass;
    }

    public Class<? extends Component> getView() {
      return view;
    }
  }

  private H1 viewTitle;

  private Button bIniciar;

  public MainLayout() {
    setPrimarySection(Section.DRAWER);
    addToNavbar(true, createHeaderContent());
    addToDrawer(createDrawerContent());
  }

  private Component createHeaderContent() {
    DrawerToggle toggle = new DrawerToggle();
    toggle.addClassName("text-secondary");
    toggle.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
    toggle.getElement().setAttribute("aria-label", "Menu toggle");

    viewTitle = new H1();
    viewTitle.addClassNames("m-0", "text-l");

    Header header = new Header(toggle, viewTitle);
    header.addClassNames(
        "bg-base",
        "border-b",
        "border-contrast-10",
        "box-border",
        "flex",
        "h-xl",
        "items-center",
        "w-full");
    HorizontalLayout hl = new HorizontalLayout();

    viewTitle.setWidthFull();
    hl.setWidthFull();
    hl.setSpacing(false);
    hl.setPadding(false);
    hl.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
    hl.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
    {
      bIniciar = new Button("Conectarse", new Icon(VaadinIcon.USER));
      bIniciar.getStyle().set("padding", "5px");
      bIniciar.getStyle().set("margin", "10px");
      bIniciar.getStyle().set("border-radius", "0px");
      bIniciar.getStyle().set("border", "none");
    }
    hl.add(bIniciar);
    header.add(hl);

    return header;
  }

  private Component createDrawerContent() {
    H2 appName = new H2("AduaSoft");
    appName.addClassNames("flex", "items-center", "h-xl", "m-0", "px-m", "text-m");

    com.vaadin.flow.component.html.Section section =
        new com.vaadin.flow.component.html.Section(appName, createNavigation(), createFooter());
    section.addClassNames("flex", "flex-col", "items-stretch", "max-h-full", "min-h-full");
    return section;
  }

  private Nav createNavigation() {
    Nav nav = new Nav();
    nav.addClassNames("border-b", "border-contrast-10", "flex-grow", "overflow-auto");
    nav.getElement().setAttribute("aria-labelledby", "views");

    H3 views = new H3("Views");
    views.addClassNames("flex", "h-m", "items-center", "mx-m", "my-0", "text-s", "text-tertiary");
    views.setId("views");

    // Wrap the links in a list; improves accessibility
    UnorderedList list = new UnorderedList();
    list.addClassNames("list-none", "m-0", "p-0");
    nav.add(list);

    for (RouterLink link : createLinks()) {
      ListItem item = new ListItem(link);
      list.add(item);
    }
    return nav;
  }

  private List<RouterLink> createLinks() {
    MenuItemInfo[] menuItems =
        new MenuItemInfo[] { //
          new MenuItemInfo("Inicio", "la la-globe", InicioView.class), //
          new MenuItemInfo("Subasta", "la la-th-list", SubastaView.class), //
          new MenuItemInfo("Lista de productos", "la la-list", ListadeproductosView.class), //
          new MenuItemInfo("Nuevo producto", "la la-user", NuevoproductoView.class), //
          new MenuItemInfo("Lista de usuarios", "la la-list", ListadeusuariosView.class), //
          new MenuItemInfo("Nuevo usuario", "la la-user", NuevousuarioView.class), //
          new MenuItemInfo("Lista de aduanas", "la la-list", ListadeaduanasView.class), //
          new MenuItemInfo("Nueva aduana", "la la-user", NuevaaduanaView.class), //
        };
    List<RouterLink> links = new ArrayList<>();
    for (MenuItemInfo menuItemInfo : menuItems) {
      links.add(createLink(menuItemInfo));
    }
    return links;
  }

  private static RouterLink createLink(MenuItemInfo menuItemInfo) {
    RouterLink link = new RouterLink();
    link.addClassNames("flex", "mx-s", "p-s", "relative", "text-secondary");
    link.setRoute(menuItemInfo.getView());

    Span icon = new Span();
    icon.addClassNames("me-s", "text-l");
    if (!menuItemInfo.getIconClass().isEmpty()) {
      icon.addClassNames(menuItemInfo.getIconClass());
    }

    Span text = new Span(menuItemInfo.getText());
    text.addClassNames("font-medium", "text-s");

    link.add(icon, text);
    return link;
  }

  private Footer createFooter() {
    Footer layout = new Footer();
    layout.addClassNames("flex", "items-center", "my-s", "px-m", "py-xs");
    //        H1 h1= new H1("PROBANDO...");
    //        layout.add(h1);
    return layout;
  }

  @Override
  protected void afterNavigation() {
    super.afterNavigation();
    viewTitle.setText(getCurrentPageTitle());
  }

  private String getCurrentPageTitle() {
    PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
    return title == null ? "" : title.value();
  }
}
