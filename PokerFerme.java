/** classe abstraite définissant le comportement général d'une partie de poker fermé.
* déroulement d'un coup :
* - demarrerCoup-> distribuerCartes -> miseDepart();
     -debutTourEnchere(); // début du tour des enchères.
     -debutEnchereJoueur // debut du tour du joueur avec comme combinaison sa main
     -finEnchereJoueur // fonction appellée poar les classes externes afin de continuer le déroulement
     -finTourEnchere // arret du tour des enchères
     -gestionMain //fonction qui gère le défaussage de cartes.
     -finChoixMainJoueur(int nbCartes) //fonction appellée par les classes externes, avec en agument, le nombre de cartes échangées.
     -debutTourEnchere(); // début du tour des enchères.
     -debutEnchereJoueur // debut du tour du joueur avec comme combinaison sa main
     -finEnchereJoueur // fonction appellée poar les classes externes afin de continuer le déroulement
     -finTourEnchere // arret du tour des enchères
      
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

 protected Combinaison creerCombinaison() // une combinaison est composée de la main du joueur.
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
 afficherMessage("Le joueur "+getJoueur().nom+" a changé "+nbCartes+" cartes."+'\n');
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
  afficherMessage("En attente du défaussage du joueur "+getJoueur().nom+":");
  getJoueur().choixCarte(this);
  
 }

}