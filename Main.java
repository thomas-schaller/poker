public class Main // classe permettant de g�rer les mains des joueurs et le flop 
 {
 Carte cartes []; // les cartes en main
 protected int nbCartes=0; // nombre de cartes actuellement en main

 public Main(int taille)
 {
	cartes = new Carte[taille];
 }
 
 public boolean estPleine() // indique sile nombre de cartes maximum pour la main est atteint.
 {
	 return nbCartes == cartes.length;
 }
 public void ajouterCarte(Carte c) // ajoute une carte si la main n'est pas complete
 {
 		 cartes[nbCartes++]=c;
 }
 public int getNbEnMain() // indique le nombre de cartes effectivement en main
 {
	 return nbCartes;
 }
 public Carte getCarte(int position)//renvoie la carte à la postion demandée (position >0 && position<getNbEnMain())
 {
		 return cartes[position];
 }
 public void remplacerCarte(int position, Carte c)
 { // remplace la carte à la position donnée
		 cartes[position]=c;
 }
 public void viderMain()
 {
  nbCartes=0;
 }
 public String toString()
 {
	 String resu="{ ";
	 for (int i=0;i<getNbEnMain();i++)
	 {
		 resu+=cartes[i];
		 if (i<getNbEnMain()-1)
			 resu+=",";
	 }
	 return resu+"}"; 
 }
 public boolean equals(Main m)
 {
	 boolean resu=true;
	 int i=0;
	 resu=getNbEnMain()==m.getNbEnMain();
	 while (resu && i< getNbEnMain() )
	 {
		 resu = m.getCarte(i).equals(getCarte(i));
		 i++;
	 }
	 return resu;
 }
 public Carte[] toArray() // renvoie un tableau de cartes correspondant aux cartes contenu dans la main
 {
  Carte resu [] =new Carte[nbCartes];
  for (int i=0;i<nbCartes;i++)
   resu[i]=cartes[i];
  return resu;
 }

}
