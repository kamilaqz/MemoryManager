package so;

import java.util.UUID;
import so.memory.AddressMemory;

public class Process {
	private String id;
	private int sizeInMemory;
	private AddressMemory am;
	
	public Process(int sizeInMemory) {
		this.id = UUID.randomUUID().toString();
		this.sizeInMemory = sizeInMemory;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSizeInMemory() {
		return sizeInMemory;
	}

	public void setSizeInMemory(int sizeInMemory) {
		this.sizeInMemory = sizeInMemory;
	}

	public AddressMemory getAm() {
		return am;
	}

	public void setAm(AddressMemory am) {
		this.am = am;
	}	
}
