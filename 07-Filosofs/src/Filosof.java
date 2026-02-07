public class Filosof extends Thread {
  //atributs
  private Forquilla forquillaDreta;
  private Forquilla forquillaEsquerra;
  private int gana;   // está bé fins a 3, com a máxim máxim 4 (però millor que no)

  // constructor
  public Filosof(String nom, Forquilla forquillaEsquerra, Forquilla forquillaDreta, int gana){
    super(nom);
    this.forquillaEsquerra = forquillaEsquerra;
    this.forquillaDreta = forquillaDreta;
    this.gana = gana;
  }

  //getters i setters
  public Forquilla getForquillaDreta() { return forquillaDreta; }
  public Forquilla getForquillaEsquerra() { return forquillaEsquerra; }
  public int getGana() { return gana; }
  
  public void setForquillaDreta(Forquilla forquillaDreta) {
    this.forquillaDreta = forquillaDreta;
  }
  public void setForquillaEsquerra(Forquilla forquillaEsquerra) {
    this.forquillaEsquerra = forquillaEsquerra;
  }
  public void setGana(int gana) {
    this.gana = gana;
  }


  // métodes auxiliars
  public void menjar(){
    System.out.printf("Filòsof: %s menja\n", getName());
    if(gana > 0){                                                           //evitar numeros negatius
      gana--;
    }
    esperar(1,2);
  }
  
  public void pensar(){
    System.out.printf("Filòsof: %s pensant\n", getName());
    esperar(1,2);
  }

  public void esperar(double min, double max){
    try{
      double numeroAleatori = Math.random() * (max - min) + min;    //genera un numero aletori entre 0 y 1, sumem la diferència del min i max perque el rndm estigui entre els dos valors que volem
      long tempsAleatoriEspera = (long) (numeroAleatori*1000);      //double per precisió, passa a long per passar a milisegons i poder utilitzar sleep
      sleep(tempsAleatoriEspera);
    } catch (InterruptedException e){
      System.err.println(e);
    }
  }

  // métode run
  public void run(){
    while(gana < 4){
      
      //intentar menjar
      if (forquillaEsquerra.isEnUs()){                                                            // si s'está utilitzant s'espera
        esperar(0.5,1);
      } else {
        forquillaEsquerra.setEnUs(true);                                                     // passa a estar en ús
        System.out.printf("Filòsof: %s agafa la forquilla esquerra\n", getName());
        
        if(forquillaDreta.isEnUs()){
          forquillaEsquerra.setEnUs(false);                                                  //deixem lliure la que haviem agafat
          System.out.printf("Filòsof: %s deixa la forquilla esquerra (%d) i espera (dreta ocupada)\n", 
          getName(), 
          forquillaEsquerra.getNumero());
          esperar(0.5,1);
          gana++;
          System.out.printf("Filòsof: %s gana=%d\n", getName(), gana);
        } else {
          forquillaDreta.setEnUs(true);
          System.out.printf("Filòsof: %s agafa la forquilla dreta\n", getName());
          
          menjar();
          // deixar la forquilla
          forquillaDreta.setEnUs(false);
          forquillaEsquerra.setEnUs(false);
          
          System.out.printf("Filòsof: %s ha acabat de menjar\n", getName());
          pensar();
        }
      }
    }
  }
}
