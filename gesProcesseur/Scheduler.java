package gesProcesseur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

import nonMemoire.IORequest;
import nonMemoire.PCB;



public class Scheduler {
	public static PriorityQueue<PCB> readyQueue = new PriorityQueue<PCB>();
	
	public static LinkedList<PCB> processQueue = new LinkedList<>();
	
	
	public static PriorityQueue<IORequest> ioQueue = new PriorityQueue<IORequest>();
	
	
	public synchronized void addPCBToReadyQueue(){
		int i=0 ;
		LinkedList<PCB> Queue = new LinkedList<>();
		ArrayList<Integer> listPriorite = new ArrayList<Integer>() ;
		Queue = processQueue ;
		while(!Queue.isEmpty()) {
			listPriorite.add(Queue.get(i).getPriority()) ;
			Queue.remove(i) ;
			i++ ;
		}
		i=0 ;
		int maxPriorite = Collections.max(listPriorite);
		while(maxPriorite != processQueue.get(i).getPriority())
			i++ ;
		readyQueue.add(processQueue.get(i));
		removePCBFromProcessQueue(processQueue.get(i)) ;
		
		for(int j = 0; j < processQueue.size(); j++)
			processQueue.get(j).setPriority(processQueue.get(j).getPriority()+1);
		addPCBToReadyQueue() ;
	}
	
	public synchronized void removePCBFromProcessQueue(PCB pcb){
		ArrayList<PCB> list = new ArrayList<>();
		processQueue.forEach(data->{
			if (data.getId() != pcb.getId()){
				list.add(data);
			}
		});
		processQueue = new LinkedList<>();
		processQueue.addAll(list);
	}
	
	public synchronized void addRequestToIOQueue(IORequest ioRequest){
		ioQueue.add(ioRequest);
	}
	
	public synchronized PCB pickPCBFromReadyQueue() {
		//System.out.println("Scheduler -> Scheduling executed!");
		//GraphOSS.Log("Scheduler -> Scheduling executed!");
		return readyQueue.poll();
	}
	
	public synchronized IORequest pickRequestFromIOQueue() {
		return ioQueue.poll();
	}
}
