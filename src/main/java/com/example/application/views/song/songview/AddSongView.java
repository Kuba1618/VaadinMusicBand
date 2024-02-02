package com.example.application.views.song.songview;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.SucceededEvent;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.ArrayList;
import java.util.List;

//<a href="https://www.flaticon.com/free-icons/attachment" title="attachment icons">Attachment icons created by Anggara - Flaticon</a>
@PageTitle("Add Song")
@Route(value = "add-song", layout = MainLayout.class)
//@RolesAllowed("ADMIN")
@Uses(Icon.class)
public class AddSongView extends Composite<VerticalLayout> {
    Upload uploadSongFile = new Upload();
    public VerticalLayout layoutColumn2 = new VerticalLayout();
    public TextField textField = new TextField();
    public TextField textField2 = new TextField();
    public TextArea textArea = new TextArea();
    public MultiSelectComboBox<String> multiSelectCmbBox;
    public HorizontalLayout layoutRow = new HorizontalLayout();
    public Icon icon = new Icon();
    public Button buttonPrimary = new Button();
    public String txtFieldWidth;
    public int uploadSongBtnWidth;
    public int uploadSongBtnHeight;

    public AddSongView() {

        txtFieldWidth = isMobileDevice() ? "90%" :"50%";
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layoutColumn2.setAlignItems(FlexComponent.Alignment.CENTER);
        textField.setLabel("Title");
        textField.setWidth(txtFieldWidth);
        textField2.setLabel("Author");
        textField2.setWidth(txtFieldWidth);
        textArea.setLabel("Description");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, textArea);
        textArea.setWidth(txtFieldWidth);
        textArea.setHeight("50%");

        multiSelectCmbBox = new MultiSelectComboBox<>("Category");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, multiSelectCmbBox);
        multiSelectCmbBox.setWidth(txtFieldWidth);
        multiSelectCmbBox.setHeight("50%");
        setMultiSelectCmbBox(multiSelectCmbBox);
        layoutColumn2.add(multiSelectCmbBox);

        layoutRow.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow.setWidth(txtFieldWidth);
        layoutRow.getStyle().set("flex-grow", "1");
        layoutRow.setAlignItems(FlexComponent.Alignment.CENTER);
        layoutRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        uploadSongBtnWidth = isMobileDevice() ? 85 : 80;
        uploadSongBtnHeight = isMobileDevice() ? 30 : 25;
        uploadSongFile.setWidth(uploadSongBtnWidth + "%");
        uploadSongFile.setHeight(uploadSongBtnHeight + "%");

        buttonPrimary.setText("Save");
        buttonPrimary.setWidth("7%");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        getContent().add(layoutColumn2);
        layoutColumn2.add(textField);
        layoutColumn2.add(textField2);
        layoutColumn2.add(textArea);
        layoutColumn2.add(multiSelectCmbBox);
        layoutColumn2.add(layoutRow);
        layoutRow.add(uploadSongFile);
        layoutRow.setSpacing(true);
        layoutRow.add(buttonPrimary);

        displayDeviceCategory();
    }
    
    public void setMultiSelectCmbBox(MultiSelectComboBox<String> multiSelectCmbBox){
        List<String> listOfCategories = new ArrayList<>();
        listOfCategories.add("Disco Polo");
        listOfCategories.add("Rock");
        listOfCategories.add("First dance");
        listOfCategories.add("Electronic");
        listOfCategories.add("Wedding cake");
        listOfCategories.add("90's hit");
        listOfCategories.add("For parents");
        multiSelectCmbBox.setItems(listOfCategories);
    }

    public void displayDeviceCategory(){
        uploadSongFile.addSucceededListener((ComponentEventListener<SucceededEvent>) succeededEvent -> {
            // Pobierz nazwę przesłanego pliku
            String fileName = succeededEvent.getFileName();
            Notification.show("" + fileName);
        });
    }

    public  boolean isMobileDevice() {
        WebBrowser webBrowser = VaadinSession.getCurrent().getBrowser();
        return webBrowser.isAndroid() || webBrowser.isIPhone() || webBrowser.isWindowsPhone();
    }

}