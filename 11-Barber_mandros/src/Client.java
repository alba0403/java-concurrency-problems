public class Client {
  private String nom;

  // constructor
  public Client(int id){
    nom = "Client-" + id;
  }

  public String getNom(){ return nom; }

  public void tallarseElCabell(){ System.out.printf("Tallant cabell a %s\n", nom); }
}
