package gesProcesseur;

import java.util.ArrayList;
import java.util.Random;

import nonMemoire.Central;
import nonMemoire.Instruction ;
import nonMemoire.PCB;
import nonMemoire.Proces;



public class Processeur {
	private int frequency; // en MHz
	private int AX;
	private int BX;
	private int CX;
	private int DX;

	private int SI;
	private int DI;
	private int SP;
	private int BP;

	private int IP;
	private int CS;
	private int DS;
	private int SS;
	private int ES;

	private Random rand = new Random();

	private int flagsRegister;

	public Processeur(int frequency) {
		this.randomValueRegisters();
		this.frequency = frequency ;
	}


	
	public void randomValueRegisters() {
		AX = rand.nextInt();
		BX = rand.nextInt();
		CX = rand.nextInt();
		DX = rand.nextInt();

		SI = rand.nextInt();
		DI = rand.nextInt();
		SP = rand.nextInt();
		BP = rand.nextInt();

		IP = rand.nextInt();
		CS = rand.nextInt();
		DS = rand.nextInt();
		SS = rand.nextInt();
		ES = rand.nextInt();

		flagsRegister = rand.nextInt();
	}
	
	public int[] stateResgisters() {
		int[] Registers = new int[14] ;
		Registers[0] = AX ;
		Registers[1] = BX ;
		Registers[2] = CX ;
		Registers[3] = DX ;
		Registers[4] = SI ;
		Registers[5] = DI ;
		Registers[6] = SP ;
		Registers[7] = BP ;
		Registers[8] = IP ;
		Registers[9] = CS ;
		Registers[10] = DS ;
		Registers[11] = SS ;
		Registers[12] = ES ;
		Registers[13] = flagsRegister ;
		
		return Registers ;
	}
	
	public int execute(ArrayList<Instruction> inst) {
		// Execute and return the interrupt number
		
		for(int i=0 ; i < inst.size() ; i++) {
			this.randomValueRegisters();
			if(Central.IT[i].getAddress() == inst.get(i).getAdresse()) 
				return PCB.READY ;
			else {
				if(inst.get(i).getAdresse() == Central.sysCallTable[i].getAddress()) 
					return PCB.BLOCKED ;
				else if(i == inst.size())
					return PCB.TERMINATED ;
			}
			
		}
		return -1 ;
	}
}
