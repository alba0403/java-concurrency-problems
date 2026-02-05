public class Organitzador {
    public static void main(String[] args) {
        Esdeveniment esd = new Esdeveniment(5);

        Assistent[] as = new Assistent[10]; 

        for (int i = 0; i < 10; i++) {
            as[i] = new Assistent("Assistent-" + i, esd);     //creem els 10 assistents
            as[i].start();                                    // i els iniciem
        }
    }
}