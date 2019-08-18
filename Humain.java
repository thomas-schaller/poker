class Humain extends Joueur
{
/* classe définissant le comportement d'un Joueur humain. Elle hérite de la classe Joueur.
Elle propose au joueur, par l'intermédiaire de la console, les différentes options offertes à l'utilisateur.
*/
protected Humain(){} // a n'utiliser que pour les enfants

public Humain(int nbCartesMain)
{
 main= new Main(nbCartesMain);
}

public void jouer(PartiePoker pp,Combinaison c) // propose entre surencherir, suivre ou abandonner.
{
 
 int choix=0;
 while (choix>3 || choix <1)
 {
  System.out.println("Vous avez "+jetons+" jetons.");
  System.out.println("Cartes: "+ getMain());
  System.out.println("Combinaison: "+ c);
  System.out.println("Que voulez vous faire ?");
  System.out.println("<1> Surencherir");
  System.out.println("<2> Parole");
  System.out.println("<3> se coucher");
  choix = Integer.parseInt(System.console().readLine());
 }
int somme=pp.p.getMise();
if (choix ==ENCHERIR)
 somme=encherir(somme*2);
 choixPoker(choix,somme);
 pp.finEnchereJoueur();
}

public void choixCarte(PokerFerme pf)
{
  int nbCartes=-1;
   while (nbCartes <0 || nbCartes > main.getNbEnMain() )
   {
     System.out.println("Votre Main:"+main);
    System.out.println("Combien de cartes voulez-vous échanger ?");
    nbCartes=Integer.parseInt(System.console().readLine());
   }
   for (int k=0; k<nbCartes;k++)
   {
    System.out.println("Indiquez la position de la carte à échanger:");
    int pos=Integer.parseInt(System.console().readLine());
    main.remplacerCarte(pos,pf.pioche.piocher());
   }
  pf.finChoixMainJoueur(nbCartes);
 }

 public int encherir(int min)
 {
  int somme=-1;
  while (somme<min)
  {
   System.out.println("Combien misez vous ? de "+min+" a "+jetons);
   somme = Integer.parseInt(System.console().readLine());
  }
  return somme;
 }
}