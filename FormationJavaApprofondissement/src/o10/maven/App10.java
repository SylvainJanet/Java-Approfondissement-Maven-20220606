package o10.maven;

public class App10 {

	public static void main(String[] args) {
		
		/*
		 * 
		 * Maven est un outil de build qui permet d'automatiser la gestion et la construction d'un
		 * projet Java
		 * 
		 * On peut l'utiliser en ligne de commande mais il est également intégré nativement
		 * dans STS et dans d'autres IDE.
		 * 
		 * Permet d'automatiser (et de paramétrer) :
		 * - la création d'un projet, avec une structure de dossiers normalisée, commune avec tous les
		 * projets Maven
		 * - le lancement des tests
		 * - le déploiement du projet
		 * 
		 * Permet également de gérer les dépendances du projet
		 * 
		 * Pour l'instant, une dépendance est rajoutée à la main et dépend de la structure des fichiers
		 * de notre machine/
		 * - soucis lorsqu'on doit rajouter plusiers dépendances, notamment lorsqu'il y a de
		 * l'interdépendance, c'est à dire que ces dépendances dépendent elles-même d'autres dépendances
		 * On a beaucoup de dépendances à gérer, et les problèmes s'accentuent quand on prend en 
		 * considération les versions de ces dépendances
		 * - soucis lors du partage de notre projet. Si les dépendances ne sont pas stockées au même
		 * endroit dans la machine des collègues, ça va poser problème. Partager les dépendances en
		 * même temps que le projet n'est pas une bonne solution non plus, on partagerait une quantité
		 * énorme de fichiers qui ne sont pas le contenu de notre travail.
		 * 
		 * La seule chose à configurer pour le fonctionnement de Maven pour un projet est un fichier
		 * pom.xml : fichier de configuration
		 * 
		 * File -> New -> Maven Project
		 * Cocher la case "skip archetype selection"
		 * Créer un projet simple
		 * Modifier le build path, le compilateur pour les mettre à la bonne version du jdk
		 * Clic droit JRE system library -> Properties -> mettre le bon jdk
		 * Clic droit projet -> properties -> Java Compiler -> compliance level 1.8
		 * 
		 * Les projets Maven ont une structure bien déinie.
		 * src/main/java : contient les classes Java de notre programme
		 * src/main/resources : contient toutes les ressources utilisées dans notre programme :
		 * images, fichiers texte, fichiers de configuration,...
		 * src/test/java : contient toutes les classes de tests
		 * src/test/resources : contient toutes les ressources utilisées pour les tests
		 * target : contient tous les fichiers générés par maven lors des différents traitements
		 * pom.xml : la configuration du projet
		 * 
		 * 
		 * Principe - dépendances
		 * 
		 * Maven va nous permettre de gérer les dépendances.
		 * Au lieu de télécharger la dépendance et de l'ajouter manuellement au projet, on
		 * va juste rajouter la dépendance dans le pom.xml de notre projet. 
		 * 
		 * Un fichier XSD (Xml Schema Document) permet de garantir la cohérence et la validité
		 * des informations contenues dans un fichier XML
		 * 
		 * Le sens des balises :
		 * - project : l'élement racine du pom.xml
		 * - modelVersion : la version du pom utilisée
		 * - groupId : un identifiant du groupe du projet. Comme un package, un espace de nom
		 * pour les projets maven
		 * - artifactId : un identifiant du projet. C'est un nom dans l'espace de nommage
		 * donné par le groupId
		 * 
		 * Un artifact est un projet type, Maven en propose plusieurs : 
		 * - maven-archetype-simple
		 * - maven-archetype-quickstart
		 * 
		 * File -> New -> Maven Project
		 * Laisser la case décochée cette fois :
		 * Chercher l'archetype : maven-archetype-simple et prendre
		 * celui de org.apache.maven.archetypes
		 * 
		 * Faire les opérations habituelles (jre + compiler) + modifier leur version dans le pom.xml
		 * 
		 * Le fichier pom.xml hérite d'un super POM (qui contient entre autres des informations sur
		 * le repo central), on peut voir le POM réel en cliquant sur "effective pom"
		 * 
		 * Dépôt : un stockage de dépendance.
		 * Dépôt local Maven : emplacement sur votre machine où est stocké un ensemble de
		 * dépendances
		 * Par défaut : 
		 * C:\Users\UTILISATEUR\.m2
		 * Dépot central Maven : emplacement distant depuis lequel Maven va télécharger
		 * les dépendances
		 * Repo Maven : https://mvnrepository.com/
		 * 
		 * 
		 * Principe - décomposition en phases et goals
		 * 
		 * Maven découpe le cycle de construction d'un projet en plusieurs phases
		 * Et chaque phase sera constituée d'un ensemble de goal, des tâchesà  accomplir
		 * L'idée : préparer les fichiers, tester le projet, si ils réussissent compiler le projet
		 * 
		 * Les principales phases dans Maven :
		 * compile : compiler le code source
		 * test : lancer les tests unitaires
		 * package : construire le package jar, war, ou autre
		 * clean : nettoyer les fichiers temporaires utilisés lors des autres phases
		 * validate : validation du pom d'un projet
		 * deploy : deployer le projet compilé quelque part
		 * 
		 * Un projet a un cycle de vie : une sucession de phases parmis celles disponibles, 
		 * exécutant certains goals. Selon le type de projet, les phases et les goals utilisés
		 * sont différents.
		 * Par exemple, le cycle de vie d'un projet JAR comprend 8 phases :
		 * - process-ressources : copie les fichiers de ressources (fichiers de propriétés,...),
		 * hors code source, dans le répertoire de construction finale
		 * - compile : compile le code source du projet dans le dossier de construction du JAR
		 * - process-test-ressources : copie les fichiers de tests; hors code source,
		 * dans le répertoire de construction finale
		 * - test-compile : compile le code des tests unitaires
		 * - test : exécute les tests unitaires
		 * - package : crée l'archive jar à partir du dossier contenant le code source compilé
		 * et les ressources conservée lors de la première phase
		 * - install : copie le jar à partir du dossier actuel dans le répo local
		 * - deploy : deploi l'archive dans le référentiel distant
		 * 
		 * 
		 * Dans le cas où votre projet a des comportement étranges du genre un erreur est
		 * détectée alors qu'il ne devrait pas y en avoir
		 * Project -> Clean -> Clean de tous les projets
		 * Pour chaque projet concerné, faire :
		 * Clic droit sur le projet, Run as, Maven Clean
		 * 
		 * 
		 * Pour exécuter une phase, ou un goal, 
		 * Clic droit sur le projet, Run as, ...
		 * - il existe plusieurs phases directement disponibles
		 * - pour lancer notre config, faire Maven build...
		 * 
		 * Run as -> Maven build -> choisit une des configurations que vous avez déjà créée
		 * Run as -> Maven build... -> créé une nouvelle config
		 * 
		 * Pour supprimer / modifier des configurations :
		 * Run as -> run configurations... -> modifier / supprimer la config
		 * 
		 * Dans la partie goal, on peut mettre la phase, ou choisir un goal précis
		 * Exemple : 
		 * package : pour exécuter la phase package
		 * phase:goal : pour exécuter un goal précis d'une phase
		 * 
		 * Dans une console, pour exécuter un jar :
		 * java -jar cheminverslejar.jar
		 * Pour l'instant, il y a un problème de manifest
		 * Essentiellement, le problème est double : 
		 * - le point d'entrée de l'application n'est pas spécifié dans le manifest
		 * - les dépendances ne sont pas incluses
		 * Le projet compilé est destiné à être une dépendance.
		 * 
		 * On souhaite rendre le jar exécutable, et on va résoudre ces problèmes en modifiant
		 * l'exécution de la phase package en modifiant le pom.xml
		 * (voir le projet simple-artifact)
		 * 
		 * Configuration de Maven, que ce soit pour le pom parent
		 * ou un fichier de configuration settings.xml
		 * 
		 * L'installation de Maven comprend les étapes suivantes:
		 * 
		 * - Vérifiez si Java est installé sur le système ou non. sinon, installez java
		 * - Vérifiez si la variable d'environnement JAVA_HOME est définie ou non.
		 * sous Windows : Ouvrez les propriétés système (avec la commande Win + Pause), 
		 * dans l'onglet « Avancé », cliquez sur le bouton « Variables d'environnement » :
		 * Ajoutez une propriété nommée JAVA_HOME avec la valeur (à adapter) :
		 * C:\chemin\vers\repertoire\env\java\jdk1.8.0_131
		 * - Télécharger Maven http://maven.apache.org
		 * - Décompressez le téléchargement maven à un endroit du système.
		 * - Créer la variable d'environnement MAVEN_HOME qui pointe sur le répertoire 
		 * contenant Maven
		 * - Maintenant, ajoutez le répertoire bin du répertoire créé (%MAVEN_HOME%\bin) à 
		 * la variable d'environnement PATH.
		 * - Ouvrez cmd et exécutez mvn -v commande pour confirmer l'installation.
		 * 
		 * 
		 * Dans le dossier conf contient un fichier settings.xml qui permet de configurer maven.
		 * Il y a deux niveaux de conf : la globale (dans le dossier d'installation maven)
		 * et une conf utilisateur (dans le dossier user/.m2)
		 * 
		 * Doc : https://maven.apache.org/settings.html
		 * 
		 * Pour faire un projet parent : 
		 * File -> New -> Maven Project -> Simple project avec packaging POM
		 * Pour faire les modules enfants :
		 * File -> New -> Maven Module -> Choisir le projet parent, et l'archetype qu'on souhaite
		 * 
		 * Dans les faits : ajout d'une balise parent dans le pom.xml des enfants et d'une 
		 * balise module dans le pom.xml du projet parent
		 * 
		 * Les modules enfants vont hériter du pom.xml du projet parent, et exécuter maven
		 * sur le projet parent exécutera maven sur tous les modules enfants
		 * -> centraliser la conf dans le projet parent
		 * -> exécuter les différentes phases maven sur tous les modules depuis un seul endroit
		 * -> l'utilisation de plusieurs modules permet d'organiser le projet, et de réutiliser 
		 * certains modules dans d'autres projets
		 * 
		 */
		

		
	}

}
