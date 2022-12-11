package by.BSUIR.WT.Lab4.entity;

public class UserInformation implements Identifiable{
	
	private int id;
	private String name;
	private String phone;
	
	public UserInformation() {}
	
	public UserInformation(int id, String name, String phone) {
		this.id = id;
		this.name = name;
		this.phone = phone;
	}
	
	@Override
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhine() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
		
        UserInformation userInformation = (UserInformation)obj;
        return id == userInformation.id &&
        		name.equals(userInformation.name) &&
        		phone.equals(userInformation.phone);
	}
	
	@Override
	public int hashCode() {
		final int mul = 44;
		int result = 4;
        result = mul * result +  id;
        result = mul * result + ((name == null) ? 0 : name.hashCode());
        result = mul * result +  ((phone==null) ? 0: phone.hashCode());
        
        return result;
	}
	
	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder("User{");
        result.append("id=").append(id);
        result.append(", name='").append(name);
        result.append(", phone=").append(phone);
        result.append('}');
		return result.toString();
	}
}
