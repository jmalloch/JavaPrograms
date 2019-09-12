package assignmentFour;
import assignmentFour.IllegalFastener;

public class CommonNail extends Nail{
	
	private static final long serialVersionUID = 1721439662333516161L;
	
	public CommonNail(String size, double length, double gauge, String finish, double unitPrice,
			int numItems) throws IllegalFastener {
		super(size, length, gauge, finish, unitPrice, numItems);
		checkSize(size);
		checkLength(length);
		checkGauge(gauge);
		checkFinish(finish);
	}//end constructor
	
	public void checkSize(String size) throws IllegalFastener{
		if(!size.equalsIgnoreCase("6D") && !size.equalsIgnoreCase("8D") && !size.equalsIgnoreCase("10D")
				&& !size.equalsIgnoreCase("12D") && !size.equalsIgnoreCase("16D") && !size.equalsIgnoreCase("60D")) {
			throw new IllegalFastener("Illegal nail size.");
		}
	}
	
	public void checkLength(double length) throws IllegalFastener{
		if(length != 2 && length != 2.5 && length != 3 && length != 3.25 && length != 3.5 && length != 6) {
			throw new IllegalFastener("Illegal common nail length.");
		}
	}
	
	public void checkGauge(double gauge) throws IllegalFastener{
		if(gauge != 2 && gauge != 8 && gauge != 9 && gauge != 10.25 && gauge != 11.5) {
			throw new IllegalFastener("Illegal nail gauge.");
		}
	}

	public void checkFinish(String finish) throws IllegalFastener{
		if(!finish.equalsIgnoreCase("Bright") && !finish.equalsIgnoreCase("Hot Dipped Galvanized") 
				&& !finish.equalsIgnoreCase("Bright")) {
			throw new IllegalFastener("Illegal common nail finish.");
		}
	}
	
	public String toString() {
		return "Common Nail, " + super.toString();
	}
}
