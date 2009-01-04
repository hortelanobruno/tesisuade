var req;
var target;
var isIE;


function getReadyStateHandler(req, responseXmlHandler) {
    return function () {
        if (req.readyState == 4) {
            if (req.status == 200) {
                responseXmlHandler(req.responseXML);
            } else {
                alert("HTTP error: "+req.status);
            }
        }
    }
}


function initRequest(url) {
    //if (window.XMLHttpRequest) {
    //   req = new XMLHttpRequest();
    // } else if (window.ActiveXObject) {
    //     isIE = true;
    //     req = new ActiveXObject('Microsoft.XMLHTTP');
    // }
    if(window.ActiveXObject)
    {
        try
        {
            isIE = true;
            req = new ActiveXObject('Microsoft.XMLHTTP');
        }
        catch (e)
        {
            req = false;
        }
    }else{
        try
        {
            req = new XMLHttpRequest();
        }
        catch (e)
        {
            req = false;
        }
    }
    if (!req)
    {
        alert('Error creating the XMLHttpRequest object.');
    }
}

