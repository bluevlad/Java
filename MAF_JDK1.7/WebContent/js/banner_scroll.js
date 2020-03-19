var isDOM = (document.getElementById ? true : false);
var isIE4 = ((document.all && !isDOM) ? true : false);
var isNS4 = (document.layers ? true : false);

// HOVER

// 좌측 메뉴 배너
// start floating 배너
function floatingBanner(objname, posX, posY)
{
	var obj = getObject(objname);
	obj.style.top = document.body.scrollTop + posY;
	obj.style.visibility = "visible";
//	obj.style.setExpression("left", posX, "javascript");
	obj.style.left=posX;
	window.setInterval("calYBanner('" + objname +"', " + posY + ")", 10);
}

function calXBanner(posX)
{
	var edge = (document.body.clientWidth);
	if( edge < 0 ) edge = 0;
	return posX + edge;
}

function calYBanner(objname, posY)
{
	//alert(objName);
	var obj = getObject(objname);
	var yBannerPos, yScrollTopPos;
	var yLimitPos;
	var interval;
	var yNewPos;
	var yNewTop;
	var dHeight = posY;

	yScrollTopPos = parseInt(document.body.scrollTop, 10);
	yBannerPos = parseInt(obj.style.top, 10);
	if (document.body.scrollTop < dHeight) {
		yLimitPos = dHeight;
	}
	else {
		yLimitPos = document.body.scrollTop + 15;
	}

	if (yBannerPos != yLimitPos) {
		yNewPos = Math.ceil(Math.abs(yLimitPos - yBannerPos) / 20);

		if (yLimitPos < yBannerPos) {
			yNewPos = -yNewPos;
		}

		yNewTop = parseInt(obj.style.top, 10) + yNewPos;
		if (yNewTop > document.body.scrollHeight) {
			obj.style.top = document.body.scrollHeight;
		}
		else {
			obj.style.top = yNewTop;
		}
	  }
}
// end floating 배너

