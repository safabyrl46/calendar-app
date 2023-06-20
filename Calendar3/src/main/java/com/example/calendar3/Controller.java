package com.example.calendar3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final int GRID_COLUMN = 7;
    private final int GRID_ROW = 6;
    @FXML
    private Button buttonKaydet;
    @FXML
    private ComboBox comboBoxSaat;
    @FXML
    private TextField textFieldTip;
    @FXML
    private TextArea textAreaComment;
    private Calendar calendar;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button buttonForward;
    @FXML
    private Button buttonBack;
    @FXML
    private Label labelYear;
    @FXML
    private Label labelMonths;
    @FXML
    private Pane selectedDatePane;
    @FXML
    private Label labelSelectedDate;
    @FXML
    private ListView<LocalDate> listView;
    @FXML
    private TextArea textAreaData;
    @FXML
    private Button buttonSil;
    @FXML
    private Pane listPane;
    ObservableList<LocalDate> dates = FXCollections.observableArrayList();
    private LocalDate selectedDate;

    public static Button[][] daysButtons;
    private LocalDate tempDate = LocalDate.now();
    private String comm;
    private String tip;
    private String time;

    public void initialize() {
        calendar = new Calendar(Calendar.today);
        daysButtons = new Button[GRID_ROW][GRID_COLUMN];
        calendar.dateItterator = calendar.getFirstMondayOfCalendar();
        //Takvim üzerindeki butonların tasarımı
        for (int i = 0; i < GRID_ROW; i++) {
            for (int j = 0; j < GRID_COLUMN; j++) {
                Button button = new setButtons(i, j);
                daysButtons[i][j] = button;
            }
        }
        placeButtons();
        setHBox(Calendar.today);
        buttonForward.setOnAction(e -> buttonForwardAction(e));
        buttonBack.setOnAction(e -> buttonBackAction(e));
        List<String> saatler = new ArrayList<>();

        for (int i = 0; i <= 23; i++) {
            for (int j = 0; j <= 1; j++) {
                String saat = String.format("%02d:%02d", i, j * 30);
                saatler.add(saat);
            }
        }

        comboBoxSaat.getItems().addAll(saatler);
        buttonKaydet.setOnAction(e -> buttonKaydetAction(e));
        listView.setOnMouseClicked(event -> {
            LocalDate selectedDate = listView.getSelectionModel().getSelectedItem();
            System.out.println("Seçilen tarih: " + selectedDate);
            textAreaData.setText("Tip: " + tip + "\nAçıklama: " + comm + "\nSaat: " + time);
        });
        buttonSil.setOnAction(e -> buttonSilAction(e));
      paneVisible(false);
    }

    private void buttonSilAction(ActionEvent e) {
        LocalDate selectedDatte = listView.getSelectionModel().getSelectedItem();
        listView.getItems().remove(selectedDatte);
        dates.remove(selectedDatte);
        textAreaData.setText("");
    }

    private void buttonBackAction(ActionEvent e) {
        tempDate.minusMonths(1);
        calendar = new Calendar(calendar.dateItterator);
        gridPane.getChildren().clear();
        calendar.dateItterator = calendar.dateItterator.minusMonths(1).minusDays(1);
        setHBox(calendar.dateItterator);
        calendar = new Calendar(calendar.dateItterator);
        calendar.dateItterator = calendar.getFirstMondayOfCalendar();
        placeButtons();
        daysStyling();
    }

    private void buttonForwardAction(ActionEvent e) {
        tempDate.plusMonths(1);
        calendar = new Calendar(calendar.dateItterator);
        gridPane.getChildren().clear();
        calendar.dateItterator = calendar.dateItterator.plusMonths(1);
        setHBox(calendar.dateItterator);
        calendar = new Calendar(calendar.dateItterator);
        calendar.dateItterator = calendar.getFirstMondayOfCalendar();
        placeButtons();
        daysStyling();

    }

    private void daysStyling() {
        for (Button[] itemArray : daysButtons) {
            for (Button item : itemArray) {
                item.getStyleClass().add("days");
            }
        }
    }
    private void selectedDay() {

    }
    //Butonları ızgaraya sığdırma ve tıklama fonksiyonuun çağırma
    private void placeButtons() {
        gridPane.getChildren().clear();
        for (int i = 0; i < GRID_ROW; i++) {
            for (int j = 0; j < GRID_COLUMN; j++) {
                // Butonun boyutları ve konumu ayarlanıyor.
                daysButtons[i][j].setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
                gridPane.add(daysButtons[i][j], j, i);

                // Buton metnine ilgili tarihin günü yazılıyor.
                daysButtons[i][j].setText(String.valueOf(calendar.dateItterator.getDayOfMonth()));

                // Butona olay dinleyicisi ekleniyor.
                daysButtons[i][j].setOnAction(e -> handleButtonAction(e));
                daysButtons[i][j].getStyleClass().add("days");
                if (calendar.dateItterator.getDayOfMonth() == Calendar.today.getDayOfMonth()
                        && calendar.dateItterator.getMonth() == Calendar.today.getMonth()) {
                    daysButtons[i][j].getStyleClass().add("today");
                }
                // Takvimdeki tarih bir sonraki güne ilerletiliyor.
                calendar.dateItterator = calendar.dateItterator.plusDays(1);
            }
        }

    }

    private void setHBox (LocalDate day){
        labelYear.setText(String.valueOf(day.getYear()));
        labelMonths.setText(String.valueOf(day.getMonth()));
    }
    private void handleButtonAction (ActionEvent e){
        Button button = (Button) e.getSource();
        int x = GridPane.getColumnIndex(button);
        int y = GridPane.getRowIndex(button);
        int day = Integer.parseInt(button.getText());
     //   calendar = new Calendar(calendar.dateItterator);
        selectedDate = calendar.firstDayOfMonth.plusDays(day - 1);
        labelSelectedDate.setText("İşlem Zamanı: " + String.valueOf(selectedDate));
        selectedDatePane.getStyleClass().add("selected-pane");
        paneVisible(true);
        System.out.println(selectedDate);

}

    private void buttonKaydetAction(ActionEvent e) {
        comm = textAreaComment.getText();
        tip = textFieldTip.getText();
        time = (String) comboBoxSaat.getValue();
        LocalDate date = selectedDate;
        paneVisible(false);
        dates.add(date);
        listView.setItems(dates);

    }

    private void paneVisible(boolean a) {
        selectedDatePane.setVisible(a);
        comboBoxSaat.setVisible(a);
        textAreaComment.setVisible(a);
        textFieldTip.setVisible(a);
        buttonKaydet.setVisible(a);
      /*  if (a == true) {
            listPane.setVisible(false);
            textAreaData.setVisible(false);
            buttonSil.setVisible(false);
            listView.setVisible(false);
        }
        else{
            listPane.setVisible(true);
            textAreaData.setVisible(true);
            buttonSil.setVisible(true);
            listView.setVisible(true);
        }*/
        listPane.setVisible(!a);
        textAreaData.setVisible(!a);
        buttonSil.setVisible(!a);
        listView.setVisible(!a);

    }
}
