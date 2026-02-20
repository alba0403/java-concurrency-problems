import java.util.concurrent.locks.ReentrantLock;

public class Forquilla {
  // atributs
  private int numero;
  ReentrantLock bloqueig;

  // constructor
  public Forquilla(int numero){
    this.numero = numero;
    bloqueig = new ReentrantLock(true);   // true perque sigui just, creem la instancia per a cada forquilla creada                                               // al inici sempre es creen lliures
  }

  // getters
  public int getNumero(){ return numero; }

  // métodes
  public void agafar(){
    bloqueig.lock();      // bloqueja automáticament si algú més la té
  }
  
  public void deixar(){
    bloqueig.unlock();    // desbloqueja automáticament
  }

}