import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BanyUnisex {
  // Atributs
  private static final int BANY_BUIT = 0;
  private static final int BANY_AMB_HOMES = 1;
  private static final int BANY_AMB_DONES = 2;
  private static final int CAPACITAT_MAX = 3;
  private int estatActual;
  private int ocupants;
  private final Semaphore capacitat;
  private final ReentrantLock lockEstat;

  // Constructor
  public BanyUnisex() {
    this.estatActual = BANY_BUIT;
    this.ocupants = 0;
    this.capacitat = new Semaphore(CAPACITAT_MAX, true);    // fair=true garanteix que els fils esperin en ordre
    this.lockEstat = new ReentrantLock(true);
  }

  // Metodes
  public void entraHome() {
    while (true) {
      lockEstat.lock();       // bloquejem l'estat per llegir-lo i modificar-lo de forma segura

      try {
        if (estatActual == BANY_BUIT || estatActual == BANY_AMB_HOMES) {
          if (capacitat.tryAcquire()) {         // tryacquire intenta agafar un tiquet sense bloquejar el fil
            ocupants++;
            estatActual = BANY_AMB_HOMES;
            System.out.println("Home entra al bany. Ocupants: " + ocupants);
            return;
          }
        }
      } finally {
        lockEstat.unlock();         // sempre al finally per garantir que es desbloqueja
      }
      
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        return;
      }
    }
  }

  public void surtHome() {
    lockEstat.lock();

    try {
      ocupants--;
      capacitat.release();        // retornem el tiquet al semafor
      System.out.println("Home surt del bany. Ocupants: " + ocupants);

      if (ocupants == 0) {
        estatActual = BANY_BUIT;
        System.out.println("El bany està buit");
      }
    } finally {
      lockEstat.unlock();
    }
  }

  public void entraDona() {
    while (true) {
      lockEstat.lock();

      try {
        if (estatActual == BANY_BUIT || estatActual == BANY_AMB_DONES) {
          if (capacitat.tryAcquire()) {
            ocupants++;
            estatActual = BANY_AMB_DONES;
            System.out.println("Dona entra en el bany. Ocupants: " + ocupants);
            return;
          }
        }
      } finally {
        lockEstat.unlock();
      }

      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        return;
      }
    }
  }

  public void surtDona() {
    lockEstat.lock();
    
    try {
      ocupants--;
      capacitat.release();
      System.out.println("Dona surt del bany. Ocupants: " + ocupants);

      if (ocupants == 0) {
        estatActual = BANY_BUIT;
        System.out.println("El bany està buit");
      }
    } finally {
      lockEstat.unlock();
    }
  }


  // main
  public static void main(String[] args) {
    BanyUnisex bany = new BanyUnisex();
    for (int i = 0; i < 5; i++) {
      new Home("Home-" + i, bany).start();
      new Dona("Dona-" + i, bany).start();
    }
  }
}