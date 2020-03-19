//
// MYHOME POPUP ADVER SCRIPT
//
// CREATE DATE : 2003.06.11
//
var 	popunder

popunder = "http://myhome.hanafos.com/share/popup20030520.htm"

var once_per_session = 1;

function get_cookie( Name ) 
{
	var search = Name + "=" ;
	var returnvalue = "";

	if (document.cookie.length > 0) 
	{
		offset = document.cookie.indexOf(search) ;

		if (offset != -1) 
		{ 
			offset += search.length ;
			end = document.cookie.indexOf(";", offset);

			if (end == -1)
				end = document.cookie.length;

		returnvalue = unescape( document.cookie.substring(offset, end) );
		}
	}

	return returnvalue;
}

function loadornot()
{
	if ( get_cookie('popunder') =='' )
	{
		loadpopunder() ;
	}
}

function loadpopunder()
{
	var comp = new RegExp('^http://myhome.hanafos.com/~([^/]+)/'); 
	chkval = comp.test(location.href) ;

	if(chkval == true)
	{
		win2 = window.open(popunder,'','width=350,height=300') ;
		win2.blur() ;
	}	
}

if ( once_per_session == 0 )
	loadpopunder() ;
else
	loadornot() ;

//
// EOF
//
