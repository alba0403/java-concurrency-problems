public class Filosof extends Thread {
  //atributs
  private Forquilla forquillaDreta;
  private Forquilla forquillaEsquerra;
  private int numero;
  // atributs per evitar el deadlock
  private static final int MAX_INTENTANT = 4;                                   // màxim numero de filòsofs intentant menjar
  private static int intentantMenjar = 0;
  private static final Object monitorIntents = new Object();

  // constructor
  public Filosof(String nom, Forquilla forquillaEsquerra, Forquilla forquillaDreta, int gana, int numero){
    super(nom);
    this.numero = numero;
    this.forquillaEsquerra = forquillaEsquerra;
    this.forquillaDreta = forquillaDreta;
  }

  //getters i setters
  public Forquilla getForquillaDreta() { return forquillaDreta; }
  public Forquilla getForquillaEsquerra() { return forquillaEsquerra; }
  public int getNumero() { return numero; }
  
  public void setForquillaDreta(Forquilla forquillaDreta) {
    this.forquillaDreta = forquillaDreta;
  }
  public void setForquillaEsquerra(Forquilla forquillaEsquerra) {
    this.forquillaEsquerra = forquillaEsquerra;
  }
  public void setNumero(int numero){
    this.numero = numero;
  }
  
  // -----------------------------------
  // Métode run
  // -----------------------------------
  public void run(){
    while(true){
      synchronized (monitorIntents) {                                           // d'aquesta manera evitem que ocorri el deadlock, nomes poden tractar de menjar 4 alhora, en comptes dels 5
        while (intentantMenjar >= MAX_INTENTANT) {
          try {
            monitorIntents.wait();
          } catch (Exception e) {
            System.err.println(e);
          }
        }
        intentantMenjar++;
      }
      pensar();                                                                 // primer fem que pensin per evitar que agafin la forquilla alhora
      agafarForquilles();
      menjar();
      deixarForquilles();
      System.out.printf("Filòsof: %s ha acabat de menjar\n", getName());
    }
  }



  public void agafarForquilles(){
    while(true){
      synchronized(forquillaEsquerra){
        while (forquillaEsquerra.getPropietari() != forquillaEsquerra.getLliure()){                 //si no está lliure (propietari diferent a -1), espera
          try {
            forquillaEsquerra.wait();
          } catch (Exception e) {
            System.err.println(e);
          }
        }
        agafarForquillaEsquerra();                                                                  //ja está lliure, l'agafem
      }
      
      synchronized(forquillaDreta){
        if(forquillaDreta.getPropietari() == forquillaDreta.getLliure()){
          agafarForquillaDreta();
          break;
        } else {
          synchronized (forquillaEsquerra) {
            forquillaEsquerra.setPropietari(forquillaEsquerra.getLliure());
            System.out.printf("Filòsof: %s deixa la forquilla esquerra (%d) i espera (dreta ocupada)\n", getName(), forquillaEsquerra.getNumero());
            forquillaEsquerra.notifyAll();
            try {
              Thread.sleep(esperaNumeroAleatori(0.5, 1));
            } catch (Exception e) {
              System.err.println(e);
            }
          }
        }
      }
    }
  }

  public void agafarForquillaEsquerra(){
    forquillaEsquerra.setPropietari(numero);                                                        // passa a estar en ús
    System.out.printf("Filòsof: %s agafa la forquilla esquerra\n", getName());
  }

  public void agafarForquillaDreta(){
    forquillaDreta.setPropietari(numero);
    System.out.printf("Filòsof: %s agafa la forquilla dreta\n", getName());
  }
  
  public void deixarForquilles(){
    synchronized(forquillaDreta){
      forquillaDreta.setPropietari(forquillaDreta.getLliure());                 //igual que posar -1, però millor especificar per si en el futur canvies el valor, mas pro
      forquillaDreta.notifyAll();
    }
    synchronized(forquillaEsquerra){
      forquillaEsquerra.setPropietari(forquillaEsquerra.getLliure());
      intentantMenjar--;
      forquillaEsquerra.notifyAll();
    }
  }

  // -----------------------------------
  // métodes auxiliars
  // -----------------------------------
  public void menjar(){
    System.out.printf("Filòsof: %s menja\n", getName());
  
    try {
      long tempsEspera = esperaNumeroAleatori(1,2);
      Thread.sleep(tempsEspera);
    } catch (Exception e) {
      System.err.println(e);
    }
  }
  
  public void pensar(){
    System.out.printf("Filòsof: %s pensant\n", getName());
    try {
      long tempsEspera = esperaNumeroAleatori(1,2);
      Thread.sleep(tempsEspera);
    } catch (Exception e) {
      System.err.println(e);
    }
  }
  
  public long esperaNumeroAleatori(double min, double max){
      double numeroAleatori = Math.random() * (max - min) + min;
      long tempsAleatoriEspera = (long) (numeroAleatori*1000);
      return tempsAleatoriEspera;
  }
}