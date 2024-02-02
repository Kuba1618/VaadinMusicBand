package com.example.application.views.song.dedication;

import com.example.application.data.Song;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;

import java.util.ArrayList;
import java.util.List;

@PageTitle("DedicationsView")
@Route(value = "dedications", layout = MainLayout.class)
public class DedicationsView extends VerticalLayout {

    public ComboBox<String> cmbBox;
    public Button saveBtn;
    public TextArea textArea;
    public VerticalLayout layoutColumn2 = new VerticalLayout();
    private static final List<Dedication> listOfDedications = new ArrayList<>();
    private static Grid<Dedication> grid = new Grid<>();
    private static Div hint = new Div();
    public int width;
    public int height;
    public DedicationsView(){

        width = isMobileDevice() ? 98 : 50;

        layoutColumn2.setWidthFull();
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layoutColumn2.setAlignItems(FlexComponent.Alignment.CENTER);

        createDynamicGrid();

        add(layoutColumn2);
    }
    public void createDynamicGrid(){
        this.setupGrid();
        this.createAddDedicationForm();
        this.refreshGrid();
    }
    public void createAddDedicationForm(){
        cmbBox = new ComboBox<>("Song");
        cmbBox.setWidth(width + "%");
        cmbBox.setHeight("15%");
        setListOfSongsTitleInCmbBox(cmbBox);
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, cmbBox);
        layoutColumn2.add(cmbBox);

        textArea = new TextArea("Description");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, textArea);
        textArea.setWidth(width + "%");
        textArea.setHeight("65%");
        layoutColumn2.add(textArea);

        saveBtn = new Button("Save");
        saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, saveBtn);
        saveBtn.addClickListener((ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
            Notification.show("SaveBtn Clicked !");
            Dedication dedication = new Dedication(cmbBox.getValue(),"JAZZ",textArea.getValue(),"uzupelnij"); // @ToDo uzupełnić parametrami
            saveDedication(dedication);
            cmbBox.setValue(null);
        });
        layoutColumn2.add(saveBtn);
    }

    private void setupGrid() {

        grid = new Grid<>(Dedication.class, false);
        grid.setWidth("100%");
        grid.setAllRowsVisible(true);
        grid.addColumn(Dedication::getTitle).setHeader("Title").setAutoWidth(true); //@ToDo opracować funkcję(zmianę szerokości kolumny na szerszą na podstawie najdłuższego tekstu) przy dodawaniu nowej dedykacji
        grid.addColumn(Dedication::getDescription).setHeader("Description").setAutoWidth(true);
        grid.addColumn(
                new ComponentRenderer<>(Button::new, (button, song) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_TERTIARY,
                            ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(e -> this.shareDedicationSong(song));
                    button.setIcon(new Icon(VaadinIcon.SHARE));
                })).setHeader("Share").setAutoWidth(true);
        grid.addColumn(
                new ComponentRenderer<>(Button::new, (button, song) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(e -> this.removeDedication(song));
                    button.setIcon(new Icon(VaadinIcon.TRASH));
                })).setHeader("Delete").setAutoWidth(true);

        grid.setItems(listOfDedications);
        grid.setWidth(width + "%");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, grid);
        layoutColumn2.add(grid);
    }

    private void shareDedicationSong(Dedication dedication) {
        Notification.show(dedication.getTitle());
    }

    private List<Song> getListOfSongs() {
        List<Song> list = new ArrayList<>();
        list.add(new Song("Shape Of You","Kombi , tempo 3/4","disco","D://desktop//"));
        list.add(new Song("All of me","FEEL, ALbum 4","rock","c://user/jakub//"));
        list.add(new Song("Shallow","Zespół nieznany","jazz","D://desktop//me"));
        return list;
    }

    public void setListOfSongsTitleInCmbBox(ComboBox<String> cmbBox){
        List<String> listOfSongs = new ArrayList<>();
        listOfSongs.add("Thinking Out Loud");
        listOfSongs.add("Shape of You");
        listOfSongs.add("Perfect");
        listOfSongs.add("All of me");
        listOfSongs.add("Girls like You");
        listOfSongs.add("Suggar");
        listOfSongs.add("Stay with Me");
        cmbBox.setItems(listOfSongs);
    }

    private void refreshGrid() {
        if (listOfDedications.size() > 0) {
            grid.setVisible(true);
            hint.setVisible(false);
            grid.getDataProvider().refreshAll();
        } else {
            grid.setVisible(false);
            hint.setVisible(true);
        }
    }

    private void saveDedication(Dedication dedication) {
        listOfDedications.add(dedication);
        this.refreshGrid();
    }

    private void removeDedication(Dedication dedication) {
        listOfDedications.remove(dedication);
        this.refreshGrid();
    }

    public  boolean isMobileDevice() {
        WebBrowser webBrowser = VaadinSession.getCurrent().getBrowser();
        return webBrowser.isAndroid() || webBrowser.isIPhone() || webBrowser.isWindowsPhone();
    }
}
