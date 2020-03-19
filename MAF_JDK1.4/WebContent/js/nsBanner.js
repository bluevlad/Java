function nsBannerMarquee(id, width, height , scrollAmount, scrollDelay, aBanner, waitTime )
{
    this.id            = id;
    this.scrollAmount  = scrollAmount ? scrollAmount : 6;
    this.scrollDelay   = scrollDelay ? scrollDelay : 85;
	this.waitTime	   = waitTime ? waitTime : this.scrollDelay * 30;
    this.direction     = 'up';  
    this.name          = 'nsBannerMarquee_' + (++nsBannerMarquee._name);
    this.runId         = null;
    //this.html          = html;

    this.head           = 0;
    this.tail           = 1;
	
    this.height = height;
    this.width = width;

    // nsMarquee UI events
    this.onmouseover   = null;
    this.onmouseout    = null;
    this.onclick       = null;
    // nsMarquee state events
    this.onstart       = null;
    this.onbounce      = null;
	// 상준 추가
	
	this.aBanner = aBanner;

    var markup = '';
    markup += '<div id="' + this.id + 'container" name="' + this.id + 'container" style="overflow: hidden; ';
    markup += 'height: ' + this.height +  'px; ';
    markup += 'width: ' + this.width +  'px; ';
    markup += '">';
    markup += '<div id="' + this.id + '0" name="' + this.id + '0" style="position:absolute; visibility: hidden; border: 0px solid red;';
    markup += '" ';
    markup += 'onmouseover="nsBannerMarqueeDispatchUIEvent(event, \'' + this.name + '\', \'mouseover\')" ';
    markup += 'onmouseout="nsBannerMarqueeDispatchUIEvent(event, \'' + this.name + '\', \'mouseout\')" ';
    markup += 'onclick="nsBannerMarqueeDispatchUIEvent(event, \'' + this.name + '\', \'click\')" ';
    markup += '>';
    markup += this.aBanner[0];
    markup += '<\/div>';
    markup += '<div id="' + this.id + '1" name="' + this.id + '1" style="position:absolute; visibility: hidden; border: 0px solid blue;';
    markup += '" ';
    markup += 'onmouseover="nsBannerMarqueeDispatchUIEvent(event, \'' + this.name + '\', \'mouseover\')" ';
    markup += 'onmouseout="nsBannerMarqueeDispatchUIEvent(event, \'' + this.name + '\', \'mouseout\')" ';
    markup += 'onclick="nsBannerMarqueeDispatchUIEvent(event, \'' + this.name + '\', \'click\')" ';
    markup += '>';
 	if (this.aBanner.length < 1 ) {
	    markup += this.aBanner[0];
		this.curIndex = 0;
	} else { 
	    markup += this.aBanner[1];
		this.curIndex = 1;
	}
    markup += '<\/div>';
    markup += '<\/div>';
    
   

    document.write(markup);

    window[this.name] = this;
}

nsBannerMarquee._name = -1;

nsBannerMarquee.prototype.setHtml = function ()
{

}

