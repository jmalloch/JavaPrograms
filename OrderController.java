package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class OrderController{
	//setting up ChoiceBox options & initializing strings to hold CheckBox values
	ObservableList<String> sizeChoiceList = FXCollections.observableArrayList("Small", "Medium", "Large");
	ObservableList<String> cheeseChoiceList = FXCollections.observableArrayList("Single", "Double", "Triple");
	private String hamChoice;
	private String gPChoice;
	private String pineappleChoice;
	private int lineCounter = 0;	//will be used to number the 
	
	//this method will update the configurable pizza item & alter the cost displayed
	private void UpdatePizza() {
		try{
			if(sizeChoiceBox.getValue().equals(null)) sizeChoiceBox.setValue("Small");
			if(cheeseChoiceBox.getValue().equals(null)) cheeseChoiceBox.setValue("Single");
			if(hamChoice == null) hamChoice = "None";
			if(gPChoice == null) gPChoice = "None";
			if(pineappleChoice == null) pineappleChoice = "None";
			pizza = new Pizza(sizeChoiceBox.getValue().toLowerCase(), cheeseChoiceBox.getValue(), pineappleChoice, gPChoice, hamChoice);
			String pCost = String.valueOf(pizza.getCost());
			currentPizzaCost.setText("$" + pCost + "0");
		} catch(IllegalPizza ex) {
			System.out.println("Illegal Pizza Configuration.");
		}
	}
	
	//Initializing everything that requires and fx id 
	//Size & Cheese
	@FXML
	private ChoiceBox<String> sizeChoiceBox;
	@FXML
	private ChoiceBox<String> cheeseChoiceBox;
		
	
	//Toppings
	//Check boxes
	@FXML
	private CheckBox hamNone;
	@FXML
	private CheckBox hamSingle;
	@FXML
	private CheckBox gPNone;
	@FXML
	private CheckBox gPSingle;
	@FXML
	private CheckBox pineappleNone;
	@FXML
	private CheckBox pineappleSingle;
	
	//saving a single pizza
	@FXML
	private Button savePizza;
	
	//Order Summary
	@FXML
	private TextArea orderList = new TextArea();
	//subtotal
	@FXML
	private Label currentOrderSubtotal = new Label();
	
		
	//Price per Pizza
	@FXML
	private Label currentPizzaCost = new Label();
	
	
	//Number of Pizzas
	@FXML
	private TextField pizzaQuantityIn = new TextField();
	@FXML
	private Label currentLineCost = new Label();
	@FXML
	private Button addToOrder = new Button();
	
	
	//creating a pizza and a lineitem object
	private Pizza pizza;
	private LineItem lineItem;
	
	//initialize method broken up into tab's
	@FXML
	private void initialize() {	
		//tab1
		//setting starting values and options for choice boxes as well as calling update method to vary cost
		sizeChoiceBox.setValue("Small");
		sizeChoiceBox.setItems(sizeChoiceList);
		
		sizeChoiceBox.valueProperty().addListener((observe, oVal, nVal)->{
			UpdatePizza();
		});

		cheeseChoiceBox.setValue("Single");
		cheeseChoiceBox.setItems(cheeseChoiceList);
		
		cheeseChoiceBox.valueProperty().addListener((observe, oVal, nVal)->{
			UpdatePizza();
		});
		
		//tab2
		//setting up various listeners to ensure one checkbox per topping is selected and updating cost
		hamNone.selectedProperty().addListener((observe, oldCheck, newCheck) ->{
			if(hamNone.isSelected()) {
				hamChoice = "None";
				hamSingle.setSelected(false); 		//ensure only one option per topping is selected
				gPSingle.setDisable(true);			//does not allow for green pepper or pineapple without ham as per Assn 3 instructions
				gPSingle.setSelected(false);		//deselect green peppers as there is no ham
				pineappleSingle.setDisable(true);	
				pineappleSingle.setSelected(false);	//deselect green peppers as there is no ham
				
				UpdatePizza();
			}
		});
		hamSingle.selectedProperty().addListener((observe, oldCheck, newCheck) ->{
			if(hamSingle.isSelected()) {
				hamChoice = "Single";
				hamNone.setSelected(false); 		//ensure only one option per topping is selected
				gPSingle.setDisable(false);
				gPSingle.setSelected(false);
				pineappleSingle.setDisable(false);
				
				UpdatePizza();
			}
		});
		
		gPNone.selectedProperty().addListener((observe, oldCheck, newCheck) ->{
			if(gPNone.isSelected()) {
				gPChoice = "None";
				gPSingle.setSelected(false); 		//ensure only one option per topping is selected
			
				UpdatePizza();
			}
		});
		gPSingle.selectedProperty().addListener((observe, oldCheck, newCheck) ->{
			if(gPSingle.isSelected()) {
				gPChoice = "Single";
				gPNone.setSelected(false); 			//ensure only one option per topping is selected
			
				UpdatePizza();
			}
		});
		
		pineappleNone.selectedProperty().addListener((observe, oldCheck, newCheck) ->{
			if(pineappleNone.isSelected()) {
				pineappleChoice = "None";
				pineappleSingle.setSelected(false); //ensure only one option per topping is selected
			
				UpdatePizza();
			}
		});
		pineappleSingle.selectedProperty().addListener((observe, oldCheck, newCheck) ->{
			if(pineappleSingle.isSelected()) {
				pineappleChoice = "Single";
				pineappleNone.setSelected(false); 	//ensure only one option per topping is selected
			
				UpdatePizza();
			}
		});
		
		//saving pizza item and allowing an entry for quantity
		savePizza.setOnAction(event ->
		{
			try {
				pizza = new Pizza(sizeChoiceBox.getValue().toLowerCase(), cheeseChoiceBox.getValue(), pineappleChoice, gPChoice, hamChoice);
				String pCost = String.valueOf(pizza.getCost());
				currentPizzaCost.setText("$" + pCost + "0");
				pizzaQuantityIn.setDisable(false);
			}
			catch(IllegalPizza ex) {
				Alert illegalPizza = new Alert(AlertType.WARNING);
				illegalPizza.setTitle("Pizza Ordering System");
				illegalPizza.setContentText("You have configured an Illegal Pizza! \nRetry your order.");
				illegalPizza.showAndWait();
			}		
		});
		
		
		//tab3
		orderList.setWrapText(true);
		//num pizzas 
		pizzaQuantityIn.setDisable(true);
		//ensuring enter quantity is in 1-100 interval && calculating lineitem cost
		pizzaQuantityIn.textProperty().addListener((observe, oVal, nVal) -> 
		{
			if (!nVal.matches("\\d*")) {
				pizzaQuantityIn.setText(nVal.replaceAll("[^\\d]", ""));
			} else {
				Double tempLineSubtotal = pizza.getCost()*Double.parseDouble(pizzaQuantityIn.getText());
				currentLineCost.setText("+ $ " + String.valueOf(tempLineSubtotal) + "0");
			}
		});		
		//adding a new lineitem to order, changing subtotal, displaying line item && reseting various input options
		addToOrder.setOnAction(event ->
		{
			if(!pizzaQuantityIn.getText().equals("")) {
				try {
					int quantity = Integer.parseInt(pizzaQuantityIn.getText());
					lineItem = new LineItem(quantity, pizza);
					double lineCost = (double)pizza.getCost()*quantity;
					double currentSubtotal = (double)(Double.parseDouble(currentOrderSubtotal.getText()) + lineCost);
					currentOrderSubtotal.setText(String.valueOf(currentSubtotal + "0"));
				
					String oldOrderList = orderList.getText();
					orderList.setText(oldOrderList + "\n\n" + ++lineCounter + ": " + lineItem.toString());
					
					Alert pizzaAdded = new Alert(AlertType.INFORMATION);
					pizzaAdded.setTitle("Pizza Ordering System");
					pizzaAdded.setContentText("Line of Pizza's successfully added to order! \n"
							+ "Review your order in the 'Order Summary' tab.");
					pizzaAdded.showAndWait();
				
					pizzaQuantityIn.setText("");
					currentLineCost.setText("+ $ 0.00");
					hamNone.setSelected(false);
					hamSingle.setSelected(false);
					gPNone.setSelected(false);
					gPSingle.setSelected(false);
					gPSingle.setDisable(false);
					pineappleNone.setSelected(false);
					pineappleSingle.setSelected(false);
					pineappleSingle.setDisable(false);
				
					sizeChoiceBox.setValue("Small");
					cheeseChoiceBox.setValue("Single");
				
					pizzaQuantityIn.setDisable(true);

					currentPizzaCost.setText("$ 7.00");
				} 
				catch(IllegalPizza ex) {
					Alert illegalLine = new Alert(AlertType.WARNING);
					illegalLine.setTitle("Pizza Ordering System");
					illegalLine.setContentText("You have configured an Illegal Line of Pizzas! \nRetry your order.");
					illegalLine.showAndWait();
				}
			}	
		});	
	}//end initialize
}//end class