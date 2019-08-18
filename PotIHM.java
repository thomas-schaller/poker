import javax.swing.*;
import java.awt.*;
// classe qui adapte l'objet Pot à une interface graphique. La fonction getContent permet de récupérer un panneau comprenant tous les composants graphiques du Pot.
public class PotIHM extends Pot{
JLabel jlPot=new JLabel();


public PotIHM()
{
 jlPot.setText(this.toString());
 jlPot.setHorizontalAlignment(JLabel.CENTER);
 jlPot.setBorder(BorderFactory.createLoweredBevelBorder());
}
public int recuperer()
{
	int resu=super.recuperer();
	jlPot.setText(toString());
	return resu;
}

public JLabel getContent()
{
 return jlPot;
}
public void miser(int somme)
{
	 super.miser(somme);
	  jlPot.setText(toString());
}

public void setMise(int enchere)
{
 super.setMise(enchere);
 jlPot.setText(toString());
}

}
