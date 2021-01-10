/**
 *
 * Base naming rule:
 * The stuff start with "_" means private , end with "_" means protect ,
 * others mean public.
 *
 * All the member field should be private.
 *
 * Life cycle: (It's very important to know when we bind the event)
 * A widget will do this by order :
 * 1. $init
 * 2. set attributes (setters)
 * 3. rendering mold (@see mold/persiandatebox.js )
 * 4. call bind_ to bind the event to dom .
 *
 * this.deskop will be assigned after super bind_ is called,
 * so we use it to determine whether we need to update view
 * manually in setter or not.
 * If this.desktop exist , means it's after mold rendering.
 *
 */
persiandatebox.PersianDatebox = zk.$extends(zul.Widget, {
	_text:'', //default value for text attribute
	_value:'',
	_btnVisible:true,
	
	/**
	 * Don't use array/object as a member field, it's a restriction for ZK object,
	 * it will work like a static , share with all the same Widget class instance.
	 *
	 * if you really need this , assign it in bind_ method to prevent any trouble.
	 *
	 * TODO:check array or object , must be one of them ...I forgot. -_- by Tony
	 */
	
	$define: {
		/**
		 * The member in $define means that it has its own setter/getter.
		 * (It's a coding sugar.)
		 *
		 * If you don't get this ,
		 * you could see the comment below for another way to do this.
		 *
		 * It's more clear.
		 *
		 */
		
		name: function (name) {
			var inp = this.getInputNode();
			if (inp) //check if bind
				inp.name = name;
		}
	},
	setValue: function (value, fromServer) {
		console.log(value, fromServer);
			this._value = value;
			this._text = value;
			var inp = this.getInputNode();
			if (inp) //check if bind
			{	
				inp.value = this.coerceToString_(value);
				value = this.coerceToString_(value);
			}
	},
	//value object set from server(smartUpdate, renderProperites)
	set_value: function (value, fromServer) {
		this.setValue(this.unmarshall_(value), fromServer);
	},
	/** Returns the value in the String format.
	 * @return String
	 */
	getValue: function () {
		return this._value;
	},
	/**
	 * If you don't like the way in $define ,
	 * you could do the setter/getter by yourself here.
	 *
	 * Like the example below, they are the same as we mentioned in $define section.
	 */
	getText: function () {
		return this._text;
	},
	/** Sets the text representing the value in the given format.
	 * @param String txt the text
	 * @since 5.0.5
	 */
	setText: function (txt) {
		console.log(txt);
		this._text = txt;
	},
	getInputNode: _zkf = function () {
		return this.$n('real') || this.$n();
	},
	getTextNode: _zkf,
	domAttrs_: function (no) {
		var attr = this.$supers('domAttrs_', arguments);
		if (!no || !no.text)
			attr += this.textAttrs_();
		return attr;
	},
	/** Attributes for the text control.
	 * Called automatically by [[#domAttrs_]] unless {text:true}
	 * is specified
	 * @return String
	 */
	textAttrs_: function () {
		var html = '', v;
			html += ' value="' + this.getText() + '"';
			html += ' type="text"';
		v = this._name;
		if (v) html += ' name="' + v + '"';
		var s = jq.filterTextStyle(this.domStyle_({width: true, height: true, top: true, left: true}));
		if (s) html += ' style="' + s + '"';

		return html;
	},
	_onChanging: function (timeout) {
		this.$class._onChanging.call(this, timeout);
	},
	_areaText: function () {
		return zUtl.encodeXML(this.coerceToString_(this._value));
	},
	coerceFromString_: function (value) {
		return value;
	},
	/** Coerces the value passed to {@link #setValue}.
	 *
	 * <p>Default: convert null to an empty string.
	 *
	 * <p>Deriving note:<br>
	 * If you want to store the value in other type, say BigDecimal,
	 * you have to override {@link #coerceToString_} and {@link #coerceFromString_}
	 * to convert between a string and your targeting type.
	 * @param Object value the value that will be coerced to a string
	 * @return String
	 */
	coerceToString_: function (value) {
		return value || '';
	},
	/** Returns shall be update or not
	 * @param zk.Widget focus
	 */
	shallUpdate_: function (focus) {
		return !focus || !zUtl.isAncestor(this, focus);
	},
	marshall_: function (val) {
		return val;
	},
	unmarshall_: function (val) {
		return val;
	},
	_equalValue: function (a, b) {
		return a == b || this.marshall_(a) == this.marshall_(b);
	},
	_removeValue: function(){
		value = '';
		this.setValue(value, false);
		this.fire('onDateChange', {persianDateValue: value, rawValue: value, value: value});
	},
	
	bind_: function () {
		/**
		 * For widget lifecycle , the super bind_ should be called
		 * as FIRST STATEMENT in the function.
		 * DONT'T forget to call supers in bind_ , or you will get error.
		 */
		this.$supers(persiandatebox.PersianDatebox,'bind_', arguments);
	
		//A example for domListen_ , REMEMBER to do domUnlisten in unbind_.
		//this.domListen_(this.$n("cave"), "onClick", "_doItemsClick");
		var imgBtn = this.$n('btn');		
		this.domListen_(imgBtn, "onClick", "_removeValue");
		var n = this.getInputNode();
		this.domListen_(n, "onChange");
		this.domListen_(n, "onBlur");
		
	},
	/*
		A example for domListen_ listener.
	*/
	/*
	_doItemsClick: function (evt) {
		alert("item click event fired");
	},
	*/
	unbind_: function () {
	
		// A example for domUnlisten_ , should be paired with bind_
		// this.domUnlisten_(this.$n("cave"), "onClick", "_doItemsClick");
		this.domUnlisten_(this.$n(this.uuid+"-btn"), "onClick", "_removeValue");
		
		var n = this.getInputNode();
		this.domUnlisten_(n, "onChange");
		this.domUnlisten_(n, "onBlur");
		/*
		* For widget lifecycle , the super unbind_ should be called
		* as LAST STATEMENT in the function.
		*/
		this.$supers(persiandatebox.PersianDatebox,'unbind_', arguments);
	},
	_doBlur: function (evt) {
		console.log('_doBlur');
		//ZK-2757, fire onChange when native drag'n' drop in different browsers
		var wgt = this;
		var inp = this.getInputNode();
		var value= inp.value;
		//in IE, current focus changes after onInput event
		this.fire('onDateChange', {persianDateValue: value, rawValue: value, value: value});
	},
	_doChange: function (evt) {
		console.log('_doChange');
		//ZK-2757, fire onChange when native drag'n' drop in different browsers
		var wgt = this;
		var inp = this.getInputNode();
		var value= inp.value;
		//in IE, current focus changes after onInput event
		this.fire('onDateChange', {persianDateValue: value, rawValue: value, value: value});
	},
	/*
		widget event, more detail 
		please refer to http://books.zkoss.org/wiki/ZK%20Client-side%20Reference/Notifications
	 */
	doClick_: function (evt) {
		this.$super('doClick_', evt, true);//the super doClick_ should be called
		console.log('doClick_');
		//var inp = this.getInputNode();
		//var value= inp.value;
		//this.fire('onBoxClick', {persianDateValue: value, rawValue: value, value: value});
	}
});