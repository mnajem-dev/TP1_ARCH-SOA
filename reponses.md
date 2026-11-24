
# TP1 – Architecture SOA & Web Services
## SOAP et WSDL – SupNum
**Année universitaire : 2026–2027**

---

# PARTIE A – CODE-FIRST

## Étape 1 – Créer et lancer le service

### Question 1
**Quelle annotation fait de EmpruntService un service web ? Quelle annotation fait de LivreIndisponibleException une erreur déclarée dans le contrat ?**

**Réponse :**

L'annotation `@WebService` permet de déclarer la classe `EmpruntService` comme un service Web SOAP. Elle permet à JAX-WS de publier cette classe comme un service accessible à travers le réseau.

L'annotation `@WebFault` permet de déclarer `LivreIndisponibleException` comme une erreur métier pouvant être communiquée au client et décrite dans le contrat WSDL.

### Question 2
**Pourquoi les classes de données doivent-elles avoir un constructeur vide ?**

**Réponse :**

Les classes de données doivent posséder un constructeur vide pour permettre à JAXB de créer des objets Java lors de la désérialisation des messages XML en objets Java.

Ce constructeur facilite donc la conversion entre les données XML reçues et les objets Java utilisés par le service.

---

## Étape 2 – Lire le WSDL généré

### Question 1
**Quelle est l'URL du service ? Quelles sont les deux opérations et leurs messages d'entrée et de sortie ?**

**Réponse :**

L'URL du service est :

`http://localhost:8080/bib/EmpruntService`

Le WSDL est accessible à l'adresse :

`http://localhost:8080/bib/EmpruntService?wsdl`

Le service possède deux opérations :

1. `emprunterLivre`
   - Entrée : `idAdherent` et `isbn`.
   - Sortie : `emprunt`, contenant le numéro d'emprunt et la date de retour.
   - Exception possible : `LivreIndisponibleException`.

2. `rendreLivre`
   - Entrée : `numeroEmprunt`.
   - Sortie : `penalite`, de type `BigDecimal`.

### Question 2
**Quel style (document ou rpc) et quel use (literal ou encoded) le binding déclare-t-il ? Est-ce du SOAP 1.1 ou 1.2 ?**

**Réponse :**

Le binding généré par JAX-WS utilise normalement :

- Style : `document`.
- Use : `literal`.
- Version : SOAP 1.1.

Le style document signifie que le contenu du message est organisé autour d'éléments XML définis dans le schéma.

Le mode literal signifie que les données XML suivent directement les types et les structures définis dans le contrat.

La version SOAP 1.1 est identifiable grâce à l'espace de noms :

`http://schemas.xmlsoap.org/soap/envelope/`

### Question 3
**Quel type XSD a été généré pour isbn et pour dateRetour ? La règle de format de l'ISBN apparaît-elle ?**

**Réponse :**

Dans la partie Code-first, les propriétés ISBN et dateRetour sont représentées par des chaînes de caractères Java (`String`).

Elles sont donc normalement générées sous forme de type `xs:string` dans le schéma XSD.

La règle de format ISBN définie dans le code Java avec l'expression régulière n'apparaît pas automatiquement dans le WSDL généré.

Cela montre que certaines règles de validation présentes dans le code Java ne sont pas nécessairement représentées dans le contrat WSDL.

### Question 4
**Dans la requête SoapUI, les paramètres idAdherent et isbn portent-ils un préfixe d'espace de noms ?**

**Réponse :**

Il faut examiner la requête XML générée par SoapUI.

Les paramètres peuvent être écrits sans préfixe, tout en appartenant à un espace de noms par défaut, ou être qualifiés avec un préfixe selon le schéma XSD généré.

Le point important est que les paramètres doivent respecter exactement la structure et les espaces de noms définis dans le WSDL.

### Question 5
**Dans l'onglet Raw, où voyez-vous HTTP, XML, SOAP ? Quel rôle joue chacun de ces standards, et quel rôle joue le WSDL ?**

**Réponse :**

Les quatre éléments ont des rôles différents :

- HTTP : permet le transport des requêtes et des réponses entre le client et le serveur.
- XML : représente les données échangées sous forme de balises.
- SOAP : définit la structure des messages échangés, notamment Envelope, Header et Body.
- WSDL : décrit le contrat du service, ses opérations, ses paramètres, ses réponses et son adresse.

Ces standards travaillent ensemble pour permettre la communication entre des applications différentes.

---

## Étape 3 – Envoyer des messages SOAP

