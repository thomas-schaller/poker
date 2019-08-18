/**
* Classe gérant le pot au poker.
* Cette classe gère:
*	- le nombre de jetons misés.
*	- la mise minimale et son augmentation selon le nombre coups joués.
*	- l'enchère minimale pour pouvoir continuer à jouer.
*
**/

public class Pot {
int jetons=0;
int petit_blind=10;
int mise=petit_blind;
int nb_avt_aug=2; // nombre de mains avant l'augmentation du small_blind
int nb_mains = 0;

public int recuperer()
{
	nb_mains++;
	int resu=jetons;
	jetons=0;
	if (nb_mains % nb_avt_aug == 0)
		petit_blind *=2;
	mise=petit_blind;
	return resu;
}

public int getBlind()
{
 return petit_blind;
}

public void miser(int somme)
{
	 jetons+=somme;
}

public void setMise(int enchere)
{
 if (mise<enchere)
  mise=enchere;
}
public int getMise()
{
	return mise;
}
public String toString()
{
 return "Pot="+jetons+" Enchere="+mise;
}

}
