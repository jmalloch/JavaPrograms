package application;
import application.IllegalPizza;

import java.io.Serializable;
import java.text.DecimalFormat;

public final class Pizza implements Serializable {

	/**
	 * A class to store pizza order information.
	 * March 7, 2019
	 * <p>
	 * Information recorded: size, volume of cheese, pineapple, green pepper, and ham.
	 * An IllegalPizza exception is thrown if an invalid argument is supplied.
	 * <p>
	 * The class is capable of returning the cost of the pizza, the information about the pizza as a string,
	 * comparing the pizza to other pizzas, and clone the current pizza object.
	 * <p>
	 * @author Jack Malloch
	 */
	private static final long serialVersionUID = -8189549335178315036L;
	private String size;
	private String cheese;
	private String greenPepper;
	private String pineapple;
	private String ham;

	/**
	 * Constructor one.
	 * @param size; S, M, or L.
	 * @param cheese; 1x, 2x, or 3x.
	 * @param pineapple; 0x, or 1x.
	 * @param greenPepper; 0x, or 1x.
	 * @param ham; 0x, or 1x.
	 * @throws IllegalPizza; if args passed are invalid.
	 */
	public Pizza(String size, String cheese, String pineapple, String greenPepper, String ham) throws IllegalPizza {
		defSize(size);
		defCheese(cheese);
		defGreenPepper(greenPepper);
		defPineapple(pineapple);
		defHam(ham);
		
		hamCheck();
	}//end constructor one
	
	/**
	 * Default pizza is as follows.
	 * @param size; Small.
	 * @param cheese; 1x.
	 * @param ham; 1x.
	 * 
	 * @throws IllegalPizza
	 */
	public Pizza() throws IllegalPizza {
		this.size = "Small";
		this.cheese = "Single";
		this.ham = "Single";
		this.greenPepper = "none";
		this.pineapple = "none";
	}//end default constructor
	
	/**
	 * Setting pizza size.
	 * @param size; Size of pizza.
	 * @throws IllegalPizza; if size arg is invalid.
	 */
	public void defSize(String size) throws IllegalPizza{
		if(size == null)
			throw new IllegalPizza("Not a valid Entry.");
		if(!size.equalsIgnoreCase("small") && !size.equalsIgnoreCase("medium") && !size.equalsIgnoreCase("large"))
			throw new IllegalPizza(size + " is not a valid entry.");
		this.size = size;
	}//end defSize mutator
	
	/**
	 * Defining volume of cheese contained on pizza.
	 * @param cheese; amount of cheese on pizza.
	 * @throws IllegalPizza; if cheese arg is invalid.
	 */
	public void defCheese(String cheese) throws IllegalPizza{
		if(cheese == null)
			throw new IllegalPizza("Not a valid Entry.");
		if(!cheese.equalsIgnoreCase("single") && !cheese.equalsIgnoreCase("double") && !cheese.equalsIgnoreCase("triple"))
			throw new IllegalPizza(cheese + " is not a valid entry.");
		this.cheese = cheese;
	}//end defCheese mutator
	
	/**
	 * Defining if toppings include green peppers.
	 * @param greenPepper; yes or no to green peppers.
	 * @throws IllegalPizza; if greenPepper arg is invalid.
	 */
	public void defGreenPepper(String greenPepper) throws IllegalPizza{
		if(greenPepper == null)
			throw new IllegalPizza("Not a valid Entry.");
		if(!greenPepper.equalsIgnoreCase("none") && !greenPepper.equalsIgnoreCase("single"))
			throw new IllegalPizza(greenPepper + " is not a valid entry.");
		this.greenPepper = greenPepper;
	}//end defGreenPepper mutator
	
