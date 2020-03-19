var submenu_ids = new Array('01','02','03','04','05');
var cur_menu_id;
var menu = new Array(2);
menu[0] = new Array(submenu_ids.length);
menu[1] = new Array(submenu_ids.length);

var menu_time_out = -1;

function clearMenu()
{
	for(i=0; i<menu[0].length; i++)
		document.images['menu_'+submenu_ids[i]].src = menu[0][i].src;
}

function msout()
{
	//dispSubMenu();
	autoClearSubMenu();
	//dispSearch('');
	//clearMenu();
}

function msover(enable_id)
{
	clearMenu();

	for(i= 0; i<submenu_ids.length; i++)
	{
		if(submenu_ids[i] == enable_id && menu[1][i].src != "")
		{
			document.images['menu_'+submenu_ids[i]].src = menu[1][i].src;
			break;
		}
	}
}


function dispSubMenu(enable_id)
{
	for(i=0; i < submenu_ids.length; i++)
	{
		var obj = document.getElementById('submenu_'+submenu_ids[i]);
		if(obj)
			obj.style.display = "none";
	}

	if(enable_id != 'undefined')
	{
		var obj = document.getElementById('submenu_'+enable_id);

		if(obj)
		{
			obj.style.display = "";
			menu_time_out = 4; //10 sec.
			dispSearch('none');
		}
	}
}


function autoClearSubMenu()
{
	if(menu_time_out-- >= 0)
	{
		if(menu_time_out == 0){
			dispSubMenu();
			dispSearch('');
		}
	}
	setTimeout('autoClearSubMenu()', 300);	//1000
}
autoClearSubMenu();

function KMM_swapImgRestore() { //v3.0
  var i,x,a=document.KMM_sr; 
  for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function KMM_preloadImages() { //v3.0
  var d=document; 
  if(d.images){ if(!d.KMM_p) d.KMM_p=new Array();
  var i,j=d.KMM_p.length,a=KMM_preloadImages.arguments; 
  for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.KMM_p[j]=new Image; d.KMM_p[j++].src=a[i];}
  }
}

function KMM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; 
  if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; 
	n=n.substring(0,p);
  }
  if(!(x=d[n])&&d.all) x=d.all[n]; 
  for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=KMM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function KMM_swapImage() { //v3.0
  var i,j=0,x,a=KMM_swapImage.arguments; 
  document.KMM_sr=new Array; 
  for(i=0;i<(a.length-2);i+=3) {
    if ((x=KMM_findObj(a[i]))!=null){document.KMM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
  }
}

function KMM_showHideLayers() { //v6.0
  var i,p,v,obj,args=KMM_showHideLayers.arguments;
  for (i=0; i<(args.length-2); i+=3) {
  	if ((obj=KMM_findObj(args[i]))!=null) { 
		v=args[i+2];
    	if (obj.style) { 
			obj=obj.style; 
			if(v=='show') {
				obj.visibility='visible'; 
				obj.display='';			
			} else {
				//obj.visibility='hide'; 
				obj.display='none';
			}
		}
	}
  }
  
}

//-->