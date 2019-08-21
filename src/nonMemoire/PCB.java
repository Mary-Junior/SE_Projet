package nonMemoire;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class PCB implements Comparable<PCB>{

	public static final int BLOCKED = 0;
	public static final int RUNNING = 1;
	public static final int TERMINATED = 2;
	public static final int READY = 3;
	private final int id;
	private Date dateCreer;
	private int priority;
	private int basePriority;
	private int state=-1;
	private Process process;
	private int currentInstruction=0;
	private ArrayList<Integer> indexesOfPages = new ArrayList<>();
	
	
	public PCB(Process process, int priority) {
		this.id=process.getId();
		this.process= process;
		this.basePriority=priority;
		this.basePriority=priority;
		this.dateCreer=Date.from(Instant.now());
		
	}
	
	public synchronized int getBasePriority() {
		return basePriority;
	}
	
   public int getPriority() {
	   return priority;
   }
   
   public void setPriority(int priority) {
	   this.priority= priority;
   }
   
   public ArrayList<Integer> getIndexesOfPages() {
		return indexesOfPages;
	}

	public void setIndexesOfPages(ArrayList<Integer> indexesOfPages) {
		this.indexesOfPages = indexesOfPages;
	}

	public int getId() {
		return id;
	}

	public Date getDateCreer() {
		return dateCreer;
	}
	
	public void setState(final int state) {
		this.state = state;
	}
	public final int getState() {
		return this.state;
	}
	public String toString() {
		return ""+this.id;
	}

	public Process getProcess() {
		return process;
	}

	public synchronized int getCurrentInstruction() {
		return currentInstruction;
	}

	public synchronized void setCurrentInstruction(int currentInstruction) {
		this.currentInstruction = currentInstruction;
	}
	
	
	@Override
	public int compareTo(PCB pcb2) {
		if(this.getPriority()!= pcb2.getPriority()) {
			return pcb2.getPriority()-this.getPriority();
		}else
			return pcb2.getDateCreer().compareTo(getDateCreer());
		// TODO Auto-generated method stub
	}

}
