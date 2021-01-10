package com.omidbiz.zk.persiandatebox;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.sys.BooleanPropertyAccess;
import org.zkoss.zk.ui.sys.PropertyAccess;
import org.zkoss.zk.ui.sys.StringPropertyAccess;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.impl.InputElement;

public class PersianDatebox extends InputElement {

	public static final String EVENT_BOXCLICK = "onBoxClick";
	public static final String EVENT_BOXCHANGE = "onDateChange";
	public static final String FIELDNAME = "persianDateValue";

	static {
		addClientEvent(PersianDatebox.class, EVENT_BOXCLICK, 0);
		addClientEvent(PersianDatebox.class, EVENT_BOXCHANGE, CE_IMPORTANT | CE_REPEAT_IGNORE);
		
	}

	public PersianDatebox() {
		setValueDirectly("");
	}

	public PersianDatebox(Date value) throws WrongValueException {
		setValue(value);
	}

	/* Here's a simple example for how to implements a member field */

	PersianDateConverter pdc = PersianDateConverter.getInstance();

	private AuxInfo _auxinf;

	private String _text;
	private boolean _btnVisible = true;

	public String getText() {
		return _text;
	}

	public void setText(String text) {
		if (text != null && !Objects.equals(_text, text)) {
		}
		_text = text;
		smartUpdate("text", _text);

	}

	/**
	 * Returns the value. The same as {@link #getText}.
	 * <p>
	 * Default: "".
	 * 
	 * @exception WrongValueException if user entered a wrong value
	 */
	public Date getValue() throws WrongValueException {
		String text = getText();
		if (text == null)
			return null;
		try {
			return pdc.SolarToGregorianAsDate(text);
		} catch (Exception e) {
			throw new WrongValueException(text);
		}
	}

	/**
	 * Sets the value.
	 *
	 * @param value the value; If null, it is considered as empty.
	 * @exception WrongValueException if value is wrong
	 */
	public void setValue(Date value) throws WrongValueException {
		try {
			String gregorianToSolar = pdc.GregorianToSolar(value, true);
			setText(gregorianToSolar);
			setRawValue(gregorianToSolar);
//		setValueDirectly(value);
		} catch (Exception e) {
			throw new WrongValueException("Invalid date value");
		}
	}

	/**
	 * Returns whether the button (on the right of the textbox) is visible.
	 * <p>
	 * Default: true.
	 * 
	 * @since 2.4.1
	 */
	public boolean isButtonVisible() {
		return _btnVisible;
	}

	/**
	 * Sets whether the button (on the right of the textbox) is visible.
	 * 
	 * @since 2.4.1
	 */
	public void setButtonVisible(boolean visible) {
		if (_btnVisible != visible) {
			_btnVisible = visible;
			smartUpdate("buttonVisible", visible);
		}
	}

	// -- super --//
	/**
	 * Coerces the value passed to {@link #setValue}.
	 *
	 * <p>
	 * Default: convert null to an empty string.
	 */
	protected Object coerceFromString(String value) throws WrongValueException {
		return value != null ? value : "";
	}

	/**
	 * Coerces the value passed to {@link #setValue}.
	 *
	 * <p>
	 * Default: convert null to an empty string.
	 */
	protected String coerceToString(Object value) {
		if (value == null)
			return "";
		if (value instanceof Date) {
			String gregorianToSolar = pdc.GregorianToSolar((Date) value, true);
			return gregorianToSolar;
		}
		return (String) value;
	}

	// super//
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer) throws java.io.IOException {
		super.renderProperties(renderer);

//		render(renderer, "text", _text);
	}

	public void service(AuRequest request, boolean everError) {
		final String cmd = request.getCommand();
		final Map<String, Object> data = request.getData();
		System.out.println("cmd " + cmd);
		if (cmd.equals(EVENT_BOXCLICK)) {
			dispatchEvent(request, data);
		}
		if (cmd.equals(EVENT_BOXCHANGE)) {
			dispatchEvent(request, data);
		} else {
			super.service(request, everError);
		}
	}
	
	private void dispatchEvent(AuRequest request, Map<String, Object> data) {
		final String persianDateValue = (String) data.get(FIELDNAME);
		
		
		if(isValidDate(persianDateValue) == false)
		{
			throw new WrongValueException(this, "Invalid Date Value");
		}
		
		final Object clientv = data.get("value");
		final Object oldval = _value;
		final String valstr = coerceToString(persianDateValue);
//		if (persianDateValue != null && !persianDateValue.equals(valstr))
		_value = persianDateValue;
		_text = persianDateValue;
		smartUpdate("_value", marshall(_value));
		smartUpdate("_text", _text);

//		if (Objects.equals(oldval, _value))
		// return; //Bug 1881557: don't post event if not modified

//		Events.postEvent(Event.getEvent(request));
		PersianDateboxEvent persianDateboxEvent = PersianDateboxEvent.getPersianDateboxEvent(request,
				persianDateValue, oldval);
		Events.postEvent(persianDateboxEvent);
	}

	private boolean isValidDate(String persianDateValue) {
		try
		{
			pdc.SolarToGregorian(persianDateValue);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	protected void validate(Object value) throws WrongValueException {
		super.validate(value);
	}

	/**
	 * The default zclass is "z-testbox"
	 */
	public String getZclass() {
		return (this._zclass != null ? this._zclass : "z-testbox");
	}

	// Cloneable//
	public Object clone() {
		final PersianDatebox clone = (PersianDatebox) super.clone();
		if (_auxinf != null)
			clone._auxinf = (AuxInfo) _auxinf.clone();
		return clone;
	}

	// --ComponentCtrl--//
	private static HashMap<String, PropertyAccess> _properties = new HashMap<String, PropertyAccess>(5);

	static {
		_properties.put("value", new PropertyAccess<Date>() {
			public void setValue(Component cmp, Date value) {
				((PersianDatebox) cmp).setValue(value);
			}

			@Override
			public Class<Date> getType() {
				return Date.class;
			}

			public Date getValue(Component cmp) {
				return ((PersianDatebox) cmp).getValue();
			}
		});
		_properties.put("text", new StringPropertyAccess() {
			public void setValue(Component cmp, String value) {
				((PersianDatebox) cmp).setText(value);
			}

			public String getValue(Component cmp) {
				return ((PersianDatebox) cmp).getText();
			}
		});
		_properties.put("buttonVisible", new BooleanPropertyAccess() {
			public void setValue(Component cmp, Boolean value) {
				((PersianDatebox) cmp).setButtonVisible(value);
			}

			public Boolean getValue(Component cmp) {
				return ((PersianDatebox) cmp).isButtonVisible();
			}
		});

	}

	public PropertyAccess getPropertyAccess(String prop) {
		PropertyAccess pa = _properties.get(prop);
		if (pa != null)
			return pa;
		return super.getPropertyAccess(prop);
	}

	private AuxInfo initAuxInfo() {
		if (_auxinf == null)
			_auxinf = new AuxInfo();
		return _auxinf;
	}

	private static class AuxInfo implements java.io.Serializable, Cloneable {
		private String text;

		public Object clone() {
			try {
				return super.clone();
			} catch (CloneNotSupportedException e) {
				throw new InternalError();
			}
		}
	}

}
