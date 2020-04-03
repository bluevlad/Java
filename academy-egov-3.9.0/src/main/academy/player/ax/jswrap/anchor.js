
function __anchor__()
{
	this.href = "#";
	this.tag = "a";
	
	this._toStr = this.toStr;
	this.toStr = toStr;

	function toStr()
	{
		this.SetAttr("href", this.href);
		return this._toStr();
	}
}

function anchor()
{
	__anchor__.prototype = new container();
	return new __anchor__();
}
