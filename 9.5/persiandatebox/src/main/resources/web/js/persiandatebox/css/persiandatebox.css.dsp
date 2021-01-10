<%--
	Here you could do any styling job you want , all CSS stuff.
--%>
<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>

.z-persiandatebox{
 
}

@font-face {
	font-family: 'Yekan';
	src: url(${c:encodeURL('~./fonts/Yekan.eot')}), url(${c:encodeURL('~./fonts/Yekan.woff')}), 
	url(${c:encodeURL('~./fonts/Yekan.ttf')}), url(${c:encodeURL('~./fonts/Yekan.otf')}); 
	font-weight: normal;
	font-style: normal;
}
.calendar {
  position: relative;
  display: none;
  font-size: 14px;
  cursor: default;
  background: Window;
  color: WindowText;
  font-family: 'Yekan';
}

.calendar table {
  font-size: 14px;
  cursor: default;
  box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.26);
  color: WindowText;
  font-family: 'Yekan';
  
}

/* Header part -- contains navigation buttons and day names. */

.calendar .button { /* "<<", "<", ">", ">>" buttons have this class */
  text-align: center;
  padding: 1px;
  border: 1px solid;
  border-color: ButtonHighlight ButtonShadow ButtonShadow ButtonHighlight;
  background: ButtonFace;
}

.calendar .nav {
  background: ButtonFace url(menuarrow.gif) no-repeat 100% 100%;
}

.calendar thead .title { /* This holds the current "month, year" */
  font-weight: normal;
  padding: 1px;
  color: CaptionText;
  background: ButtonFace;
  text-align: center;
  color: #1c90b1;
  border: 1px solid;
  border-color: ButtonHighlight ButtonShadow ButtonShadow ButtonHighlight;
}

.calendar thead .headrow { /* Row <TR> containing navigation buttons */
}

.calendar thead .daynames { /* Row <TR> containing the day names */
}

.calendar thead .name { /* Cells <TD> containing the day names */
  border-bottom: 1px solid ButtonShadow;
  padding: 2px;
  text-align: center;
  background: ButtonFace;
  color: ButtonText;
}

.calendar thead .weekend { /* How a weekend day name shows in header */
  color: #f00;
}

.calendar thead .hilite { /* How do the buttons in header appear when hover */
  border: 2px solid;
  padding: 0px;
  border-color: ButtonHighlight ButtonShadow ButtonShadow ButtonHighlight;
}

.calendar thead .active { /* Active (pressed) buttons in header */
  border-width: 1px;
  padding: 2px 0px 0px 2px;
  border-color: ButtonShadow ButtonHighlight ButtonHighlight ButtonShadow;
}

/* The body part -- contains all the days in month. */
.calendar tbody .daysrow{
background: #f7f7f7;
}
.calendar tbody .day { /* Cells <TD> containing month days dates */
 border-color: #fff #fff #ededed #ededed;
    border-style: solid;
    border-width: 1px;
    padding: 7px 5px;
    text-align: center;
    width: 2em;
}
.calendar tbody .day.othermonth {
  font-size: 80%;
  color: #aaa;
}
.calendar tbody .day.othermonth.oweekend {
  color: #faa;
}

.calendar table .wn {
  padding: 2px 3px 2px 2px;
  border-right: 1px solid ButtonShadow;
  background: ButtonFace;
  color: ButtonText;
}

.calendar tbody .rowhilite td {
  background: #f7f7f7;
  color: #171717;
}

.calendar tbody td.hilite { /* Hovered cells <TD> */
  padding: 7px 5px;
  background: #ccc;
  color: #171717;
}

.calendar tbody td.active { /* Active (pressed) cells <TD> */
  padding: 2px 2px 0px 2px;
  border: 1px solid;
  border-color: ButtonShadow ButtonHighlight ButtonHighlight ButtonShadow;
}

.calendar tbody td.selected { /* Cell showing selected date */
  font-weight: bold;
  border: 1px solid;
  border-color: ButtonShadow ButtonHighlight ButtonHighlight ButtonShadow;
  padding: 2px 2px 0px 2px;
  background: ButtonFace;
  color: #1c90b1;
}

.calendar tbody td.weekend { /* Cells showing weekend days */
  color: #f00;
}

.calendar tbody td.today { /* Cell showing today date */
  font-weight: normal;
  color: #1c90b1;
}

.calendar tbody td.disabled { color: GrayText; }

.calendar tbody .emptycell { /* Empty cells (the best is to hide them) */
  visibility: hidden;
}

.calendar tbody .emptyrow { /* Empty row (some months need less than 6 rows) */
  display: none;
}

/* The footer part -- status bar and "Close" button */

.calendar tfoot .footrow { /* The <TR> in footer (only one right now) */
}

.calendar tfoot .ttip { /* Tooltip (status bar) cell <TD> */
  background: #ededed;
  padding: 1px;
  color: ButtonText;
  text-align: center;
}

.calendar tfoot .hilite { /* Hover style for buttons in footer */
  border-top: 1px solid #fff;
  border-right: 1px solid #000;
  border-bottom: 1px solid #000;
  border-left: 1px solid #fff;
  padding: 1px;
  background: #e4e0d8;
}

.calendar tfoot .active { /* Active (pressed) style for buttons in footer */
  padding: 2px 0px 0px 2px;
  border-top: 1px solid #000;
  border-right: 1px solid #fff;
  border-bottom: 1px solid #fff;
  border-left: 1px solid #000;
}

/* Combo boxes (menus that display months/years for direct selection) */

.calendar .combo {
  position: absolute;
  display: none;
  width: 4em;
  top: 0px;
  left: 0px;
  cursor: default;
  border: 1px solid;
  border-color: ButtonHighlight ButtonShadow ButtonShadow ButtonHighlight;
  background: Menu;
  color: MenuText;
  font-size: 90%;
  padding: 1px;
  z-index: 100;
}

.calendar .combo .label,
.calendar .combo .label-IEfix {
  text-align: center;
  padding: 1px;
}

.calendar .combo .label-IEfix {
  width: 4em;
}

.calendar .combo .active {
  padding: 0px;
  border: 1px solid #000;
}

.calendar .combo .hilite {
  background: Highlight;
  color: HighlightText;
}

.calendar td.time {
  border-top: 1px solid ButtonShadow;
  padding: 1px 0px;
  text-align: center;
  background-color: ButtonFace;
}

.calendar td.time .hour,
.calendar td.time .minute,
.calendar td.time .ampm {
  padding: 0px 3px 0px 4px;
  border: 1px solid #889;
  font-weight: bold;
  background-color: Menu;
}

.calendar td.time .ampm {
  text-align: center;
}

.calendar td.time .colon {
  padding: 0px 2px 0px 3px;
  font-weight: bold;
}

.calendar td.time span.hilite {
  border-color: #000;
  background-color: Highlight;
  color: HighlightText;
}

.calendar td.time span.active {
  border-color: #f00;
  background-color: #000;
  color: #0f0;
}

.z-persiandatebox-inp {
    margin: 0;
    padding: 0;
    padding-right: 2%;
    text-align: right;
    cursor: pointer;
}
.z-persiandatebox-btn {
      position: absolute;
  height: 60%;
  width: auto;
  
  top: 20%;
  cursor: pointer;
}
.persian-dtb-clearfix-div{
    width: 100%;
    position: relative;
}
.persian-dtb-clearfix-div:after {
	content: " "; /* Older browser do not support empty content */
	visibility: hidden;
	display: block;
	height: 0;
	clear: both;
}