function __getattr__(p_attr)
{
	res = "";
	for(i = 0; i < p_attr.length; i++)
	{
		res += " " + p_attr[i][0] + "=\"" + p_attr[i][1] + "\"";
	}
	return res;
}

function html_object()
{
	this.tag = "object";
	this.id = null;
	this.name = null;
	this.clss = null;
	this.attr = new Array();

	this.SetId = SetId;
	this.SetName = SetName;
	this.SetClass = SetClass;
	this.SetAttr = SetAttr;

	this.GetAttr = GetAttr;
	this.GetClass = GetClass;
	this.GetId = GetId;
	this.GetName = GetName;

	this.toStr = toStr;
	this.writeTo = writeTo;

	this.__setattr__ = __setattr__;

	function SetId(p_id)
	{
		this.id = p_id;
		this.SetAttr("id", p_id);
	}
	function SetName(p_name)
	{
		this.name = p_name;
		this.SetAttr("name", p_name);
	}
	function SetClass(p_class)
	{
		this.clss = p_class;
		this.SetAttr("class", p_class);
	}

	function GetId()
	{
		return this.id;
	}
	function GetName()
	{
		return this.name;
	}
	function GetClass()
	{
		return this.clss;
	}

	function SetAttr(key, value)
	{
		this.attr[this.attr.length] = new Array(key, value);
	}

	function GetAttr()
	{
		if (this.attr)
			return __getattr__(this.attr);
		return "";
	}

	function __setattr__()
	{
		if (this.id)
			this.SetAttr("id", this.id);
		if (this.name)
			this.SetAttr("name", this.name);
		if (this.clss)
			this.SetAttr("class", this.clss);
	}

	function toStr()
	{
		_attr = "";
		//__setattr__();
		if (this.attr.length)
			_attr = " " + this.GetAttr();

		str = "<";
		str += this.tag;
		str += _attr;
		str += " />";

		return str;
	}
	function writeTo(target)
	{
		obj = document.getElementById(target);
		obj.innerHTML = this.toStr();
	}
}
