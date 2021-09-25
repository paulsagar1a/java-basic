package design.pattern.Builder;

class BankAccount {
	protected int accountID;
	protected String accountOwner;
	protected String ownerAddress;
	protected float amount;
	
	public BankAccount() {}
	
	@Override
	public String toString() {
		return "[id="+accountID+"\nowner="+accountOwner+"\naddress="+ownerAddress+"\namount="+amount+"]";
	}
}

class BankAccountBuilder  {
	private BankAccount account;
	private int accountID;
	private String accountOwner;
	private String ownerAddress;
	private float amount;
	
	public BankAccountBuilder(int accountID) {
		this.accountID = accountID;
	}
	
	public BankAccountBuilder setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
		return this;
	}
	
	public BankAccountBuilder setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
		return this;
	}
	
	public BankAccountBuilder setAmount(float amount) {
		this.amount = amount;
		return this;
	}
	
	public BankAccount build() {
		BankAccount acc = new BankAccount();
		acc.accountID = this.accountID;
		acc.accountOwner = this.accountOwner;
		acc.ownerAddress = this.ownerAddress;
		acc.amount = this.amount;
		return acc;
	}
}

public class BuilderExecutor2 {

	public static void main(String[] args) {
		BankAccount bankAccount = new BankAccountBuilder(1000212)
				.setAccountOwner("Animesh Maity")
				.setAmount(10000.0f)
				.build();
		System.out.println(bankAccount);
	}

}
