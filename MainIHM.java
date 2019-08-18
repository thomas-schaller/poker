import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/* classe héritant de la classe Main, elle permet de représentée cette dernière dans une IHM. La méthode getContent permet de récuperer le panneau contenant tous les composants graphiques.
*
*/
public class MainIHM extends Main {
 private JPanel jpCartes=new JPanel();
 private JPanel contenu= new JPanel(new BorderLayout());
 JButton jbValiderCartes=new JButton("Fin de la selection des Cartes");
 SelectionnerCartes sc;
 public MainIHM(int taille)
 {
	super(taille);

	sc= new SelectionnerCartes(this);
	contenu.add(jpCartes,BorderLayout.CENTER);
	contenu.add(jbValiderCartes,BorderLayout.SOUTH);
	jbValiderCartes.setEnabled(false);
	jbValiderCartes.addActionListener( sc);
	
	for (int i=0; i<getNbEnMain();i++)
	{
	 CarteIHM c=new CarteIHM();
	 jpCartes.add( c);
	 c.setEnabled(false);
	}
	jbValiderCartes.setVisible(false);
 }

 private void remplacerCartes(PokerFerme pf)
 {
  int nbCartes =0;
  for (int i=0;i<getNbEnMain();i++)
  {
   if ( ((CarteIHM)jpCartes.getComponent(i)).isSelected())
   {
    remplacerCarte( i,pf.pioche.piocher() );
    nbCartes++;
   }
   ((CarteIHM)jpCartes.getComponent(i)).setEnabled(false);
  }
   jbValiderCartes.setVisible(false);
  jbValiderCartes.setEnabled(false);
  pf.finChoixMainJoueur(nbCartes);
 }

 public void choisirCartes(PokerFerme pf)
 {
  sc.setPoker(pf);
  for (int i=0;i< getNbEnMain();i++)
   jpCartes.getComponent(i).setEnabled(true);
  jbValiderCartes.setVisible(true);
  jbValiderCartes.setEnabled(true);
 } 
 
 public JPanel getContent()
 {
  return contenu;
 }

 public void ajouterCarte(Carte c) // ajoute une carte si la main n'est pas complete sinon provoque une erreur
 {
		CarteIHM courant=new CarteIHM(c);
 		cartes[nbCartes++]=c;
		jpCartes.add(courant);
		courant.setEnabled(false);
 }
 
 public void remplacerCarte(int position, Carte c)
 { // remplace la carte Ã  la position donnÃ©e
		CarteIHM courant=new CarteIHM(c);
		cartes[position]=c;
		jpCartes.remove(position);
		jpCartes.add(courant,position);
		courant.setEnabled(false);
 }
 public void viderMain()
 {
  super.viderMain();
  jpCartes.removeAll();
 }

 public Carte[] toArray()
 {
  Carte resu [] = new Carte [getNbEnMain()];
  for (int i=0;i<getNbEnMain();i++)
   resu[i]=cartes[i];
  return resu;
 }

class SelectionnerCartes implements ActionListener 
{
 MainIHM mIhm;
 PokerFerme pf;

 public SelectionnerCartes(MainIHM ihm)
 {
  mIhm=ihm;
 }

 public void setPoker(PokerFerme p)
 {
  pf=p;
 }

 public void actionPerformed(ActionEvent e) // lorsque le bouton est appuyé, on remplace les cartes selectionnées.
 {
  mIhm.remplacerCartes(pf);
 }

}
}
