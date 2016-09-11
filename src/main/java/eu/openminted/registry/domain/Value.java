package eu.openminted.registry.domain;

public class Value implements Comparable<Value> {
	 private String value;
	 private int count;
	 
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public int compareTo(Value o) {
		return Integer.compare(count, o.count);
	}
	 
	 
}