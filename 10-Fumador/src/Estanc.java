import java.util.ArrayList;
import java.util.List;

public class Estanc {
  // Llistes
  private List<Tabac> llistaTabac;
  private List<Paper> llistaPaper;
  private List<Llumi> llistaLlumi;

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

  public void addTabac(){}
  public void addLlumi(){}
  public void addPaper(){}
}
