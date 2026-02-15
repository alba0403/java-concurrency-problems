/***
 * Classe principal que ser utilitzada per les altres classes. 
 * Aquest fitxer conté la classe Fil que hereta de Thread i 
 * sobreescriu el mètode run.
 */
public class Fil extends Thread {

    private boolean activarPausa;  // Determina si s'ha d'usar sleep
    private boolean activarCarrega;   // Determina si s'ha d'usar càrrega de feina

    // Constructor que inicialitza el fil amb els paràmetres donats
    public Fil(String nomFil, int nivellPrioritat, boolean activarPausa, boolean activarCarrega) {
        this.setName(nomFil);                   // Settegem el nom del fil
        this.setPriority(nivellPrioritat);      // Li assignem el nivell de prioritat d'execució
        this.activarPausa = activarPausa;       // Assignem si s'ha d'usar sleep
        this.activarCarrega = activarCarrega;   // Assignem si s'ha d'usar càrrega de feina
    }

    @Override
    public void run() {
        for (int i = 1; i <= 9; i++) { // Bucle de 1 a 9

            System.out.println(getName() + " " + i);    // Mostrem el nom del fil i el valor de i

            if (activarCarrega) {   // Simula una càrrega de treball si està activat
                for (int j = 0; j < 1000; j++) { // Bucle buit per consumir temps de CPU
                }
            }

            if (activarPausa) {   // Realitza una pausa breu si està activat
                try {
                    Thread.sleep(1); // S'atura l'execució 1 milisegon
                } catch (InterruptedException e) {
                    e.printStackTrace(); //qualsevol excepció es mostra per consola
                }
            }
        }
        System.out.println("Acaba el fil " + getName());  // Missatge que indica que el fil ha acabat
    }
}