### Question 1
**Pourquoi la requête utilise-t-elle POST, même pour lire une information ?**

**Réponse :**

La méthode HTTP POST est utilisée parce que SOAP transmet généralement les messages XML dans le corps d'une requête HTTP POST.

POST ne signifie pas obligatoirement qu'une ressource est modifiée. Dans le cas de SOAP, cette méthode sert principalement à transmettre les informations nécessaires à l'exécution d'une opération du service.

### Question 2
**Le bloc jeton du Header a-t-il empêché le traitement ? Pourquoi ?**

**Réponse :**

Non, le bloc `jeton` n'empêche normalement pas le traitement dans le premier test.

Le serveur ne reconnaît pas nécessairement cet élément du Header, mais comme l'attribut `mustUnderstand` n'est pas activé, il peut ignorer cet élément et continuer le traitement de la requête.

### Question 3
**Que répond le service au message SOAP 1.2 ? Expliquez avec le binding lu à l'étape 2.**

**Réponse :**

Le service Code-first utilise un binding SOAP 1.1.

Lorsque le client envoie un message SOAP 1.2, le serveur peut rejeter la requête parce que la version de l'enveloppe SOAP ne correspond pas à celle attendue par le service.

Cette incompatibilité peut provoquer une erreur HTTP et une SOAP Fault.

La réponse exacte doit être observée dans le terminal ou dans la réponse de curl.

### Question 4
**Listez les cinq erreurs du dernier message et leur correction. Certaines ne déclenchent aucune erreur côté serveur : lesquelles, et pourquoi ?**

**Réponse :**

Les cinq erreurs sont :

1. Mauvaise casse de l'élément Envelope :
   - Incorrect : `soap:envelope`
   - Correct : `soap:Envelope`

2. Mauvais espace de noms :
   - Incorrect : `http://supnum.mr/bibliotheque`
   - Correct : `http://supnum.mr/bib`

3. Mauvais nom du paramètre :
   - Incorrect : `numero`
   - Correct : `numeroEmprunt`

4. Mauvaise qualification du paramètre :
   - Le paramètre `numeroEmprunt` doit respecter la qualification XML attendue par le WSDL. Il faut utiliser la forme observée dans la requête SoapUI.

5. Mauvais ordre des éléments :
   - Le Header doit être placé avant le Body dans l'Envelope SOAP.

Certaines erreurs peuvent empêcher la reconnaissance de la requête ou provoquer une erreur de désérialisation.

En revanche, un élément Header inconnu peut être ignoré lorsque `mustUnderstand` n'est pas activé. C'est pourquoi le bloc `jeton` ne provoque pas nécessairement une erreur.

---

## Étape 4 – Provoquer et construire des Faults

### Question 1
**Pour les cas 1 et 2, quels faultcode et faultstring recevez-vous, et quel code HTTP ?**

**Réponse :**

Cas 1 : ISBN mal formé (`978-2-10`).

Le service détecte que le format ISBN est incorrect et déclenche une `SOAPFaultException` avec le code `Client`.

Le faultstring contient le message :

`ISBN mal formé : 978-2-10`

Cas 2 : Livre déjà emprunté (`978-2-10-074444-4`).

Le service déclenche une `LivreIndisponibleException`.

Le message retourné est :

`Livre indisponible`

Le code Fault effectivement retourné doit être relevé dans la réponse SOAP.

Dans les deux cas, une SOAP Fault est normalement accompagnée d'une réponse HTTP 500.

### Question 2
**Dans le cas 2, que contient detail ? Où sa structure est-elle décrite dans le WSDL ?**

**Réponse :**

L'élément `detail` contient les informations supplémentaires concernant l'erreur métier.

Il contient notamment :

- L'ISBN du livre indisponible.
- La date prévue de retour du livre.

Sa structure est décrite dans le WSDL, dans la partie `types`, à travers le type XML correspondant à `LivreIndisponibleInfo`.

L'exception permet ainsi au serveur de transmettre au client des informations structurées sur l'erreur.

### Question 3
**Quel code JAX-WS a-t-il choisi pour le livre indisponible ? Est-ce une erreur du client ou du serveur ? Qu'auriez-vous choisi ?**

**Réponse :**

Le code réellement choisi doit être observé dans le `faultcode` de la réponse SOAP.

Une exception métier déclarée avec `@WebFault` peut être traduite par JAX-WS en Fault SOAP selon son traitement de l'exception.

Si le code retourné est `Server`, le serveur classe cette erreur comme une faute serveur.

