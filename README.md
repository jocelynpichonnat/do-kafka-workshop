# Digit'Owl : ateliers de formation Apache Kafka

## Préparation

- Avoir Java installé correctement (min : Java 8 ; recommandé : 17 )
- Récupérer auprès du formateur le contenu du certificat
    - Coller le contenu dans un fichier "ca.pem" **en dehors d'un repos Git ou tout autre dossier
      partagé**
    - Puis utiliser la commande suivante pour créer un JKS
        - keytool -import -file ca.pem -alias **[UseAnAlias]** -keystore client.truststore.jks
        - Mettre le fichier client.truststore.jks **en dehors d'un repos Git ou tout autre dossier
          partagé**
        - Bien garder en tête le mot de passe choisi

## Importation dans un IDE

- Importer le projet dans votre IDE
- Chaque module est un exercice : ne les faites pas en avance !
- Chaque module peut être importé comme un projet Maven