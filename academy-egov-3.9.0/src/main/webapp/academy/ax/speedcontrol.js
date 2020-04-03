
function __speedcontrol__()
{
	imgSpeed_0_6x = new image();
	imgSpeed_0_6x.src = def_btn_x06_normal;
	imgSpeed_0_6x.SetAttr("onclick", "javascript:player_setRate(0.6);");
	imgSpeed_0_6x.SetAttr("style", "cursor: hand;");
	imgSpeed_0_6x.SetAttr("name", "speed0_6");
	imgSpeed_0_6x.SetAttr("onmouseover", "javascript:speedcontrol_onmouse(this, '" + def_btn_x06_over + "');");
	imgSpeed_0_6x.SetAttr("onmouseout", "javascript:speedcontrol_onmouse(this, null);");
	imgSpeed_0_6x.SetAttr("onmousedown", "javascript:speedcontrol_onmouse(this, '" + def_btn_x06_down + "');");
	imgSpeed_0_6x.SetAttr("onmouseup", "javascript:speedcontrol_onmouse(this, '" + def_btn_x06_over + "');");

	imgSpeed_0_8x = new image();
	imgSpeed_0_8x.src = def_btn_x08_normal;
	imgSpeed_0_8x.SetAttr("onclick", "javascript:player_setRate(0.8);");
	imgSpeed_0_8x.SetAttr("style", "cursor: hand;");
	imgSpeed_0_8x.SetAttr("name", "speed0_8");
	imgSpeed_0_8x.SetAttr("onmouseover", "javascript:speedcontrol_onmouse(this, '" + def_btn_x08_over + "');");
	imgSpeed_0_8x.SetAttr("onmouseout", "javascript:speedcontrol_onmouse(this, null);");
	imgSpeed_0_8x.SetAttr("onmousedown", "javascript:speedcontrol_onmouse(this, '" + def_btn_x08_down + "');");
	imgSpeed_0_8x.SetAttr("onmouseup", "javascript:speedcontrol_onmouse(this, '" + def_btn_x08_over + "');");

	imgSpeed_1xe = new image();
	imgSpeed_1xe.src = def_btn_x1e_normal;
	imgSpeed_1xe.SetAttr("onclick", "javascript:player_setRate(1);");
	imgSpeed_1xe.SetAttr("style", "cursor: hand;");
	imgSpeed_1xe.SetAttr("name", "speed1e");
	imgSpeed_1xe.SetAttr("onmouseover", "javascript:speedcontrol_onmouse(this, '" + def_btn_x1e_over + "');");
	imgSpeed_1xe.SetAttr("onmouseout", "javascript:speedcontrol_onmouse(this, null);");
	imgSpeed_1xe.SetAttr("onmousedown", "javascript:speedcontrol_onmouse(this, '" + def_btn_x1e_down + "');");
	imgSpeed_1xe.SetAttr("onmouseup", "javascript:speedcontrol_onmouse(this, '" + def_btn_x1e_over + "');");

	imgSpeed_1x = new image();
	imgSpeed_1x.src = def_btn_x1_normal;
	imgSpeed_1x.SetAttr("onclick", "javascript:player_setRate(1);");
	imgSpeed_1x.SetAttr("style", "cursor: hand;");
	imgSpeed_1x.SetAttr("name", "speed1");
	imgSpeed_1x.SetAttr("onmouseover", "javascript:speedcontrol_onmouse(this, '" + def_btn_x1_over + "');");
	imgSpeed_1x.SetAttr("onmouseout", "javascript:speedcontrol_onmouse(this, null);");
	imgSpeed_1x.SetAttr("onmousedown", "javascript:speedcontrol_onmouse(this, '" + def_btn_x1_down + "');");
	imgSpeed_1x.SetAttr("onmouseup", "javascript:speedcontrol_onmouse(this, '" + def_btn_x1_over + "');");
	
	imgSpeed_1_2x = new image();
	imgSpeed_1_2x.src = def_btn_x12_normal;
	imgSpeed_1_2x.SetAttr("onclick", "javascript:player_setRate(1.2);");
	imgSpeed_1_2x.SetAttr("style", "cursor: hand;");
	imgSpeed_1_2x.SetAttr("name", "speed1_2");
	imgSpeed_1_2x.SetAttr("onmouseover", "javascript:speedcontrol_onmouse(this, '" + def_btn_x12_over + "');");
	imgSpeed_1_2x.SetAttr("onmouseout", "javascript:speedcontrol_onmouse(this, null);");
	imgSpeed_1_2x.SetAttr("onmousedown", "javascript:speedcontrol_onmouse(this, '" + def_btn_x12_down + "');");
	imgSpeed_1_2x.SetAttr("onmouseup", "javascript:speedcontrol_onmouse(this, '" + def_btn_x12_over + "');");


	imgSpeed_1_4x = new image();
	imgSpeed_1_4x.src = def_btn_x14_normal;
	imgSpeed_1_4x.SetAttr("onclick", "javascript:player_setRate(1.4);");
	imgSpeed_1_4x.SetAttr("style", "cursor: hand;");
	imgSpeed_1_4x.SetAttr("name", "speed1_4");
	imgSpeed_1_4x.SetAttr("onmouseover", "javascript:speedcontrol_onmouse(this, '" + def_btn_x14_over + "');");
	imgSpeed_1_4x.SetAttr("onmouseout", "javascript:speedcontrol_onmouse(this, null);");
	imgSpeed_1_4x.SetAttr("onmousedown", "javascript:speedcontrol_onmouse(this, '" + def_btn_x14_down + "');");
	imgSpeed_1_4x.SetAttr("onmouseup", "javascript:speedcontrol_onmouse(this, '" + def_btn_x14_over + "');");

	imgSpeed_1_6x = new image();
	imgSpeed_1_6x.src = def_btn_x16_normal;
	imgSpeed_1_6x.SetAttr("onclick", "javascript:player_setRate(1.6);");
	imgSpeed_1_6x.SetAttr("style", "cursor: hand;");
	imgSpeed_1_6x.SetAttr("name", "speed1_6");
	imgSpeed_1_6x.SetAttr("onmouseover", "javascript:speedcontrol_onmouse(this, '" + def_btn_x16_over + "');");
	imgSpeed_1_6x.SetAttr("onmouseout", "javascript:speedcontrol_onmouse(this, null);");
	imgSpeed_1_6x.SetAttr("onmousedown", "javascript:speedcontrol_onmouse(this, '" + def_btn_x16_down + "');");
	imgSpeed_1_6x.SetAttr("onmouseup", "javascript:speedcontrol_onmouse(this, '" + def_btn_x16_over + "');");

	imgSpeed_1_8x = new image();
	imgSpeed_1_8x.src = def_btn_x18_normal;
	imgSpeed_1_8x.SetAttr("onclick", "javascript:player_setRate(1.8);");
	imgSpeed_1_8x.SetAttr("style", "cursor: hand;");
	imgSpeed_1_8x.SetAttr("name", "speed1_8");
	imgSpeed_1_8x.SetAttr("onmouseover", "javascript:speedcontrol_onmouse(this, '" + def_btn_x18_over + "');");
	imgSpeed_1_8x.SetAttr("onmouseout", "javascript:speedcontrol_onmouse(this, null);");
	imgSpeed_1_8x.SetAttr("onmousedown", "javascript:speedcontrol_onmouse(this, '" + def_btn_x18_down + "');");
	imgSpeed_1_8x.SetAttr("onmouseup", "javascript:speedcontrol_onmouse(this, '" + def_btn_x18_over + "');");

	imgSpeed_2x = new image();
	imgSpeed_2x.src = def_btn_x2_normal;
	imgSpeed_2x.SetAttr("onclick", "javascript:player_setRate(2);");
	imgSpeed_2x.SetAttr("style", "cursor: hand;");
	imgSpeed_2x.SetAttr("name", "speed2");
	imgSpeed_2x.SetAttr("onmouseover", "javascript:speedcontrol_onmouse(this, '" + def_btn_x2_over + "');");
	imgSpeed_2x.SetAttr("onmouseout", "javascript:speedcontrol_onmouse(this, null);");
	imgSpeed_2x.SetAttr("onmousedown", "javascript:speedcontrol_onmouse(this, '" + def_btn_x2_down + "');");
	imgSpeed_2x.SetAttr("onmouseup", "javascript:speedcontrol_onmouse(this, '" + def_btn_x2_over + "');");

	tab = new table();
	if (config_lowspeed_enabled)
		tab.SetAttr("id", "speedcontrol_lowspeed");
	else
		tab.SetAttr("id", "speedcontrol");

	if (config_lowspeed_enabled)
		tab.Add([imgSpeed_0_6x, imgSpeed_0_8x, imgSpeed_1xe, imgSpeed_1_2x, imgSpeed_1_4x, imgSpeed_1_6x, imgSpeed_1_8x, imgSpeed_2x]);
	else
		tab.Add([imgSpeed_1x, imgSpeed_1_2x, imgSpeed_1_4x, imgSpeed_1_6x, imgSpeed_1_8x, imgSpeed_2x]);

	this.Add(tab);
}

function speedcontrol()
{
	__speedcontrol__.prototype = new container();
	return new __speedcontrol__();
}
