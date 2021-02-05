import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import graphe.GrapheValue;
import graphe.Sommet;

public class App extends JFrame implements ActionListener{
	private static final long serialVersionUID = -2092066060813945962L;
	private ArrayList<Salle> ls; //Liste des salles
	private ArrayList<Salle> lss; //Liste des salles sélectionnable
	private ArrayList<Arete> la; //Liste des aretes
	private GrapheValue g; //Graphe de la carte
	private Carte carte; //JPanel affichant la carte
	private JComboBox<String> etageDepart; //Liste déroulante 1
	private JComboBox<String> salleDepart; //Liste déroulante 2
	private JComboBox<String> etageArrivee; //Liste déroulante 3
	private JComboBox<String> salleArrivee; //Liste déroulante 4
	private int fd; //Etage combobox 1
	private int fa; //Etage combobox 2
	
	//Constructeur de l'objet
	public App() {
		super("ProjetS2");
		
		//Récupération des données ds la BDD
		ls = RecupBDD.recupListSalle();
		la = RecupBDD.recupListArete();
		g = RecupBDD.recupGraphe();
		
		//Initialisation de variables
		lss = (ArrayList<Salle>) ls.stream().filter(e -> verifValide(e.nom)).collect(Collectors.toList());
		fd = 0;
		fa = 0;
		List<String> ls0 = lss.stream().filter(e -> e.floor==0).map(e -> e.nom).sorted().collect(Collectors.toList());
		String[] etages = new String[]{"Rez de Chaussée","Etage 1", "Etage2"};
		String[] salles = new String[ls0.size()];
		ls0.toArray(salles);
		lss.stream().filter(e->e.floor==0).map(e -> e.nom).sorted().collect(Collectors.toList()).toArray(salles);
		
		//Crée la carte
		carte = new Carte(ls,la);
		JPanel carte1 = new JPanel();
		carte1.setBackground(Color.RED);
		
		//Initialisation de l'interface
		
		//Initialisation de la JFrame
		setSize(1050,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new GridBagLayout());
		
		//Initialisation des GridBagConstraints
		GridBagConstraints cs1 = new GridBagConstraints(0, 0, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,new Insets(0, 0, 0, 0) , 0, 0);
		GridBagConstraints cs2 = new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,new Insets(20, 50, 0, 50) , 20, 0);
		
		//JPanel de controle
		JPanel p1 = new JPanel();
		p1.setBackground(Color.LIGHT_GRAY);
		p1.setLayout(new GridBagLayout());
		
		//Boutons et listes déroulantes
		JButton bouton = new JButton("Valider");
		bouton.addActionListener(this);
		etageDepart = new JComboBox<String>(etages);
		etageDepart.addActionListener(this);
		salleDepart = new JComboBox<String>(salles);
		etageDepart.addActionListener(this);
		etageArrivee = new JComboBox<String>(etages);
		etageArrivee.addActionListener(this);
		salleArrivee = new JComboBox<String>(salles);
		salleArrivee.addActionListener(this);
		
		//Placement des objets ds le JPanel
		p1.add(new JLabel("Départ",JLabel.CENTER),cs2);
		cs2.gridy=2;
		p1.add(etageDepart,cs2);
		cs2.gridy=3;
		p1.add(salleDepart,cs2);
		cs2.gridy=4;
		p1.add(new JLabel("Arrivée",JLabel.CENTER),cs2);
		cs2.gridy=5;
		p1.add(etageArrivee,cs2);
		cs2.gridy=6;
		p1.add(salleArrivee,cs2);
		cs2.gridy=7;
		p1.add(bouton,cs2);
		cs2.gridy=8;
		p1.add(clabel("Départ",Color.RED),cs2);
		cs2.gridy=9;
		p1.add(clabel("Chemin",Color.BLUE),cs2);
		cs2.gridy=10;
		p1.add(clabel("Arrivée",Color.GREEN),cs2);
		cs2.gridy=11;
		p1.add(clabel("Ascenseur",Color.ORANGE),cs2);
		cs2.gridy=12;
		p1.add(clabel("Accès étage",new Color(255,0,255)),cs2);
		cs2.gridy=13;
		cs2.weighty=1;
		JPanel p2 = new JPanel();
		p2.setBackground(Color.LIGHT_GRAY);
		p1.add(p2,cs2);
		
		//Ajout du JPanel à la JFrame
		add(p1,cs1);
		
		//Ajout de la Carte à la JFrame
		cs1.gridx=1;
		cs1.weightx=1;
		add(carte,cs1);
	}
	
	//Renvoie le sommet du graphe de nom "nom"
	public static Sommet recupSommet(GrapheValue g,String nom) {
		return g.ensSommet().stream().filter(e -> e.nom.equals(nom)).findFirst().get();
	}
	
	//Vérifie si une salle peut être affichée ds les listes déroulantes
	public Boolean verifValide(String s) {
		return !(s.equals("Asc1") || s.equals("Asc2") || s.equals("B0") || s.equals("B1") || s.equals("B2"));
	}
	
	//Renvoie un label de texte s et de couleur c
	public JLabel clabel(String s,Color c) {
		JLabel cl = new JLabel(s,JLabel.CENTER);
		cl.setFont(new Font("TimesRoman",Font.PLAIN,20));
		cl.setForeground(c);
		return cl;
	}
	
	//Modifie dynamiquement l'interface lors de l'intéraction avec le JPanel de controle
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand()=="Valider") {
			//Lorsque l'utilisateur appuye sur le bouton valider
			Sommet s1 = recupSommet(g, salleDepart.getSelectedItem().toString());
			Sommet s2 = recupSommet(g, salleArrivee.getSelectedItem().toString());
			LinkedList<Sommet> chemin = g.dijkstra(s1, s2);
			carte.initialiseStatus((ArrayList<String>) chemin.stream().map(e -> e.nom).collect(Collectors.toList()));
		} else {
			if (etageDepart.getSelectedIndex()!=fd) {
				//Lorsque l'utilisateur modifie l'étage de départ
				fd = etageDepart.getSelectedIndex();
				List<String> lsd = lss.stream().filter(e -> e.floor==fd).map(e -> e.nom).sorted().collect(Collectors.toList());
				String[] sd = new String[lsd.size()];
				lsd.toArray(sd);
				salleDepart.setModel(new DefaultComboBoxModel<String>(sd));
			}
			if (etageArrivee.getSelectedIndex()!=fa) {
				//Lorsque l'utilisateur modifie l'étage d'arrivée
				fa = etageArrivee.getSelectedIndex();
				List<String> lsa = lss.stream().filter(e -> e.floor==fa).map(e -> e.nom).sorted().collect(Collectors.toList());
				String[] sa = new String[lsa.size()];
				lsa.toArray(sa);
				salleArrivee.setModel(new DefaultComboBoxModel<String>(sa));
			}
		}
	}
	
	//Affiche la JFrame et initialise la carte
	public void launch() {
		//Rends visible la fenêtre
		setVisible(true);
		
		//Attente de 0.1 s pour le bon fonctionnement de la suite
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Lance l'affichage de la carte
		carte.updateLigne();
		carte.updateSalle();
	}
}

