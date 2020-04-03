
function __bookmark__()
{
	title = new image();
	title.SetAttr("id", "bookmark_title");
	title.src = def_title_bookmark;

	add = new image();
	add.src = def_btn_bookmark_add_normal;
	add.SetAttr("id", "bookmark_add");
	add.SetAttr("style", "cursor: hand;");
	add.SetAttr("onclick", "javascript:BookmarkAdd();");
	add.SetAttr("onmouseover", "javascript:button_onmouse(this, '" + def_btn_bookmark_add_over + "');");
	add.SetAttr("onmouseout", "javascript:button_onmouse(this, null);");
	add.SetAttr("onmousedown", "javascript:button_onmouse(this, '" + def_btn_bookmark_add_down + "');");
	add.SetAttr("onmouseup", "javascript:button_onmouse(this, '" + def_btn_bookmark_add_over + "');");


	del = new image();
	del.src = def_btn_bookmark_del_normal;
	del.SetAttr("id", "bookmark_del");
	del.SetAttr("style", "cursor: hand;");
	del.SetAttr("onclick", "javascript:BookmarkDelete();");
	del.SetAttr("onmouseover", "javascript:button_onmouse(this, '" + def_btn_bookmark_del_over + "');");
	del.SetAttr("onmouseout", "javascript:button_onmouse(this, null);");
	del.SetAttr("onmousedown", "javascript:button_onmouse(this, '" + def_btn_bookmark_del_down + "');");
	del.SetAttr("onmouseup", "javascript:button_onmouse(this, '" + def_btn_bookmark_del_over + "');");


	mov = new image();
	mov.src = def_btn_bookmark_mov_normal;
	mov.SetAttr("id", "bookmark_mov");
	mov.SetAttr("style", "cursor: hand;");
	mov.SetAttr("onclick", "javascript:BookmarkMove();");
	mov.SetAttr("onmouseover", "javascript:button_onmouse(this, '" + def_btn_bookmark_mov_over + "');");
	mov.SetAttr("onmouseout", "javascript:button_onmouse(this, null);");
	mov.SetAttr("onmousedown", "javascript:button_onmouse(this, '" + def_btn_bookmark_mov_down + "');");
	mov.SetAttr("onmouseup", "javascript:button_onmouse(this, '" + def_btn_bookmark_mov_over + "');");

	tm = new combobox();
	tm.SetAttr("id", "bookmark_time");
	opt1 = new option();
	opt1.SetAttr("value", "");
	opt1.Add("ÁöÁ¡(0)");
	tm.Add(opt1);

	tab = new table();
	tab.SetAttr("id", "bookmark_tab");
	tab.Add(Array(title, tm, mov, add, del));

	layout = new container();
	layout.SetAttr("id", "bookmark");
	layout.Add(tab);

	if (!config_bookmark_enabled)
		layout.SetAttr("style", "display:none;");

	this.Add(layout);
}

function bookmark()
{
	__bookmark__.prototype = new container();
	return new __bookmark__();
}
