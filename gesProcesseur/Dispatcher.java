package gesProcesseur;


import java.util.LinkedList;

import gesMemoire.GesRAM;
import nonMemoire.Central;
import nonMemoire.IORequest;
import nonMemoire.PCB;

public class Dispatcher {

	private Processeur CPU = new Processeur(1) ; 
	private GesRAM ram = new GesRAM(2048) ;
	private Scheduler scheduler ;
	
	public synchronized void contextSwitch() {
		try {
			PCB newPCB=Central.scheduler.pickPCBFromReadyQueue();
			if (Central.currentPCB!=newPCB) {
				Central.currentPCB.setStateRegisters(CPU.stateResgisters());
				Central.scheduler.addPCBToProcessQueue(Central.currentPCB);
				Central.currentPCB=newPCB;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public synchronized void runningProcess() {
		scheduler.processQueue = (LinkedList<PCB>) ram.getPCBs() ;
		scheduler.addPCBToReadyQueue();
		PCB p = scheduler.pickPCBFromReadyQueue() ;
		p.setState(PCB.RUNNING);
		int etat = CPU.execute(p.getProcess().getInstructions());
		if(etat == PCB.READY) {
			scheduler.processQueue.add(p) ;
			contextSwitch() ;
			runningProcess() ;
		}
		
		else {
			if(etat == PCB.BLOCKED) {
				IORequest io = new IORequest(p, etat) ;
				scheduler.addRequestToIOQueue(io);
			}
			else if(etat == PCB.TERMINATED) {
				contextSwitch() ;
				runningProcess() ;
			}
		}
	}
	
	private IORequest IOok(int temps) {
		return scheduler.pickRequestFromIOQueue() ;
	}

}
