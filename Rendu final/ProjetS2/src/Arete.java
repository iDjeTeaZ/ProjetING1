import java.io.Serializable;

public class Arete implements Serializable{
	
	private static final long serialVersionUID = 2161346455528166620L;
	
	public int x1; //Abscisse de la salle 1
	public int y1; //Ordonnée de la salle 1
	public int x2; //Abscisse de la salle 2
	public int y2; //Ordonnée de la salle 2
	public int floor; //Etage de l'arete
	
	//Constructeur de l'objet
	public Arete(int pX1, int pY1, int pX2, int pY2, int pFloor) {
		x1=pX1;
		y1=pY1;
		x2=pX2;
		y2=pY2;
		floor=pFloor;
	}
	
	public String toString() {
		return ("{"+x1+","+y1+","+x2+","+y2+","+floor+"}");
	}
}
