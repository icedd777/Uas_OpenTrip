package uas;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class App extends Application {

    private final OpenTripController service = new OpenTripController();
    private final ObservableList<OpenTrip> data = FXCollections.observableArrayList();
    private final TableView<OpenTrip> table = new TableView<>();

    private final TextField tfNamaTrip = new TextField();
    private final TextField tfDestinasi = new TextField();
    private final DatePicker dpTanggal = new DatePicker();
    private final TextField tfKuota = new TextField();
    private final TextField tfHarga = new TextField();
    private final TextArea taDeskripsi = new TextArea();

    private OpenTrip selectedTrip = null;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("🧳 Manajemen Open Trip");

        Label labelTitle = new Label("🧳 Manajemen Data Open Trip");
        labelTitle.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
        labelTitle.setAlignment(Pos.CENTER);

        // Tabel
        TableColumn<OpenTrip, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<OpenTrip, String> colNamaTrip = new TableColumn<>("Nama Trip");
        colNamaTrip.setCellValueFactory(new PropertyValueFactory<>("namaTrip"));

        TableColumn<OpenTrip, String> colDestinasi = new TableColumn<>("Destinasi");
        colDestinasi.setCellValueFactory(new PropertyValueFactory<>("destinasi"));

        TableColumn<OpenTrip, LocalDate> colTanggal = new TableColumn<>("Tanggal");
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggalBerangkat"));

        TableColumn<OpenTrip, Integer> colKuota = new TableColumn<>("Kuota");
        colKuota.setCellValueFactory(new PropertyValueFactory<>("kuotaPeserta"));

        TableColumn<OpenTrip, Double> colHarga = new TableColumn<>("Harga");
        colHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));

        TableColumn<OpenTrip, String> colDeskripsi = new TableColumn<>("Deskripsi");
        colDeskripsi.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));

        table.getColumns().addAll(colId, colNamaTrip, colDestinasi, colTanggal, colKuota, colHarga, colDeskripsi);
        table.setItems(data);
        table.setPrefHeight(300);

        // Form
        tfNamaTrip.setPromptText("Nama Trip");
        tfDestinasi.setPromptText("Destinasi");
        tfKuota.setPromptText("Kuota");
        tfHarga.setPromptText("Harga");
        taDeskripsi.setPromptText("Deskripsi");
        taDeskripsi.setPrefRowCount(3);

        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(10));

        formGrid.add(new Label("Nama Trip:"), 0, 0);
        formGrid.add(tfNamaTrip, 1, 0);
        formGrid.add(new Label("Destinasi:"), 0, 1);
        formGrid.add(tfDestinasi, 1, 1);
        formGrid.add(new Label("Tanggal:"), 0, 2);
        formGrid.add(dpTanggal, 1, 2);
        formGrid.add(new Label("Kuota:"), 0, 3);
        formGrid.add(tfKuota, 1, 3);
        formGrid.add(new Label("Harga:"), 0, 4);
        formGrid.add(tfHarga, 1, 4);
        formGrid.add(new Label("Deskripsi:"), 0, 5);
        formGrid.add(taDeskripsi, 1, 5);

        Button btnTambah = new Button("Tambah");
        Button btnUpdate = new Button("Update");
        Button btnHapus = new Button("Hapus");

        HBox buttonBox = new HBox(10, btnTambah, btnUpdate, btnHapus);
        buttonBox.setAlignment(Pos.CENTER);

        VBox formBox = new VBox(10, formGrid, buttonBox);
        formBox.setPadding(new Insets(10));

        TitledPane formPane = new TitledPane("➕ Tambah / Edit Trip", formBox);
        formPane.setCollapsible(false);

        // Action Table
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            selectedTrip = newVal;
            if (newVal != null) {
                tfNamaTrip.setText(newVal.getNamaTrip());
                tfDestinasi.setText(newVal.getDestinasi());
                dpTanggal.setValue(newVal.getTanggalBerangkat());
                tfKuota.setText(String.valueOf(newVal.getKuotaPeserta()));
                tfHarga.setText(String.valueOf(newVal.getHarga()));
                taDeskripsi.setText(newVal.getDeskripsi());
            }
        });

        btnTambah.setOnAction(e -> tambahTrip());
        btnUpdate.setOnAction(e -> updateTrip());
        btnHapus.setOnAction(e -> hapusTrip());

        VBox root = new VBox(20, labelTitle, table, formPane);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f4f4f4;");

        Scene scene = new Scene(root, 1000, 720);
        stage.setScene(scene);
        stage.show();

        refreshTable();
    }

    private void tambahTrip() {
        try {
            String nama = tfNamaTrip.getText().trim();
            String destinasi = tfDestinasi.getText().trim();
            LocalDate tanggal = dpTanggal.getValue();
            int kuota = Integer.parseInt(tfKuota.getText().trim());
            double harga = Double.parseDouble(tfHarga.getText().trim());
            String deskripsi = taDeskripsi.getText().trim();

            if (nama.isEmpty() || destinasi.isEmpty() || tanggal == null || deskripsi.isEmpty()) {
                showError("Semua field wajib diisi.");
                return;
            }

            service.tambahTrip(nama, destinasi, tanggal, kuota, harga, deskripsi);
            refreshTable();
            clearForm();
        } catch (NumberFormatException e) {
            showError("Kuota & harga harus angka valid.");
        }
    }

    private void updateTrip() {
        if (selectedTrip != null) {
            try {
                String nama = tfNamaTrip.getText().trim();
                String destinasi = tfDestinasi.getText().trim();
                LocalDate tanggal = dpTanggal.getValue();
                int kuota = Integer.parseInt(tfKuota.getText().trim());
                double harga = Double.parseDouble(tfHarga.getText().trim());
                String deskripsi = taDeskripsi.getText().trim();

                service.updateTrip(selectedTrip.getId(), nama, destinasi, tanggal, kuota, harga, deskripsi);
                refreshTable();
                clearForm();
            } catch (NumberFormatException e) {
                showError("Kuota & harga harus angka valid.");
            }
        }
    }

    private void hapusTrip() {
        if (selectedTrip != null) {
            service.hapusTrip(selectedTrip.getId());
            refreshTable();
            clearForm();
        }
    }

    private void refreshTable() {
        data.setAll(service.getAll());
    }

    private void clearForm() {
        tfNamaTrip.clear();
        tfDestinasi.clear();
        tfKuota.clear();
        tfHarga.clear();
        dpTanggal.setValue(null);
        taDeskripsi.clear();
        table.getSelectionModel().clearSelection();
        selectedTrip = null;
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Kesalahan");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
