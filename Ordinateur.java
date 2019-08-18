import java.util.*;
class Ordinateur extends Joueur
{
/* classe d�finissant le comportement d'un Joueur g�r� par l'Ordinateur. Elle h�rite de la classe Joueur.
L'ordinateur choisit al�atoirement l'une des options possibles:
 - pour la fonction jouer; il choisit principalement entre Suivre et surencherir, il a 1 chance sur 10 de se coucher.
 - pour la fonction choixCarte; il choisit de remplacer entre 0 � son nombre de cartes en main -1 de mani�re �quiprobable.
*/

public Ordinateur(int nbCartesMain)
{
 main= new Main(nbCartesMain);
}

public void choixCarte(PokerFerme pq)
{
 Random alea = new Random();
 int j;
 int nbCartes=alea.nextInt(main.getNbEnMain());
 for (j=0;j<nbCartes;j++)
 {
  main.remplacerCarte(j,pq.pioche.piocher());
 }
 pq.finChoixMainJoueur(nbCartes);
}

public void jouer(PartiePoker pf,Combinaison c)
{
 int choix;
 Random alea = new Random();
 if (jetons<pf.p.getMise() || jetons ==0)
  choix=PAROLE;
 else if ( getMiseTotale()-pf.p.getMise()==0)
  choix= (alea.nextInt(3)+1)%2+1;
 else
 {
  choix=alea.nextInt(10);
  if (choix == 9)
   choix=COUCHE;
  else
   choix=choix%2+1;
 }
 int somme = pf.p.getMise();
 if ( choix== ENCHERIR)
  somme=encherir(somme*2);
 choixPoker(choix,somme);
 pf.finEnchereJoueur();
}

public int encherir(int min)
{
 return Math.min(min,jetons);
}

}