Cependant, le livre indisponible est une situation métier liée à la demande du client.

Je choisirais donc un code `Client`, car la demande concerne un livre qui ne peut pas être emprunté.

### Question 4
**Que se passe-t-il avec mustUnderstand="1" ? Et avec "0" ?**

**Réponse :**

Avec `mustUnderstand="1"`, le destinataire SOAP doit comprendre et traiter l'élément Header.

Si le serveur ne reconnaît pas cet élément, il doit retourner une Fault `MustUnderstand`.

Avec `mustUnderstand="0"`, le serveur peut ignorer l'élément Header inconnu et continuer le traitement de la requête.

### Question 5
**Quelle différence voyez-vous entre votre Fault 1.2 et les Faults 1.1 reçues ?**

**Réponse :**

SOAP 1.1 et SOAP 1.2 utilisent des structures différentes pour représenter les erreurs.

SOAP 1.1 utilise principalement :

- `faultcode`
- `faultstring`
- `detail`

SOAP 1.2 utilise :

- `Code`
- `Reason`
- `Detail`

Les codes d'erreur changent également :

- SOAP 1.1 : `Client` et `Server`.
- SOAP 1.2 : `Sender` et `Receiver`.

SOAP 1.2 utilise donc une structure plus organisée pour décrire les erreurs.

---

# PARTIE B – CONTRACT-FIRST

## Étape 5 – Générer le code depuis le WSDL

### Question 1
**Dans ce WSDL, retrouvez l'opération, son message de fault et l'action SOAP à envoyer.**

**Réponse :**

L'opération définie dans le WSDL est :

`emprunterLivre`

Son message Fault est :

`LivreIndisponibleFault`

L'action SOAP à envoyer est :

`http://supnum.mr/bib/emprunterLivre`

Cette action est définie dans l'élément `soap:operation` du binding WSDL.

### Question 2
**Quelle est la signature de emprunterLivre dans EmpruntPortType ?**

**Réponse :**

La signature générée est normalement :

```java
EmpruntType emprunterLivre(
    String idAdherent,
    String isbn
) throws LivreIndisponibleFault;
```

Cette méthode appartient à l'interface `EmpruntPortType`, générée automatiquement à partir du WSDL avec l'outil `wsimport`.

### Question 3
**Quel type Java a été généré pour dateRetour ? Comparez avec la partie A.**

**Réponse :**

Dans la partie Contract-first, le champ `dateRetour` est généré avec le type Java :

`XMLGregorianCalendar`

Cela s'explique par le fait que le WSDL définit le champ avec le type XML `xs:date`.

Dans la partie Code-first, le champ était déclaré comme `String` dans la classe Java et était donc normalement généré comme `xs:string`.

La différence montre que le type défini dans le contrat WSDL influence directement le type Java généré.

### Question 4
**Quel est le nom de la classe d'exception générée, et d'où vient ce nom dans le WSDL ?**

**Réponse :**

La classe générée est :

`LivreIndisponibleFault`

Elle provient de la déclaration du message Fault `LivreIndisponibleFault` dans le WSDL et de son association à l'opération `emprunterLivre`.

Cette classe représente l'erreur métier que le service peut retourner au client.

---

## Étape 6 – Implémenter et comparer

### Question 1
**Le WSDL publié est-il celui que vous avez fourni ? Contient-il le motif de l'ISBN et le type xs:date ?**

**Réponse :**

Lorsque l'attribut `wsdlLocation` est correctement configuré, Metro utilise le WSDL fourni comme contrat de référence du service.

Le contrat contient :

- Le type personnalisé `IsbnType`, avec son motif de validation ISBN.
- Le type `xs:date` pour le champ `dateRetour`.

Le WSDL fourni permet donc de conserver les contraintes et les types définis initialement.

### Question 2
**Avez-vous dû modifier 01-emprunter.xml pour qu'il fonctionne ici ? Pourquoi ?**

**Réponse :**

La structure principale du fichier XML peut rester identique si elle respecte les éléments, les paramètres et les espaces de noms définis dans le WSDL.

Cependant, il faut modifier l'en-tête HTTP `SOAPAction`.

Dans la partie Code-first, nous utilisions :

`SOAPAction: ""`

Dans la partie Contract-first, nous utilisons :

`SOAPAction: "http://supnum.mr/bib/emprunterLivre"`

Cette différence est due à l'action SOAP explicitement définie dans le binding du WSDL de la partie B.

