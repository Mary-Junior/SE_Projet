package nonMemoire;


public class Dispatcher {
	
	public synchronized void contextSwitch() {
		try {
			PCB newPCB=Central.scheduler.pickPCBFromReadyQueue();
			if (Central.currentPCB!=newPCB) {
				Central.currentPCB=newPCB;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
