package pd;

import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.omidbiz.zk.persiandatebox.PersianDatebox;



public class EditController extends SelectorComposer<Component> {

	@Wire
    private Window win;
	
	@Wire
	private PersianDatebox createdPersianDatebox;
	
	@Wire
	private PersianDatebox updatedPersianDatebox;
	
	@Wire
    private Datebox createdDatebox;
	
	private Date currentDate = new Date();
	
	@Wire
	Textbox textBox;

	InventoryItem inventoryItem = new InventoryItem(new Date());
	
	

	public Date getCurrentDate() {
		return currentDate;
	}

	public InventoryItem getInventoryItem() {
		return inventoryItem;
	}

	@Listen("onChange = #createdDatebox")
	public void changeCreatedDate() {
		Date createdDate = createdDatebox.getValue();
		inventoryItem.setCreatedDate(createdDate);
		System.out.println(createdDate);
	}
	
//	@Listen("onChange = #textBox")
//	public void changetextBox() {
//		System.out.println(textBox.getValue());
//	}
	
	@Listen("onChange = #createdPersianDatebox")
	public void createdPersianDatebox() {
		String createdDate = createdPersianDatebox.getText();
//		inventoryItem.setPersianCreatedDatebox(persianCreatedDatebox);
		System.out.println(createdDate);
//		System.out.println(createdPersianDatebox.getValue());
	}
	
	@Listen("onBoxClick = #createdPersianDatebox")
	public void createdPersianDateboxFoo() {
		String createdDate = createdPersianDatebox.getText();
//		inventoryItem.setPersianCreatedDatebox(persianCreatedDatebox);
		System.out.println("onBoxClick : "+createdDate);
//		System.out.println(createdPersianDatebox.getValue());
	}
	
	@Listen("onDateChange = #createdPersianDatebox")
	public void createdPersianDateboxonDateChange() {
		String createdDate = createdPersianDatebox.getText();
//		inventoryItem.setPersianCreatedDatebox(persianCreatedDatebox);
		System.out.println("onDateChange : "+createdDate);
//		System.out.println(createdPersianDatebox.getValue());
	}

	@Listen("onClick = #submitButton")
	public void submit() {

//		System.out.println(createdDatebox.getValue());
		System.out.println(textBox.getValue());
//		System.out.println(createdPersianDatebox.getValue());
		System.out.println("text : " + createdPersianDatebox.getText());
		System.out.println("value : " + createdPersianDatebox.getValue());
//		System.out.println(createdPersianDatebox.getText());
//		System.out.println(createdPersianDatebox.getValue());
//		System.out.println(createdDatebox.getValueAsSolar());
//		showNotify("Saved", win);
	}

	private void showNotify(String msg, Component ref) {
		Clients.showNotification(msg, "info", ref, "end_center", 2000);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		createdDatebox.setValue(new Date());
		createdPersianDatebox.setValue(new Date());
//		createdPersianDatebox.setText("1397/01/01");
	}

}
