public class Home extends Thread {
  // Atributs
  private final String nom;
  private final BanyUnisex bany;

  // Constructor
  public Home(String nom, BanyUnisex bany) {
    this.nom = nom;
    this.bany = bany;
  }

  @Override
  public void run() {
    try {
      System.out.println(nom + " vol entrar al bany");
      bany.entraHome();

      Thread.sleep(1000 + (long)(Math.random() * 1000));    // utilitza el bany entre 1 i 2 segons
      
      bany.surtHome();
      System.out.println(nom + " ha acabat d'usar el bany");
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}