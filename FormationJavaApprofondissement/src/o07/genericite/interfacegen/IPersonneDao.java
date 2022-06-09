package o07.genericite.interfacegen;

import classes.Personne;

public interface IPersonneDao extends IGenericDao<Personne> {

	Personne birthday(long id); // cette opération est plutôt à mettre dans la couche métier
}