nsBannerMarquee.prototype.start = function ()
{
    this.stop();

    if (!this.dirsign)
    {
        if (document.getElementById)
        {
            this.containerDiv = document.getElementById(this.id + 'container');
            this.div          = new Array(2);
            this.div[0]       = document.getElementById(this.id+'0');
            this.div[1]       = document.getElementById(this.id+'1');
            this.styleObj     = new Array(2);
            this.styleObj[0]  = this.div[0].style;
            this.styleObj[1]  = this.div[1].style;


            this.styleObj[0].position = 'absolute';
            this.styleObj[1].position = 'absolute';
            this.styleObj[0].visibility = 'visible'; 
            this.styleObj[1].visibility = 'visible'; 
        }
        else if (document.all)
        {
            this.containerDiv = document.getElementById(this.id + 'container');//document.all[this.id + 'container'];
            this.div          = new Array(2);
            this.div[0]          = document.all[this.id+'0'];
            this.div[1]          = document.all[this.id+'1'];
            this.styleObj     = new Array(2);
            this.styleObj[0]  = this.div[0].style;
            this.styleObj[1]  = this.div[1].style;
            if (this.heightUnit == '%')
            {
                this.containerDiv.style.height = this.containerDiv.parentElement.offsetHeight * (this.height/100) + 'px';
            }
            if (this.widthUnit == '%')
            {
                this.containerDiv.style.width = this.containerDiv.parentElement.offsetWidth * (this.width/100) + 'px';
            }

            this.styleObj[0].position = 'absolute';
            this.styleObj[1].position = 'absolute';
            this.styleObj[0].visibility = 'visible'; 
            this.styleObj[1].visibility = 'visible'; 
        }

        // Start must not run until the page load event has fired
        // due to Internet Explorer not setting the height and width of 
        // the dynamically written content until then

        this.innerHeight = this._getInnerSize(0, 'height');
        this.dirsign = -1;
        this.startAt = 0; //this.styleObj[0].top = this.height;
        this.stopAt  = -1 * this.innerHeight;
        this.styleObj[1].top = this.height + this.innerHeight;

        this.newPosition  = this.startAt;
        this.styleObj[0].visibility = 'visible'; 
        this.styleObj[1].visibility = 'visible'; 
    }

    this.newPosition += this.dirsign * this.scrollAmount;
    
    if ( (this.dirsign == -1 && this.newPosition < this.stopAt) )
    {
        // fire start when position is a start
        if (this.onstart)
        {
            this.onstart();
        }
		
        if(this.head == 0)
        {
            this.head = 1;
            this.tail = 0;
        }
        else
        {
            this.head = 0;
            this.tail = 1;
        }
		this.div[this.tail].innerHTML = this.aBanner[(++this.curIndex) % (this.aBanner.length)];
        this.newPosition = this.newPosition + this.innerHeight;
		this.runId = setTimeout(this.name + '.start()', this.waitTime);
	} else {
  
	   this.styleObj[this.head].top = this.newPosition;
	   this.styleObj[this.tail].top = this.newPosition + this.innerHeight;
	   var dv = Math.sin(Math.abs(this.newPosition / this.innerHeight * Math.PI))*2+.5;
	   // window.status = "요소 = "+this.newPosition + ", " + dv.toPrecision (2);
	   this.runId = setTimeout(this.name + '.start()', this.scrollDelay * dv);
    }
};

nsBannerMarquee.prototype.stop = function ()
{
    if (this.runId)
        clearTimeout(this.runId);
    
    this.runId = null;
};


nsBannerMarquee.prototype._getInnerSize = function (idx, propName)
{
    var val = 0;

    if (typeof(this.styleObj[idx][propName]) == 'number')
    {
        // opera
        // bug in Opera 6 width/offsetWidth. Use clientWidth
        if (propName == 'width' && typeof(this.div[idx].clientWidth) == 'number')
            val = this.div[idx].clientWidth;
        else
        val =  this.styleObj[idx][propName];
    }
    else
    {
        //mozilla and IE
        switch (propName)
        {
            case 'height':
                if (typeof(this.div[idx].offsetHeight) == 'number')
                    val =  this.div[idx].offsetHeight;
                if (val == 0)
                    val =  this.height;
                break;
            case 'width':
                if (typeof(this.div[idx].offsetWidth) == 'number')
                    val = this.div[idx].offsetWidth;
                if (val == 0)
                    val =  this.width;
                break;
        }
    }

  return val;

};

nsBannerMarqueeDispatchUIEvent = function (event, marqueeName, eventName)
{
    var marquee = window[marqueeName];
    var eventAttr = 'on' + eventName;
    if (!marquee)
    {
        return false;
    }

    if (!event && window.event)
    {
        event = window.event;
    }

    switch (eventName)
    {
        case 'mouseover':
        case 'mouseout':
        case 'click':
            if (marquee[eventAttr])
                return marquee['on' + eventName](event);
    }

    return false;
};

