package pd;

import java.util.Date;

public class InventoryItem {
	
	
	private Date createdDate;
	
	private Date persianCreatedDatebox;
	
	
	
	

	public InventoryItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InventoryItem(Date createdDate) {
		super();
		this.createdDate = createdDate;
	}

	public Date getPersianCreatedDatebox() {
		return persianCreatedDatebox;
	}

	public void setPersianCreatedDatebox(Date persianCreatedDatebox) {
		this.persianCreatedDatebox = persianCreatedDatebox;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	
}
