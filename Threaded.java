package assignmentFour;
import assignmentFour.IllegalFastener;

public abstract class Threaded extends Fastener{

	private static final long serialVersionUID = 5016036040843405773L;
	
	private String diameter;
	
	public Threaded(String diameter, String material, String finish, double unitPrice, int numItems) throws IllegalFastener {
		super(material, finish, unitPrice, numItems);
		initDiameter(diameter);
		checkMaterial(material);
	}//end constructor
	
	public void initDiameter(String diameter) throws IllegalFastener{
		if(diameter.equalsIgnoreCase("#8-13") || diameter.equalsIgnoreCase("#8-15") ||diameter.equalsIgnoreCase("#8-32") 
				||diameter.equalsIgnoreCase("#10-13")||diameter.equalsIgnoreCase("#10-24") ||diameter.equalsIgnoreCase("#10-32")
				||diameter.equalsIgnoreCase("1/4-20") ||diameter.equalsIgnoreCase("5/16-18") ||diameter.equalsIgnoreCase("3/8-16")
				||diameter.equalsIgnoreCase("7/16-14") ||diameter.equalsIgnoreCase("1/2-13") ||diameter.equalsIgnoreCase("5/8-11")
				||diameter.equalsIgnoreCase("3/4-10")) {
		this.diameter = diameter;
		} else {
			throw new IllegalFastener("Illegal diameter");
		}
	}
	
	public void checkMaterial(String material) throws IllegalFastener{
		if(!material.equalsIgnoreCase("Brass") && !material.equalsIgnoreCase("Stainless Steel") 
				&& !material.equalsIgnoreCase("Steel")) {
			throw new IllegalFastener("Illegal material type.");
		}
	}
	
	
	public String toString() {
		return diameter + " thread, " + super.toString();
	}

}
