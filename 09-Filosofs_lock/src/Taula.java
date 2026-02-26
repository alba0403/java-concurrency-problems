public class Taula {

  private Filosof[] comensals = new Filosof[4];
  private Forquilla[] forquilles = new Forquilla[4];

  // constructor on assignem a cada filosof les seves dos forquilles correctes
  public Taula(){
    for(int i = 0; i < forquilles.length; i++){
      
      forquilles[i] = new Forquilla(i);
    }

    for(int j = 0; j < comensals.length; j++){
      comensals[j] = new Filosof("Fil"+j, forquilles[j],                             //forquilla esquerra -- j=0 -> fil0 -> esq0
                                          forquilles[(j + 1) % comensals.length]);    //forquilla dreta -- j=0 -> fil0 -> dret1
    }
  }

  // associem els fils amb les seves forquilles
  public void showTaula(){
    for(int i = 0; i < comensals.length; i++){
      System.out.printf("Comensals: %s esq:%d dret:%d \n", comensals[i].getName(),
                                                                  comensals[i].getForquillaEsquerra().getNumero(),
                                                                  comensals[i].getForquillaDreta().getNumero());
    }
  }
  
  
  public void cridarATaula(){
    for(int i = 0; i < comensals.length; i++){
      comensals[i].start();                           // mètode on iniciem els fils
    }
  }

  public static void main(String[] args) {
    Taula taula = new Taula();

    taula.showTaula();
    taula.cridarATaula();
  }
}