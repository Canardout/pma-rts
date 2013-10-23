package jeu;
/**Classe Villageois définis l'unité "Villageoi" et son comportement lors de son activation 
 * 
 * @author fayej
 *
 */
public class Villageois extends Unité {
	
		public Villageois()  {
		// Constructeur du villageois
		}

	
	protected void activate(){
		// Définir le rôle de l'objet "Villeagois" dans la société
		//requestRole(Société.COMMUNAUTE , Société.GROUPE, Société.ROLE);
	}
	
	
	/*protected void ChercheBois(){
		if(logger != null){
			Villagois.getEnv().getPerception(); // le villageois regarde son environnement
			Si le villageois trouve une ressource dans la liste de cellule qu'on lui envoie:
			Alors , il fixe son objectif sur le bois et va le couper aucours des temps suivants
			tout en vérifiant à chaque fois son environnement pour voir s'il n'y a pas un agent de 
			l'autre alignement.
			Sinon il avance , se déplace ou autre... (A voir celon les envies ! :D )
			
			
			 PS : A chaque fois que l'horloge redémarre , le Villageois doit regarder son Environnement , celà 
			permet de ne pas avoir de problème en cas de bois "terminé".
	*/
	
}
