import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import java.io.InputStream;
import javafx.beans.value.ObservableValue;

public class CruisesContent extends ContentArea {
	
	// Ship
    private Label shipName;
    private Label shipCruiseLine;
    private Label tripLength;
    private Label yearBuilt;
    private Label origin;
    private Label finalDestination;
    private Label passengers;
    InputStream imageURL;
    ImageView shipView;
    Image shipImage;
	
    // Lodging
    Label estCost;
    Spinner<Integer> passengerCount;
    InputStream roomImageURL;
    ImageView roomView;
    Image roomImage;
    
    public CruisesContent() {
    	
		shipName = new Label("");
		shipCruiseLine = new Label("");
		tripLength = new Label("");
		yearBuilt = new Label("");
		origin = new Label("");
		finalDestination = new Label("");
		passengers = new Label("");
		imageURL = getClass().getResourceAsStream("/images/ships/empty.jpg");
		shipView = new ImageView();
		shipImage = new Image(imageURL);
		estCost = new Label("$0.00");
        passengerCount = new Spinner<Integer>(1, 10, 1);
        roomView = new ImageView();
    }
    
	
    @Override
    public void initialize() {

        GridPane selectPane = new GridPane();
        VBox vbox = new VBox();

        Label searchLabel = new Label("Select ship by name");
        
        ComboBox<String> searchComboBox = new ComboBox<String>(); // This needs to be set to ships
        searchComboBox.setPrefSize(200, 25);
        searchComboBox.setItems(getShips());
        
        // search box
        HBox searchContainer = new HBox(searchLabel, searchComboBox);
        searchContainer.setSpacing(20);
        searchContainer.getStyleClass().add("search-box");
        
        // ship info
        GridPane shipInfoContainer = new GridPane();

        shipView.setImage(shipImage);
        shipView.setFitHeight(200);
        shipView.setFitWidth(200);
        shipView.setPreserveRatio(true);
        
        shipInfoContainer.add(shipName, 0, 0);
        shipInfoContainer.add(shipCruiseLine, 0, 1);
        shipInfoContainer.add(new HBox(tripLength, new Label(" Days")), 0, 2);
        shipInfoContainer.add(new HBox(new Label("Built in " ), yearBuilt), 0, 3);
        shipInfoContainer.add(new HBox(origin, new Label(" - "), finalDestination), 0, 4);
        shipInfoContainer.add(new HBox(passengers, new Label(" passengers")), 0, 5);
        
        // ship display box
        HBox shipBox = new HBox(shipView, shipInfoContainer);
        shipBox.setSpacing(15);
        
        shipBox.getStyleClass().add("ship-box");
        
        Button shipButton = new Button("Select Cruise");

        selectPane.setHgap(10);
        selectPane.setVgap(10);

        selectPane.add(searchContainer, 0, 0);
        selectPane.add(shipBox, 0, 1);
        selectPane.add(shipButton, 0, 2);

        // Set the GridPane to expand within its container
        GridPane.setHgrow(selectPane, Priority.ALWAYS);
        GridPane.setVgrow(selectPane, Priority.ALWAYS);
        selectPane.setMaxWidth(Double.MAX_VALUE);

        vbox.getChildren().addAll(selectPane);
        
        // Event Listeners
        shipButton.setOnAction(e -> {
        	if (searchComboBox.getValue() != null) {
            	openLodgingWindow();
        	}
        	else {
        		openErrorWindow("CRUISE");
        	}
        });
        
        
        searchComboBox.valueProperty().addListener(new ChangeListener<String>() {
        	@Override public void changed(ObservableValue ov, String t, String t1) {
        		updateCruiseInfo(t1);
              }    
        });

        // Super class content set
        content = vbox;
        content.getStyleClass().add("overview-content");
    }
      
    private void openLodgingWindow() {
        Stage newStage = new Stage();
        newStage.setTitle("Lodging");

        GridPane lodgingLayout = new GridPane();
        Scene newScene = new Scene(lodgingLayout, 600, 400); // Adjust window size

        lodgingLayout.setHgap(10); 
        lodgingLayout.setVgap(10);
        lodgingLayout.setStyle("-fx-padding: 20;");

        // Fields
        ComboBox<String> roomTypes = new ComboBox<String>(getRooms()); // Need to change to Room Type Object
        roomTypes.getSelectionModel().selectFirst();
        passengerCount.setPrefWidth(60);

        HBox roomInfo = new HBox();
      
        // Buttons
	    Button reserveButton = new Button("Reserve");
	    reserveButton.setDisable(true);
        Button bookButton = new Button("Book");
        roomImageURL = getClass().getResourceAsStream("/images/rooms/room.jpg");
        roomImage = new Image(roomImageURL);
        
//        roomInfo.getChildren().add();
        
        // Add Components to layout
        lodgingLayout.add(new HBox(new Label("Select Room "), roomTypes), 0, 0);
        lodgingLayout.add(new HBox(new Label("Passengers " ), passengerCount), 1, 0);
        lodgingLayout.add(new HBox(new Label("Estimated Cost "), estCost), 2, 0);
        lodgingLayout.add(roomInfo, 0, 3);
        lodgingLayout.add(bookButton, 1, 3); // Adjust column index
        lodgingLayout.add(reserveButton, 2, 3); // Adjust column index

        // Event Listeners
        bookButton.setOnAction(e -> {
        	if (roomTypes.getValue() != null) {
        		openBillingWindow();
        	}
        	else {
        		openErrorWindow("ROOM");
        	}
        });
        
        roomTypes.setOnAction(e -> {
        	updateLodgingInfo(estCost, passengerCount.getValue(), roomTypes.getValue());
        });
        
        
        newStage.setScene(newScene);
        newStage.show();
    }

