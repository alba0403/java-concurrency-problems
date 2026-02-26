public class Filosof extends Thread {
  //atributs
  private Forquilla forquillaDreta;
  private Forquilla forquillaEsquerra;
  private long iniciGana;           //long per poder utilitzar el currentTimeMillis
  private long fiGana;
  private long gana;

  // constructor
  public Filosof(String nom, Forquilla forquillaEsquerra, Forquilla forquillaDreta){
    super(nom);
    this.forquillaEsquerra = forquillaEsquerra;
    this.forquillaDreta = forquillaDreta;
  }

  //getters i setters
  public Forquilla getForquillaDreta() { return forquillaDreta; }
  public Forquilla getForquillaEsquerra() { return forquillaEsquerra; }
  
  public void setForquillaDreta(Forquilla forquillaDreta) {
    this.forquillaDreta = forquillaDreta;
  }
  public void setForquillaEsquerra(Forquilla forquillaEsquerra) {
    this.forquillaEsquerra = forquillaEsquerra;
  }
  
  // -----------------------------------
  // Métode run
  // -----------------------------------
  public void run(){
    while(true){
      pensar();                                                                 // primer fem que pensin per evitar que agafin la forquilla alhora
      agafarForquilles();
      menjar();
      deixarForquilles();
      System.out.printf("%s ha acabat de menjar\n", getName());
    }
  }



  public void agafarForquilles(){
      agafarForquillaEsquerra();
      agafarForquillaDreta();
      System.out.printf("%s té forquilles esq(%d) dreta(%d)\n", getName(),
        forquillaEsquerra.getNumero(),
        forquillaDreta.getNumero());
  }

  public void agafarForquillaEsquerra(){
    forquillaEsquerra.agafar();
    System.out.printf("%s agafa la forquilla esquerra\n", getName());
  }

  public void agafarForquillaDreta(){
    forquillaDreta.agafar();
    System.out.printf("%s agafa la forquilla dreta\n", getName());
  }
  
  public void deixarForquilles(){
    forquillaDreta.deixar();
    forquillaEsquerra.deixar();
    System.out.printf("%s deixa les forquilles\n", getName());
  }

  public void menjar(){
    fiGana = System.currentTimeMillis();
    calcularGana();
    System.out.printf("%s menja amb gana %d\n", getName(), gana);
    try{
        Thread.sleep(esperaNumeroAleatori(1,2));
    }catch(Exception e){
      System.err.println(e);
    }

    System.out.printf("%s ha acabat de menjar\n", getName());
    resetGana();
  }
  
  public void pensar(){
    iniciGana = System.currentTimeMillis();
    System.out.printf("%s pensant\n", getName());
    try {
      Thread.sleep(esperaNumeroAleatori(1,2));
    } catch (Exception e) {
      System.err.println(e);
    }
  }
  
  public long esperaNumeroAleatori(double min, double max){
      double numeroAleatori = Math.random() * (max - min) + min;
      long tempsAleatoriEspera = (long) (numeroAleatori*1000);
      return tempsAleatoriEspera;
  }

  public long calcularGana(){
        gana = (fiGana - iniciGana) / 1000;
        return gana;
    }

    public void resetGana(){
        iniciGana = System.currentTimeMillis();
        gana = 0;
    }
}