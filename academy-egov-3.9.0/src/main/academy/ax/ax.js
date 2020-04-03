/* ax.js : a function for ms ie activex rollup patch */
function __ws__(id){
  document.write(id.text);
  id.id="";
}

function include(destination) {
	document.write("<script src='" + destination + "'>" + "</script>");
}

function KeyPress()
{
	switch(window.event.keyCode)
	{
		case 37: // left
			play_back(1);
			break;
		case 38: // up
			VolumeAdjust(+(config_volume_scale));
			break;
		case 39: // right
			play_back(2);
			break;
		case 40: // down
			VolumeAdjust(-(config_volume_scale));
			break;
	}
}

function axinit(home, activex_name)
{
	def_home_path = home;
	include(home + "/config.js");
	include(home + "/define.js");
	include(home + "/player.js");
	include(home + "/jswrap/html_object.js");
	include(home + "/jswrap/table.js");
	include(home + "/jswrap/container.js");
	include(home + "/jswrap/image.js");
	include(home + "/jswrap/anchor.js");
	include(home + "/jswrap/combobox.js");
	include(home + "/jswrap/pagebar.js");

	include(home + "/playbuttons.js");
	include(home + "/soundvolume.js");
	include(home + "/progressbar.js");
	include(home + "/speedcontrol.js");
	include(home + "/repeatcontrol.js");
	include(home + "/playtime.js");
	include(home + "/playstatus.js");
	include(home + "/fullscreen.js");
	include(home + "/bookmark.js");
	include(home + "/controlbox.js");

	// css
	document.write("<link href=\"" + home + "/style.css" + "\" rel=\"stylesheet\" type=\"text/css\">");

	// event for activex
	document.write("<script for=\"" + activex_name + "\" event=\"OpenStateChange(state);\">MediaPlayer_DoOpenStateChange(state);</script>");
	document.write("<script for=\"" + activex_name + "\" event=\"PlayStateChange(state);\">MediaPlayer_DoPlayStateChange(state);</script>");
	document.write("<script for=\"" + activex_name + "\" event=\"Buffering(buffering);\">MediaPlayer_DoBuffering(buffering);</script>");
	document.write("<script for=\"" + activex_name + "\" event=\"PositionChange(oldPos, newPos);\">MediaPlayer_DoPositionChange(oldPos, newPos);</script>");
	document.write("<script for=\"" + activex_name + "\" event=\"RateChange(rate);\">AxPlayer_RateChange(rate);</script>");
	document.write("<script for=\"" + activex_name + "\" event=\"VolumeChange(volume, mute);\">AxPlayer_VolumeChange(volume, mute);</script>");

	document.onkeydown = KeyPress;
}