### Question 3
**Sans @SchemaValidation, l'ISBN 978-2-10 est-il refusé ? Et avec ? Quelle Fault obtenez-vous ?**

**Réponse :**

Sans l'annotation `@SchemaValidation`, le service ne valide pas automatiquement les messages entrants selon le schéma XSD.

Dans le code fourni, la méthode vérifie seulement si l'ISBN correspond au livre déjà emprunté.

L'ISBN `978-2-10` peut donc être accepté par le code métier.

Avec `@SchemaValidation`, Metro valide le message XML selon le schéma XSD associé au contrat.

Le format incorrect de l'ISBN est alors rejeté avant l'exécution normale de la méthode.

Le serveur retourne une Fault liée à l'échec de validation du message XML.

### Question 4
**Retirez wsdlLocation de l'annotation et redémarrez : qu'est-ce qui change dans le WSDL publié ?**

**Réponse :**

Lorsque `wsdlLocation` est supprimé, Metro ne s'appuie plus sur le WSDL fourni de la même manière.

Le WSDL publié peut alors être reconstruit à partir des annotations et des classes Java.

Certaines contraintes définies manuellement dans le contrat initial, notamment le motif de validation ISBN, peuvent ne plus apparaître dans le WSDL généré.

Cela montre l'importance de conserver le contrat WSDL comme référence dans une architecture Contract-first.

---

# CONCLUSION – CHOISIR ET RENDRE

### Question 1
**Citez deux différences que vous avez observées entre le WSDL de la partie A et celui de la partie B.**

**Réponse :**

Première différence :

Dans la partie A, le WSDL est généré automatiquement à partir des classes Java et des annotations JAX-WS.

Dans la partie B, le WSDL est écrit manuellement avant la génération des classes Java.

Deuxième différence :

Dans la partie A, les champs `isbn` et `dateRetour` sont normalement représentés comme des chaînes de caractères `xs:string`.

Dans la partie B, le contrat définit un type personnalisé `IsbnType` avec une restriction de format et utilise `xs:date` pour `dateRetour`.

Cela montre que Contract-first permet de contrôler plus précisément les types et les contraintes du contrat.

### Question 2
**Si on renomme le champ dateRetour dans le code, quel projet voit son contrat changer ? Quel risque pour les clients ?**

**Réponse :**

Le projet Code-first voit son contrat WSDL changer automatiquement lorsque le champ Java est renommé, car le WSDL est généré à partir du code.

Dans le projet Contract-first, le contrat WSDL reste la référence et ne change pas automatiquement si le code Java est modifié.

Le risque est une rupture de compatibilité avec les clients qui utilisent l'ancien contrat.

Par exemple, si un client attend un champ `dateRetour` et que le serveur publie un champ différent, le client peut ne plus réussir à traiter correctement la réponse SOAP.

### Question 3
**Quelle approche choisiriez-vous pour le service Paiement, appelé par Scolarité (.NET), Bibliothèque (PHP) et le portail (Node.js) ? Et pour un prototype interne de deux jours ?**

**Réponse :**

Pour le service Paiement utilisé par plusieurs applications développées dans différents langages, je choisirais l'approche Contract-first.

Cette approche permet de définir un contrat WSDL commun que toutes les équipes peuvent utiliser, indépendamment de leur langage de programmation.

Elle garantit une meilleure maîtrise des interfaces, des types et des messages échangés entre les applications.

Pour un prototype interne de deux jours, je choisirais plutôt Code-first.

Cette approche est plus rapide à mettre en place, car le développeur commence directement par les classes Java et laisse JAX-WS générer automatiquement le WSDL.

---

# CONCLUSION GÉNÉRALE

Ce TP nous a permis de comprendre le fonctionnement des Web Services SOAP et le rôle du WSDL dans une architecture orientée services (SOA).

Nous avons appris à créer un service SOAP en utilisant deux approches :

- Code-first : le service est développé en Java et le WSDL est généré automatiquement.
- Contract-first : le contrat WSDL est défini en premier et les classes Java sont générées à partir de ce contrat.

Nous avons également appris à construire et envoyer des messages SOAP avec SoapUI et curl, à comprendre les différentes parties d'une enveloppe SOAP et à gérer les erreurs avec les SOAP Faults.

Enfin, nous avons compris l'importance du contrat WSDL pour assurer l'interopérabilité entre des applications développées dans différents langages de programmation.

**En conclusion, Code-first est adapté aux prototypes et aux projets simples, tandis que Contract-first est préférable lorsque plusieurs applications ou équipes doivent partager un contrat commun et stable.**
