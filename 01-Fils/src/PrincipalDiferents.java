/**
 * Exercici de fils amb diferents prioritats.
 */
public class PrincipalDiferents {
    public static void main(String[] args) {

        // Al fil Pepe li assignem que tingui prioritat mínima
        Fil pepe = new Fil(
                "Pepe",
                Thread.MIN_PRIORITY,
                false,
                true
        );

        // I al fil de Juan li assignem prioritat màxima
        Fil juan = new Fil(
                "Juan",
                Thread.MAX_PRIORITY,
                false,
                true
        );

        pepe.start();    // Iniciem primer Pepe que tè la prioritat mínima 
        juan.start();    // i després Juan

        System.out.println("Acaba thread main");
    }
}
