
function __playstatus__()
{
	img = new image();
	img.SetAttr("id", "playstatus_text");
	img.src = def_txt_stop;

	layout = new container();
	layout.SetAttr("id", "playstatus");
	layout.Add(img);

	this.Add(layout);
}

function playstatus()
{
	__playstatus__.prototype = new container();
	return new __playstatus__();
}
