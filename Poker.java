import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
*
* Classe gérant l'application du jeu de Poker. 
* Il est possible de lancer l'application avec des arguments:
*	- 1er argument, le type de jeu de poker ainsi que sa visualisation en console ou en fenetre.
	- 2eme argument, le nombre de joueurs. (les joueurs, à part le premier, seront gérés par l'ordinateur.
Si le deuxième argument est omis, le nombre de joueurs est fixé à 3.
Si le premier argument est omis, il est demandé à travers la console quel type de poker il veut lancer.
*
*
**/

public class Poker implements ActionListener
{
 PartiePoker pp;
 int nbJoueurs=3;
 JFrame fenetre;
 JPanel jeuPoker= new JPanel();
 String choixJeu [] ={"PokerFerme","Texas Holdem"};
 JComboBox jcbChoix = new JComboBox ( choixJeu );
 public static final int POKER_FERME_CONSOLE=1;
 public static final int HOLDEM_CONSOLE=2;
 public static final int POKER_FERME_IHM=3;
 public static final int HOLDEM_IHM=4;

public Poker(int type)
 {
  this(type,3);
 
 }

 public Poker(int type,int nbJoueur)
 {
    nbJoueurs=nbJoueur;
    if (type == POKER_FERME_IHM || type == HOLDEM_IHM ) // gestion des fenetres
   {
    fenetre= new JFrame("Jeu de Poker");
    fenetre.getContentPane().add(jcbChoix,BorderLayout.NORTH); // la combobox pour le choix du type de poker est placé en haut.
    fenetre.getContentPane().add(jeuPoker,BorderLayout.CENTER); // le jeu de poker est placé au centre
    fenetre.setSize(Toolkit.getDefaultToolkit().getScreenSize()); // l'application est initialisée à la taille de l'écran.
    //fenetre.pack();
    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // on quitte l'application lorsque l'on ferme la fenetre.
    jcbChoix.addActionListener(this);
   }

   switch(type)
   {
    case POKER_FERME_CONSOLE:
     pp = new PokerFermeConsole(nbJoueur);
     pp.demarrerCoup();
    break;
    case HOLDEM_CONSOLE:
     pp = new HoldemConsole(nbJoueur);
     pp.demarrerCoup();
    break;
    case POKER_FERME_IHM:
     jcbChoix.setSelectedIndex(0);
     fenetre.setVisible(true);
    break;
    default:
     jcbChoix.setSelectedIndex(1);
     fenetre.setVisible(true);
   }

 }

public void actionPerformed(ActionEvent e)
{
 int choix=jcbChoix.getSelectedIndex();
 jeuPoker.removeAll();
 if (choix == 0)
 {
  PokerFermeIHM pfi=new PokerFermeIHM(nbJoueurs);
  pp =pfi;
  jeuPoker.add(pfi.getContent());
 }
 else
 {
  HoldemIHM hi = new HoldemIHM(nbJoueurs);
  pp=hi;
  jeuPoker.add(hi.getContent());
 }
 fenetre.repaint();
 pp.demarrerCoup();
}

public static void main (String args [] )
{
 Poker p;
if (args.length == 0)
{
 System.out.println("Que voulez vous faire ?");
 System.out.println("1. Lancer un poker fermé sur la console.");
 System.out.println("2. Lancer un texas holdem sur la console.");
 System.out.println("3. Lancer une partie de poker ferme par une interface graphique.");
 System.out.println("4. Lancer une partie de poker holdem par une interface graphique.");
 int choix =Integer.parseInt(System.console().readLine());
 if (choix >=POKER_FERME_CONSOLE && HOLDEM_IHM >= choix)
  p = new Poker(choix);
}
else if (args.length == 1)
 p= new Poker(Integer.parseInt(args[0]));
else 
 p= new Poker(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
}

}
