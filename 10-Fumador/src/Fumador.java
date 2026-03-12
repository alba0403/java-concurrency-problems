import java.util.Random;

public class Fumador extends Thread {

    private Estanc estanc;
    private int id;
    private Tabac tabac;
    private Paper paper;
    private Llumi llumi;
    private int numFumades;
    private Random random;

    public Fumador(Estanc estanc, int id) {
        this.estanc = estanc;
        this.id = id;
        this.random = new Random();
    }

    public void compraTabac() throws InterruptedException {
        tabac = estanc.venTabac();
        System.out.println("Fumador " + id + " comprant Tabac");
    }

    public void compraPaper() throws InterruptedException {
        paper = estanc.venPaper();
        System.out.println("Fumador " + id + " comprant Paper");
    }

    public void compraLlumi() throws InterruptedException {
        llumi = estanc.venLlumi();
        System.out.println("Fumador " + id + " comprant Llumí");
    }

    public void fuma() throws InterruptedException {
        System.out.println("Fumador " + id + " fumant");
        tabac = null;
        paper = null;
        llumi = null;

        numFumades++;

        int espera = 500 + random.nextInt(501);                 // fuma entre 0,5 seg i 1 seg
        Thread.sleep(espera);

        System.out.println("Fumador " + id + " ha fumat " + numFumades + " vegades");
    }

    @Override
    public void run() {
        try {
            while (numFumades < 3) {            // Fuma fins a 3 vegades
                compraTabac();
                compraPaper();
                compraLlumi();
                fuma();
            }
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }
}
