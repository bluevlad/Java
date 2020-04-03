
function __fullscreen__()
{
	imgFull = new image();
	imgFull.src = def_btn_full_normal;
	imgFull.SetAttr("id", "button_fullscreen");
	imgFull.SetAttr("onclick", "javascript:playerControl('full');");
	imgFull.SetAttr("style", "cursor:hand;");
	imgFull.SetAttr("onmouseover", "javascript:this.src = '" + def_btn_full_over + "'");
	imgFull.SetAttr("onmouseout", "javascript:this.src = '" + def_btn_full_normal + "'");
	imgFull.SetAttr("onmousedown", "javascript:this.src = '" + def_btn_full_down + "'");
	imgFull.SetAttr("onmouseup", "javascript:this.src = '" + def_btn_full_over + "'");

	layout = new container();
	layout.SetAttr("id", "fullscreen");
	layout.Add(imgFull);

	this.Add(layout);
}

function fullscreen()
{
	__fullscreen__.prototype = new container();
	return new __fullscreen__();
}
