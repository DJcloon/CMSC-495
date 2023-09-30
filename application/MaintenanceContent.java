package application;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.control.ComboBox;


public class MaintenanceContent extends ContentArea {

	@Override
	public void initialize() {
		VBox vbox = new VBox();
		
		GridPane gridPane = new GridPane();
		gridPane.add(new Label("Maintenance Information"), 0, 2, 2, 1);
		//Create and configure the ComboBox
		   ComboBox<String> maintenanceComboBox = new ComboBox<>();
		   maintenanceComboBox.getItems().addAll("Under maintenance", "Schedule maintenance", "Upcoming maintenance", "Past maintenance");
		   maintenanceComboBox.setValue("Under maintenance"); // Default selection
		   maintenanceComboBox.setOnAction(event -> {
		       String selectedMaintenance = maintenanceComboBox.getValue();
		       if ("Schedule maintenance".equals(selectedMaintenance)) {
		           //list cruises by name here
		       }
		   });

		   gridPane.add(maintenanceComboBox, 0, 0);

		   gridPane.add(createMaintenanceTable(), 0, 3, 2, 1);
		   gridPane.add(createMaintenanceTable(), 0, 3, 2, 1);
        
        GridPane.setHgrow(gridPane, Priority.ALWAYS);
        GridPane.setVgrow(gridPane, Priority.ALWAYS);
        gridPane.setMaxWidth(Double.MAX_VALUE);
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);

        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(50);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(50);

        gridPane.getColumnConstraints().addAll(col1, col2);

        vbox.getChildren().addAll(gridPane);

        content = vbox;
        content.getStyleClass().add("overview-content");
	}
	

	@SuppressWarnings("unchecked")
	private TableView<String> createMaintenanceTable() {
        TableView<String> maintenanceTable = new TableView<>();
        maintenanceTable.getStyleClass().add("overview-table");

        TableColumn<String, String> shipColumn = new TableColumn<>("Ship name");
        shipColumn.setCellValueFactory(new PropertyValueFactory<>("ship"));

        TableColumn<String, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<String, String> dateColumn = new TableColumn<>("Date of service");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        

        maintenanceTable.getColumns().addAll(shipColumn, statusColumn, dateColumn);

        return maintenanceTable;
    }
}
