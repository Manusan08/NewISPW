# MediaGlobe - ISPW 23/24

## Descrizione
Questo progetto è stato realizzato in quanto requisito vincolante per lo
svolgimento dell'esame _"Ingegneria del Software e Progettazione Web 2023/24"_ presso
_Università degli Studi di Roma "Tor Vergata"_.

Il progetto, in particolare, riguarda la gestione di un e-commerce di prodotto di elettronica come telefoni  o computer


## Struttura
```cmd
/ISPW
  
    /src                                        # Codice sorgente del progetto
        /main
             /java
                it/.../ispw
                    /bean                       # Classi bean
                    /controller                 # Controller applicativi
                    /enums                      # Enumerazioni
                    /model                      # Model divisi per tipo + DAO
                    /utils                      # Classi utilitarie
                    /view
                        /cli                    # Interfaccia CLI applicazione
                        /graphicalcontroller    # Controller grafici JavaFx
                    Conf.java                    # gestore della persistenza
                    Main.java                    # Main class (main)
                module-info.java
             /resources
                /csv                            # File csv per la persistenza
                /images                         # Immagini
                /views
                    /Admin                 # Interfacce grafiche admin
                    /Client                     # Interfacce grafiche client
              
                config.properties          # Configurazioni persistenza e UIs
        /test
            /java                        #test
                
    pom.xml                                     # Dipendenze progetto
    README.md                                   # Documentazione del progetto
```
## Avvio
Per l'applicativo sono previsti due livelli di persistenza e due diverse interfacce grafiche.
Si possono specificare entrambe le scelte direttamente dal file _config.properties_.  


### Livelli di persistenza
* **DBMS**: sarà necessario creare lo schema (cartella _.../sql_)
* **File System**: i dati saranno salvati in  file _.csv_ all'interno dell'apposita cartella (_.../csv_)

### Interfacce grafiche
* **JavaFx**
* **CLI**