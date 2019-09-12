package application;

import java.io.Serializable;
import java.lang.Math;

public class LineItem implements Serializable {

	/**
	  A class to store a pizza order line item.
	 * March 7, 2019
	 * <p>
	 * Information recorded: number of pizzas on the line.
	 * <p>
	 * The class is capable of returning the cost of pizzas facotring in discounts on bulk orders, 
	 * the number and type of pizzas as a string, and can compare two pizza lines by cost.
	 * <p>
	 * @author Jack Malloch
	 */
	private static final long serialVersionUID = 12679343344744499L;
	private Pizza pizza;
	public int count = 1;
	
	/**
	 * Full constructor class.
	 * @param pizzaCount; pizza object.
	 * @param pizzaPie; number of pizza objects.
	 * @throws IllegalPizza; if the pizza object or number of pizzas is invalid.
	 */
	public LineItem(int pizzaCount, Pizza pizzaPie) throws IllegalPizza{
		if(pizzaPie == null)
			throw new IllegalPizza("Bad pizza, try again.");
		if(pizzaCount < 1 || pizzaCount > 100)
			throw new IllegalPizza("Pizza count must be between 1 & 100.");
		
		pizza = pizzaPie;
		count = pizzaCount;
	}//end LineItem
	
	/**
	 * Constructor with a default of 1 pizza.
	 * @param pizzaPie; pizza object.
	 * @throws IllegalPizza; thrown if pizza object is invalid.
	 */
	public LineItem(Pizza pizzaPie) throws IllegalPizza{
		if(pizzaPie == null)
			throw new IllegalPizza("Bad Pizza, try again.");
		pizza = pizzaPie;
	}//end LineItem
	
	/**
	 * Defining the number of pizzas.
	 * @param pizzaCount; number of pizzas.
	 * @throws IllegalPizza; thrown if the number of pizzas is invalid.
	 */
	public void setNumber(int pizzaCount) throws IllegalPizza{
		if(pizzaCount < 1 || pizzaCount > 100)
			throw new IllegalPizza("Pizza count must be between 1 & 100.");
		count = pizzaCount;
	}//end defNumber
	
	/**
	 * Returns pizza object.
	 * @return pizza object.
	 */
	public Pizza getPizza() {
		return pizza;
	}//end getPizza
	
	/**
	 * Returns integer number of pizzas.
	 * @return number of pizzas.
	 */
	public int getNumber() {
		return count;
	}//end getCount
	
	/**
	 * Calculates the cost of pizzas facotring in discounts.
	 * @return total cost as a double.
	 */
	public double getCost() {
		double cost = pizza.getCost() * count;
		double total = cost;
		if(count > 20)
			total = total * .90;
		else if (count > 9)
			total =total * .95;
		
		return total;
	}//end getCost
	
	/**
	 * A string describing the current lineItem instance.
	 * @return A string of the number of pizzas , including size, toppings, and cost.
	 */
	public String toString() {
		String lineStatus = pizza.toString();
		if(count < 10)
			lineStatus = " " + count + " " + lineStatus;
		else
			lineStatus = count + " " + lineStatus;
		
		return lineStatus;
	}//end toString
	
	/**
	 * Compares line cost of two LineItem objects.
	 * @param line2; LineItem object of comparison.
	 * @return 0 if the difference is within $1, 1 if line2 > current LineItem object,
	 * and -1 if line2 < current LineItem object.
	 */
	public int compareTo(LineItem line2) {
		double cost1 = this.getCost();
		double cost2 = line2.getCost();
		if(Math.abs(cost1-cost2) < 1)		//absolute value of the difference
			return 0;
		return (Integer)Double.compare(cost2, cost1);
	}//end compareTo
}
