import java.util.*;

public abstract class PartiePoker
{
/** classe abstraite définissant le comportement général d'une partie de poker.
* déroulement d'un coup :
* - demarrerCoup-> distribuerCartes -> miseDepart();
     -debutTourEnchere(); // début du tour des enchères.
     -debutEnchereJoueur // debut du tour du joueur, définie dans les sous classes à cause de la combinaison à former
     -finEnchereJoueur // fonction appellée poar les classes externes afin de continuer le déroulement
     -finTourEnchere // arret du tour des enchères, déroulement détaillé dans les sous classes.
     -gererResultat // si plusieurs joueurs sont dans la couse, ils confrontent leur combinaison, sinon le dernier empoche la mise.
  - confronterCombinaison //fonction confrontant les combinaisons des joueurs valides
  - nouveauCoup
**/
 int position=0; //variable indiquant le nombre de joueur n'ayant pas surencherie depuis la derniere surenchere.
 int nbCouche=0; //nombre de joueurs couchés
 int nbTapis=0; // nombre de joueurs au tapis
 Joueur [] jo; // les joueurs
 Pot p;
 PaquetCarte pioche= new PaquetCarte();
 int sommeDepart=2000;
 int nbCartesJoueurs;
 int donneur=0; //variable indiquant la position du donneur
 int posJCourant=0; // variable indiquant la position du joueur courant
 
 protected PartiePoker(int nbJ)
 {
  jo= new Joueur[nbJ];
 }

 protected abstract void debutEnchereJoueur ();
 protected abstract void finTourEnchere();
 public abstract void afficherMessage(String message); // fonction servant à afficher les messages d'informations
 protected abstract Combinaison creerCombinaison();
 
protected void nouveauCoup()
{
 System.out.println("Voulez vous refaire un coup de poker ('o' pour recommencer) ?");
 String reponse=System.console().readLine();
 if (reponse.charAt(0) =='o')
  demarrerCoup();
}


 protected int confronterCombinaison()
{ //fonction qui compare les combinaisons entre les joueurs et partage les gains aux gagnants (renvoie le contenu du pot n'ayant pas été distribué )
 Combinaison max= creerCombinaison();
 boolean gEstTapis=false;
 Vector<Joueur> gagnants=new Vector<Joueur>(); // vecteur des gagnants
 afficherMessage("Joueur "+getJoueur().nom+" montre "+ max+"."+'\n');
 gagnants.add(getJoueur());
  gEstTapis=getJoueur().estTapis();
 joueurSuivant();
 for (int i=1;i<jo.length;i++,joueurSuivant())
 {//recherche des meilleurs Combinaisons
  if ( !getJoueur().estCouche() ) //seul les joueurs non couché comparent leurs mains
  {
   Combinaison temp =creerCombinaison();
   afficherMessage("Joueur "+getJoueur().nom+" montre "+ temp+"."+'\n');
   int resu=max.compareTo(temp);
   if (resu ==0)
   {
    afficherMessage("Joueur "+getJoueur().nom+" est à égalité."+'\n');
    gagnants.add(getJoueur());
    gEstTapis=gEstTapis || getJoueur().estTapis();
   }
   if (resu <0)
   {
    afficherMessage("Joueur "+getJoueur().nom+" a une meilleure combinaison."+'\n');
    max=temp;
    gEstTapis=getJoueur().estTapis();
    gagnants.removeAllElements();
    gagnants.add(getJoueur());
   }
  }
 }
 int reste=0;
 int gain=p.recuperer();
 if (gEstTapis)
 {
  int min=0;
  min=gagnants.get(0).getMiseTotale();
  for (int i=1;i<gagnants.size();i++)// si au moins un des gagnants a fait tapis alors on récupère la valeur minimale et on soustrait aux mises des joueurs cette mise minimale
   min=Math.min(min,gagnants.get(i).getMiseTotale());
  reste=gain-min*jo.length;
  gain=min*jo.length/gagnants.size();
  for (int i=0;i<jo.length;i++,joueurSuivant())
   getJoueur().setMiseTotale(getJoueur().getMiseTotale()-min);
 }
 else
  gain=gain/gagnants.size();
 for (int i=0;i<gagnants.size();i++)
   gestionGain(gain,gagnants.get(i));
 return reste;
}
 
protected Joueur getJoueur()
 {
  return jo[posJCourant];
 }

