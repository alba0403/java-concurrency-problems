public class Forquilla {
  // atributs
  private boolean enUs;     //true -> forquilla ocupada  ;  false -> forquilla libre
  private int numero;
  private int propietari;
  private final int LLIURE = -1;

  // constructor
  public Forquilla(int numero){
    setNumero(numero);
  }

  // getters
  public boolean isEnUs(){ return enUs; }

  public int getNumero(){ return numero; }

  public int getPropietari(){ return propietari; }

  public int getLliure(){ return LLIURE; }

  // setters
  public void setEnUs(boolean enUs){
    this.enUs = enUs;
  }

  public void setNumero(int numero){
    this.numero = numero;
  }

  public void setPropietari(){
    this.propietari = propietari;
  }
}