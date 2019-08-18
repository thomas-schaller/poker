import java.util.Vector;
/*
Classe gérant les différentes combinaisons de 5 cartes possibles au Poker.
Cette classe construit à partir d'un tableau de cartes la meilleure combinaison possible.
Cette classe implémente l'interface Comparable permettant de hierarchiser deux objets Combinaisons.
*/
public class Combinaison implements Comparable<Combinaison> {
Carte selections []=new Carte[2];
int type = RIEN;

public static final int QUINTE_ROYALE=9;
public static final int QUINTE_FLUSH=8;
public static final int CARRE=7;
public static final int FULL=6;
public static final int COULEUR=5;
public static final int SUITE=4;
public static final int BRELAN=3;
public static final int DOUBLE_PAIRE=2;
public static final int PAIRE=1;
public static final int RIEN=0;

private static Carte[] fusionCartes(Carte [] cartes1,Carte [] cartes2) // fonction qui fusionne 2 tableaux de cartes en un seul
{
 Carte resu [] = new Carte [cartes1.length+cartes2.length];
 for (int i=0;i<cartes1.length+cartes2.length;i++)
 {
  if (i<cartes1.length)
   resu[i]=cartes1[i];
  else
   resu[i]=cartes2[i-cartes1.length];
 }
 return resu;
}

public Combinaison (Carte [] cartes1,Carte [] cartes2)
{

 this(fusionCartes(cartes1,cartes2));
}
public Combinaison (Carte [] cartes) //
{
	int k;
	// boucle servant à trier les cartes
	for (int i=0;i<cartes.length-1;i++)
	{
		for(int j=i+1;j<cartes.length;j++)
		{
			
			if ( cartes[i].getValeur().compareTo(cartes[j].getValeur())>0 ) 
			{
				Carte c=cartes[i] ;
				cartes[i]=cartes[j];
				cartes[j]=c;
				
			}
		}

	}//fin du triage des cartes
	//on initialise les meilleurs cartes par les plus grandes
		selections[0]=cartes[cartes.length-1];	
	if (cartes.length>1)
		selections[1]=cartes[cartes.length-2];
	else
		selections[1]=cartes[cartes.length-1];	
	// on recherche 5 cartes de la meme couleur
	for (int i=0;i<cartes.length-1 && type !=COULEUR;i++)
	{
		int nbC=1;//nombre de cartes de la même couleur.
		for(int j=i+1;j<cartes.length;j++)
			if (cartes[i].getCouleur().equals(cartes[j].getCouleur()))
				nbC++;
		if (nbC>=5)
		{
			selections[0]=cartes[i];
			type=COULEUR;
		}
		
	}



	// ****************************recherche de la combinaison
	int nbS=1; // nb de cartes se suivant.
	for (int i=0;i<cartes.length-1;i++)
	{
		boolean aContinuer=true;
		Carte c=cartes[i];
		int nbP=1; // nombre de cartes identiques.
		for(int j=i+1;j<cartes.length && aContinuer ;j++)
		{
			c=cartes[i];
			int resu;
			//System.out.println("Comp entre "+cartes[i] +" et "+cartes[j]);
			resu=c.getValeur().compareTo(cartes[j].getValeur()); 
			if(resu==-1) // si les deux cartes se suivent
			{
				aContinuer=false;
				nbS++;
				if (nbS>=5)
				{

					if (estFlush(cartes,j))
					{
						type=QUINTE_FLUSH;
						selections[0]=cartes[j];
					}
					else if (type<=SUITE)
					{
						type=SUITE;
						selections[0]=cartes[j];
					}
				}
				if (type==QUINTE_FLUSH && selections[0].getValeur().equals(new ValeurCartePoker (ValeurCartePoker.AS )))
					type=QUINTE_ROYALE;
			}
			else if(resu==0) // si la carte est identique
			{
				aContinuer=true;
				nbP++;
			}
			else
			{
				
				aContinuer=false;
				nbS=1;
			}
		}
		switch (nbP)
		{
		case 2:// 2 cartes identiques (soit paire, soit deux paires)
			if (type <=DOUBLE_PAIRE)
				// si la combinaison trouvée précédemment est moins forte qu'une double paire
			{
				
				selections[1]=selections[0];
				selections[0]=c;
				if (type==RIEN)
					type=PAIRE;
				else
					type=DOUBLE_PAIRE;
			}
			else
			if (type== BRELAN || type == FULL)
				//si la combinaison est un brelan ou un full
			{
				//on obtient un full (Brelan+Paire)
				type=FULL;
				selections[1]=c;
			}
			else
			{
				selections[1]=c;
			}
				
		break;
		case 3: // 3 cartes identiques
			if (type <=BRELAN || type == FULL)
			{

				if (type==RIEN) // si il n'y a pas de paire, c'est un brelan
				{
					type=BRELAN;
				}
				else //si il y a au moins une paire, c'est un full
					type=FULL;
			
				selections[1]=selections[0];
				selections[0]=c;
			}
			else selections[1]=c;
			
		break;
		case 4: // 4 cartes identiques
			if (type<=CARRE)
			{
				selections[1]=selections[0];
				selections[0]=c;
				type=CARRE;
			}

			break;
		}
		i+=nbP-1;// on recherche à partir de la prochaine carte différente 
	}
	k=cartes.length-2;
	if (type !=COULEUR)
		while( selections[1].getValeur().equals(selections[0].getValeur()) && k>=0)
		{
			selections[1]=cartes[k];
			k--;
		}
	else
		selections[1]=cartes[cartes.length-1];
}

public String toString()
{
	String resu="";;
	switch (type)
	{
	case RIEN:
		resu="Plus hautes cartes: "+selections[0] + " et "+selections[1];
		break;
	case PAIRE:
		resu="Paire de "+selections[0].getValeur() + " avec la plus haute carte "+selections[1].getValeur();
		break;
	case DOUBLE_PAIRE:
		resu="Double Paire de "+selections[0].getValeur() + " par les "+selections[1].getValeur();
		break;
	case BRELAN:
		resu="Brelan de "+selections[0].getValeur();
		break;
	case SUITE:
		resu="Quinte au "+selections[0].getValeur();
		break;
	case COULEUR:
		resu="Couleur à "+selections[0].getCouleur() + " au "+selections[1].getValeur();
		break;
	case FULL:
		resu="Main pleine au "+selections[0].getValeur() + " par les "+selections[1].getValeur();
		break;
	case CARRE:
		resu="Carre de "+selections[0].getValeur();
		break;
	case QUINTE_FLUSH:
		resu="Quinte Flush au "+selections[0].getValeur();
		break;
	case QUINTE_ROYALE:
		resu="Quinte Royale";
		break;
	}
return resu;
}

private boolean estFlush(Carte c [],int position)
//fonction qui teste si une quinte est une de la meme couleur en comptant les couleurs identiques.
{
int [] nbMCouleurs= new int[CouleurCartePoker.getNbCouleurs()];
for (int i=0;i<nbMCouleurs.length;i++)
	nbMCouleurs[i]=0;
int nbS=1;
int i=1;
boolean estFlush=false;
int resuComp=0;
while (nbS<6 && (position-1)>=0 && resuComp<=1)
{
	resuComp = c[position].getValeur().compareTo(c[position-1].getValeur());
	nbMCouleurs[c[position].getCouleur().couleur-1]++;
	if (resuComp==1)
		nbS++;
	position--;
}
if (position==0 &&nbS<6 && resuComp<=1)
	nbMCouleurs[c[position].getCouleur().couleur-1]++;
	
	for (i=0;i<nbMCouleurs.length && !estFlush;i++)
		{
		if (nbMCouleurs[i]>=5)
		
			estFlush=true;
		}
	return estFlush;
}

public int compareTo(Combinaison c) // fonction de comparaison
{
 int resu= type-c.type;
 if (resu==0)
  if (type==COULEUR)
   resu=selections[1].getValeur().compareTo(c.selections[1].getValeur());
  else if (type != QUINTE_ROYALE)
  {
   resu=selections[0].getValeur().compareTo(c.selections[0].getValeur());
   if (resu == 0 && (type <= DOUBLE_PAIRE) )
    resu=selections[1].getValeur().compareTo(c.selections[1].getValeur());
  }
 return resu;
}
}
