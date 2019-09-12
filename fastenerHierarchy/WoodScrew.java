package assignmentFour;
import assignmentFour.IllegalFastener;

public class WoodScrew extends Screw{

	private static final long serialVersionUID = -7109360928913711609L;
	
	private String point;

	public WoodScrew(double length, String diameter, String material, String finish, String head, String drive, String point,  
			double unitPrice, int numItems) throws IllegalFastener {
		super(head, drive, length, diameter, material, finish, unitPrice, numItems);
		initPoint(point);
	}//end constructor

	public void initPoint(String point) throws IllegalFastener{
		if(point.equalsIgnoreCase("Double Cut") || point.equalsIgnoreCase("Sharp")
				||point.equalsIgnoreCase("Type 17")) {
			this.point = point;
		}else {
			throw new IllegalFastener("Illegal wood screw point type.");
		}
	}
	
	public String toString() {
		return "Wood Screw, " + point + " point, " + super.toString();
	}
}
