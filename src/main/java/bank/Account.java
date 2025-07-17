package bank;

public class Account {
  int id;
  float balance;

  public Account(int id, float balance) {
    this.id = id;
    this.balance = balance;
  }


  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public float getBalance() {
    return this.balance;
  }

  public void setBalance(float balance) {
    this.balance = balance;
  }



}
