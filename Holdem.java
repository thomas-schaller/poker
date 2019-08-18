public abstract class Holdem extends PartiePoker
{

/** classe abstraite définissant le comportement général d'une partie de poker texas Hold'em.
Il existe un flop.
Une combinaison est composée ici, du flop et de la main du joueur.
4 phases de jeu: préflop, flop, turn et river. Avant de confronter les combinaisons, il faut toujours avoir fait la phase river.
* déroulement d'un coup :
* - demarrerCoup-> distribuerCartes -> miseDepart();
     -debutTourEnchere(); // début du tour des enchères.
     -debutEnchereJoueur // debut du tour du joueur avec comme combinaison sa main et le flop.
     -finEnchereJoueur // fonction appellée par les classes externes afin de continuer le déroulement
     -finTourEnchere // arret du tour des enchères

     -gestion flop :chg de phase de jeu, tant que l'on est pas dans la phase river, alors soit on recommence les enchères, soit si il ne reste qu'un joueur, on change directement de phase de jeu. Si on est dans la phase river, on passe à la gestion du resultat.


     -gererResultat // si plusieurs joueurs sont dans la course, ils confrontent leur combinaison, sinon le dernier empoche la mise.
  - confronterCombinaison //fonction confrontant les combinaisons des joueurs valides
  - nouveauCoup
**/


 int phaseJeu=PREFLOP;
 public static final int PREFLOP=0;
 public static final int FLOP=1;
 public static final int TURN=2;
 public static final int RIVER=3;
 Main flop;

 public Holdem(int nbJoueur)
 {
  super(nbJoueur);
  nbCartesJoueurs=2; // chaque joueur a deux cartes dans sa main
  for (int i=1;i<jo.length;i++)
  {
   jo[i]=new Ordinateur(nbCartesJoueurs);
   jo[i].nom="Ordi["+i+"]";
  }

 }

 protected Combinaison creerCombinaison()// on crée une combinaison à partir du flop et de la main du joueur
 {
  return new Combinaison(getJoueur().getMain().toArray(),flop.toArray());
 }
 public String getPhase()
 {
  String resu="Resultat";
  switch (phaseJeu)
  {
   case PREFLOP:
    resu="Pre-Flop";
   break;
   case FLOP:
    resu="Flop";
   break;
   case TURN:
    resu="Turn";
   break;
   case RIVER:
    resu="River";
   break;
  }
  return resu;
 }
 protected void gestionFlop()
 {
  switch (phaseJeu)
  {
  case PREFLOP:
   pioche.piocher(); // on brule une carte
   flop.ajouterCarte(pioche.piocher());
   flop.ajouterCarte(pioche.piocher());
   flop.ajouterCarte(pioche.piocher());
  break;
  default:
   pioche.piocher(); // on brule une carte
   flop.ajouterCarte(pioche.piocher());
  }
  phaseJeu++;
  afficherMessage("Phase "+getPhase()+" commence:\n");
  afficherMessage("nbTapis "+nbTapis+" nbCouche:"+nbCouche+"\n");
  afficherMessage("Flop:"+flop+"\n");
  if ( (nbTapis+ nbCouche)>= (jo.length-1) && phaseJeu<RIVER)
   gestionFlop();
  else if ((nbTapis+ nbCouche)>= (jo.length-1))
   gererResultat();
  else
   debutTourEnchere();
 }

 public void demarrerCoup()
 {
  phaseJeu=PREFLOP;
  flop.viderMain();
  afficherMessage("Phase "+getPhase()+" commence:\n");
  super.demarrerCoup();
 }

 protected void finTourEnchere()//a la fin des encheres, si on est pas dans la phase river, on augmente le flop sinon on va aux résultat
 {
    if (phaseJeu==RIVER )
   gererResultat();
  else
   gestionFlop();
 }

protected void debutEnchereJoueur ()
{
 afficherMessage("Au Joueur "+getJoueur()+" de miser:");
  getJoueur().jouer(this, new Combinaison(getJoueur().getMain().toArray(),flop.toArray()));
}

}