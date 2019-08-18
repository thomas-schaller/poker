import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.GroupLayout.*;

/*
Classe reprenant la Classe Humain afin de pouvoir faire interagir l'utilisateur à travers une interface graphique et non plus par la console.
Les fonctions s'arretent et n'appelent pas directement finEnchereJoueur(), ni finChoixMainJoueur(nbCartes), ce sont les conséquences de l'appuie sur un bouton qui appelent ces fonctions permettant à la partie de continuer. Ceci est nécessaire afin d'attendre une action du joueur.
*/

public class HumainIHM extends Humain
{
 JPanel jpChoixMise =new JPanel();
 JPanel jpActions =new JPanel();
 JButton [] jbChoixMise= new JButton[3];
 MiserPoker mp [] = new MiserPoker[jbChoixMise.length];
 JLabel jlArgent = new JLabel();
 private JLabel jlCombinaison=new JLabel();

public HumainIHM(int nbCartesMain)
{
 main= new MainIHM(nbCartesMain);
 
 GroupLayout layout=new GroupLayout(jpActions);
 jpActions.setLayout(layout);
 layout.setAutoCreateGaps(true);
 layout.setAutoCreateContainerGaps(true);
 ParallelGroup hGroup=layout.createParallelGroup(Alignment.CENTER);
 hGroup.addComponent(jlArgent);
 hGroup.addComponent(((MainIHM)main).getContent());
 hGroup.addComponent(jlCombinaison);
 hGroup.addComponent(jpChoixMise);
 layout.setHorizontalGroup(hGroup);
 
 SequentialGroup vGroup= layout.createSequentialGroup();
 vGroup.addComponent(jlArgent);
 vGroup.addComponent(((MainIHM)main).getContent());
 vGroup.addComponent(jlCombinaison);
 vGroup.addComponent(jpChoixMise);
 layout.setVerticalGroup(vGroup);

 Font police = new Font("Times",Font.ITALIC,30); 
 jlArgent.setText("Jetons: "+jetons);
 jlArgent.setForeground(Color.ORANGE);
 jlArgent.setFont(police);
 
 jbChoixMise[0]=new JButton("Surencherir");
 jbChoixMise[1]=new JButton("Voir");
 jbChoixMise[2]=new JButton("Se coucher");
 for (int i=0;i<jbChoixMise.length;i++)
 {
  jpChoixMise.add(jbChoixMise[i]);
  mp[i]= new MiserPoker(this,i+1);
  jbChoixMise[i].addActionListener(mp[i]);
 }

 jpActions.add(jlArgent);
 jpChoixMise.setBorder(BorderFactory.createTitledBorder("Mise:"));
 jpActions.setBorder(BorderFactory.createTitledBorder("Joueur:"));
 jpActions.add( ((MainIHM)main).getContent() );
 jpActions.add(jpChoixMise);
}

public void finjouer(PartiePoker pp)
{
 for (int i=0;i<jbChoixMise.length;i++)
  jbChoixMise[i].setEnabled(false);
 jpChoixMise.setVisible(false);
 pp.finEnchereJoueur();
}

public void jouer(PartiePoker pp, Combinaison c)
{
 System.out.println("En attente du joueur");
 jpChoixMise.setVisible(true);
 for (int i=0;i<jbChoixMise.length;i++)
 {
  mp[i].setEnchere(pp);
  jbChoixMise[i].setEnabled(true);
 }
 jlCombinaison.setText(c.toString());
}

public void gagner(int mis)
 {
  super.gagner(mis);
  jlArgent.setText("Jetons: "+jetons);
 }

 public void miser(int somme)
 {
  super.miser(somme);
  jlArgent.setText("Jetons: "+jetons);
 }

public JPanel getContent()
{
 return jpActions;
}

public int encherir(int min)
{
 if (min >= jetons)
  return jetons+getMiseTotale();
 int nombre=-1;
 do
  nombre=Integer.parseInt(JOptionPane.showInputDialog("Combien misez-vous ?( de "+min+ " à "+jetons+")"));
 while (nombre<min || nombre>jetons);
 return nombre+getMiseTotale();
}

public void choixCarte(PokerFerme pf)
{
 ((MainIHM)main).choisirCartes(pf);
}

class MiserPoker implements ActionListener
{
 HumainIHM j; 
 PartiePoker pp;
 int choix;
 public MiserPoker(HumainIHM j1,int choix)
 {
  j=j1;
  this.choix=choix;
 }

public void setEnchere(PartiePoker pf1)
 {
  pp=pf1;
}
 public void actionPerformed(ActionEvent e) // lorsque le joueur appuie un bouton, la mise est finie.
 {
  
  int somme=pp.p.getMise();
  if (choix== ENCHERIR)
   somme=encherir(somme*2);
  j.choixPoker(choix,somme); 
  finjouer(pp);
 }

}


}