/**
 * Fonction modélisant un paquet de cartes. Il est possible de mélanger ce paquet et de piocher une carte.
 * une erreur peut se produire lorsque l'on essaye de piocher une carte alors qu'il n'y a plus de cartes dans le paquet.
 */

import java.util.Vector;

/**
 * @author SCHALLER thomas
 *
 */
public class PaquetCarte {
	Vector <Carte>  element=new Vector <Carte>();
	public PaquetCarte()
	{
		CouleurCartePoker cc=CouleurCartePoker.getMin();
		for (int i=0;i<CouleurCartePoker.getNbCouleurs();i++)
		{
			ValeurCartePoker vc=ValeurCartePoker.getMin();
			for (int j=0;j<ValeurCartePoker.getNbValeurs();j++)
			{
				element.add(new Carte(vc,cc));
				vc=vc.suivant();
			}
			cc=cc.suivant();
		}
		melanger();

	}
	public Carte piocher()
	{
		return element.remove(0);
	}
	public void melanger()
	{
		for (int i=0;i<element.size();i++)
		{   int position = (int)(Math.random()*element.size());
			Carte temp=(Carte)element.elementAt(i);
			element.set(i,element.get(position));
			element.set(position,temp);
		}
	}
	public String toString()
	{
		return element.toString();
	}


}
