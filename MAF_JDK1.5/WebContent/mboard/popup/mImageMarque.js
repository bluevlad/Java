
function mImageMarque(id, width, height, scrollAmount, scrollDelay, direction) {
    this.id            = id;
    this.scrollAmount  = scrollAmount ? scrollAmount : 6;
    this.scrollDelay   = scrollDelay ? scrollDelay : 85;  // 85
    this.direction     = direction ? direction.toLowerCase() : 'left';  // left, right;
    this.name          = 'mImageMarque_' + (++mImageMarque._name);
	this.runId         = null;
	
    // nsMarquee UI events
    this.onmouseover   = null;
    this.onmouseout    = null;
    this.onclick       = null;
    // nsMarquee state events
    this.onstart       = null;
    this.onbounce      = null;	
    this.height = 100;
    this.heightUnit = 'px';
    this.width = 100;
    this.widthUnit = 'px';	
    window[this.name] = this;
};

mImageMarque._name = -1;
mImageMarque.prototype.start = function ()
{
    this.stop();
    if (!this.dirsign)
    {
	    if (document.getElementById)
        {
            this.containerDiv = document.getElementById(this.id + 'container');
            this.div          = new Array(1);
            this.div[0]       = document.getElementById(this.id);
            this.styleObj     = new Array(1);
            this.styleObj[0]  = this.div[0].style;

            if (this.heightUnit == '%')
            {
                this.containerDiv.style.height = 
                            this.containerDiv.parentNode.offsetHeight * (this.height/100) + 'px';
            }
            if (this.widthUnit == '%')
            {
                this.containerDiv.style.width = 
                            this.containerDiv.parentNode.offsetWidth * (this.width/100) + 'px';
            }

            this.styleObj[0].position = 'absolute';
            this.styleObj[0].visibility = 'visible'; 
        }
        this.innerWidth = this._getInnerSize(0, 'width');
        switch (this.direction)
        {
            case 'down':
                this.dirsign = 1;
                this.startAt = this.styleObj[0].top = -1 * this.innerHeight;
                this.stopAt  = this.height;
                break;

            case 'left':
                this.dirsign = -1;
                this.startAt = this.styleObj[0].left = this.width;
                //this.stopAt  = -1 * this.innerHeight;
                this.stopAt  = 10;
                break;

        }
        this.newPosition  = this.startAt;
        this.styleObj[0].visibility = 'visible'; 
	}
	this.newPosition += this.dirsign * this.scrollAmount;
	switch(this.direction)
    {
        case 'left': 
            this.styleObj[0].left = this.newPosition - this.width;
            break;
        case 'right':
            this.styleObj[0].top = this.newPosition - this.innerHeight;
            break;
    };
	if ( (this.dirsign == 1  && this.newPosition > this.stopAt) ||
        (this.dirsign == -1 && this.newPosition < this.stopAt) )
    {
		this.styleObj[this.tail]
        // fire start when position is a start
        if (this.onstart)
        {
            this.onstart();
        }
		
		this.runId = setTimeout(this.name + '.start()', this.scrollDelay);
    } else {
		this.runId = setTimeout(this.name + '.start()', this.scrollDelay);
	}
};

mImageMarque.prototype.stop = function ()
{
    if (this.runId)
        clearTimeout(this.runId);
    
    this.runId = null;
};

mImageMarqueDispatchUIEvent = function (event, marqueeName, eventName)
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
            if (mImageMarque[eventAttr])
                return mImageMarque['on' + eventName](event);
    }

    return false;
};

mImageMarque.prototype._getInnerSize = function (idx, propName)
{
    var val = 0;

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

  return val;

};