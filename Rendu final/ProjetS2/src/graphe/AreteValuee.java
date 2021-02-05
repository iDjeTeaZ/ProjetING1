package graphe;
import java.io.Serializable;

public class AreteValuee implements Comparable<AreteValuee>,Serializable {
	
	private static final long serialVersionUID = 293536648499394933L;
	
	private Sommet debut; //Sommet de départ de l'arete
	private Sommet fin; //Sommet d'arrivé de l'arete
	private double valeur; //Poids de l'arete
	
	//Constructeur de l'objet
	public AreteValuee(Sommet s1, Sommet s2, double pValeur) {
		debut = s1;
		fin = s2;
		valeur = pValeur;
	}
	
	//Getters
	public Sommet getDebut() {return debut;}
	public Sommet getFin() {return fin;}
	public double getValeur() {return valeur;}

	public int compareTo(AreteValuee autreArete) {
		return ((Double)valeur).compareTo(autreArete.getValeur());
	}
	
	public String toString() {
		return ("{"+debut.nom+","+fin.nom+","+valeur+"}");
	}
}
