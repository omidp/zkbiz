/**
* Here's the mold file , a mold means a HTML struct that the widget really presented.
* yep, we build html in Javascript , that make it more clear and powerful.
*/
function (out) {

	//Here you call the "this" means the widget instance. (@see PersianDatebox.js)

	var zcls = this.getZclass(),
		uuid = this.uuid;

	//The this.domAttrs_() means it will prepare some dom attributes,
	//like the pseudo code below
	/*
		class="${zcls} ${this.getSclass()}" id="${uuid}"
	*/
	out.push('<input', this.domAttrs_(), '/>');
	out.push("<img ");	
	out.push("id=\"" + uuid + "-btn" +"\"");
	out.push(" src=\"");
	out.push(zk.ajaxURI('/web/img/remove.png', {au:true}));
	out.push('" class="'+zcls+'-btn"/>');
	out.push('<script id="' + uuid + '_script" type="text/javascript">');
//	if (this._dateType == 'jalali')
//		out.push('$.getScript("'+ zk.ajaxURI('/web/js/lang/calendar-fa.js', {au:true})
//			+'", function(){');
//	else 
//		out.push('$.getScript("'+ zk.ajaxURI('/web/js/lang/calendar-en.js', {au:true})
//				+'", function(){');
	out.push('Calendar.setup({ '
			+ 'inputField: "' + uuid +'", ' 
			+ 'button: "' + uuid + '", ' 
			+ 'ifFormat: "%Y/%m/%d %H:%M", autoShowOnFocus: false, showsTime: true, timeFormat: "24", '
			+ 'dateType: "jalali", weekNumbers: false, '
			+ 'language: "persian", ' 
			+ 'firstDay: "jalali" ' 
			+' });'
			+ '</script>');
	
}