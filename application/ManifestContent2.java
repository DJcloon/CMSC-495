package application;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManifestContent extends ContentArea {

	private TableView<Passenger> manifestTable;

	@Override
	public void initialize() {
		VBox vbox = new VBox(15); // space between components

		Label manifestLabel = new Label("Cruise Ship Manifest Information");
		ComboBox<String> manifestComboBox = setupComboBox();

		manifestTable = createManifestTable();

		vbox.getChildren().addAll(manifestLabel, manifestComboBox, manifestTable);

		content = vbox;
		content.getStyleClass().add("overview-content");
	}

	private ComboBox<String> setupComboBox() {
		ComboBox<String> manifestComboBox = new ComboBox<>();
		manifestComboBox.getItems().addAll("Select a ship", "Cruise Ship 1", "Cruise Ship 2", "Cruise Ship 3");
		manifestComboBox.setValue("Select a ship"); // Default selection

		manifestComboBox.setOnAction(event -> {
			switch (manifestComboBox.getValue()) {
				case "Cruise Ship 1":
					populateDataForShip1();
					break;
				case "Cruise Ship 2":
					populateDataForShip2();
					break;
				case "Cruise Ship 3":
					populateDataForShip3();
					break;
				default:
					manifestTable.getItems().clear();
			}
		});

		return manifestComboBox;
	}

	private TableView<Passenger> createManifestTable() {
		TableView<Passenger> table = new TableView<>();

		TableColumn<Passenger, String> nameColumn = new TableColumn<>("Passenger Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Passenger, String> cabinColumn = new TableColumn<>("Cabin Number");
		cabinColumn.setCellValueFactory(new PropertyValueFactory<>("cabin"));

		TableColumn<Passenger, String> embarkColumn = new TableColumn<>("Embarkation Point");
		embarkColumn.setCellValueFactory(new PropertyValueFactory<>("embark"));

		TableColumn<Passenger, String> disembarkColumn = new TableColumn<>("Disembarkation Point");
		disembarkColumn.setCellValueFactory(new PropertyValueFactory<>("disembark"));

		TableColumn<Passenger, String> emergencyContactColumn = new TableColumn<>("Emergency Contact");
		emergencyContactColumn.setCellValueFactory(new PropertyValueFactory<>("emergency"));

		table.getColumns().addAll(nameColumn, cabinColumn, embarkColumn, disembarkColumn, emergencyContactColumn);
		return table;
	}

	private void populateDataForShip1() {
		ObservableList<Passenger> data = FXCollections.observableArrayList(
				new Passenger("John Doe", "A203", "Miami", "Bahamas", "Jane Doe"));
		manifestTable.setItems(data);
	}

	private void populateDataForShip2() {
		ObservableList<Passenger> data = FXCollections.observableArrayList(
				new Passenger("Alice Smith", "B309", "Los Angeles", "Hawaii", "Bob Smith"));
		manifestTable.setItems(data);
	}

	private void populateDataForShip3() {
		ObservableList<Passenger> data = FXCollections.observableArrayList(
				new Passenger("Charlie Brown", "C507", "New York", "Bermuda", "Lucy Brown"));
		manifestTable.setItems(data);
	}

	public static class Passenger {
		private final String name;
		private final String cabin;
		private final String embark;
		private final String disembark;
		private final String emergency;

		public Passenger(String name, String cabin, String embark, String disembark, String emergency) {
			this.name = name;
			this.cabin = cabin;
			this.embark = embark;
			this.disembark = disembark;
			this.emergency = emergency;
		}

		public String getName() {
			return name;
		}

		public String getCabin() {
			return cabin;
		}

		public String getEmbark() {
			return embark;
		}

		public String getDisembark() {
			return disembark;
		}

		public String getEmergency() {
			return emergency;
		}
	}
}
