package graphe;
import java.io.Serializable;

public class Sommet implements Serializable {
	
	private static final long serialVersionUID = -6134204954677795173L;
	
	public String nom; //Nom du sommet
	public int x; //Coordonnée en abscisse du sommet
	public int y; //Coordonnée en ordonnée du sommet
	public int f; //Etage du sommet

	//Constructeur de l'objet
	public Sommet(String nom, int x, int y, int f) {
		this.nom = nom;
		this.x = x;
		this.y = y;
		this.f = f;
	}
	
	public String toString() {
		return ("{"+nom+","+x+","+y+"}");
	}
}