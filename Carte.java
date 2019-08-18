import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.*;
import java.awt.font.*;

public class Carte {
	/*
	 *  @author SCHALLER thomas
	 * Classe implémentant une carte.
	 * Une carte est définie par une valeur et une couleur représentées par les objets CouleurCartePoker et ValeurCartePoker.
	 * à partir d'une carte, il est possible de connaitre le nombre total de cartes possibles.
	 */
 protected CouleurCartePoker couleur;
 protected ValeurCartePoker valeur;
 
 public Carte ()
 {
	 this(null,null);
 }
 
 public Carte (ValeurCartePoker vc,CouleurCartePoker c)
 {

	 couleur=c;
	 valeur = vc;
 }
  
 public String toString()
 {
	if (valeur ==null || couleur == null)
		return "Pas de cartes";
	else
	 	return valeur+" de " +couleur;
 }
 
 public ValeurCartePoker getValeur()
 {
	 return valeur;
 }
 
 public CouleurCartePoker getCouleur()
 {
	 return couleur;
 }
public boolean equals(Carte c)
{
	return c.getValeur().equals(getValeur()) && getCouleur().equals(c.getCouleur());
}

public int getNbCartesMax()
{
	return couleur.getNbCouleurs()*valeur.getNbValeurs() ;
}

}
