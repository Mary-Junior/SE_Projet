package gesDisqueEtFichier;

import java.util.ArrayList;
import java.util.List;

public class GesDisque {
	private Disque disque ;
	private final static int sizeBlock = 32 ;
	private int Nb_Block ;
	private int[] TBlock ;
	private static Fichier[] Blocks ;
	private List<Fichier> ListFileInDisk = new ArrayList<Fichier>() ;
	public static Fichier Root = new Fichier(null, "root", true) ;;

	public GesDisque(int size) {
		super();
		if(size<1024) this.disque.setSizeDisque(1024);
		else this.disque.setSizeDisque(size);
		
		this.Nb_Block = this.disque.getSizeDisque()/sizeBlock ;
		this.TBlock = new int[this.Nb_Block] ;
		GesDisque.Blocks = new Fichier[this.Nb_Block] ;
		
		for(int i=0; i<Nb_Block; i++) TBlock[i] = -1 ;
		
		FichierSysteme OS = new FichierSysteme(Root, "OS", true, null, 1) ;
		addFileInBlock(OS) ;
	}
	
	public int getNb_Block() { return Nb_Block ;}
	
	public void addFileInBlock(Fichier fichier) {
		if(!verifFileInDisk(fichier)) {
			fichier.setListBlock(getIndexesToAllow(fichier.getSize()));
			for(int j : fichier.getListBlock()) {
				if(TBlock[j] == -1) {
					TBlock[j] = j ;
					Blocks[j] = fichier ;
					ListFileInDisk.add(fichier) ;
				}
			}
		}
	}
	
	public boolean verifFileInDisk(Fichier file) {
		boolean verif = false ;
		
		for(Fichier f : ListFileInDisk) {
			if(f.getName().equals(file.getName())) {
				verif = true ;
				break ;
			}
		}
		return verif ;
	}
	
	public static ArrayList<Integer> getIndexesToAllow(long sizeOfFichier_KB) {
		int nombreBlockToUse = (int) (sizeOfFichier_KB / sizeBlock);
		int n = 0;
		ArrayList<Integer> indexes = new ArrayList<>();
		// empty pages ?
		for (Fichier fic : Blocks) {
			if (fic == null) {
				indexes.add(n);
				if (indexes.size() == nombreBlockToUse) {
					break;
				}
			}
			n++;
		}
		return indexes;
	}
}
