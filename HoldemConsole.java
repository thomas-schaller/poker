public class HoldemConsole extends Holdem
{

/**
* Classe permettant d'instancier une partie de Poker Texas Hold'hem par la Console.
**/

  public HoldemConsole(int nbJoueurs)
 {
  super(nbJoueurs);
  p= new Pot();
  flop = new Main(5);

  Humain humain=new Humain(nbCartesJoueurs);
  jo[0]=humain;
  humain.nom="humain";
  afficherMessage("Debut de la partie du Texas Holdem\n");
 }

 public void afficherMessage(String message)
 {
  System.out.print(message);
 }

protected void debutEnchereJoueur ()
{
 afficherMessage(p+".");
 super.debutEnchereJoueur();
}


}