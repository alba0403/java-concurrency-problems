class Assistent extends Thread {
    // atribut
    private Esdeveniment esdeveniment;

    // constructor
    public Assistent(String nom, Esdeveniment esdeveniment) {
        super(nom);
        this.esdeveniment = esdeveniment;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (Math.random() < 0.3) {
                    esdeveniment.ferReserva(this);  //50%
                } else {
                    esdeveniment.cancelaReserva(this);  // 50%
                }
                Thread.sleep((int) (Math.random() * 1000)); // per esperar un temps aleatori entre 0 i 1
                
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}