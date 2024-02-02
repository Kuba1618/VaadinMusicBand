package com.example.application.views.live;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;

@PageTitle("Live View")
@Route(value = "live-view", layout = MainLayout.class)
public class LiveView extends VerticalLayout {
    public int imgWidth;
    public int imgHeigth;
    public Button forteBtn = new Button("Forte",new Icon(VaadinIcon.VOLUME_UP));
    public Button pianoBtn = new Button("Piano",new Icon(VaadinIcon.VOLUME_DOWN));
    public Button fasterBtn = new Button("Faster",new Icon(VaadinIcon.FLIGHT_TAKEOFF));
    public Button slowerBtn = new Button("Slower",new Icon(VaadinIcon.FLIGHT_LANDING));

    public LiveView() {
        setSpacing(false);
        setPadding(false);

        imgWidth = isMobileDevice()? 115 : 65;
        imgHeigth = isMobileDevice()? 85 : 97;

        Image img = new Image("images/poland.png", "placeholder plant");
        img.setWidth(imgWidth + "%");
        img.setHeight(imgHeigth + "%");
        add(img);

        Button openPopupButton = new Button("Notifications", new Icon(VaadinIcon.BELL));
        openPopupButton.setHeight(imgHeigth * 0.07 + "%");

        Dialog popup = new Dialog();
        popup = setPopupDialog(popup);

        Dialog finalPopup = popup;
        openPopupButton.addClickListener(event -> finalPopup.open());
        add(openPopupButton);

        dialogClickBtnListeners();

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

    private Dialog setPopupDialog(Dialog popup) {
        if(isMobileDevice()){
            popup.setHeight(imgHeigth * 0.34 + "%");
            popup.setWidth(imgWidth * 0.7 + "%");
        } else if (!isMobileDevice()) {
            popup.setHeight(imgHeigth * 0.24 + "%");
            popup.setWidth(imgWidth * 0.55 + "%");
        }
        popup.setResizable(true);
        //        popup.add("To jest zawartość okna popup.");

        VerticalLayout verticalLayout = new VerticalLayout();

        // Utwórz przycisk do zamknięcia okna dialogowego
        Button closeButton = new Button(new Icon(VaadinIcon.CLOSE), event -> popup.close());
        if(isMobileDevice()){
            closeButton.setHeight(imgHeigth * 0.005 + "%");
            closeButton.setWidth(imgWidth * 0.005 + "%");
        } else if (!isMobileDevice()) {
            closeButton.setHeight(imgHeigth * 0.001 + "%");
            closeButton.setWidth(imgWidth * 0.001 + "%");
        }


        HorizontalLayout buttonLayout = new HorizontalLayout(closeButton);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(JustifyContentMode.END);
        buttonLayout.setSpacing(false);
        buttonLayout.setPadding(false);
        verticalLayout.setPadding(false);
        verticalLayout.setSpacing(false);
        verticalLayout.add(buttonLayout);
        popup.add(verticalLayout);

        VerticalLayout layout = new VerticalLayout();

        if(isMobileDevice()){
            // Dodaj przyciski (2x2)
            HorizontalLayout row1 = new HorizontalLayout(pianoBtn,forteBtn);
            HorizontalLayout row2 = new HorizontalLayout(slowerBtn,fasterBtn);
            layout.add(row1, row2);
        } else if (!isMobileDevice()) {
            // Dodaj przyciski (2x2)
            HorizontalLayout row1 = new HorizontalLayout(pianoBtn,forteBtn,slowerBtn,fasterBtn);
            layout.add(row1);
        }

        layout.setSpacing(true);
        layout.setPadding(true);

        // Dodaj elementy do okna dialogowego
        popup.add(layout);

        return popup;
    }

    public void showNotification(String notificationMessage){
        Notification notification = Notification.show(notificationMessage);
        if(isMobileDevice()){
            notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
        }
        else if(!isMobileDevice()){
            notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
        }
    }

    public void dialogClickBtnListeners(){
        forteBtn.addClickListener((ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
            showNotification("Let's play it LOUDER !");
        });
        pianoBtn.addClickListener((ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
            showNotification("Let's play it MORE QUIETLY !");
        });
        fasterBtn.addClickListener((ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
            showNotification("Let's play it FASTER !");
        });
        slowerBtn.addClickListener((ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
            showNotification("Let's play it SLOWER !");
        });
    }
    public  boolean isMobileDevice() {
        WebBrowser webBrowser = VaadinSession.getCurrent().getBrowser();
        return webBrowser.isAndroid() || webBrowser.isIPhone() || webBrowser.isWindowsPhone();
    }

}
