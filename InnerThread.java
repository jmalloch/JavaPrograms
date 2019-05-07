package assignmentFour;
import assignmentFour.IllegalFastener;

public abstract class InnerThread extends Threaded{

	private static final long serialVersionUID = 5581652587201559579L;

	public InnerThread(String diameter, String material, String finish, double unitPrice, int numItems)
			throws IllegalFastener {
		super(diameter, material, finish, unitPrice, numItems);
		checkFinish(material, finish);
	}//end constructor
	
	public void checkFinish(String material, String finish) throws IllegalFastener{
		if(material.equalsIgnoreCase("Steel")) {
			if(!finish.equalsIgnoreCase("Chrome") && !finish.equalsIgnoreCase("Hot Dipped Galvanized") &&
					!finish.equalsIgnoreCase("Plain") && !finish.equalsIgnoreCase("Yellow Zinc") 
					&& !finish.equalsIgnoreCase("Zinc")) {
				throw new IllegalFastener("Illegal Finish.");
			}
		} else if(!finish.equalsIgnoreCase("Plain")) {
					throw new IllegalFastener("Illegal Finish");
			}
	}
}
