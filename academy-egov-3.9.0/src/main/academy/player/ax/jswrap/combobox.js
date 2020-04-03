
function __option__()
{
	this.tag = "option";
}

function option()
{
	__option__.prototype = new container();
	return new __option__();
}

function __combobox__()
{
	this.tag = "select";
}

function combobox()
{
	__combobox__.prototype = new container();
	return new __combobox__();
}
