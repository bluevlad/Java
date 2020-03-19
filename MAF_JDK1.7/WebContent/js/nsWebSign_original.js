function nsMarquee(id, height, width, scrollAmount, scrollDelay, direction, html)
{
    this.id            = id;
    this.scrollAmount  = scrollAmount ? scrollAmount : 6;
    this.scrollDelay   = scrollDelay ? scrollDelay : 85;
    this.direction     = direction ? direction.toLowerCase() : 'left';  
    this.name          = 'nsMarquee_' + (++nsMarquee._name);
    this.runId         = null;
    this.html          = html;

    this.head           = 0;
    this.tail           = 1;
	this.viewCount		= 0;
    if (typeof(height) == 'number')
    {
        this.height = height;
        this.heightUnit = 'px';
    }
    else if (typeof(height) == 'string')
    {
        this.height = parseInt('0' + height, 10);
        this.heightUnit = height.toLowerCase().replace(/^[0-9]+/, '');
    }
    else
    {
        this.height = 100;
        this.heightUnit = 'px';
    }
    
    if (typeof(width) == 'number')
    {
        this.width = width;
        this.widthUnit = 'px';
    }
    else if (typeof(width) == 'string')
    {
        this.width = parseInt('0' + width, 10);
        this.widthUnit = width.toLowerCase().replace(/^[0-9]+/, '');
    }
    else
    {
        this.width = 100;
        this.widthUnit = 'px';
    }

    // nsMarquee UI events
    this.onmouseover   = null;
    this.onmouseout    = null;
    this.onclick       = null;
    // nsMarquee state events
    this.onstart       = null;
    this.onbounce      = null;

    var markup = '';
    markup += '<div id="' + this.id + 'container" name="' + this.id + 'container" style="overflow: hidden; ';
    if (this.heightUnit != '%')
    {
      markup += 'height: ' + this.height + this.heightUnit + '; ';
    }
    if (this.widthUnit != '%')
    {
      markup += 'width: ' + this.width + this.widthUnit + '; ';
    }
    markup += '">';
    markup += '<div id="' + this.id + '0" name="' + this.id + '0" style="position:absolute; visibility: hidden; border: 0px solid red;';
    markup += '" ';
    markup += 'onmouseover="nsMarqueeDispatchUIEvent(event, \'' + this.name + '\', \'mouseover\')" ';
    markup += 'onmouseout="nsMarqueeDispatchUIEvent(event, \'' + this.name + '\', \'mouseout\')" ';
    markup += 'onclick="nsMarqueeDispatchUIEvent(event, \'' + this.name + '\', \'click\')" ';
    markup += '>';
    markup += html;
    markup += '<\/div>';
    markup += '<div id="' + this.id + '1" name="' + this.id + '1" style="position:absolute; visibility: hidden; border: 0px solid blue;';
    markup += '" ';
    markup += 'onmouseover="nsMarqueeDispatchUIEvent(event, \'' + this.name + '\', \'mouseover\')" ';
    markup += 'onmouseout="nsMarqueeDispatchUIEvent(event, \'' + this.name + '\', \'mouseout\')" ';
    markup += 'onclick="nsMarqueeDispatchUIEvent(event, \'' + this.name + '\', \'click\')" ';
    markup += '>';
    markup += html;
    markup += '<\/div>';
    markup += '<\/div>';
    
   

    document.write(markup);

    window[this.name] = this;
}

nsMarquee._name = -1;

nsMarquee.prototype.start = function ()
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

            if (this.heightUnit == '%')
            {
                this.containerDiv.style.height = 
                            this.containerDiv.parentNode.offsetHeight * 
                            (this.height/100) + 'px';
            }
            if (this.widthUnit == '%')
            {
                this.containerDiv.style.width = 
                            this.containerDiv.parentNode.offsetWidth * 
                            (this.width/100) + 'px';
            }

            this.styleObj[0].position = 'absolute';
            this.styleObj[1].position = 'absolute';
            this.styleObj[0].visibility = 'visible'; 
            this.styleObj[1].visibility = 'visible'; 
        }
        else if (document.all)
        {
            this.containerDiv = document.all[this.id + 'container'];
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
        switch (this.direction)
        {
            case 'down':
                this.dirsign = 1;
                this.startAt = this.styleObj[0].top = -1 * this.innerHeight;
                this.stopAt  = this.height;
                this.styleObj[1].top = -2 * this.innerHeight;
                break;

            case 'up':
                this.dirsign = -1;
                this.startAt = this.styleObj[0].top = this.height;
                this.stopAt  = -1 * this.innerHeight;
                this.styleObj[1].top = this.height + this.innerHeight;
                break;

        }
        this.newPosition  = this.startAt;
        this.styleObj[0].visibility = 'visible'; 
        this.styleObj[1].visibility = 'visible'; 
    }

    this.newPosition += this.dirsign * this.scrollAmount;
    
    if ( (this.dirsign == 1  && this.newPosition > this.stopAt) ||
        (this.dirsign == -1 && this.newPosition < this.stopAt) )
    {
		this.viewCount ++;
        // fire start when position is a start
        if (this.onstart)
        {
            this.onstart();
        }
		// ±è»óÁØ Ãß°¡ 
		if(this.viewCount >2)
		{
			location.reload();
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
        this.newPosition = this.newPosition + this.innerHeight;
    }
  
    switch(this.direction)
    {
        case 'up': 
            this.styleObj[this.head].top = this.newPosition;
            this.styleObj[this.tail].top = this.newPosition + this.innerHeight;
            break;
        case 'down':
            this.styleObj[this.head].top = this.newPosition;
            this.styleObj[this.tail].top = this.newPosition - this.innerHeight;
            break;
    }
    //window.status =  this.direction+','+this.newPosition;

    this.runId = setTimeout(this.name + '.start()', this.scrollDelay);
};

nsMarquee.prototype.stop = function ()
{
    if (this.runId)
        clearTimeout(this.runId);
    
    this.runId = null;
};


nsMarquee.prototype.setInnerHTML = function (html)
{
  if (typeof(this.div[0].innerHTML) != 'string')
  {
    return;
  }

  var running = false;
  if (this.runId)
  {
    running = true;
    this.stop();
    this.dirsign = null;
  }
  this.html = this.div[0].innerHTML = html;
  this.html = this.div[1].innerHTML = html;
  if (running)
  {
    this.start();
  }
};

nsMarquee.prototype._getInnerSize = function (idx, propName)
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

nsMarqueeDispatchUIEvent = function (event, marqueeName, eventName)
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

