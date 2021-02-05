import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import graphe.AreteValuee;
import graphe.GrapheValue;
import graphe.Sommet;

public class InitBDD {

	public static void main(String[] args) {
		//Création des salles
		ArrayList<Salle> ls = new ArrayList<Salle>();
		//Rez-de-chaussée
		ls.add(new Salle("Ad-Sys",180,20,0,0));
        ls.add(new Salle("A001-0",170,230,0,0));
        ls.add(new Salle("Asc2",20,420,4,0));
        ls.add(new Salle("B0",160,400,0,0));
        ls.add(new Salle("Assos",20,520,0,0));
        ls.add(new Salle("Foy3",20,640,0,0));
        ls.add(new Salle("WC2",100,620,0,0));
        ls.add(new Salle("Foy2",170,660,0,0));
        ls.add(new Salle("Foy1",450,680,0,0));
        ls.add(new Salle("Parking",600,700,0,0));
        ls.add(new Salle("Entrée",350,100,0,0));
        ls.add(new Salle("Accueil",670,100,0,0));
        ls.add(new Salle("WC1",730,90,0,0));
        ls.add(new Salle("Asc1",700,20,4,0));
        ls.add(new Salle("E002",670,170,0,0));
        ls.add(new Salle("E003",670,250,0,0));
        ls.add(new Salle("E004",670,320,0,0));
        ls.add(new Salle("E005",670,390,0,0));
        ls.add(new Salle("E006",670,460,0,0));
        ls.add(new Salle("E007",670,550,0,0));
		//1er étage
        ls.add(new Salle("Asc1",700,20,4,1));
        ls.add(new Salle("Asc2",20,420,4,1));
        ls.add(new Salle("A001-1",180,20,0,1));
        ls.add(new Salle("B1",160,400,0,1));
        ls.add(new Salle("WC-1",90,360,0,1));
        ls.add(new Salle("E110",160,500,0,1));
        ls.add(new Salle("E109",160,600,0,1));
        ls.add(new Salle("E108",200,700,0,1));
        ls.add(new Salle("E102",670,430,0,1));
        ls.add(new Salle("E101",670,330,0,1));
        ls.add(new Salle("E103",670,510,0,1));
        ls.add(new Salle("E104",670,600,0,1));
        ls.add(new Salle("E105",670,700,0,1));
        ls.add(new Salle("E106",550,700,0,1));
        ls.add(new Salle("E107",450,700,0,1));
		//2ème étage
        ls.add(new Salle("Asc1",700,20,4,2));
        ls.add(new Salle("E201",630,20,0,2));
        ls.add(new Salle("E202",670,90,0,2));
        ls.add(new Salle("E203",670,155,0,2));
        ls.add(new Salle("E204",670,220,0,2));
        ls.add(new Salle("E205",670,285,0,2));
        ls.add(new Salle("E206",670,355,0,2));
        ls.add(new Salle("E207",670,420,0,2));
        ls.add(new Salle("E208",670,485,0,2));
        ls.add(new Salle("E209",670,560,0,2));
        ls.add(new Salle("E210",670,625,0,2));
        ls.add(new Salle("E211",620,710,0,2));
        ls.add(new Salle("E212",420,710,0,2));
        ls.add(new Salle("E213",340,710,0,2));
        ls.add(new Salle("E214",160,710,0,2));
        ls.add(new Salle("E215",160,600,0,2));
        ls.add(new Salle("E216",160,530,0,2));
        ls.add(new Salle("B2",160,400,0,2));
        ls.add(new Salle("WC-2",90,360,0,2));
        ls.add(new Salle("Asc2",20,420,4,2));
        ls.add(new Salle("E217",20,480,0,2));
        ls.add(new Salle("E218",160,270,0,2));
        ls.add(new Salle("Admin",180,20,0,2));
		
        
		//Création des aretes
		ArrayList<Arete> la = new ArrayList<Arete>();
		//Rez-de-chaussée
		la.add(new Arete(180,20,170,230,0)); //Ad-Sys/A001-0
        la.add(new Arete(180,20,350,100,0)); //Ad-Sys/Entrée
        la.add(new Arete(170,230,350,100,0)); //A001-0/Entrée
        la.add(new Arete(350,100,670,100,0)); //Entrée/Accueil
        la.add(new Arete(670,100,700,20,0)); //Accueil/Asc1
        la.add(new Arete(670,100,730,90,0)); //Accueil/WC1
        la.add(new Arete(670,100,670,170,0)); //Accueil/E002
        la.add(new Arete(670,170,670,250,0)); //E002/E003
        la.add(new Arete(670,250,670,320,0)); //E003/E004
        la.add(new Arete(670,320,670,390,0)); //E004/E005
        la.add(new Arete(670,390,670,460,0)); //E005/E006
        la.add(new Arete(670,460,670,550,0)); //E006/E007
        la.add(new Arete(670,550,600,700,0)); //E007/Parking
        la.add(new Arete(600,700,450,680,0)); //Parking/Foy1
        la.add(new Arete(450,680,170,660,0)); //Foy1/Foy2
        la.add(new Arete(170,660,100,620,0)); //Foy2/WC2
        la.add(new Arete(100,620,20,640,0)); //WC2/Foy3
        la.add(new Arete(20,640,20,520,0)); //Foy3/Assos
        la.add(new Arete(100,620,20,520,0)); //WC2/Assos
        la.add(new Arete(20,520,20,420,0)); //Assos/Asc2
        la.add(new Arete(20,420,160,400,0)); //Asc2/BO
        la.add(new Arete(170,230,160,400,0)); //AOO1-0/BO
        la.add(new Arete(170,660,160,400,0)); //Foy2/BO
		//1er étage
		la.add(new Arete(180,20,700,20,1)); //A001/Asc1
        la.add(new Arete(700,20,670,330,1)); //Asc1/E101
        la.add(new Arete(670,330,670,430,1)); //E101/E102
        la.add(new Arete(670,430,670,510,1)); //E121/E103
        la.add(new Arete(670,510,670,600,1)); //E103/E104
        la.add(new Arete(670,600,670,700,1)); //E104/E105
        la.add(new Arete(670,700,550,700,1)); //E105/E106
        la.add(new Arete(550,700,450,700,1)); //E106/E107
        la.add(new Arete(450,700,200,700,1)); //E107/E108
        la.add(new Arete(200,700,160,600,1)); //E108/E109
        la.add(new Arete(160,600,160,500,1)); //E109/E110
        la.add(new Arete(160,500,160,400,1)); //E110/B1
        la.add(new Arete(160,400,180,20,1)); //B1/A001
        la.add(new Arete(160,400,670,430,1)); //B1/E102
        la.add(new Arete(160,400,90,360,1)); //B1/WC
        la.add(new Arete(90,360,20,420,1)); //WC/Asc2
		//2ème étage
        la.add(new Arete(180,20,630,20,2)); //Admin/E201
        la.add(new Arete(630,20,700,20,2)); //E201/Asc1
        la.add(new Arete(630,20,670,90,2)); //E201/E202
        la.add(new Arete(670,90,670,155,2)); //E202/E203
        la.add(new Arete(670,155,670,220,2)); //E203/E204
        la.add(new Arete(670,220,670,285,2)); //E204/E205
        la.add(new Arete(670,285,670,355,2)); //E205/E206
        la.add(new Arete(670,355,670,420,2)); //E206/E207
        la.add(new Arete(670,420,670,485,2)); //E207/E208
        la.add(new Arete(670,485,670,560,2)); //E208/E209
        la.add(new Arete(670,560,670,625,2)); //E209/E210
        la.add(new Arete(670,625,620,710,2)); //E210/E211
        la.add(new Arete(620,710,420,710,2)); //E211/E212
        la.add(new Arete(420,710,340,710,2)); //E212/E213
        la.add(new Arete(340,710,160,710,2)); //E213/E214
        la.add(new Arete(160,710,160,600,2)); //E214/E215
        la.add(new Arete(160,600,160,530,2)); //E215/E216
        la.add(new Arete(160,530,160,400,2)); //E216/B2
        la.add(new Arete(160,400,90,360,2)); //B2/WC-2
        la.add(new Arete(90,360,20,420,2)); //WC-2/Asc2
        la.add(new Arete(20,420,20,480,2)); //Asc2/E217
        la.add(new Arete(160,400,160,270,2)); //B2/E218
        la.add(new Arete(160,270,180,20,2)); //E218/Admin
        la.add(new Arete(160,270,670,220,2)); //E218/E204
        
        
        //Création du graphe
        GrapheValue g = new GrapheValue();
        for (Salle s : ls) {
        	if(! s.nom.equals("Asc1") && ! s.nom.equals("Asc2")) {
        		g.ajouterSommet(new Sommet(s.nom,s.x,s.y,s.floor));
        	}
		}
        g.ajouterSommet(new Sommet("Asc1",700,20,-1));
        g.ajouterSommet(new Sommet("Asc2",20,420,-1));
        
        for (Arete a : la) {
			Sommet s1 = null;
			Sommet s2 = null;
			for (Sommet s : g.ensSommet()) {
				if(a.x1==s.x && a.y1==s.y && (a.floor==s.f || s.f==-1)) {
					s1 = s;
				}
				if(a.x2==s.x && a.y2==s.y && (a.floor==s.f || s.f==-1)) {
					s2 = s;
				}
			}
			if(s1!=null && s2!=null) {
				double p = 0;
				if(s1.nom.equals("Asc1")||(s1.nom.equals("Asc2"))||(s2.nom.equals("Asc1"))||(s2.nom.equals("Asc2"))) {
					p = 10000;
				} else {
					p = Math.sqrt(Math.pow(a.x1-a.x2, 2)+Math.pow(a.y1-a.y2, 2));
				}
				g.ajouterArete(new AreteValuee(s1,s2,p));
			} else {
				System.out.println("oof");
			}
		}
        
        //Sérialisation
        //Ouverture des fichiers
		File f1 =  new File("listeSalle.ser") ;
		File f2 =  new File("listeArete.ser") ;
		File f3 =  new File("grapheValue.ser") ;
		
		//Ouverture des flux sur les fichiers
		ObjectOutputStream oos1 = null;
		ObjectOutputStream oos2 = null;
		ObjectOutputStream oos3 = null;
		try {
			oos1 = new ObjectOutputStream(new FileOutputStream(f1));
			oos2 = new ObjectOutputStream(new FileOutputStream(f2));
			oos3 = new ObjectOutputStream(new FileOutputStream(f3));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		//Sérialization des objets
		try {
			oos1.writeObject(ls) ;
			oos2.writeObject(la) ;
			oos3.writeObject(g) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
}
