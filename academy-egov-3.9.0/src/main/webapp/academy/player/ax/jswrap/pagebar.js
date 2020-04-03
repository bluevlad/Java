
function __pagebar__()
{
	this.currentPage = 1;
	this.totalCount = 0;
	this.rowsPerPage = 20;

	this.totalPages = 0;
	this.pagesPerThread = 3;

	this.currentThread = 1;
	this.totalThreads = 0;

	this.func = null;
	this.addscript = "";

	this.Left = Left;
	this.Right = Right;
	this.Pages = Pages;

	this._toStr = this.toStr;
	this.toStr = toStr;

	function toStr()
	{
		this.totalPages = Math.ceil(this.totalCount / this.rowsPerPage);
		this.currentThread = parseInt((this.currentPage - 1) / this.pagesPerThread);
		this.totalThreads = Math.ceil(this.totalPages / this.pagesPerThread);

		this.Left();
		this.Pages();
		this.Right();

		return this._toStr();
	}


	function Left()
	{
		if (this.currentThread <= 0)
			return;

		page = (this.currentThread - 1) * this.pagesPerThread + 1;
		if (page <= 1) page = 1;

		img = new image();
		img.SetAttr("align", "absmiddle");
		img.src = "images/icon_arrow_prev.gif";
		a = new anchor();
		a.SetAttr("onclick", "javascript:" + this.func + "(" + page + ");" + this.addscript);
		a.Add(img);
		a.Add("&nbsp;");
		this.Add(a);
	}

	function Right()
	{
		if (this.currentThread >= (this.totalThreads - 1))
			return;

		page = (this.currentThread+1) * this.pagesPerThread + 1;
		if (page > this.totalPages)
			page = this.totalPages;

		img = new image();
		img.SetAttr("align", "absmiddle");
		img.src = "images/icon_arrow_next.gif";

		a = new anchor();
		a.SetAttr("onclick", "javascript:" + this.func + "(" + page + ");" + this.addscript);
		a.Add("&nbsp;");
		a.Add(img);
		this.Add(a);
	}

	function Pages()
	{
		start = this.currentThread * this.pagesPerThread + 1;
		end = (this.currentThread + 1) * this.pagesPerThread;
		if (end > this.totalPages)
			end = this.totalPages;

		for(i =start; i <= end; i++)
		{
			a = new anchor();
			a.SetAttr("onclick", "javascript:" + this.func + "(" + i + ");" + this.addscript);
			a.Add("<strong>&nbsp;" + i + "&nbsp;</strong>");
			if (i == this.currentPage)
				a.SetAttr("style", "color:#ffffff;");
			this.Add(a);
		}
	}
}

function pagebar()
{
	__pagebar__.prototype = new container();
	return new __pagebar__();
}
