package assignmentFour;
import assignmentFour.IllegalFastener;

public class WingNut extends InnerThread{

	private static final long serialVersionUID = 83340398909528100L;

	public WingNut(String diameter, String material, String finish, double unitPrice, int numItems)
			throws IllegalFastener {
		super(diameter, material, finish, unitPrice, numItems);
		
	}//end constructor

	public String toString() {
		return "Wing Nut " + super.toString();
	}
}
