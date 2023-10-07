package application;
import javafx.concurrent.Worker.State;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.image.ImageView;

public class OverviewContent extends ContentArea {
    private Stage primaryStage;

    @Override
    public void initialize() {
        // Create a VBox to contain the navigation bar and content
        VBox vbox = new VBox();

        // Create a navigation bar at the top
        VBox navigationBar = createNavigationBar();

        // Add padding to the navigation bar
        navigationBar.setPadding(new Insets(10, 10, 10, 10)); // Adjust the values as needed

        // Create a GridPane to organize categories
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // Horizontal spacing
        gridPane.setVgap(20); // Vertical spacing
        
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        webEngine.load("https://www.cruisemapper.com/");

        // Define the four categories (replace with actual content)
        VBox category1 = new VBox(new Label("Category 1 Content"));
        VBox category2 = new VBox(webView);
        VBox category3 = new VBox(new Label("Category 3 Content"));

        // Add CSS styles to the categories
        category1.getStyleClass().add("overview-category");
        category2.getStyleClass().add("overview-category");
        category3.getStyleClass().add("overview-category");

        // Add categories to the GridPane
        gridPane.add(category1, 0, 0);
        gridPane.add(category2, 1, 0);
        gridPane.add(category3, 0, 1);
        gridPane.add(new Label("Ships"), 0, 2, 2, 1);
        gridPane.add(createShipTable(), 0, 3, 2, 1);

        // Set the GridPane to expand within its container
        GridPane.setHgrow(gridPane, Priority.ALWAYS);
        GridPane.setVgrow(gridPane, Priority.ALWAYS);
        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.setMaxHeight(Double.MAX_VALUE);
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);

        gridPane.getColumnConstraints().addAll(col1, col2);

        vbox.getChildren().addAll(navigationBar, gridPane);

        content = vbox;
        content.getStyleClass().add("overview-content");
    }

    private VBox createNavigationBar() {
        VBox navigationBar = new VBox();
        navigationBar.getStyleClass().add("overview-navigation-bar");

        return navigationBar;
    }

    private void configureFileChooser(FileChooser fileChooser) {
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Excel Files", "*.xls", "*.xlsx")
        );
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    private TableView<String> createShipTable() {
        TableView<String> shipTable = new TableView<>();
        shipTable.getStyleClass().add("overview-table");

        TableColumn<String, String> shipColumn = new TableColumn<>("Ship");
        shipColumn.setCellValueFactory(new PropertyValueFactory<>("ship"));

        TableColumn<String, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<String, String> dportColumn = new TableColumn<>("Departure Port");
        dportColumn.setCellValueFactory(new PropertyValueFactory<>("dport"));
        
        TableColumn<String, String> destinationColumn = new TableColumn<>("Destination");
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));

        shipTable.getColumns().addAll(shipColumn, statusColumn, dportColumn, destinationColumn);
        

        return shipTable;
    }
}