 protected void joueurSuivant()
 {
  posJCourant= (posJCourant+1)%jo.length;
 }

 protected void distribuerCartes() // fonction distribuant les cartes aux joueurs
 {
  pioche=new PaquetCarte();
  joueurSuivant();
  for (int j=0;j<nbCartesJoueurs;j++)
   for (int i=0;i<jo.length;i++)
   {
    if ( ! getJoueur().estCouche())
    	getJoueur().getMain().ajouterCarte(pioche.piocher());
    joueurSuivant();
   }
 }

protected void miseDepart()
{
 
 getJoueur().miser(p.getBlind());
 p.miser(p.getBlind());
 afficherMessage("Joueur "+getJoueur()+" a misé le blind de "+(getJoueur().getMiseCourante()) +"."+'\n');
 joueurSuivant();

}

 public void demarrerCoup() //fonction qui démarre le tour de jeu
 {
  for( int i=0;i<jo.length;i++,joueurSuivant())
  {
   if (getJoueur().getJetons() <= p.getBlind())
   {
    getJoueur().gagner(p.getBlind()*2);
    afficherMessage("Joueur "+getJoueur().nom+" a ajouté "+(p.getBlind()*2)+" jetons.\n");
   }
   getJoueur().rejouer();
  }
  nbCouche=0;
  nbTapis=0;
  position=0;
  donneur=(donneur+1)%jo.length;
  posJCourant=donneur;
  distribuerCartes();
  miseDepart();
  debutTourEnchere();
 }
 
 protected void gererResultat()
 {
  afficherMessage(p.toString());
   while (getJoueur().estCouche())
   joueurSuivant();
   if (jo.length-nbCouche>1) // si plus d'un joueur ne s'est pas couché
   {
    int reste =confronterCombinaison();
    if (reste > 0)
    { //si tout le pot n'a pas été distribué, on relance la fonction gererResultat
     p.miser(reste);
     gererResultat();
    }
    else
     nouveauCoup();
   }
   else // le joueur qui ne s'est pas couché récupère ses gains
   {
    gestionGain(p.recuperer(),getJoueur());
    nouveauCoup();
   }
  }

protected void gestionGain(int gain, Joueur j)//fonction qui donne un gain à un joueur gagnant
{
 int reste=gain;
 if (j.estTapis() && j.getMiseTotale() == 0)
 //si le joueur a récupérer sa mise maximale possible il se couche.
   j.seCouche();
 j.gagner(reste);
 afficherMessage("Joueur "+j+" a gagne "+reste+"."+'\n');
}

 protected void debutTourEnchere()
 {
  position=0;
  debutEnchereJoueur();
  if ( (nbTapis+ nbCouche) >= (jo.length-1))
   finTourEnchere();
  while (getJoueur().estTapis() || getJoueur().estCouche())
   joueurSuivant();
 }

 public void finEnchereJoueur ()
{
 if ( ! getJoueur().estCouche() )
 {
  if (getJoueur().estTapis())
  {
  afficherMessage("Joueur "+getJoueur().nom+" Fait Tapis. Il mise "+getJoueur().getMiseCourante()+"."+'\n');
  nbTapis++;
  }
  else
  if (p.getMise() == getJoueur().getMiseTotale())
    afficherMessage("Joueur "+getJoueur().nom+" suit. Il mise "+getJoueur().getMiseCourante()+"."+'\n');
  else
   afficherMessage("Joueur "+getJoueur().nom+" surencherit à  "+getJoueur().getMiseTotale()+", Il mise "+getJoueur().getMiseCourante()+". "+'\n');
  p.miser(getJoueur().getMiseCourante());
  p.setMise(getJoueur().getMiseTotale());
 }
 else
 {
  afficherMessage("Joueur  "+getJoueur().nom+" se couche."+'\n');
  nbCouche++;
 }
 
 if ( nbCouche+nbTapis == jo.length  )//si tous les joueurs non couché sont mis au tapis, les encheres s'arretent.
  finTourEnchere();
 else
 {
  joueurSuivant();
  position++;
  while (getJoueur().estCouche() ||getJoueur().estTapis() )
  {
   joueurSuivant();
   position++;
  }
  if ( position<jo.length || getJoueur().getMiseTotale() != p.getMise())
   debutEnchereJoueur();
  else
   finTourEnchere();
 }
}
}

