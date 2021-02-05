package graphe;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class GrapheValue implements Serializable {
	
	private static final long serialVersionUID = 9004659880352057365L;
	
	private Set<Sommet> sommets; //Ensemble de Sommet
	private Set<AreteValuee> aretes; //Ensemble d'AreteValuee
	
	//Constructeur de l'objet
	public GrapheValue() {
		sommets = new HashSet<Sommet>();
		aretes = new HashSet<AreteValuee>();
	}
	
	//Ajoute une AreteValuee à l'ensemble aretes
	public boolean ajouterArete(AreteValuee arete) {
		if (sommets.contains(arete.getDebut()) && sommets.contains(arete.getFin())) {
			aretes.add(arete);
			return true;
		} else {
			return false;
		}
	}

	//Ajoute un Sommet à l'ensemble sommets
	public boolean ajouterSommet(Sommet sommet) {
		if (!this.sommets.contains(sommet)) {
			this.sommets.add(sommet);
			return true;
		} else {
			return false;
		}
	}
	
	//Getters
	public Set<Sommet> ensSommet() {
		return sommets;
	}
	public Set<AreteValuee> ensAretes() {
		return aretes;
	}
	public Set<AreteValuee> ensAretes(Sommet sommet) {
		return aretes.stream().filter(arete -> arete.getDebut() == sommet || arete.getFin() == sommet).collect(Collectors.toSet());
	}
	public Set<Sommet> ensSuivant(Sommet sommet) {
		return aretes.stream().filter(arete -> arete.getDebut() == sommet).map(arete -> arete.getFin()).collect(Collectors.toSet());
	}
	public Set<Sommet> ensPrecedent(Sommet sommet) {
		return aretes.stream().filter(arete -> arete.getFin() == sommet).map(arete -> arete.getDebut()).collect(Collectors.toSet());
	}
	
	//Renvoie le chemin le plus court entre deux Sommet via Dijkstra
	public LinkedList<Sommet> dijkstra(Sommet depart, Sommet fin) {
		//Initialisation des variables
		final Set<Sommet> marques=new HashSet<Sommet>();
		final Map<Sommet,Sommet> peres=new HashMap<Sommet,Sommet>();
		final Map<Sommet,Double> poids=new HashMap<Sommet,Double>();
		for (final Sommet sommet:sommets) {
			if (sommet == depart) {
				poids.put(sommet,0.0);
			} else {
				poids.put(sommet,Double.MAX_VALUE);
			}
		}
		int nbSommets = sommets.size();
		marques.add(depart);
		marquesSuivants(depart,peres,poids);
		boolean end = false;
		
		do {
			Optional<Sommet> sommetOPT = plusPetitSommetNonMarque(marques,poids);
			try {
				Sommet sommet = sommetOPT.get();
				marquesSuivants(sommet,peres,poids);
				marques.add(sommet);
			} catch (Exception e) {
				end = true;
			}
		} while (marques.size()<nbSommets && !end);
		LinkedList<Sommet> chemin = new LinkedList<Sommet>();
		if (peres.containsKey(fin)) {
			Sommet sommet = fin;
			int i = 0;
			while (sommet != depart && i<1000) {
				chemin.addFirst(sommet);
				sommet = peres.get(sommet);
				i++;
			}
			chemin.addFirst(depart);
			return chemin;
		} else {
			System.out.println("pas de chemin");
			return new LinkedList<Sommet>();
		}
	}
	private void marquesSuivants(Sommet sommet,Map<Sommet,Sommet> peres,Map<Sommet,Double> poids) {
		double ps = poids.get(sommet);
		aretes.stream().filter(v -> (v.getDebut() == sommet && ps+v.getValeur()<poids.get(v.getFin())) || (v.getFin() == sommet && ps+v.getValeur()<poids.get(v.getDebut()))).forEach(v -> {
			if (v.getDebut() == sommet) {
				poids.put(v.getFin(), ps+v.getValeur());
				peres.put(v.getFin(), sommet);
			} else {
				poids.put(v.getDebut(), ps+v.getValeur());
				peres.put(v.getDebut(), sommet);
			}}
		);
	}
	private Optional<Sommet> plusPetitSommetNonMarque(final Set<Sommet> marques, final Map<Sommet,Double> poids) {
		//System.out.println(aretes.stream().filter(e -> (marques.contains(e.getDebut()) || marques.contains(e.getFin())) && (!marques.contains(e.getDebut()) || !marques.contains(e.getFin()))).collect(Collectors.toList()));
		return aretes.stream().filter(e -> (marques.contains(e.getDebut()) || marques.contains(e.getFin())) && (!marques.contains(e.getDebut()) || !marques.contains(e.getFin()))).map(e -> {
			if (marques.contains(e.getDebut())) {
				return e.getFin();
			} else {
				return e.getDebut();
			}}).min((v1, v2) -> (int)(poids.get(v1)-poids.get(v2)));
	}
}