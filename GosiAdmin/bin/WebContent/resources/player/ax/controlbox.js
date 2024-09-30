
function __controlbox__()
{
	this.SetAttr("id", "controlbox");
	this.build = build;
	this.setwidth = setwidth;

	function build()
	{
		pb = new playbuttons();
		pb.SetAttr("style", "position: absolute;");
		sv = new soundvolume();
		sv.SetAttr("style", "position: absolute;");
		progress = new progressbar();
		progress.SetAttr("style", "position: absolute;");
		speed = new speedcontrol();
		speed.SetAttr("style", "position: absolute;");
		repeat = new repeatcontrol();
		repeat.SetAttr("style", "position: absolute;");
		time = new playtime();
		time.SetAttr("style", "position: absolute;");
		stat = new playstatus();
		stat.SetAttr("style", "position: absolute;");
		full = new fullscreen();
		full.SetAttr("style", "position: absolute;");
		bm = new bookmark();
		bm.SetAttr("style", "position: absolute;");
		

		tab = new table();
		tab.SetAttr("id", "controlbox_tab");

		center = new container();
		center.SetAttr("id", "controlbox_center");
		center.Add(progress);
		center.Add(pb);
		center.Add(stat);
		center.Add(repeat);
		center.Add(full);
		center.Add(bm);
		//center.Add(time);
		//center.Add(sv);

		right_center = new container();
		right_center.Add(time);
		right_center.Add(sv);
		right_center.Add(speed);
		//right_center.Add(full);

		left = new container();
		left.SetAttr("id", "controlbox_left");

		right = new container();
		right.SetAttr("id", "controlbox_right");

		row = new tr();
		row.Add(left, [["class", "left"]]);
		row.Add(center, [["class", "center"], ["style", "width: " + def_left_center_width + "px"]]);
		row.Add(right_center, [["class", "right_center"], ["style", "width: " + def_right_center_width + "px"]]);
		row.Add(right, [["class", "right"]]);
		tab.Add(row);

		this.Add(tab);
	}

	function setwidth(width)
	{
		def_full_width = width;
		def_left_center_width = def_full_width - def_left_edge_width - def_right_edge_width - def_right_center_width;
		def_progressbar_size = def_left_center_width - 60;
	}
}

function controlbox()
{
	__controlbox__.prototype =new container();
	return new __controlbox__();
}


