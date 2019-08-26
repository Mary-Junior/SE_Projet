package gesMemoire;

import java.util.ArrayList;
import java.util.List;

import nonMemoire.PCB;
import nonMemoire.Proces;

public class GesRAM {

	private static RAM ram;
	private List<Proces> process = new ArrayList<Proces>();
	private List<PagesTable> pagesTable = new ArrayList<PagesTable>();
	private List<PCB> PCBs = new ArrayList<PCB>();
	private int Frame[];
	private int framesDispo;
	int l = 0 ;
	
	public GesRAM(int Size) {
		super();
		this.ram.setSizeRAM(Size) ;
		this.Frame = new int[ram.NbreDeFrames()] ;
		framesDispo = Frame.length ;
		for(int i=0 ; i<Frame.length;i++ )
			Frame[i]=-1;
	}
	
	public void addProcess(Proces p) {
		int i = 0, pages=p.getSize_KB()/16 ;
		while(Frame[i] != -1 && (i <Frame.length)) {
			framesDispo -=i ;
			i++;
		}
		if(i < Frame.length && framesDispo >= pages) {
			PCB pcb = new PCB(p, ++i);
			ArrayList<Integer> IofPage = new ArrayList<>() ;
			PagesTable pageTable = new PagesTable(pages);
			
			for(int k=0; k<Frame.length; k++) {
				if(Frame[k] == -1 && IofPage.size()<pages) {
					Frame[k]=k ;
					pageTable.addElem(i, Frame[k]);
					IofPage.add(k) ;
				}
			}
			
			pcb.setIndexesOfPages(IofPage) ;
			pcb.setState(3);
			PCBs.add(pcb) ;
			framesDispo -= pages ;
			process.add(p) ;
			pagesTable.add(pageTable) ;
		}
		
		else { 
			enleverProcess();
			addProcess(p);
		}
	}
	
	public void enleverProcess() {
		
		if(!PCBs.isEmpty()) {
			//Proces po = new Proces(PCBs.get(l).getProcess());
			PCB pcb =  PCBs.get(l);
			pcb.setState(2);
			int i = 0;
			while(pcb.getIndexesOfPages().get(i) == Frame[i] && i< Frame.length) {
				Frame[i] = -1 ;
				framesDispo += 1 ;
				i++ ;
				
				if(i < Frame.length)
					break ;
			}
			
			if(i>=Frame.length) {
				PCBs.remove(l) ;
				process.remove(l) ;
				pagesTable.remove(l) ;
				l++ ;
			}
		}
		
	}
	
}
