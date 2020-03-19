/**
 * by 행복한고니 (20050316)
 *
 * Homepage : http://www.mygony.com
 */

var ColorPicker2 = function() {};

ColorPicker2.appendSWF = false;
ColorPicker2.show = function(obj, handler) {
	if (ColorPicker2.path == undefined) ColorPicker2.path = "ColorPicker2.swf";
	if (ColorPicker2.align == undefined) ColorPicker2.align = "left";

	ColorPicker2._handler = (handler==undefined)?(ColorPicker2.handler==undefined?new Function(""):ColorPicker2.handler):handler;

	if (ColorPicker2.appendSWF == false) ColorPicker2.DoAppendSWF();

	var X, Y;
	var pos = ColorPicker2.getOffsetPos(obj);
	var W = obj.offsetWidth, H = obj.offsetHeight;
	
	with (ColorPicker2) {
		align = align.toLowerCase();
		layer.style.display = "block";
		X = pos.x, Y = pos.y;

		if (align == "left") {
			Y += H;
		} else if (align == "right") {
			X = pos.x - (layer.offsetWidth - W);
			Y += H;
		} else if (align == "top") {
			X += W;
		} else if (align == "middle") {
			X += W;
			Y -= Math.round((layer.offsetHeight-H)/2);
		} else if (align == "bottom") {
			X += W;
			Y -= layer.offsetHeight - H;
		}

		layer.style.top = Y + 'px';
		layer.style.left = X + 'px';
	}
}

ColorPicker2.hide = function() {
	if (ColorPicker2.appendSWF == false) ColorPicker2.DoAppendSWF();

	ColorPicker2.layer.style.display = "none";
}

ColorPicker2.getOffsetPos = function(obj) {
	if (obj.offsetParent == null) {
		return {"x":obj.offsetLeft, "y":obj.offsetTop};
	} else {
		var pos = ColorPicker2.getOffsetPos(obj.offsetParent);
		return {"x":obj.offsetLeft + pos.x, "y":obj.offsetTop + pos.y};
	}
}

ColorPicker2.DoAppendSWF = function() {
	var DIV = document.createElement("DIV");
	var BODY = document.getElementsByTagName("BODY")[0];
	var W = 200, H = 210;

	DIV.innerHTML = "<object classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" codebase=\"http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0\" width=\""+W+"\" height=\""+H+"\"><param name=\"movie\" value=\""+ColorPicker2.path+"\"><param name=\"quality\" value=\"high\"><embed src=\""+ColorPicker2.path+"\" quality=\"high\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" type=\"application/x-shockwave-flash\" width=\""+W+"\" height=\""+H+"\"></embed></object>";
	DIV.style.position = "absolute";
	DIV.style.display = "none";
	DIV.style.visibility="visible";
	ColorPicker2.layer = BODY.appendChild(DIV);

	ColorPicker2.appendSWF = true;
}