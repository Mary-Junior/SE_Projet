package nonMemoire;


public class Central implements Runnable {
	public static Interruption IT[];
	public static SysCall sysCallTable[];
	private short addressInit=0;
	public static Scheduler scheduler=new Scheduler();
	public static Dispatcher dispatcher=new Dispatcher();
	public static PCB currentPCB;
	public static int counterTimer=0;
	private SharedInfo info;
	
	public Central(SharedInfo info) {
		super();
		this.info=info;
		initSysCallTable();
		initIT();
	}
	
	
	public Interruption[] getIT() {
		return IT;
	}
	
	public SysCall[] getSysCallTable() {
		return sysCallTable;
	}
	
	public void initIT() {
		IT= new Interruption[256];
	for (int i=0; i<256; i++){
		IT[i]=new Interruption("Unknown Interruption", addressInit, (byte) 0);
	}
	
	//Exception initialization
			addressInit+=8;
			IT[0]= new Interruption("Divide error", addressInit, (byte) 3);
			addressInit+=8;
			IT[1]= new Interruption("Debug", addressInit, (byte) 0);
			addressInit+=8;
			IT[2]= new Interruption("NMI", addressInit, (byte) 0);
			addressInit+=8;
			IT[3]= new Interruption("Breakpoint", addressInit, (byte) 0);
			addressInit+=8;
			IT[4]= new Interruption("Overflow", addressInit, (byte) 0);
			addressInit+=8;
			IT[5]= new Interruption("Bounds check", addressInit, (byte) 0);
			addressInit+=8;
			IT[6]= new Interruption("Invalid opcode", addressInit, (byte) 0);
			addressInit+=8;
			IT[7]= new Interruption("Device not available", addressInit, (byte) 0);
			addressInit+=8;
			IT[8]= new Interruption("Double falut", addressInit, (byte) 0);
			addressInit+=8;
			IT[9]= new Interruption("Coprocessor segment overrun", addressInit, (byte) 0);
			addressInit+=8;
			IT[10]= new Interruption("Invalid TSS", addressInit, (byte) 0);
			addressInit+=8;
			IT[11]= new Interruption("Segment not present", addressInit, (byte) 0);
			addressInit+=8;
			IT[12]= new Interruption("Stack exception", addressInit, (byte) 0);
			addressInit+=8;
			IT[13]= new Interruption("General protection", addressInit, (byte) 0);
			addressInit+=8;
			IT[14]= new Interruption("Page fault", addressInit, (byte) 0);
			addressInit+=8;
			IT[16]= new Interruption("Floating point error", addressInit, (byte) 0);
			addressInit+=8;
			IT[17]= new Interruption("Alignement check", addressInit, (byte) 0);
			
			addressInit+=8;
			IT[128]= new Interruption("System call", addressInit, (byte) 0);
			
			//Interruption initialisation
			addressInit+=8;
			IT[32]= new Interruption("Timer", addressInit, (byte) 0);
			addressInit+=8;
			IT[33]= new Interruption("Keyboard", addressInit, (byte) 0);
			addressInit+=8;
			IT[34]= new Interruption("PIC cascading", addressInit, (byte) 0);
			addressInit+=8;
			IT[35]= new Interruption("Second serial port", addressInit, (byte) 0);
			addressInit+=8;
			IT[36]= new Interruption("First serial port", addressInit, (byte) 0);
			addressInit+=8;
			IT[38]= new Interruption("Floppy disk", addressInit, (byte) 0);
			addressInit+=8;
			IT[40]= new Interruption("System clock", addressInit, (byte) 0);
			addressInit+=8;
			IT[43]= new Interruption("Network interface", addressInit, (byte) 0);
			addressInit+=8;
			IT[44]= new Interruption("PS/2 mouse", addressInit, (byte) 0);
			addressInit+=8;
			IT[45]= new Interruption("Mathematical coprocessor", addressInit, (byte) 0);
			addressInit+=8;
			IT[46]= new Interruption("EIDE disk controller's first chain", addressInit, (byte) 0);
			addressInit+=8;
			IT[47]= new Interruption("EIDE disk controller's second chain", addressInit, (byte) 0);
	}
	
	public void initSysCallTable() {
		sysCallTable=new SysCall[256];
		addressInit=+8;
		for(int i=0; i<256;i++) {
			sysCallTable[i]=new SysCall("Unknown SysCall",addressInit);
		}
		addressInit+=4;
		sysCallTable[0]= new SysCall("restart syscall", addressInit);
		addressInit+=4;
		sysCallTable[1]= new SysCall("exit", addressInit);
		addressInit+=4;
		sysCallTable[2]= new SysCall("fork", addressInit);
		addressInit+=4;
		sysCallTable[3]= new SysCall("read", addressInit);
		addressInit+=4;
		sysCallTable[4]= new SysCall("write", addressInit);
		addressInit+=4;
		sysCallTable[5]= new SysCall("open", addressInit);
		addressInit+=4;
		sysCallTable[6]= new SysCall("close", addressInit);
		addressInit+=4;
		sysCallTable[7]= new SysCall("waitpid", addressInit);
		addressInit+=4;
		sysCallTable[8]= new SysCall("create", addressInit);
		addressInit+=4;
		sysCallTable[9]= new SysCall("link", addressInit);
		addressInit+=4;
		sysCallTable[10]= new SysCall("unlink", addressInit);
		addressInit+=4;
		sysCallTable[11]= new SysCall("execve", addressInit);
		addressInit+=4;
		sysCallTable[12]= new SysCall("chdir", addressInit);
		addressInit+=4;
		sysCallTable[13]= new SysCall("time", addressInit);
		addressInit+=4;
		sysCallTable[14]= new SysCall("mknod", addressInit);
		addressInit+=4;
		sysCallTable[16]= new SysCall("chmod", addressInit);
		addressInit+=4;
		sysCallTable[17]= new SysCall("lchown", addressInit);
		addressInit+=4;
		sysCallTable[18]= new SysCall("stat", addressInit);
		addressInit+=4;
		sysCallTable[19]= new SysCall("lseek", addressInit);
		addressInit+=4;
		sysCallTable[20]= new SysCall("getpid", addressInit);
		addressInit+=4;
		sysCallTable[21]= new SysCall("mount", addressInit);
		addressInit+=4;
		sysCallTable[22]= new SysCall("unmount", addressInit);
		addressInit+=4;
		sysCallTable[23]= new SysCall("setuid", addressInit);
		addressInit+=4;
		sysCallTable[24]= new SysCall("getuid", addressInit);
		addressInit+=4;
		sysCallTable[25]= new SysCall("stime", addressInit);
		
	}
	
	@Override
	public void run() {}
}
