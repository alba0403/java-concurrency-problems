/**
 * Exemple de fils amb la mateixa prioritat
 */
public class PrincipalIguals {
    public static void main(String[] args) {

        // Creem dos instancies de fil amb la mateixa prioritat
        Fil juan = new Fil(
                "Juan",
                Thread.MAX_PRIORITY,
                false, // no fa pauses amb sleep
                true // simula que tè càrrega
        );

        Fil pepe = new Fil(
                "Pepe",
                Thread.MAX_PRIORITY,
                false,
                true
        );

        // Iniciem l'execució dels dos fils
        juan.start();
        pepe.start();

        System.out.println("Acaba thread main");    // El fil principal finalitza
    }
}
