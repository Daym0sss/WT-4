package by.BSUIR.WT.Lab4.entity;

public class Role implements Identifiable{

	private int id;
	private String role;
	
	public Role() {}
	
	public Role(int id, String role) {
		this.id = id;
		this.role = role;
	}
	
	@Override
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String name) {
		this.role = name;
	}
	
	@Override
	public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        
        Role role = (Role) obj;
        return id == role.id &&
        		role.equals(role.role);
	}
	
	@Override
	public int hashCode() {
		final int mul = 44;
		int result = 4;
		result = mul * result + id;
		result = mul * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder("Role{");
		result.append("id=").append(id);
		result.append("role=").append(role);
		result.append('}');
		return result.toString();
	}
	
}
