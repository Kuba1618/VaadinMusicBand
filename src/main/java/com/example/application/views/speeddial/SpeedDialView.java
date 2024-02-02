package com.example.application.views.speeddial;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "speed-dial",layout = MainLayout.class)
public class SpeedDialView extends VerticalLayout {
    public SpeedDialView() {
        // Tworzymy przycisk główny (trigger button)
        Button mainButton = new Button(VaadinIcon.BELL.create());
        mainButton.addClickListener(e -> toggleSpeedDial());

        // Tworzymy przyciski dodatkowe
        Button button1 = createSpeedDialButton("Option 1", VaadinIcon.AIRPLANE);
        Button button2 = createSpeedDialButton("Option 2", VaadinIcon.ALARM);
        Button button3 = createSpeedDialButton("Option 3", VaadinIcon.CAR);

        // Ukrywamy przyciski dodatkowe na początku
        button1.setVisible(false);
        button2.setVisible(false);
        button3.setVisible(false);

        add(mainButton);
        // Dodajemy przyciski dodatkowe do layoutu
        add(button1, button2, button3);
        // Dodajemy przycisk główny do layoutu

    }

    private Button createSpeedDialButton(String text, VaadinIcon icon) {
        Button button = new Button(icon.create());
        button.setText(text);
        button.addClickListener(e -> {
            // Tutaj możesz dodać logikę obsługi kliknięcia na przycisk dodatkowy
        });
        return button;
    }

    private void toggleSpeedDial() {
        // Pokazuje lub ukrywa przyciski dodatkowe
        getChildren().skip(1).forEach(component -> component.setVisible(!component.isVisible()));
    }
}
