function __image__()
{
	this.tag = "image"
	this.src = "";

	this._toStr = this.toStr;
	this.toStr = toStr;

	function toStr()
	{
		this.SetAttr("src", this.src);

		return this._toStr();
	}
}

function image()
{
	__image__.prototype = new html_object();
	return new __image__();
}
