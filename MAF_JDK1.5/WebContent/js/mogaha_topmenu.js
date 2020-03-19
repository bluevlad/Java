<!--
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


function KMM_TopMenu_Show(mid, obj) {
    
	KMM_showHideLayers('submenu_M01','','hide', 'submenu_M02','','hide', 'submenu_M03','','hide', 'submenu_M04','','hide');
		if (mid=="M01") {
			KMM_swapImage('top_menu_M01','','/ehrd/images/mogaha/common/tmenu_01_on.gif',1);
			KMM_showHideLayers('submenu_M01','','show');
		} else if (mid == "M02") {
			KMM_swapImage('top_menu_M02','','/ehrd/images/mogaha/common/tmenu_02_on.gif',1);
			KMM_showHideLayers('submenu_M02','','show');		
		} else if (mid == "M03") {
			KMM_swapImage('top_menu_M03','','/ehrd/images/mogaha/common/tmenu_03_on.gif',1);
			KMM_showHideLayers('submenu_M03','','show');		
		} else if (mid == "M04") {
			KMM_swapImage('top_menu_M04','','/ehrd/images/mogaha/common/tmenu_04_on.gif.gif',1);
			KMM_showHideLayers('submenu_M04','','show');		
		}
        
    if(obj) {
        odiv = document.getElementById('submenu_' + mid);
        if(odiv) {
            var lastObj = document.getElementById("top_menu_M04");
            var xmax = element_left(lastObj)+lastObj.width;
            var x = element_left(obj);
            var y = element_top(obj) + obj.height;
            //alert(odiv.offsetWidth + "," + x + "," + xmax);
            if((odiv.offsetWidth + x) > xmax ) {
                odiv.style.left = xmax-(odiv.offsetWidth);
            } else {
                odiv.style.left = x;
            }
            odiv.style.top = y;
        }
     }
}

function KMM_TopMenu_Hide() {
		KMM_swapImgRestore();
		KMM_showHideLayers('submenu_M01','','hide', 'submenu_M02','','hide', 'submenu_M03','','hide', 
                            'submenu_M04','','hide' );
	}
//-->

