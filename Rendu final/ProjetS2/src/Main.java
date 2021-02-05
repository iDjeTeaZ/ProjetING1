public class Main{
	public static void main(String[] args) {
		//Initialise la BDD
		InitBDD.main(null);
		//Initialise la fenetre de l'appli
		App appli = new App();
		//Lance l'appli
		appli.launch();
	}
}
