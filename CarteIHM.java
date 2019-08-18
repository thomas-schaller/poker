import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JCheckBox;

public class CarteIHM extends JCheckBox {
  /*
   *  @author SCHALLER thomas
   * Classe implémentant une checkbox utilisant trois états: selectionnée, non selectionnée et indisponible. Elle étend la JCheckBox, car plusieurs CarteIHM peuvent être selectionnées en même temps (contrairement à radiobutton).
   * elle utilise la classe Carte dont elle se sert afin de dessiner la carte qu'elle représente. Elle est utilisée afin de représentée les cartes dans une IHM.

   */
  Carte c;
  
  public CarteIHM() {
	this(null, null);
  }
  
  public CarteIHM(Carte c) {
	this(c.getValeur(), c.getCouleur());
  }
  
  public CarteIHM(ValeurCartePoker vc, CouleurCartePoker cc) {
	super();
	
	c = new Carte(vc, cc);
	setPreferredSize(new Dimension(50, 100));
	setMaximumSize(new Dimension(100, 200));
	setMinimumSize(new Dimension(25, 50));
	
  }
  
  public Carte getCarte() {
	return c;
  }
  
  public String toString() {
	return c.valeur + " de " + c.couleur;
  }
  
  public boolean equals(CarteIHM c1) {
	return c1.c.getValeur().equals(c.getValeur())
	    && c.getCouleur().equals(c1.c.getCouleur());
  }
  
  private void dessinerCoeur(Graphics2D g2) {
	Dimension t = g2.getClip().getBounds().getSize();
	BufferedImage img = null;
	try {
	  img = ImageIO.read(new File("coeur.png"));
	}
	catch (IOException e) {
	  System.err.println(e);
	}
	
	g2.drawImage(img, 1, 1, t.width - 1, t.height - 1, null);
	
  }
  
  private void dessinerPique(Graphics2D g2) {
	Dimension t = g2.getClip().getBounds().getSize();
	BufferedImage img = null;
	try {
	  img = ImageIO.read(new File("pique.png"));
	}
	catch (IOException e) {
	  System.err.println(e);
	}
	
	g2.drawImage(img, 1, 1, t.width - 1, t.height - 1, null);
  }
  
  private void dessinerCarreau(Graphics2D g2) {
	Dimension t = g2.getClip().getBounds().getSize();
	BufferedImage img = null;
	try {
	  img = ImageIO.read(new File("carreau.png"));
	}
	catch (IOException e) {
	  System.err.println(e);
	}
	
	g2.drawImage(img, 1, 1, t.width - 1, t.height - 1, null);
  }
  
  private void dessinerTrefle(Graphics2D g2) {
	Dimension t = g2.getClip().getBounds().getSize();
	BufferedImage img = null;
	try {
	  img = ImageIO.read(new File("trefle.jpg"));
	}
	catch (IOException e) {
	  System.err.println(e);
	}
	
	g2.drawImage(img, 1, 1, t.width - 1, t.height - 1, null);
  }
  
  public void paint(Graphics g) // fonction qui dessine la valeur de la carte et et qui la redessine avec une rotation de Pie et qui dessine la couleur de la carte. Le fond est coloré selon que la carte soit selectionnée, cliquable ou non.
  {
	// préparation pour dessiner en antialiasing
	Graphics2D g2 = (Graphics2D) g;
	g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
	    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	    RenderingHints.VALUE_ANTIALIAS_ON);
	
	Dimension taille = g2.getClip().getBounds().getSize(); // on récupère la zone allouée pour dessiner.
	
	AffineTransform atDefault = g2.getTransform();
	int taillePolice = Math.min(taille.width, taille.height) / 4;
	int EpaisseurTrait = 2;
	g2.setStroke(new BasicStroke(EpaisseurTrait)); //on fixe l'epaisseur du trait pour dessiner
	Font f = new Font("Times", Font.BOLD, taillePolice); // police servant à écrire
	FontRenderContext frc = g2.getFontRenderContext();
	
	if (c.valeur != null && c.couleur != null) {
	  String resultat = c.valeur.toString();
	  TextLayout tlResu = new TextLayout(resultat, f, frc);
	  AffineTransform rotation = AffineTransform.getRotateInstance(Math.PI,
		  taille.width / 2, taille.height / 2);
	  
	  //Changement de Couleur du fond lorsque la carte est selectionnée ou non cliquable
	  if (isSelected())
		g2.setColor(Color.YELLOW);
	  else if (isEnabled())
		g2.setColor(Color.GRAY);
	  else
		g2.setColor(Color.WHITE);
	  
	  g2.fillRect(0, 0, taille.width, taille.height);
	  if (c.getCouleur().equals(CouleurCartePoker.Coeur)
		  || c.getCouleur().equals(CouleurCartePoker.Carreau))
		g2.setColor(Color.RED);
	  else
		g2.setColor(Color.BLACK);
	  
	  int tailleForme = getSize().width / 3;
	  if (c.getCouleur().equals(CouleurCartePoker.Carreau)) {//dessin du carreau
		dessinerCarreau((Graphics2D) g2.create(taille.width / 2 - tailleForme,
		    taille.height / 2 - tailleForme, tailleForme * 2, tailleForme * 2));
	  }
	  else if (c.getCouleur().equals(CouleurCartePoker.Trefle)) { //dessin du trefle
		dessinerTrefle((Graphics2D) g2.create(taille.width / 2 - tailleForme,
		    taille.height / 2 - tailleForme, tailleForme * 2, tailleForme * 2));
	  }
	  else if (c.getCouleur().equals(CouleurCartePoker.Pique)) { //dessin du pique
		dessinerPique((Graphics2D) g2.create(taille.width / 2 - tailleForme,
		    taille.height / 2 - tailleForme, tailleForme * 2, tailleForme * 2));
	  }
	  else {//dessin du coeur
		dessinerCoeur((Graphics2D) g2.create(taille.width / 2 - tailleForme,
		    taille.height / 2 - tailleForme, tailleForme * 2, tailleForme * 2));
		
	  }
	  //ecriture de la valeur de la carte
	  tlResu.draw(g2, taillePolice / 2, taillePolice + taillePolice / 2);
	  g2.transform(rotation);
	  tlResu.draw(g2, taillePolice / 2, taillePolice + taillePolice / 2);
	}
	else {
	  System.out.println("Carte Vide");
	  g2.setColor(Color.GRAY);
	  g2.fillRect(1, 1, taille.width, taille.height);
	}
	//dessin de la bordure de la carte
	g2.setColor(Color.BLACK);
	g2.setTransform(atDefault);
	g2.drawRect(1, 1, taille.width - 2, taille.height - 2);
  }
  
}
