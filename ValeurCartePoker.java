/* classe modélisant les valeurs des cartes possibles au Poker(du 2 au 10 puis valet, dame, roi et as).
* le constructeur a besoin d'un entier compris entre 2 et 14 inclus. L'as est représenté par la valeur entière 14 et est la plus grande valeur de carte possible. Pour construire les valeurs de cartes correspondants aux valet, dame, roi et as, il est possible d'utiliser les constantes entières correspondantes.
* Il y a deux façons d'utiliser cette classe :
	- soit on utilise le constructeur avec en argument les constantes entières statiques COEUR,CARREAU,PIQUE et TREFLE.
* Les valeurs de cartes ont un ordre total, cet classe implémente donc l'interface Comparable permettant de trier deux objets à l'aide de la méthode compareTo(ValeurCartePoker vcp).
Les methodes getMin(),suivant() et getNbValeurs() sont utiles pour parcourir itérativement toutes les Valeurs de cartes.
*/
public class ValeurCartePoker implements Comparable <ValeurCartePoker>{

	 public static final int VALET = 11;
	 public static final int DAME = 12;
	 public static final int ROI = 13;
	 public static final int AS = 14;
	 int valeur=AS;
	 public ValeurCartePoker(){}
	 
	 public ValeurCartePoker(int v)
	 {
		 valeur=v;
	 }
	 
	 public String toString()
	 {
		 String resu;
		 switch (valeur)
		 {
		 case ROI:
			 resu="Roi";
		break;
		 case DAME:
			 resu="Dame";
		break;
		 case VALET:
			 resu="Valet";
		break;
		 case AS:
			 resu="As";
		break;
		default:
			resu=(new Integer(valeur)).toString();
				 
		 }
		return resu;
	 }
	 
	 public boolean equals(ValeurCartePoker vcp)
	 {
		 return valeur == vcp.valeur;
	 }

	 public static ValeurCartePoker getMin()
	 {
		return new ValeurCartePoker(2); 
	 }

	public static int getNbValeurs()
	{
			return ROI;
	}

	public ValeurCartePoker suivant()
	{
		 if ( this.valeur < AS)
			 return new ValeurCartePoker(valeur+1);
		 else
			 return new ValeurCartePoker(AS);
	 }
	 public int compareTo(ValeurCartePoker vcp)
 /* renvoie un entier:
  - <0 si l'objet en argument est plus grand que l'objet comparé
  - =0 si les deux objets ont la même valeur
  - >0 si l'objet en argument est plus petit que l'objet comparé
*/
	 {
		 return this.valeur - vcp.valeur;
	 }
}
