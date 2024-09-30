
function __soundvolume__()
{
	imgSpeaker = new image();
	imgSpeaker.src = def_btn_speaker_normal;
	imgSpeaker.SetAttr("id", "button_speaker");
	imgSpeaker.SetAttr("onclick", "javascript:SoundVolumeMute();");
	imgSpeaker.SetAttr("style", "position:absolute; cursor: hand;");

	imgVolumeStick = new image();
	imgVolumeStick.src = def_btn_volumeball_normal;
	imgVolumeStick.SetAttr("id", "volume_adjuster");
	imgVolumeStick.SetAttr("onmousedown", "javascript:button_onmouse(this, '" + def_btn_volumeball_down + "'); VolumeBar_onmousedown();");
	imgVolumeStick.SetAttr("onmouseover", "javascript:button_onmouse(this, '" + def_btn_volumeball_over + "');");
	imgVolumeStick.SetAttr("onmouseout", "javascript:button_onmouse(this, '" + def_btn_volumeball_normal + "');");
	imgVolumeStick.SetAttr("onmouseup", "javascript:button_onmouse(this, '" + def_btn_volumeball_over + "');");


	imgVolumeStick.SetAttr("style", "position:absolute;");

	volumeHandle = new container();
	volumeHandle.SetAttr("id", "volumepath");
	volumeHandle.SetAttr("style", "position:absolute;");
	volumeHandle.Add(imgVolumeStick);

	volumeTrack = new container();
	volumeTrack.SetAttr("id", "volume_track");
	volumeTrack.SetAttr("style", "position:absolute;");

	currentVolume = new container();
	currentVolume.SetAttr("id", "volume_current");
	currentVolume.SetAttr("style", "position:absolute;");

	/*
	tab = new table();
	tab.SetAttr("id", "soundvolume_tab");
	row = new tr();
	row.Add(imgSpeaker, [["class", "td1"]]);
	row.Add(volumeHandle, [["class", "td2"]]);
	tab.Add(row);
	*/

	layout = new container();
	layout.SetAttr("id", "soundvolume");
	layout.Add(volumeTrack);
	layout.Add(currentVolume);
	layout.Add(imgSpeaker);
	layout.Add(volumeHandle);

	this.Add(layout);
}

function soundvolume()
{
	__soundvolume__.prototype = new container();
	return new __soundvolume__();
}
