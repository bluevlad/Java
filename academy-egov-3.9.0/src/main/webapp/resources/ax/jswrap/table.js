
function __td__(parent_obj)
{
	this.tag = "td";
}

function td()
{
	__td__.prototype = new container();
	return new __td__();
}

function __tr__()
{
	this.tag = "tr";
	this._parent_Add = this.Add;
	this.Add = Add;

	function Add(obj, attr)
	{
		attr = (typeof(attr) != "undefined") ? attr : null;

		if (obj instanceof __td__)
		{
			this._parent_Add(obj);
		} else {
			_td = new td();
			if (attr != null)
			{
				for(j in attr)
				{
					keyval = attr[j];
					key = keyval[0];
					val = keyval[1];
					_td.SetAttr(key, val);
				}
			}
			_td.Add(obj);
			this._parent_Add(_td);
		}
	}
}

function tr()
{
	__tr__.prototype = new container();
	return new __tr__();
}

function __table__(parent_obj)
{
	this.tag = "table";
	this.head = new Array();
	this.rows = new Array();
	this.colattr = new Array();

	this.SetAttr("cellspacing", "0");
	this.SetAttr("cellpadding", "0");
	this.SetAttr("border", "0");

	this.Add = Add;
	this.toStr = toStr;

	function Add(row)
	{
		//alert(row instanceof __tr__);
		if (row instanceof Array)
		{
			_tr = new tr();
			for(i in row)
			{
				obj = row[i];
				_td = new td();
				_td.Add(obj);
				_tr.Add(_td);
			}
			row = _tr;
		}

		if (typeof(row) == "object")
			this.rows[this.rows.length] = row;
	}

	/*
	function __setattr__()
	{
		if (this.id)
			SetAttr("id", this.id);
		if (this.name)
			SetAttr("name", this.name);
		if (this.clss)
			SetAttr("class", this.clss);
	}
	*/

	function toStr()
	{
		//__setattr__();

		str = "<";
		str += this.tag;
		if (this.attr)
			str += __getattr__(this.attr);
		str += ">";

		for(i in this.rows)
		{
			_tr = this.rows[i];
			str += _tr.toStr();
		}

		str += "</" + this.tag + ">";

		return str;
	}
}

function table()
{
	obj = new container();
	__table__.prototype = obj;
	return new __table__(obj);
}
