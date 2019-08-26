package gesDisqueEtFichier;

import java.util.ArrayList;

import nonMemoire.Instruction;

public class FichierSysteme extends Fichier{
	private ArrayList<Instruction> instructions = new ArrayList<>();
	private final int priority; 
	
	public FichierSysteme(Fichier parent, String name, boolean directory, ArrayList<Instruction> instructions,
			int priority) {
		super(parent, name, directory);
		this.instructions = instructions;
		this.priority = priority;
	}
	public ArrayList<Instruction> getInstructions() {
		return instructions;
	}
	public void setInstructions(ArrayList<Instruction> instructions) {
		this.instructions = instructions;
	}

	public int getPriority() {
		return priority;
	}
	public int getSize() {
		int size = 0;
		for(Instruction instruction : instructions){
			size+= instruction.getSize_B();
		}
		
		if(this.getFichierParent().equals(GesDisque.Root))
			return 512 ;
		
		return size;
	}
	public String toString() {
		return this.name+"\t"+this.getSize()+" B";
	}
}
