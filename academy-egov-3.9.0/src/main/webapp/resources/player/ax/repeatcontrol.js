
function __repeatcontrol__()
{
	img = new image();
	img.src = def_btn_repeat_normal;
	img.SetAttr("onclick", "javascript:SectionRepeatEnable();");
	img.SetAttr("style", "cursor:hand;");
	img.SetAttr("id", "button_repeat");
	img.SetAttr("onmouseover", "javascript:repeatcontrol_onmouse(this, '" + def_btn_repeat_over + "');");
	img.SetAttr("onmouseout", "javascript:repeatcontrol_onmouse(this, null);");
	img.SetAttr("onmousedown", "javascript:repeatcontrol_onmouse(this, '" + def_btn_repeat_down + "');");
	img.SetAttr("onmouseup", "javascript:repeatcontrol_onmouse(this, null);");


	layout = new container();
	layout.SetAttr("id", "repeatcontrol");
	layout.Add(img);

	this.Add(layout);
}

function repeatcontrol()
{
	__repeatcontrol__.prototype = new container();
	return new __repeatcontrol__();
}
