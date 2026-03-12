import java.util.ArrayList;
import java.util.List;

public class Estanc extends Thread{
  // Llistes
  private List<Tabac> llistaTabac;
  private List<Paper> llistaPaper;
  private List<Llumi> llistaLlumi;
  private boolean obert;

  // Constructor
  public Estanc() {
    this.llistaTabac = new ArrayList<>();
    this.llistaPaper = new ArrayList<>();
    this.llistaLlumi = new ArrayList<>();
    this.obert = true;
  }

  public synchronized void nouSubministrament(){
    double numRndom = Math.random();

    if(numRndom < 0.33){
      addTabac();
    } else if((numRndom >= 0.33) && (numRndom < 0.66)){
      addPaper();
    } else {
      addLlumi();
    }
  }

  public synchronized void addTabac(){
    llistaTabac.add(new Tabac());
    System.out.println("Afegint Tabac");
    notifyAll();
  }

  public synchronized void addLlumi(){
    llistaLlumi.add(new Llumi());
    System.out.println("Afegint Llumí");
    notifyAll();
  }

  public synchronized void addPaper(){
    llistaPaper.add(new Paper());
    System.out.println("Afegint Paper");
    notifyAll();
  }

  private synchronized <Producte> Producte venProducte(List<Producte> llista) throws InterruptedException {
      while (llista.isEmpty() && obert) {
          wait();
      }
      if (!llista.isEmpty()) {
          return llista.remove(0);
      }
      return null;
  }

  public synchronized Tabac venTabac() throws InterruptedException {
      return venProducte(llistaTabac);
  }
  public synchronized Paper venPaper() throws InterruptedException {
      return venProducte(llistaPaper);
  }
  public synchronized Llumi venLlumi() throws InterruptedException {
      return venProducte(llistaLlumi);
  }

  public synchronized void tancarEstanc() {
      obert = false;
      System.out.println("Estanc tancat");
      notifyAll();
  }

  @Override
  public void run() {
    System.out.println("Estanc obert");
    while (obert) {
      nouSubministrament();
      try {
        int esperar = 500 + (int)(Math.random() * 1001);      //multiplicar per 1001 fa que generi un nombre entre 0 y 1000
        Thread.sleep(esperar);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }
  }
}
