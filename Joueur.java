public abstract class Joueur {
/* classe abstraite définissant le comportement d'un Joueur et gérant ses mises et son statut pour une partie de Texas Holdem ou de PokerFerme.
Pour pouvoir être utiliser, les classes enfants doivent définir le comportement des foncions:
	- jouer : fonction où le joueur doit choisir une action entre surencherir, miser la somme minimale pour continuer à jouer, et abandonner le coup. lorsque le joueur a fini de jouer, il est nécessaire d'appeler la fonction finEnchereJoueur() de l'objet PartiePoker entré en argument.
	- encherir : fonction qui renvoie la surenchere du joeur
	- choixCarte : fonction gérant le choix et le remplacement des cartes du joueur. Cette fontion ne concerne que la classe PokerFerme. Elle doit se terminer par la fonction finChoixMainJoueur(nbCartes) de la classe PokerFerme.
*/
 Main main;
 
 protected int jetons=2000;
 int miseTotale=0;
 int miseCourante=0;
 boolean estCouche=false;
 boolean estTapis=false;
 public String nom="Anonyme";
 public static final int PAROLE=2;
 public static final int COUCHE=3;
 public static final int ENCHERIR=1;

 public abstract void jouer(PartiePoker pf, Combinaison c); // fonction qui définit un comportement durant la phase d'encheres. il est nécessaire d'appeler la fonction finEnchereJoueur() de l'objet PartiePoker entré en argument.
 public abstract int encherir(int min);
 public abstract void choixCarte(PokerFerme pf); // Elle doit se terminer par la fonction finChoixMainJoueur(nbCartes) de la classe PokerFerme.
 public void setJetons(int somme)
 {
  jetons=somme;
 }

 public void miser(int somme)
 {
  jetons -= somme;
  miseCourante = somme;
  miseTotale += somme;
 }
public boolean estTapis()
{
 return estTapis;
}

public void choixPoker(int choix,int valeurEnchere)
{
 if ( (valeurEnchere-getMiseTotale()) >= jetons)
 {
  estTapis=true;
  valeurEnchere=jetons+getMiseTotale();
 }
switch (choix)
 {
  case COUCHE:
   seCouche();
   break;
  default:
  miser(valeurEnchere-getMiseTotale()); // on mise la différence entre les encheres et ce que l'on a déjà misé.
   
 }
}

public String toString()
{
 return nom+"("+jetons+")";
}
public void gagner(int mis)
 {
  jetons += mis;
 }

 public int getMiseTotale()
 {
  return miseTotale;
 }

 public void setMiseTotale(int m)
 {
  miseTotale=m;
 }
 public int getMiseCourante()
 {
  return miseCourante;
 }

 public Main getMain()
 {
  return main;
 }

 public boolean estCouche()
 {
  return estCouche;
 }

 public void seCouche()
 {
  estCouche=true;
 }

 public void rejouer() //fonction qui réinitialise les donnée pour refair un coup
 {
  main.viderMain();
  miseTotale=0;
  miseCourante=0;
  estTapis=false;
  estCouche=false;
 }

 public int getJetons()//renvoie le nombre de jetons restant du Joueur
 {
  return jetons;
 }
}
