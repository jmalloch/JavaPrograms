package assignmentFour;
import assignmentFour.IllegalFastener;

public abstract class OuterThread extends Threaded{

	private static final long serialVersionUID = 371215395913499353L;

	private double length;
	
	public OuterThread(double length, String diameter, String material, String finish, double unitPrice, int numItems) throws IllegalFastener {
		super(diameter, material, finish, unitPrice, numItems);
		initLength(length);
	}//end constructor
	
	public void initLength(double length) throws IllegalFastener{
		if(length >= 0.5 && length <= 6.0 && length % 0.25 == 0) {
			this.length = length;
		} else if(length >= 6.0 && length <= 11.0 && length % 0.5 == 0) {
			this.length = length;
		} else if(length >= 11.0 && length <= 20.0 && length % 1 == 0) {
			this.length = length;
		} else {
			throw new IllegalFastener("Illegal fastener length");
		}
	}

	public String toString() {
		return length + " \" long, " + super.toString();
	}
}
