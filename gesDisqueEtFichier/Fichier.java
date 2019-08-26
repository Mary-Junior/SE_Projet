package gesDisqueEtFichier;

import java.util.ArrayList;

public class Fichier {
	
		private Fichier fichierParent;
		private final String path;
		protected String name;
		protected boolean directory = false;//le programme est vide 
		private ArrayList<Fichier> listFichier = new ArrayList<>();
		protected ArrayList<Integer> listBlock = new ArrayList<>();
		
		
		public Fichier(Fichier parent, String name, boolean directory) {
			this.fichierParent = parent;
			this.name = name;
			this.directory = directory;
			if(parent !=null)
				path = parent.getPath()+"/"+this.name;
			else
				path=this.name;
		}
		
		public boolean isDirectory() {
			return this.directory;
		}
		public String getName() {
			return name;
		}

		public ArrayList<Integer> getListBlock() {
			return listBlock;
		}
		public void setListBlock(ArrayList<Integer> listBlock) {
			this.listBlock = listBlock;
		}
		public String getPath() {
			return path;
		}
		public Fichier getFichierParent() {
			return fichierParent;
		}
		public String toString() {
			return this.name;
		}
		
		public void setListFichier(Fichier fichier) {
			listFichier.add(fichier) ;
		}
		
		public int getSize() {
			int size=0;
			for(Fichier fichier: listFichier) 
				size+=fichier.getSize();
			return size;
		}
		

	}

