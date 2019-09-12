package assignmentFour;
import assignmentFour.IllegalFastener;

public abstract class Bolt extends OuterThread{

	private static final long serialVersionUID = 5648949808942118819L;
	
	public Bolt(double length, String diameter, String material, String finish, double unitPrice, int numItems)
			throws IllegalFastener {
		super(length, diameter, material, finish, unitPrice, numItems);
		checkFinish(material,finish);
	}//end constructor
	
	public void checkFinish(String material, String finish) throws IllegalFastener{
		if(material.equalsIgnoreCase("Steel")) {
			if(!finish.equalsIgnoreCase("Chrome") && !finish.equalsIgnoreCase("Hot Dipped Galvanized") &&
					!finish.equalsIgnoreCase("Plain") && !finish.equalsIgnoreCase("Yellow Zinc") 
					&& !finish.equalsIgnoreCase("Zinc")) {
				throw new IllegalFastener("Illegal Finish.");
			}
		} else if(!finish.equalsIgnoreCase("Plain")) {
			throw new IllegalFastener("Illegal Finish.");
		}
	}

}
