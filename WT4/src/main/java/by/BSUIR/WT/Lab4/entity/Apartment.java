package by.BSUIR.WT.Lab4.entity;

import java.io.Serializable;

public class Apartment implements Identifiable, Serializable{

	private int id;
	private String status;
	private int number;
	private double price;

	public Apartment() {}
	
	public Apartment(int id, String status, double price) {
		this.id = id;
		this.status = status;
		this.price = price;
	}

	@Override
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getNumber () {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        
        Apartment apartment = (Apartment)obj;
        return id == apartment.id &&
        		number == apartment.number &&
        		status.equals(apartment.status) &&
        		Math.abs(price - apartment.price) < 0.01;
	}
	
	@Override
	public int hashCode() {
		final int mul = 44;
		int result = 4;
		result = mul*result + id;
		result = mul*result + number;
		result = mul*result + (int) price;
		result = mul*result + ((status == null) ? 0 : status.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder("Product{");
        result.append("id=").append(id);
        result.append(", status=").append(status);
        result.append(", number=").append(number);
        result.append(", price=").append(price);
        result.append('}');
		return result.toString();
	}
}
