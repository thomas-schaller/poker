/** classe abstraite d�finissant le comportement g�n�ral d'une partie de poker ferm�.
* d�roulement d'un coup :
* - demarrerCoup-> distribuerCartes -> miseDepart();
     -debutTourEnchere(); // d�but du tour des ench�res.
     -debutEnchereJoueur // debut du tour du joueur avec comme combinaison sa main
     -finEnchereJoueur // fonction appell�e poar les classes externes afin de continuer le d�roulement
     -finTourEnchere // arret du tour des ench�res
     -gestionMain //fonction qui g�re le d�faussage de cartes.
     -finChoixMainJoueur(int nbCartes) //fonction appell�e par les classes externes, avec en agument, le nombre de cartes �chang�es.
     -debutTourEnchere(); // d�but du tour des ench�res.
     -debutEnchereJoueur // debut du tour du joueur avec comme combinaison sa main
     -finEnchereJoueur // fonction appell�e poar les classes externes afin de continuer le d�roulement
     -finTourEnchere // arret du tour des ench�res
      
     -gererResultat // si plusieurs joueurs sont dans la course, ils confrontent leur combinaison, sinon le dernier empoche la mise.
  - confronterCombinaison //fonction confrontant les combinaisons des joueurs valides
  - nouveauCoup
**/
public abstract class PokerFerme extends PartiePoker
{
 boolean mainChange =false;

public PokerFerme(int nbJoueur)
{
  super(nbJoueur);
  nbCartesJoueurs=5;

  for (int i=1;i<jo.length;i++)
  {
   jo[i]=new Ordinateur(nbCartesJoueurs);
   jo[i].nom="Ordi["+i+"]";
  }

}

 protected Combinaison creerCombinaison() // une combinaison est compos�e de la main du joueur.
 {
  return new Combinaison(getJoueur().getMain().toArray());
 }

 public void demarrerCoup()
 {
  mainChange =false;
  super.demarrerCoup();
 }

 protected void finTourEnchere()
 {
  if (mainChange || (nbTapis+ nbCouche)== (jo.length-1))
   gererResultat();
  else
   gestionMain();
 }

protected void gestionMain()
{
  mainChange=true;
  position=0;
  debutChoixMainJoueur();
}

protected void debutEnchereJoueur ()
{
 afficherMessage("Au Joueur "+getJoueur()+" de miser:");
  getJoueur().jouer(this, new Combinaison(getJoueur().getMain().toArray()));
}

public void finChoixMainJoueur(int nbCartes)
{
 afficherMessage("Le joueur "+getJoueur().nom+" a chang� "+nbCartes+" cartes."+'\n');
 do
 {
  joueurSuivant();
  position++;
 }
 while(getJoueur().estCouche());
 if (position<jo.length)
  debutChoixMainJoueur();
 else
  debutTourEnchere();
}

 protected void debutChoixMainJoueur()
 {
  afficherMessage("En attente du d�faussage du joueur "+getJoueur().nom+":");
  getJoueur().choixCarte(this);
  
 }

}