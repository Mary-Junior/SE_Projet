package gesMemoire;

public class PagesTable {
	private int Table[][] ;
	private int pageNumber ;
	private int frameNumber ;
	
	public PagesTable(int pageNumber) {
		super();
		this.pageNumber = pageNumber;
		this.frameNumber = pageNumber;
		this.Table = new int[this.frameNumber][2] ;
	}

	public int getSizeTable() {
		return Table.length;
	}

	public void addElem(int i ,int elem) {
		if(i < Table.length) {
			Table[i][0] = ++i ;
			Table[i][0] = elem ;
		}
	}
	
}
