
function __progressbar__()
{
	imgStick = new image();
	imgStick.src = def_btn_seeker_normal;
	imgStick.SetAttr("id", "progress_seekball");
	imgStick.SetAttr("style", "position: absolute; cursor: hand;");
	imgStick.SetAttr("onmouseover", "javascript:button_onmouse(this, '" + def_btn_seeker_over + "');");
	imgStick.SetAttr("onmouseout", "javascript:button_onmouse(this, '" + def_btn_seeker_normal + "');");
	imgStick.SetAttr("onmousedown", "javascript:button_onmouse(this, '" + def_btn_seeker_down + "');");
	imgStick.SetAttr("onmouseup", "javascript:button_onmouse(this, '" + def_btn_seeker_over + "');");

	repeatBegin = new image();
	repeatBegin.src = def_btn_repeat_begin;
	repeatBegin.SetAttr("style", "position: absolute;");

	repeatEnd = new image();
	repeatEnd.src = def_btn_repeat_end;
	repeatEnd.SetAttr("style", "position: absolute;");

	handlePoint = new container();
	handlePoint.tag = "span";
	handlePoint.SetAttr("id", "progress_seekpath");
	handlePoint.SetAttr("style", "position: absolute;");
	handlePoint.SetAttr("onmousedown", "javascript:progressBar_onmousedown();");
	handlePoint.Add(imgStick);

	repeatBlockBeginHandle = new container();
	repeatBlockBeginHandle.SetAttr("id", "repeat_block_begin");
	repeatBlockBeginHandle.SetAttr("style", "position: absolute;");
	repeatBlockBeginHandle.SetAttr("onmousedown", "javascript:ControlHandle_onmousedown(this);");
	repeatBlockBeginHandle.Add(repeatBegin);

	repeatBlockEndHandle = new container();
	repeatBlockEndHandle.SetAttr("id", "repeat_block_end");
	repeatBlockEndHandle.SetAttr("style", "position: absolute;");
	repeatBlockEndHandle.SetAttr("onmousedown", "javascript:ControlHandle_onmousedown(this);");
	repeatBlockEndHandle.Add(repeatEnd);

	playTrack = new container();
	playTrack.SetAttr("id", "play_track");
	playTrack.SetAttr("style", "position: absolute; width: " + (def_progressbar_size + 6));
	playTrack.SetAttr("onclick", "javascript:ChangeTrackPosition();");

	playedBlock = new container();
	playedBlock.SetAttr("id", "played_block");
	playedBlock.SetAttr("style", "position: absolute;");
	playedBlock.SetAttr("onclick", "javascript:ChangeTrackPosition();");

	repeatBlock = new container();
	repeatBlock.SetAttr("id", "repeat_block");
	repeatBlock.SetAttr("style", "position: absolute;");
	repeatBlock.SetAttr("onclick", "javascript:ChangeTrackPosition();");

	/*
	handle = new container();
	handle.SetAttr("style", "position: absolute;");
	handle.Add(playeTrack);
	handle.Add(playedBlock);
	handle.Add(repeatBlock);
	handle.Add(repeatBlockBeginHandle);
	handle.Add(repeatBlockEndHandle);
	handle.Add(handlePoint);
	*/

	/*
	row = new tr();
	row.Add(handle, [["class", "td1"]]);
	//row.Add(handle, [["class", "td1"], ["style", "background-image: url('" + def_img_bg_seek + "'); background-repeat: repeat; height: 20px; width: 620px;"]]);
	*/

	/*
	tab = new table();
	tab.SetAttr("id", "progressbar_tab");
	tab.Add(row);
	*/

	layout = new container();
	layout.SetAttr("id", "progressbar");
	layout.Add(playTrack);
	layout.Add(playedBlock);
	layout.Add(repeatBlock);
	layout.Add(repeatBlockBeginHandle);
	layout.Add(repeatBlockEndHandle);
	layout.Add(handlePoint);


	this.Add(layout);
}

function progressbar()
{
	__progressbar__.prototype = new container();
	return new __progressbar__();
}
