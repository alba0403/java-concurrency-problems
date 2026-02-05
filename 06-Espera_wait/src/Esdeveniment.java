import java.util.ArrayList;
import java.util.List;

public class Esdeveniment {
    //atributs
    private int placesDisponibles = 5;
    private List<Assistent> llistaAssistents = new ArrayList<Assistent>();  // creem una llista de tots els assistents

    // constructor
    public Esdeveniment(int placesMax){
        this.placesDisponibles = placesMax;
        this.llistaAssistents = new ArrayList<>();
    }


    public synchronized  void ferReserva(Assistent assistent) {
        while (placesDisponibles == 0) {    //si no hi han places
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //si hi han places
        llistaAssistents.add(assistent);
        placesDisponibles--;
        System.out.println(assistent.getName() + " ha fet una reserva. Places disponibles: " + placesDisponibles);
    }

    public synchronized void cancelaReserva(Assistent assistent) {
        if (llistaAssistents.contains(assistent)) {
            llistaAssistents.remove(assistent);
            placesDisponibles++;
            System.out.println(assistent.getName() + " ha cancel·lat una reserva. Places disponibles: " + placesDisponibles);

            notifyAll();    //avisem als altres fils per a que competeixin entre ells
        } else {
            System.out.println(assistent.getName() + " no ha pogut cancel·lar una reserva inexistent. Places disponibles: " + placesDisponibles);
        }
    }
}