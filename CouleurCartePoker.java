public class CouleurCartePoker
/* classe modélisant les couleurs possibles (COEUR, CARREAU,PIQUE,TREFLE) d'une carte au Poker.
* le constructeur a besoin d'un entier compris entre 1 et 4 inclus.
* Il y a deux façons d'utiliser cette classe :
	- soit on utilise le constructeur avec en argument les constantes entières statiques COEUR,CARREAU,PIQUE et TREFLE.
	- soit on utilise directement les constantes de type CouleurCartePoker Trefle,Pique,Carreau,Coeur initialisé avec le constructeur et la constante entière correspondante.
Les methodes getMin,suivant et getNbCouleurs sont utiles pour parcourir itérativement toutes les couleurs.
*/
{

	 public static final int COEUR = 1;
	 public static final int CARREAU = 2;
	 public static final int PIQUE = 3;
	 public static final int TREFLE = 4;
	 public static final CouleurCartePoker Trefle = new CouleurCartePoker(TREFLE);
	 public static final CouleurCartePoker Pique = new CouleurCartePoker(PIQUE);
	 public static final CouleurCartePoker Carreau = new CouleurCartePoker(CARREAU);
	 public static final CouleurCartePoker Coeur = new CouleurCartePoker(COEUR);
	 int couleur;
	 
	 public CouleurCartePoker ()
	 {
		 this(COEUR);
	 }
	 public CouleurCartePoker(int couleur)
	 {
		 if (couleur <COEUR || couleur >TREFLE)
		 {
			 System.out.println("Erreur: couleur non définie");
			 System.exit(1);
		 }
		 else
			 this.couleur=couleur;
	 }
	 public String toString()
	 {
		 String resu="";
		 switch(couleur)
		 {
		 case COEUR:
			 resu="Coeur";
			 break;
		 case PIQUE:
			 resu="Pique";
			 break;
		 case CARREAU:
			 resu="Carreau";
			 break;
		 case TREFLE:
			 resu="Trefle";
			 break;
				 
		 }
		 return resu;
	 }
	 
	 public boolean equals(CouleurCartePoker c)
	 {
		 return (c.couleur == couleur);
	 }
	 public static CouleurCartePoker getMin()
	 {
		 return new CouleurCartePoker(COEUR);
	 }
	 public static int getNbCouleurs()
	 {
		 return 4;
	 }
	 public CouleurCartePoker suivant()
	 {
	  if (this.couleur < TREFLE) 
	   return new CouleurCartePoker(this.couleur+1);
	  else
           return Trefle;	
	 }
}
