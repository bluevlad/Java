<!--
 
function fusionChart(type,  width, height) {
	this.config = {
		basedir	: CPATH+"/FusionCharts"
	}
	this.width	= (width)?width:320;
	this.height	= (height)?height:240;
	this.dataSrc	= null;
	this.path_2	= "/Charts/";
	this.path_3	= "/FC_3_0/";
	this.bgcolor	= "#ffffff";
	this.id		= null;
	this.align	= null;
	this.movie	= null;
	this.xmlData = null;	
	this.xmlDataUrl = null;	
	this.valid	= true;
	switch (type) {
		case "3DColumn":
			this.setChartFlash(this.path_2 + "FC_2_3_Column3D.swf");
			break;
		case "2DColumn":
			this.setChartFlash(this.path_2 + "FC_2_3_Column2D.swf");
			break;
		case "2DBar":
			this.setChartFlash(this.path_2 + "FC_2_3_Bar2D.swf");
			break;
		case "MS3DColumn":
			this.setChartFlash(this.path_2 + "FC_2_3_MSColumn3D.swf");
			this.setChartFlash(this.path_3 + "MSColumn3D.swf");
			break;
		case "MS2DColumn":
			this.setChartFlash(this.path_2 + "FC_2_3_MSColumn2D.swf");
			break;			
		case "MS3DStackColumnLineDY":
			this.setChartFlash(this.path_2 + "FC_2_3_StckdColumn_MSLine_DY_2D.swf");
			this.setChartFlash(this.path_3 + "StackedColumn3DLineDY.swf");		
			break;		
		case "MSStackColumnLineDY":
			this.setChartFlash(this.path_3 + "MSStackedColumn2DLineDY.swf");
			break;	
		case "MSScatter":
			this.setChartFlash(this.path_2 + "FC_2_3_MSScatter.swf");
			this.setChartFlash(this.path_3 + "Scatter.swf");
			break;		
		case "MSLine":
			this.setChartFlash(this.path_2 + "FC_2_3_MSLine.swf");
			break;			
		case "Radar":
			this.setChartFlash(this.path_2 + "FC_2_3_Radar.swf");
			break;			
		case "SSGrid":
			this.setChartFlash(this.path_2 + "FC_2_3_SSGrid.swf");
			break;	
					
		default:
			this.valid = false;
			// alert("invalie chart type");
	}

}
fusionChart.prototype.setXmlData = function(xmlData) {
	this.dataSrc = 'xml';
	this.xmlData = xmlData;
}
fusionChart.prototype.setXmlDataUrl = function(xmlDataUrl) {
	this.dataSrc = 'url';
	this.xmlDataUrl = xmlDataUrl;
}
fusionChart.prototype.setChartFlash = function(flashFileName) {
	this.movie = this.config.basedir + flashFileName;
} 

fusionChart.prototype.getDataUrl = function() {
	var str = "";
	if(this.dataSrc == 'url') {
		str += "&dataURL=" + this.xmlDataUrl;
	} else {
		str += "&dataXML=" + this.xmlData;
	}

	return str;
} 
fusionChart.prototype.getMovie = function() {
	var str=this.movie;
	str += "?chartWidth=" + this.width;
	str += "&chartHeight=" + this.height;
	return str;
} 
fusionChart.prototype.draw = function() {
	document.write(this.toString());
}
fusionChart.prototype.toString = function () {
	var str = "";
	if(this.valid) {
		str += "<OBJECT classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' ";
		str +=" codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0' ";
		str += this.addAttribute("WIDTH", this.width);
		str += this.addAttribute("HEIGHT", this.height);
		str += this.addAttribute("id", this.id);
		str += this.addAttribute("ALIGN", this.align);
		str += " >";
		str += "<PARAM NAME='movie' VALUE='" + this.getMovie() + "'>\n";
		str += "<PARAM NAME='quality' VALUE='high'>\n";
		str	+= "<PARAM NAME='bgcolor' VALUE='" + this.bgcolor + "'>\n";
		str	+= "<PARAM NAME='FlashVars' VALUE='" + this.getDataUrl() + "'>\n";
		str	+= "<PARAM NAME='wmode' VALUE='transparent'>\n";			
		str += "<EMBED  quality='high' ";
		str += this.addAttribute("WIDTH", this.width);
		str += this.addAttribute("HEIGHT", this.height);
		str += this.addAttribute("NAME", this.id);
		str += this.addAttribute("ALIGN", this.align);
		str += this.addAttribute("src", this.getMovie());
		str += this.addAttribute("bgcolor", this.bgcolor);
		str += this.addAttribute("FlashVars", this.getDataUrl());
		str += this.addAttribute("wmode", "transparent");		
		str	+= "\n TYPE='application/x-shockwave-flash' PLUGINSPAGE='http://www.macromedia.com/go/getflashplayer'></EMBED>";
		str	+= "\n</OBJECT>";
	} else {
		str += "$$$ invalid chart $$$";
	}
	return str;
}

fusionChart.prototype.addAttribute = function(name, value) {
	var str = "";
	if(name && value ) {
		str += name + "= '" + value + "' ";
	}
	
	return str;
}
//-->