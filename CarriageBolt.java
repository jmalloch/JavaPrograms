package assignmentFour;
import assignmentFour.IllegalFastener;

public class CarriageBolt extends Bolt{

	private static final long serialVersionUID = 9032329262622712700L;

	public CarriageBolt(double length, String diameter, String material, String finish, double unitPrice, int numItems)
			throws IllegalFastener {
		super(length, diameter, material, finish, unitPrice, numItems);
	}//end constructor

	public String toString() {
		return "Carriage Bolt " + super.toString();
	}
}
