package assignmentFour;
import assignmentFour.IllegalFastener;

public abstract class Nail extends Fastener {

	private static final long serialVersionUID = -8408651106681351127L;

	private String size;
	private double length;
	private double gauge;
	
	public Nail(String size, double length, double gauge, String finish, double unitPrice, int numItems) throws IllegalFastener {
		super("Steel", finish, unitPrice, numItems);
		this.size = size;
		this.length = length;
		this.gauge = gauge;
	}//end constructor

	public String toString() {
		return size + " size, " + length + "\" long, " + gauge + " gauge, " + super.toString();
	}
}
