
function __playtime__()
{
	layout = new container();
	layout.SetAttr("id", "playtime");
	layout.Add("00:00 / 00:00");

	this.Add(layout);
}

function playtime()
{
	__playtime__.prototype = new container();
	return new __playtime__();
}
