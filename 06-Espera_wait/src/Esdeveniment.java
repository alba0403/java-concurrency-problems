import java.util.ArrayList;
import java.util.List;

public class Esdeveniment {
    //atributs
    private int placesDisponibles = 5;
    // creem una llista de tots els assistents
    List<Assistent> llistaAssistents = new ArrayList<Assistent>();

    // constructor
    public Esdeveniment(){

    }


    public void ferReserva(Assistent assistent){
        if (llistaAssistents.size() < 5){   //si la llista de reserves no es plena, que afegeixi
            llistaAssistents.add(assistent);
            placesDisponibles --;
        }
    }

    public void cancelaReserva(Assistent assistent) {
        if ()
    }
}