import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Carte extends JPanel implements MouseListener{
	private Canvas can; //Canvas ds lequal on dessine la carte
	private ArrayList<Salle> ls; //Liste des salles
	private ArrayList<Arete> la; //Liste des arete
	private ArrayList<String> chemin; //Liste des salles sur le chemin choisi
	private int floor; //Etage actuel
	private int nfloor; //Etage destination
	
	private static final long serialVersionUID = 1L;

	//Constructeur de l'objet
	public Carte(ArrayList<Salle> pls, ArrayList<Arete> pla) {
		//Initialisation des paramètres de la Carte
		ls = pls;
		la = pla;
		chemin = null;
		floor=0;
		nfloor=-1;
		
		//Initialisation du Canvas
		can = new Canvas() {
			private static final long serialVersionUID = 1L;
			public void paint(Graphics g)
			{
			}
		};
		
		//Ajout d'un listener
		can.addMouseListener(this);
		
		//Ajout du Canvas à la Carte(extension d'un JPanel)
		add(can);
		setLayout(new GridLayout(1,1));
	}

	//Listener de click souris
	public void mouseClicked(MouseEvent e) {
		//Récupère les coordonnées du click
		int x, y;
        x = e.getX();
        y = e.getY();

        //Parcourt les salles pour voir si l'utilisateur à cliqué sur l'une d'elles et agit en conséquence
        for (Salle salle : ls) {
			if(salle.x<x && salle.x+50>x && salle.y<y && salle.y+50>y && salle.floor==floor) {
				if(salle.status==2) {
					ArrayList<String> ln = new ArrayList<String>();
					for (Salle s : ls) {
						if(chemin.contains(s.nom) && chemin.indexOf(s.nom)<chemin.indexOf(salle.nom)) {
							ln.add(s.nom);
						}
					}
					resetStatus(ln);
					salle.status=1;
				}else if(salle.status==3) {
					resetStatus(chemin);
					salle.status=0;
				}else if(salle.status==5) {
					floor=nfloor;
					updateLigne();
				}
			}
		}
		updateSalle();
	}
	
	//Dessine une salle passée en paramètre sur le canvas
	public void updateSalle(String nom, int x, int y, Color c) {
		Graphics g = can.getGraphics();
		g.setColor(c);
        g.fillRect(x, y, 50, 50);
        g.setColor(Color.WHITE);
        g.drawString(nom, x+2, y+12);
	}
	
	//Update l'affichage de toutes les salles de l'étage actuel
	public void updateSalle() {
		for (Salle salle : ls) {
			if(salle.floor==floor) {
				if(salle.status==0) {
					updateSalle(salle.nom,salle.x,salle.y,Color.BLACK);
				} else if(salle.status==1) {
					updateSalle(salle.nom,salle.x,salle.y,Color.RED);
				} else if(salle.status==2) {
					updateSalle(salle.nom,salle.x,salle.y,Color.BLUE);
				} else if(salle.status==3) {
					updateSalle(salle.nom,salle.x,salle.y,Color.GREEN);
				} else if(salle.status==4) {
					updateSalle(salle.nom,salle.x,salle.y,Color.ORANGE);
				} else if(salle.status==5) {
					updateSalle(salle.nom,salle.x,salle.y,new Color(255,0,255));
				}
			}
		}
	}
	
	//Dessine une ligne sur le canvas
	public void updateLigne(int x1, int y1, int x2, int y2) {
		Graphics g = can.getGraphics();
		g.setColor(Color.BLACK);
		g.drawLine(x1, y1, x2, y2);
	}
	
	//Update l'affichage de toutes les aretes de l'étage actuel
	public void updateLigne() {
		Graphics g = can.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, can.getWidth(), can.getHeight());
		for (Arete a : la) {
			if(a.floor==floor) {
				updateLigne(a.x1+25, a.y1+25, a.x2+25, a.y2+25);
			}
		}
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("Etage "+floor, 10, 760);
	}
	
	//Change le status d'une salle dont le nom est passé en paramètre
	public void updateStatus(String uNom, int uStatus) {
		for (Salle salle : ls) {
			if(salle.nom==uNom) {
				salle.status = uStatus;
			}
		}
	}
	
	//Met à jour le status des salles du chemin déjà passée
	public void resetStatus(ArrayList<String> ln) {
		for (Salle s : ls) {
			if (ln.contains(s.nom) && s.status==5){
				s.status=4;
			}
			if(ln.contains(s.nom) && s.status!=4){
				s.status=0;
			}
		}
	}
	
	//Change le status des salles en fct du chemin passé en paramètre
	public void initialiseStatus(ArrayList<String> path) {
		for (Salle s : ls) {
			if (s.status==5) {
				s.status=4;
			}
			if (s.status!=4) {
				s.status=0;
			}
		}
		chemin = path;
		for (Salle s : ls) {
			if(chemin.contains(s.nom)){
				if(s.nom.equals(chemin.get(0))) {
					s.status=1;
					floor=s.floor;
				} else if(s.nom.equals(chemin.get(chemin.size()-1))) {
					s.status=3;
					nfloor=s.floor;
				} else if(s.status!=4){
					s.status=2;
				} else {
					s.status=5;
				}
			}
		}
		updateLigne();
		updateSalle();
	}

	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
