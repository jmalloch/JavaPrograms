package assignmentFour;
import assignmentFour.IllegalFastener;
import java.io.Serializable;

public abstract class Fastener implements Serializable {

	private static final long serialVersionUID = 6601640245900033646L;
	
	private String material;
	private String finish;
	private double unitPrice;
	private int numItems;
	
	public Fastener(String material, String finish, double unitPrice, int numItems) throws IllegalFastener{
		this.material = material;
		this.finish = finish;
		initUnitPrice(unitPrice);
		initNumItems(numItems);
		
	}//end constructor 
	
	public void initUnitPrice(double unitPrice) throws IllegalFastener{
		if(unitPrice > 0) {
			this.unitPrice = unitPrice;
		} else {
			throw new IllegalFastener("Illegal Unit Price");
		}
	}
	
	public void initNumItems(int numItems) throws IllegalFastener{
		if(numItems > 0 && numItems < 10000) {
			this.numItems = numItems;
		} else {
			throw new IllegalFastener("Illegal Number of Items");
		}
	}

	public String toString() {
		return material + ", with a " + finish + "finish. " + numItems + " in a unit, $" + unitPrice + " per unit.";
	}
	
	public double getCost(int numItems) {
		return (double)unitPrice * numItems;
	}
}
