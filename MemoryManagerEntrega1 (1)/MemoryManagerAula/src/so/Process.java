package so;

import java.util.List;
import java.util.UUID;

import so.memory.AddressMemory;

public class Process {
	private String id;
	private int sizeInMemory;
	private int timeToExecute;
	private int priority;
	private List<Process> processed;
	private int instructions;
	private AddressMemory address;
	
	public Process(int sizeInMemory) {
		this.setId(UUID.randomUUID().toString());
		this.setSizeInMemory(sizeInMemory);
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

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getTimeToExecute() {
		return timeToExecute;
	}

	public void setTimeToExecute(int timeToExecute) {
		this.timeToExecute = timeToExecute;
	}

	public List<Process> getProcessed() {
		return processed;
	}

	public void setProcessed(List<Process> processed) {
		this.processed = processed;
	}

	public int getInstructions() {
		return instructions;
	}

	public void setInstructions(int instructions) {
		this.instructions = instructions;
	}

	public AddressMemory getAddress() {
		return address;
	}

	public void setAddress(AddressMemory address) {
		this.address = address;
	}

	public String getData() {
		// TODO Auto-generated method stub
		return null;
	}
}
