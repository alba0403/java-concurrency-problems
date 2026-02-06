public class Forquilla {
  // atributs
  private boolean enUs;     //true -> forquilla ocupada  ;  false -> forquilla libre
  private int numero;

  // constructor
  public Forquilla(int numero){
    setNumero(numero);
  }

  // getters
  public boolean isEnUs(){ return enUs; }

  public int getNumero(){ return numero; }

  // setters
  public void setEnUs(boolean enUs){
    this.enUs = enUs;
  }

  public void setNumero(int numero){
    this.numero = numero;
  }
}
