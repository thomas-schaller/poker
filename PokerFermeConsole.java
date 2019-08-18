public class PokerFermeConsole extends PokerFerme
{
/**
* Classe permettant d'instancier une partie de Poker Ferm� en Console.
**/
 public PokerFermeConsole(int nbJoueurs)
 {
  super(nbJoueurs);
  p= new Pot();
  Humain humain=new Humain(nbCartesJoueurs);
  jo[0]=humain;
  humain.nom="humain";
  afficherMessage("Debut de la partie du Poker Ferm�\n");
 }

public void afficherMessage(String message)
{
 System.out.print(message);
}

protected void debutEnchereJoueur ()
{
 afficherMessage(p+".\n");
 super.debutEnchereJoueur();
}
}