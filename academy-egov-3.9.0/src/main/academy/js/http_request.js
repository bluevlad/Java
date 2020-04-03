var httpRequest = null;

var ajax={};
ajax.xhr={};
ajax.xhr.Request = function(url, params, callback, method){
	this.url = url;
	this.params = params;
	this.callback = callback;
	this.method = method;
	this.send();
};
ajax.xhr.Request.prototype = {
		getXMLHttpRequest:function(){
			if(window.ActiveXObject) {
				try {
					return new ActiveXObject("Msxml2.XMLHTTP");
				} catch(e) {
					try {
						return new ActiveObject("Microsoft.XMLHTTP");
					} catch(e1) {
						return null;
					}
				}
			} else if (window.XMLHttpRequest) {
				return new XMLHttpRequest();
			} else {
				return null;
			}
		},
		send:function(){
			this.req = this.getXMLHttpRequest();
			//httpRequest = getXMLHttpRequest();

			var httpMethod = this.method ? this.method : 'GET';
			if(httpMethod != 'GET' && httpMethod != 'POST') {
				httpMethod = 'GET';
			}

			var httpParams = (this.params == null || this.params == '') ? null : this.params;
			var httpUrl = this.url;

			if(httpMethod == 'GET' && httpParams != null) {
				httpUrl = httpUrl + "?" + httpParams;
			}

			this.req.open(httpMethod, httpUrl, true);
			this.req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			var request =this;
			this.req.onreadystatechange = function(){
				request.onStateChange.call(request);
			}
			this.req.send(httpMethod == 'POST' ? httpParams : null);
//			if(callback) {
//				httpRequest.onreadystatechange = callback;
//			}
//			httpRequest.send(httpMethod == 'POST' ? httpParams : null);
		},
		onStateChange:function(){
			this.callback(this.req);
		}
};

function getXMLHttpRequest() {
	if(window.ActiveXObject) {
		try {
			return new ActiveXObject("Msxml2.XMLHTTP");
		} catch(e) {
			try {
				return new ActiveObject("Microsoft.XMLHTTP");
			} catch(e1) {
				return null;
			}
		}
	} else if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	} else {
		return null;
	}
}

function sendRequest(url, params, callback, method) {
	
	httpRequest = getXMLHttpRequest();

	var httpMethod = method ? method : 'GET';
	if(httpMethod != 'GET' && httpMethod != 'POST') {
		httpMethod = 'GET';
	}

	var httpParams = (params == null || params == '') ? null : params;
	var httpUrl = url;

	if(httpMethod == 'GET' && httpParams != null) {
		httpUrl = httpUrl + "?" + httpParams;
	}

	httpRequest.open(httpMethod, httpUrl, true);
	httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	if(callback) {
		httpRequest.onreadystatechange = callback;
	}
	httpRequest.send(httpMethod == 'POST' ? httpParams : null);
}