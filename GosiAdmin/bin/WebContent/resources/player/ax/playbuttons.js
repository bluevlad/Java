
function __playbuttons__()
{
	imgPlay = new image();
	imgPlay.src = def_btn_play_normal;
	imgPlay.SetAttr("id", "button_play");
	imgPlay.SetAttr("onclick", "javascript:playerControl('play');");
	imgPlay.SetAttr("style", "cursor:hand;");
	imgPlay.SetAttr("name", "ImgPlay");
	imgPlay.SetAttr("onmouseover", "javascript:this.src = '" + def_btn_play_over + "'");
	imgPlay.SetAttr("onmouseout", "javascript:this.src = '" + def_btn_play_normal + "'");
	imgPlay.SetAttr("onmousedown", "javascript:this.src = '" + def_btn_play_down + "'");
	imgPlay.SetAttr("onmouseup", "javascript:this.src = '" + def_btn_play_over + "'");

	imgStop = new image();
	imgStop.src = def_btn_stop_normal;
	imgStop.SetAttr("id", "button_stop");
	imgStop.SetAttr("onclick", "javascript:playerControl('stop');");
	imgStop.SetAttr("style", "cursor:hand;");
	imgStop.SetAttr("name", "ImgStop");
	imgStop.SetAttr("onmouseover", "javascript:this.src = '" + def_btn_stop_over + "'");
	imgStop.SetAttr("onmouseout", "javascript:this.src = '" + def_btn_stop_normal + "'");
	imgStop.SetAttr("onmousedown", "javascript:this.src = '" + def_btn_stop_down + "'");
	imgStop.SetAttr("onmouseup", "javascript:this.src = '" + def_btn_stop_over + "'");

	imgBackward = new image();
	imgBackward.src = def_btn_prev_normal;
	imgBackward.SetAttr("id", "button_previous");
	imgBackward.SetAttr("onclick", "javascript:play_back(1);");
	imgBackward.SetAttr("style", "cursor:hand;");
	imgBackward.SetAttr("name", "ImgBack");
	imgBackward.SetAttr("onmouseover", "javascript:this.src = '" + def_btn_prev_over + "'");
	imgBackward.SetAttr("onmouseout", "javascript:this.src = '" + def_btn_prev_normal + "'");
	imgBackward.SetAttr("onmousedown", "javascript:this.src = '" + def_btn_prev_down + "'");
	imgBackward.SetAttr("onmouseup", "javascript:this.src = '" + def_btn_prev_over + "'");


	imgForward = new image();
	imgForward.src = def_btn_next_normal;
	imgForward.SetAttr("id", "button_next");
	imgForward.SetAttr("onclick", "javascript:play_back(2);");
	imgForward.SetAttr("style", "cursor:hand;");
	imgForward.SetAttr("name", "ImgNext");
	imgForward.SetAttr("onmouseover", "javascript:this.src = '" + def_btn_next_over + "'");
	imgForward.SetAttr("onmouseout", "javascript:this.src = '" + def_btn_next_normal + "'");
	imgForward.SetAttr("onmousedown", "javascript:this.src = '" + def_btn_next_down + "'");
	imgForward.SetAttr("onmouseup", "javascript:this.src = '" + def_btn_next_over + "'");

	imgPause = new image();
	imgPause.src = def_btn_pause_normal;
	imgPause.SetAttr("id", "button_pause");
	imgPause.SetAttr("onclick", "javascript:playerControl('pause');");
	imgPause.SetAttr("style", "cursor:hand;");
	imgPause.SetAttr("name", "ImgPause");
	imgPause.SetAttr("onmouseover", "javascript:this.src = '" + def_btn_pause_over + "'");
	imgPause.SetAttr("onmouseout", "javascript:this.src = '" + def_btn_pause_normal + "'");
	imgPause.SetAttr("onmousedown", "javascript:this.src = '" + def_btn_pause_down + "'");
	imgPause.SetAttr("onmouseup", "javascript:this.src = '" + def_btn_pause_over + "'");

	tab = new table();
	tab.SetAttr("id", "playbuttons");
	tab.Add([imgPlay, imgPause, imgStop, imgBackward, imgForward]);

	this.Add(tab);
}

function playbuttons()
{
	__playbuttons__.prototype = new container();
	return new __playbuttons__();
}
