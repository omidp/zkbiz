ZK 3/6.5/7 Persian Date Picker
===


Contributors
===

I'd like to thank [Shahab NasrollahZadeh Mehrabadi](shahab.mehrabadi@gmail.com) for his contribution on ZK 6.5/7

[Omid Pourhadi](http://omidbiz.com)

Usage
===

+ copy PersianZkDatePicker.jar (zk3)/ persiandatebox (zk6.5/7) in your project library (CLASSPATH)

+ Then use it

```
<?page title="ZK Persian Date Picker"?>
<window position="center,top" title="ZK Persian Date Picker!!" border="normal"
width="750px" height="750px">
<label value="You are using zk version : ${desktop.webApp.version}"/>
<PersianDateBox id="pdt" />
<button label="Click To Pick A Date">
<attribute name="onClick">
lblDate.setValue(pdt.getValue().toString());
lblSolarDate.setValue(pdt.getValueAsSolar());
</attribute>
</button>
<label id="lblDate" ></label>
<label id="lblSolarDate"></label>
</window>
```

for more information about methods please visit java docs. source code also is available in jar files.

