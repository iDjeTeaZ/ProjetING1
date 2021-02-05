import java.io.Serializable;

public class Salle implements Serializable{
	
	private static final long serialVersionUID = -8644169688307800342L;
	
	public String nom; //Nom de la salle
	public int x; //Coordonnee en abscisse dans le canvas
	public int y; //Coordonnee en ordonnee dans le canvas
	public int status; //0:salle std; 1:salle actuelle; 2:chemin; 3:objectif; 4:ascenseur
	public int floor; //Etage de la salle
	
	//Constructeur de l'objet
	public Salle(String pNom, int px, int py, int pStatus, int pFloor) {
		nom = pNom;
		x = px;
		y = py;
		status = pStatus;
		floor = pFloor;
	}
	
	public String toString() {
		return "{"+nom+","+x+","+y+","+status+","+floor+"}";
	}
}