    private void openBillingWindow() {
        Stage newStage = new Stage();
        newStage.setTitle("Billing");

        GridPane billingPane = new GridPane();
        Scene newScene = new Scene(billingPane, 400, 400);

        billingPane.setHgap(10);
        billingPane.setVgap(10);
        billingPane.setStyle("-fx-padding: 20;");

        // Define column constraints
        ColumnConstraints col1 = new ColumnConstraints(100);
        ColumnConstraints col2 = new ColumnConstraints(250);
        billingPane.getColumnConstraints().addAll(col1, col2);

        // Define row constraints
        for (int i = 0; i < 7; i++) {
            RowConstraints row = new RowConstraints(40);
            billingPane.getRowConstraints().add(row);
        }

        // Add labels and input fields
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();

        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label paymentMethodLabel = new Label("Payment Method:");
        ComboBox<String> paymentMethodComboBox = new ComboBox<>();
        paymentMethodComboBox.getItems().addAll("Credit Card", "PayPal", "Bank Transfer");

        Label cardNumberLabel = new Label("Card Number:");
        TextField cardNumberField = new TextField();

        Label expiryDateLabel = new Label("Expiry Date:");
        DatePicker expiryDatePicker = new DatePicker();

        Button payButton = new Button("Complete");
        Button cancelButton = new Button("Cancel");

        billingPane.add(firstNameLabel, 0, 0);
        billingPane.add(firstNameField, 1, 0);

        billingPane.add(lastNameLabel, 0, 1);
        billingPane.add(lastNameField, 1, 1);

        billingPane.add(emailLabel, 0, 2);
        billingPane.add(emailField, 1, 2);

        billingPane.add(paymentMethodLabel, 0, 3);
        billingPane.add(paymentMethodComboBox, 1, 3);

        billingPane.add(cardNumberLabel, 0, 4);
        billingPane.add(cardNumberField, 1, 4);

        billingPane.add(expiryDateLabel, 0, 5);
        billingPane.add(expiryDatePicker, 1, 5);

        HBox buttonBox = new HBox(cancelButton, payButton);
        buttonBox.setAlignment(Pos.CENTER); 
        billingPane.add(buttonBox, 0, 6, 2, 1);
        HBox.setMargin(cancelButton, new Insets(10,10,10,10));
        newStage.setScene(newScene);
        newStage.show();
    }
    
    private void openErrorWindow(String error) {
        Stage newStage = new Stage();
        newStage.setTitle("ERROR");

        GridPane errorPane = new GridPane();
        errorPane.setAlignment(Pos.CENTER);
        Scene newScene = new Scene(errorPane, 300, 70);

        Button close = new Button("Close");
        close.setPrefSize(100, 10);
        close.setOnAction(e -> newStage.close());
        errorPane.add(new Label("SELECT A " + error), 0, 0);
        errorPane.add(close, 0, 1);

        newStage.setScene(newScene);
        newStage.show();
    }
    
    // Updates Room Information (Need to update)
    private void updateLodgingInfo(Label estCost, int passengers, String room) {
    	int cost = 0;
    	switch (room) {
			case "Room 1":
				cost = 400;
				break;
			case "Room 2":
				cost = 600;
				break;
			case "Room 3":
				cost = 800;
				break;
    	}
    	
   		Integer totalCost = cost*passengers;
    	estCost.setText(totalCost.toString());
    }
    
    // Updates Cruise Information (Need to update)
    private void updateCruiseInfo(String ship) {
        
        // this will get just get all the info from database
    	// temporary case statement for testing purposes
    	switch (ship) {
    		case "Item 1":
    			imageURL = getClass().getResourceAsStream("/images/ships/ship.jpg");
    			shipName.setText("Ship 1");
    			shipCruiseLine.setText("Cruise 1");
    			tripLength.setText("99");
    			yearBuilt.setText("1992");
    			origin.setText("Start");
    			finalDestination.setText("End");
    			passengers.setText("123");
    			break;
    		case "Item 2":
    			imageURL = getClass().getResourceAsStream("/images/ships/ship2.jpg");
    			shipName.setText("Ship 2");
    			shipCruiseLine.setText("Cruise 2");
    			tripLength.setText("8");
    			yearBuilt.setText("1876");
    			origin.setText("Start 2");
    			finalDestination.setText("End 2");
    			passengers.setText("1234");
    			break;
    		case "Item 3":
    			imageURL = getClass().getResourceAsStream("/images/ships/empty.jpg");
    			shipName.setText("Ship 3");
    			shipCruiseLine.setText("Cruise 3");
    			tripLength.setText("12");
    			yearBuilt.setText("1784");
    			origin.setText("Start 3");
    			finalDestination.setText("End 3");
    			passengers.setText("555");
    			break;
    	}
    	shipImage = new Image(imageURL);
        shipView.setImage(shipImage);
    }
    
    // Get Ships from database (Need to update)
    private ObservableList<String> getShips() {
    	return FXCollections.observableArrayList("Item 1", "Item 2", "Item 3");
    }
    
    // Gets Rooms from database (Need to Update)
    private ObservableList<String> getRooms() {
    	return FXCollections.observableArrayList("Room 1", "Room 2", "Room 3");
    }
    
    
}
