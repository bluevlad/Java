
/*
* 크로스 브라우저 XMLHttpRequest 구현하기
* Returns a new XMLHttpRequest object, or false if this browser
* doesn't support it
* from : dbguide.net
*/
function newXMLHttpRequest() {
	var xmlreq = false;
	if (window.XMLHttpRequest) {
		// Create XMLHttpRequest object in non-Microsoft browsers
		xmlreq = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		// Create XMLHttpRequest via MS ActiveX
		try {
			// Try to create XMLHttpRequest in later versions
			// of Internet Explorer
			xmlreq = new ActiveXObject("Msxml2.XMLHTTP");

		} catch (e1) {
			// Failed to create required ActiveXObject
			try {
				// Try version supported by older versions
				// of Internet Explorer
				xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e2) {
				// Unable to create an XMLHttpRequest with ActiveX
			}
		}
	}
	
	return xmlreq;
}




/*
* getReadyStateHandler() 함수
* Returns a function that waits for the specified XMLHttpRequest
* to complete, then passes its XML response
* to the given handler function.
* req - The XMLHttpRequest whose state is changing
* responseXmlHandler - Function to pass the XML response to
*/
function getReadyStateHandler(req, responseXmlHandler) {
	
	// Return an anonymous function that listens to the
	// XMLHttpRequest instance
	return function () {
		// If the request's status is "complete"
		if (req.readyState == 4) {
			// Check that a successful server response was received
			if (req.status == 200) {
				// Pass the XML payload of the response to the
				// handler function
				responseXmlHandler(req.responseXML);
			} else {
				// An HTTP problem has occurred
				alert("HTTP error: "+req.status);
			}
		}
	}
}

/*
* Sample 
* Add to Cart XMLHttpRequest 실행
* Adds an item, identified by its product code,
* to the shopping cart
* itemCode - product code of the item to add.
*/
function addToCart(itemCode) {
	
	// Obtain an XMLHttpRequest instance
	var req = newXMLHttpRequest();
	// Set the handler function to receive callback notifications
	// from the request object
	var handlerFunction = getReadyStateHandler(req, updateCart);
	req.onreadystatechange = handlerFunction;
	
	// Open an HTTP POST connection to the shopping cart servlet.
	// Third parameter specifies request is asynchronous.
	req.open("POST", "cart.do", true);
	
	// Specify that the body of the request contains form data
	req.setRequestHeader("Content-Type",
	"application/x-www-form-urlencoded");
	
	// Send form encoded data stating that I want to add the
	// specified item to the cart.
	req.send("action=add&item="+itemCode);
}