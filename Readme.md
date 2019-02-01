L'applicazione è strutturata seguendo il pattern model <-> repository <-> service <-> controller.
Punti e Segmenti vengono salvati all'interno di due Set, attributi della classe PointRepository.
I metodi che espongono le proprietà di Point e Segment sono incapsulati in modo da garantire la modifica controllata dei due Set.
Ho scelto di demandare il maggior carico computazionale alle operazioni di inserimento che dovrebbero essere quelle invocate una tantum.
In fase di inserimento di un punto vengono verificati quanti nuovi segmenti sono stati creati o a quanti segmenti esistenti aggiungere il punto.
Il vantaggio ottenuto è la riduzione sul costo computazionale del servizio /lines/{n} che invocherà semplicemente il metodo size del Set O(1).