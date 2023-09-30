package application;

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


public class CruisesContent extends ContentArea {
	
    VBox cruiseMain = new VBox();
    ImageView shipImageView = new ImageView();
    
	
    @SuppressWarnings({ "rawtypes" })
	@Override
    public void initialize() {

        GridPane selectPane = new GridPane();
        VBox vbox = new VBox();
        VBox searchLabels = new VBox();
        VBox searchFields = new VBox();

        // need to fix this spacing
        searchLabels.setSpacing(19);
        searchFields.setSpacing(16);
        
        Label searchLabel = new Label("Search");

        searchLabels.getChildren().addAll
                ( 
                		searchLabel, new Label("Date"), 
                    new Label("Ship"), new Label("Origin"), 
                    new Label("Destination")
                );

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(10);
        
        // Cruise search components
        ComboBox cruiseSearch = new ComboBox();
        cruiseSearch.setMaxSize(200, 200);
        /*cruiseSearch.setItems(getShips());*/
        
        Label cruiseDate = new Label("1/1/2023");
        cruiseDate.setMaxSize(150, 150);
        
        Label cruiseShip = new Label("Ship Name");
        cruiseShip.setMaxSize(150, 150);
        
        Label cruiseOrigin = new Label("Cruise Origin");
        cruiseOrigin.setMaxSize(150, 150);
        
        Label cruiseDestination = new Label("Cruise Destination");
        cruiseDestination.setMaxSize(150, 150);

        searchFields.getChildren().addAll(
        		cruiseSearch, cruiseDate, cruiseShip,
        		cruiseOrigin, cruiseDestination
                );
        


        // search box
        HBox searchBox = new HBox(searchLabels, searchFields);
        searchBox.setSpacing(20);
        searchBox.getStyleClass().add("search-box");
        
        // ship info
        GridPane shipInfo = new GridPane();
        shipInfo.add(new Label("Ship Name"), 0, 0);
        shipInfo.add(new Label("Origin"), 0, 1);
        shipInfo.add(new Label("Date"), 0, 2);
        shipInfo.add(new Label("Destination"), 0, 3);
        shipInfo.add(new Label("Link"), 0, 4);
        shipInfo.add(new Label("Price"), 0, 5);
        
        // ship box
        HBox shipBox = new HBox(shipImageView, shipInfo);
        setImageUrl("ship.png");      
        shipBox.getStyleClass().add("ship-box");
        
        Button shipButton = new Button("Select Cruise");
        shipButton.setOnAction(e -> {
        	if (cruiseSearch.getValue() != null) {
            	openLodgingWindow();
        	}
        	else {
        		openErrorWindow("CRUISE");
        	}

        });
        

        selectPane.setHgap(10);
        selectPane.setVgap(10);

        selectPane.add(searchBox, 0, 0);
        selectPane.add(shipBox, 0, 1);
        selectPane.add(shipButton, 0, 2);

        // Set the GridPane to expand within its container
        GridPane.setHgrow(selectPane, Priority.ALWAYS);
        GridPane.setVgrow(selectPane, Priority.ALWAYS);
        selectPane.setMaxWidth(Double.MAX_VALUE);

        vbox.getChildren().addAll(selectPane);

        content = vbox;
        content.getStyleClass().add("overview-content");
    }
      
    private void openLodgingWindow() {
        Stage newStage = new Stage();
        newStage.setTitle("Lodging");

        GridPane lodgingLayout = new GridPane();
        Scene newScene = new Scene(lodgingLayout, 400, 200); // Adjust window size

        lodgingLayout.setHgap(10); 
        lodgingLayout.setVgap(10);
        lodgingLayout.setStyle("-fx-padding: 20;");



        // Fields
        ComboBox<String> roomTypes = new ComboBox<String>(getRooms()); // Need to change to Room Type Object
        Spinner<Integer> passengers = new Spinner<Integer>();
        Label estCost = new Label("$0.00"); // Set Est Cost
        HBox roomInfo = new HBox();
      
        // Buttons
	    Button reserveButton = new Button("Reserve");
	    reserveButton.setDisable(true);
        Button bookButton = new Button("Book");
        bookButton.setOnAction(e -> {
        	if (roomTypes.getValue() != null) {
        		openBillingWindow();
        	}
        	else {
        		openErrorWindow("ROOM");
        	}
        });
        
        roomTypes.setOnAction(e -> {
        	updateRoomInfo(estCost, passengers.getValue(), roomTypes.getValue());
        });
        
        // Add Components
        lodgingLayout.add(roomTypes, 0, 0);
        lodgingLayout.add(passengers, 1, 0);
        lodgingLayout.add(estCost, 2, 0);
        lodgingLayout.add(roomInfo, 0, 3);
        lodgingLayout.add(bookButton, 1, 3); // Adjust column index
        lodgingLayout.add(reserveButton, 2, 3); // Adjust column index

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
        errorPane.setAlignment(Pos.CENTER); // Center the GridPane within the Scene
        Scene newScene = new Scene(errorPane, 300, 70);

        Button close = new Button("Close");
        close.setPrefSize(100, 10);
        close.setOnAction(e -> newStage.close());
        errorPane.add(new Label("SELECT A " + error), 0, 0);
        errorPane.add(close, 0, 1);

        newStage.setScene(newScene);
        newStage.show();
    }
    
    // Need to update 
    private void updateRoomInfo(Label estCost, int passengers, String room) {
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
    
    @SuppressWarnings("unused")
	private ObservableList<String> getShips() {
    	return FXCollections.observableArrayList("Item 1", "Item 2", "Item 3");
    }
    
    private ObservableList<String> getRooms() {
    	return FXCollections.observableArrayList("Room 1", "Room 2", "Room 3");
    }
    
    private void setImageUrl(String imageUrl) {
        Image image = new Image(getClass().getResourceAsStream(imageUrl));
        shipImageView.setImage(image);
    }
    
}
