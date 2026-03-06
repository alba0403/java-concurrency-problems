import java.util.ArrayList;
import java.util.List;

public class Estanc {
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
  }

  public void nouSubministrament(){
    double numRndom = Math.random();

    if(numRndom < 0.33){
      addTabac();
    } else if((numRndom >= 0.33) && (numRndom < 0.66)){
      addPaper();
    } else {
      addLlumi();
    }
  }

  public void addTabac(){
    llistaTabac.add(new Tabac());
    System.out.println("Afegint Tabac");
    notifyAll();
  }

  public void addLlumi(){
    llistaLlumi.add(new Llumi());
    System.out.println("Afegint LlumÃ");
    notifyAll();
  }

  public void addPaper(){
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

    public Tabac venTabac() throws InterruptedException {
        return venProducte(llistaTabac);
    }
    public Paper venPaper() throws InterruptedException {
        return venProducte(llistaPaper);
    }
    public Llumi venLlumi() throws InterruptedException {
        return venProducte(llistaLlumi);
    }

    public synchronized void tancarEstanc() {
        obert = false;
        System.out.println("Estanc tancat");
        notifyAll();
    }
}
