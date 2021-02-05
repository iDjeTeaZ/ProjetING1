import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import graphe.GrapheValue;

public class RecupBDD {
	static ArrayList<Salle> recupListSalle() {
		//Ouverture d'un fichier
		File fichier =  new File("listeSalle.ser") ;
		
		//Ouverture d'un flux sur le fichier
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(fichier));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//Désérialization de l'objet
		ArrayList<Salle> ls = null;
		try {
			Object obj = ois.readObject();
			ls = new ArrayList<Salle>();
			if (obj instanceof ArrayList<?>) {
				  ArrayList<?> al = (ArrayList<?>) obj;
				  if (al.size() > 0) {
				    for (int i = 0; i < al.size(); i++) {
				      Object o = al.get(i);
				      if (o instanceof Salle) {
				        Salle s = (Salle) o;
				        ls.add(s);
				      }
				    }
				  }
				}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ls;
	}
	
	static ArrayList<Arete> recupListArete() {
		//Ouverture d'un fichier
		File fichier =  new File("listeArete.ser") ;
		
		//Ouverture d'un flux sur le fichier
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(fichier));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//Désérialization de l'objet
		ArrayList<Arete> la = null;
		try {
			Object obj = ois.readObject();
			la = new ArrayList<Arete>();
			if (obj instanceof ArrayList<?>) {
				  ArrayList<?> al = (ArrayList<?>) obj;
				  if (al.size() > 0) {
				    for (int i = 0; i < al.size(); i++) {
				      Object o = al.get(i);
				      if (o instanceof Arete) {
				        Arete a = (Arete) o;
				        la.add(a);
				      }
				    }
				  }
				}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return la;
	}
	
	static GrapheValue recupGraphe() {
		//Ouverture d'un fichier
		File fichier =  new File("grapheValue.ser") ;
				
		//Ouverture d'un flux sur le fichier
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(fichier));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		GrapheValue g=null;
		try {
			g = (GrapheValue) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return g;
	}
}
