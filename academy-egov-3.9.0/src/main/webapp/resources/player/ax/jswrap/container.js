
function __container__()
{
	this.things = new Array();

	this.Add = Add;
	this.toStr = toStr;
	this.writeTo = writeTo;

	this.tag = "div";

	function Add(obj)
	{
		this.things[this.things.length] = obj;
	}

	/*
	function __setattr__()
	{
		if (this.id)
			this.SetAttr("id", this.id);
		if (this.name)
			this.SetAttr("name", this.name);
		if (this.clss)
			this.SetAttr("class", this.clss);
	}
	*/

	function toStr()
	{
	//	__setattr__();

		str = "<";
		str += this.tag;
		if (this.attr)
			str += __getattr__(this.attr);
		str += ">";

//		alert("attr:" + __getattr__(this.attr));

		for(i in this.things)
		{
			obj = this.things[i];
			
			switch(typeof(obj))
			{
				case "object":
					str += obj.toStr();
					break;
				case "string":
					str += obj;
					break;
				case "number":
					str += obj;
					break;
				default:
					str += "unknown";
					break;

			}
		}
		str += "</" + this.tag + ">";

		return str;
	}

	function writeTo(target)
	{
		obj = document.getElementById(target);
		obj.innerHTML = this.toStr();
	}
}

function container()
{
	__container__.prototype = new html_object();
	return new __container__();
}
