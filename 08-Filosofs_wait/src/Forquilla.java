public class Forquilla {
  // atributs
  private int numero;
  private int propietari;
  private final int LLIURE = -1;

  // constructor
  public Forquilla(int numero){
    setNumero(numero);
    setPropietari(LLIURE);                                                      // al inici sempre es creen lliures
  }

  // getters
  public int getNumero(){ return numero; }

  public int getPropietari(){ return propietari; }

  public int getLliure(){ return LLIURE; }

  // setters
  public void setNumero(int numero){
    this.numero = numero;
  }

  public void setPropietari(int propietari){
    this.propietari = propietari;
  }
}