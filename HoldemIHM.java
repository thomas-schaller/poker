import javax.swing.*;
import java.awt.*;
import javax.swing.GroupLayout.*;
import java.util.*;

/**
* Classe permettant d'instancier une partie de Poker Texas Hold'hem avec une interface graphique.
**/

public class HoldemIHM extends Holdem
{
 Vector<String> historique = new Vector<String>();
 JPanel contenu= new JPanel();
 JTextArea jtaInfo = new JTextArea(10,10);
 JLabel jlInfo=new JLabel();
 JScrollPane jspInfo = new JScrollPane(jtaInfo);

 public HoldemIHM(int nbJoueurs)
 {
  super(nbJoueurs);
  PotIHM potAff = new PotIHM();
  p=potAff;

  HumainIHM humain=new HumainIHM(nbCartesJoueurs);
  jo[0]=humain;
  humain.nom="humain";
  MainIHM flopIhm=new MainIHM(5);
  flop = flopIhm;

  Font police = new Font("Times",Font.BOLD,30);
  potAff.getContent().setFont(police);
  jlInfo.setFont(police);
  jlInfo.setForeground(Color.BLUE);

  GroupLayout gl= new GroupLayout(contenu);
  contenu.setLayout(gl);
  gl.setAutoCreateGaps(true);
  gl.setAutoCreateContainerGaps(true);
  jspInfo.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
  jspInfo.setViewportBorder(BorderFactory.createLineBorder(Color.BLUE));
  jspInfo.setBorder(BorderFactory.createTitledBorder("Historique:"));
  jtaInfo.setLineWrap(true);
  jtaInfo.setWrapStyleWord(true);
  jtaInfo.setEditable(false);

  //gestion de l'axe horizontal(x)
  ParallelGroup hGroupe= gl.createParallelGroup(Alignment.CENTER);
  hGroupe.addComponent(jlInfo);
  hGroupe.addComponent(jspInfo);
  hGroupe.addComponent(potAff.getContent());
  hGroupe.addComponent(flopIhm.getContent());
  hGroupe.addComponent(humain.getContent());
  gl.setHorizontalGroup(hGroupe);

  //gestion de l'axe vertical(y)
  SequentialGroup vGroupe= gl.createSequentialGroup();
  vGroupe.addComponent(jlInfo);
  vGroupe.addComponent(jspInfo);
  vGroupe.addComponent(flopIhm.getContent());
  vGroupe.addComponent(potAff.getContent());
  vGroupe.addComponent(humain.getContent());
  gl.setVerticalGroup(vGroupe);
  afficherMessage("Debut de la partie du Texas Holdem\n");
 }

 protected void nouveauCoup()
 {
  int choix=JOptionPane.showConfirmDialog(null,"Voulez-vous recommencer un coup de Poker ?","Fin de la Partie",JOptionPane.YES_NO_OPTION);
  if (choix == JOptionPane.YES_OPTION  )
   demarrerCoup();
 }

 public JPanel getContent()
 {
  return contenu;
 }
public void afficherMessage(String message)
{
 historique.add(message);
 if (historique.size()>=40)
  historique.remove(0);
 jtaInfo.setText("");
 for (int i=0;i<historique.size();i++)
  jtaInfo.append(historique.get(i));
 System.out.print(message);
 jlInfo.setText(message);
}

}