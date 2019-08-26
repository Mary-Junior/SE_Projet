package gesMemoire;

public class RAM {
	private static int sizeRAM ;

	public static int getSizeRAM() {
		return sizeRAM;
	}

	public static void setSizeRAM(int sizeRAM) {
		RAM.sizeRAM = sizeRAM;
	}
	
	public int NbreDeFrames() {
		if(sizeRAM % 16 == 0)
			return (sizeRAM/16) ;
		else return (sizeRAM/16)+1 ;
	}
}
