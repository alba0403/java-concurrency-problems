/**
 * Exemple on es veu l'ús de la classe Fil amb 
 * un estricte compliment del mètode sleep().
 */
public class PrincipalEstricte {
    public static void main(String[] args) {

        Fil juan = new Fil(
                "Juan",
                Thread.NORM_PRIORITY,
                true,     // Utilizem sleep
                false   // sense càrrega
        );

        Fil pepe = new Fil(
                "Pepe",
                Thread.NORM_PRIORITY,
                true,     // Utilizam sleep
                false   // sense càrrega
        );

        juan.start();   // Activem els dos processos
        pepe.start();

        System.out.println("Termina thread main");
    }
}
