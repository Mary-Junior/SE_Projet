package nonMemoire;

public class SysCall extends Interruption{
	private final int SIZE=32; // chaque entree est sur 32 bits, 16 pour le numero de segment, 16 de la routine
	private byte privilege =0;
	public SysCall() {
		
	}
	
	
	public SysCall(String name, short address, byte privilege) {
		super();
	}
	
	public SysCall(String name, short address) {
	    this.setName(name);
	    this.setAddress(address);
	}
}

