package assignmentFour;
import assignmentFour.IllegalFastener;

public abstract class Screw extends OuterThread{
	
	private static final long serialVersionUID = -6527639717196290438L;

	private String head;
	private String drive;
	
	public Screw(String head, String drive, double length, String diameter, String material, String finish, double unitPrice, int numItems)
			throws IllegalFastener {
		super(length, diameter, material, finish, unitPrice, numItems);
		initHead(head);
		initDrive(drive);
		checkFinish(material, finish);
	}//end constructor
	
	public void initHead(String head) throws IllegalFastener{
		if(head.equalsIgnoreCase("Bugle") || head.equalsIgnoreCase("Flat") || head.equalsIgnoreCase("Oval")
				|| head.equalsIgnoreCase("Pan") || head.equalsIgnoreCase("Round")) {
			this.head = head;
		} else {
			throw new IllegalFastener("Illegal screw head type.");
		}
	}
	
	public void initDrive(String drive) throws IllegalFastener{
		if(drive.equalsIgnoreCase("6-Lobe") || drive.equalsIgnoreCase("Philips")
				|| drive.equalsIgnoreCase("Slotted") || drive.equalsIgnoreCase("Square")) {
			this.drive = drive;
		}else {
			throw new IllegalFastener("Illegal screw drive type.");
		}
	}
	
	public void checkFinish(String material, String finish) throws IllegalFastener{
		if(material.equalsIgnoreCase("Steel")) {
			if(!finish.equalsIgnoreCase("Chrome") && !finish.equalsIgnoreCase("Hot Dipped Galvanized") &&
					!finish.equalsIgnoreCase("Plain") && !finish.equalsIgnoreCase("Yellow Zinc") &&
					!finish.equalsIgnoreCase("Zinc") && !finish.equalsIgnoreCase("Black Phosphate") &&
					!finish.equalsIgnoreCase("ACQ 1000 Hour") && !finish.equalsIgnoreCase("Lubricated")) {
				throw new IllegalFastener("Illegal Finish.");
			}
		} else if(!finish.equalsIgnoreCase("Plain")) {
			throw new IllegalFastener("Illegal Finish.");
		}
	}
	
	public String toString() {
		return head + " head, " + drive + " drive, " + super.toString();
	}
}
