
	if (typeof maf == "undefined") {
	    var maf = {
	    	init:function() {
	    		maf.layout.render();
	    	}
	    };
	    maf.topMenu = {};
	    maf.leftMenu ={};
	}
	
	maf.event = {
		tr_over:  function(obj) {
			if (obj.tagName == "TR") {
				for(var i=0;i< obj.cells.length; i++) {
					var oTd = obj.cells.item(i);
					oTd.setAttribute("oldBackground", oTd.style.backgroundColor);
					oTd.style.backgroundColor = "#ebebeb";
				}
			}
	
		},
		tr_out: function(obj) {
			if (obj.tagName == "TR") {
				for(var i=0;i< obj.cells.length; i++) {
					var oTd = obj.cells.item(i);
					oTd.style.backgroundColor = oTd.getAttribute("oldBackground");
				}
			}
		}
	}
	
	maf.layout = {
		render: function() {
		
			//var obj = document.getElementsByTagName("table");

			var lists = getElementsByClass("list",document,"table");
			
			for (var i = 0; i < lists.length ; i++) {

				if(lists[i].tagName == "TABLE" && lists[i].className=="list") {
					this.makeListTable(lists[i]);
				}
			}
		},
		makeListTable: function(tbl) {
				var enableAlternateRows = false;
				var enableMouseOver = true;
				var rowAlternateClass = '';
				if(tbl.getAttribute("enableAlternateRows")=='true') {
					enableAlternateRows = true;
					rowAlternateClass = tbl.getAttribute("rowAlternateClass")
				}
				if(tbl.getAttribute("enableMouseOver")=='false') {
					enableMouseOver = false;
				}
				if (enableAlternateRows) {
					var body=tbl.tBodies[0];
	
					var rows=body.rows;
					
					for (var i=0; i < rows.length; i++)   {
						this.serListRow(rows[i], i, enableAlternateRows, rowAlternateClass, enableMouseOver);
						this.setLlisRowCellClass(rows[i].cells, i, enableAlternateRows, rowAlternateClass);
					}
				}
				
		},
		serListRow: function(row, rowIndex, enableAlternateRows, rowAlternateClass, enableMouseOver) {
			if(row.tagName=="TR") {
				if(enableMouseOver) {
					addEvent(row, 'mouseover', function(){if(typeof maf != "undefined") {maf.event.tr_over(row);}});
					addEvent(row, 'mouseout', function(){if(typeof maf != "undefined") {maf.event.tr_out(row);}});
				}
				if(rowIndex % 2 && enableAlternateRows ) {
				    	row.className += " " + rowAlternateClass;
				 }
			 }
		},
		 setLlisRowCellClass: function(rowCells, rowIndex){
		    for (var i=0; i < rowCells.length; i++){
		    	if(i == 0) {
			        rowCells[i].className += " first";
			    }
			    if(i == rowCells.length-1) {
			    	rowCells[i].className += " last";
			    }
			    
		    }
		}
	};
    
    maf.alert = function (bundle, key, param) {

            var URL = CPATH + "/message.do?bundle=" + escape(bundle) + "&key=" + escape(key) + "&param=" + param;
            // using prototype Ajax Request
            var rqst = new Ajax.Request(URL, {
                onSuccess: function (xmlHttp) {
                	
                	var serverData = xmlHttp.responseText;
            		var data =  eval('(' + serverData + ')');
                    alert(data.message);  
                },
                onFailure: function (xmlHttp) {
                    alert('fail!!!');
                }
            });

    }
    
    var LangSelector = {
        clickCount:0, 
		over_sel:false,
        loaded:false,
        hideLangList: function(event) {
			if(!LangSelector.over_sel) {
				$('divLangList').setStyle({'visibility':'hidden'});
			}
        },
		showLangList: function(event) {
            if(!LangSelector.loaded) {
                LangSelector.getList();
            }
			$('divLangList').setStyle({'visibility':'visible'});
		},
        overLangSel:function() {
            LangSelector.over_sel = true;
        },
        outLangSel:function() {
            LangSelector.over_sel = false;
        },
        getList:function() {
            var URL = CPATH+'/common.do?cmd=langList';
            new Ajax.Request(URL, {
                        onSuccess: function(transport) {
                            var json = transport.responseText.evalJSON();
                            var html = "";
                            for(var i=0; i < json.length; i++ ) {
                                var d = json[i];
                                html += "&nbsp;<a href='"+CPATH+"/index.jsp?hl=" + d.code + "'>" + d.name + "</a><br>";
                            }
                            $('divLangList').innerHTML = html;
                            LangSelector.loaded=true;
                            var offesetLeftTop = Position.page($('divLangSelector'));
                            var left=offesetLeftTop[0];
                            var top=offesetLeftTop[1] + $('divLangSelector').offsetHeight;
                            $('divLangList').setStyle({'top':top + 'px','left':left + 'px'});
                            
                        },
                        onFailure: function() {
                            alert("fail to load language list!!!");
                        }
                    })
        }
    }
    
    Event.observe(window, 'load', maf.init );