	/**
	 * Defining if toppings include pineapple.
	 * @param pineapple; yes or no to pineapple.
	 * @throws IllegalPizza; if pineapple arg is invalid.
	 */
	public void defPineapple(String pineapple) throws IllegalPizza{
		if(pineapple == null)
			throw new IllegalPizza("Not a valid Entry.");
		if(!pineapple.equalsIgnoreCase("none") && !pineapple.equalsIgnoreCase("single"))
			throw new IllegalPizza(pineapple + " is not a valid entry.");
		this.pineapple = pineapple;
	}//end defPineapple mutator
	
	/**
	 * * Defining if toppings include ham.
	 * @param ham; yes or no to ham.
	 * @throws IllegalPizza; if ham arg is invalid.
	 */
	public void defHam(String ham) throws IllegalPizza{
		if(ham == null)
			throw new IllegalPizza("Not a valid Entry.");
		if(!ham.equalsIgnoreCase("none") && !ham.equalsIgnoreCase("single"))
			throw new IllegalPizza(ham + " is not a valid entry.");
		this.ham = ham;
	}//end defHam mutator
	
	/**
	 * Checking if a pizza was distastefully made with green peppers or pineapple and no ham.
	 * @throws IllegalPizza; if Pizza composition is in poor taste.
	 */
	public void hamCheck() throws IllegalPizza{
		if(ham.equalsIgnoreCase("none"))
			if(greenPepper.equalsIgnoreCase("single") || pineapple.equalsIgnoreCase("single"))
				throw new IllegalPizza("GROSS! Include ham with your green peppers or pineapple.");
	}//end hamCheck
	
	/**
	 * Calculates the cost of a specified pizza instance.
	 * @return cost; the cost as a double.
	 */
	public double getCost() {
		DecimalFormat costFormat = new DecimalFormat("#.##");
		
		double cost = 0.00;
		
		if(size.equalsIgnoreCase("small"))
			cost += 7.00;
		if(size.equalsIgnoreCase("medium"))
			cost += 9.00;
		if(size.equalsIgnoreCase("large"))
			cost += 11.00;
		
		if(cheese.equalsIgnoreCase("double"))
			cost += 1.50;
		if(cheese.equalsIgnoreCase("triple"))
			cost += 3.00;
		
		if(greenPepper.equalsIgnoreCase("single"))
			cost += 1.50;
		if(pineapple.equalsIgnoreCase("single"))
			cost += 1.50;
		if(ham.equalsIgnoreCase("single"))
			cost += 1.50;
		
		costFormat.format(cost);
		return cost;
	}//end getCost
	
	/**
	 * A string describing the current pizza instance.
	 * @return A string of the pizza, including size, toppings, and cost.
	 */
	public String toString() {
		String pizzaStatus ="";
		String costString= String.format("%.2f", getCost());
		
		pizzaStatus += size + " pizza(s), ";
		pizzaStatus += cheese + " cheese";
		
		if(pineapple.equalsIgnoreCase("single"))
			pizzaStatus += ", pineapple";
		if(greenPepper.equalsIgnoreCase("single"))
			pizzaStatus += ", green pepper";
		if(ham.equalsIgnoreCase("single"))
			pizzaStatus += ", ham";

		pizzaStatus += ". Cost: $" + costString + " each.";
		
		return pizzaStatus;
	}//end toString
	
	/**
	 * Testing 2 Pizza objects for equality.
	 * @return <code>true</code> if all the attributes of both objects are exactly equal, <code>false</code>
     * otherwise.
     * @param anyObject; The other Pizza object.
	 */
	@Override
	public boolean equals(Object anyObject) {
		if(anyObject instanceof Pizza) {
			Pizza other = (Pizza)anyObject;
			if(toString() .equals(other.toString()))
				return true;
		}
		return false;
	}//end equals
	
	/**
	 * Copying the current pizza object.
	 * @return a copy of the object.
	 */
	@Override
	public Pizza clone() {
		Pizza copy = null;
		try {
			copy = new Pizza(size, cheese, pineapple, greenPepper, ham);
		} catch(IllegalPizza e) {
			System.out.println(e.getMessage());
			return null;
		}
		return copy;
	}//end clone
}//end Pizza the Class
