
= Notes =
Auf Deutsch, restliches Projekt auf Englisch.

== Transactional ==
Das @Transactional-Annotation gehört in Spring Boot standardmäßig auf die Service-Schicht (auf Klassen- oder Methodenebene), nicht auf Controller oder Repositories.

Warum auf die Service-Klasse?
• Geschäftslogik bündeln: Eine Transaktion sollte eine geschäftliche Operation kapseln (z. B. mehrere Repository-Aufrufe wie Bestandsprüfung + Abbuchung + Speichern), die komplett gelingt oder komplett fehlschlägt (ACID).