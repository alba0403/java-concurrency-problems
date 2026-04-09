// Dona.java
public class Dona extends Thread {
  // Atributs
  private final String nom;
  private final BanyUnisex bany;

  // Constructor
  public Dona(String nom, BanyUnisex bany) {
    this.nom = nom;
    this.bany = bany;
  }


  @Override
  public void run() {
    try {
      System.out.println(nom + " vol entrar al bany");
      bany.entraDona();

      Thread.sleep(2000 + (long)(Math.random() * 1000));    // utilitza el lavabo entre 2 i 3 segons
      
      bany.surtDona();
      System.out.println(nom + " ha acabat d'usar el bany");

    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}