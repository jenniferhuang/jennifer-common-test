function toggleTimeStamp() {
	var elements = document.getElementsByClassName('timeStamp');

	var status = (document.getElementById("timeStampSwitch")).src;
	var isOn = status.indexOf('timeStampOn.png')  > 0;
	if (!isOn) {
	    createCookie("timeStamp", "enabled", 365);
	} else {
	    eraseCookie("timeStamp");
	}
	var current = status.indexOf('timeStampOff.png')  > 0 ? 'inline': 'none';
    for(var i = 0; i < elements.length; i++) {
			elements[i].style.display = current;
    }
	(document.getElementById("timeStampSwitch")).src = isOn ? 'timeStampOff.png': 'timeStampOn.png';
}

function toggleErrorMessages() {
	var elements = document.getElementsByClassName('stackTrace');
	var status = (document.getElementById("errorMessageSwitch")).src;
	var isOn = status.indexOf('errorMessageOn.png')  > 0;
	if (!isOn) {
	    createCookie("errorMessage", "enabled", 365);
	} else {
	    eraseCookie("errorMessage");
	}
	var current = status.indexOf('errorMessageOff.png')  > 0 ? 'inline': 'none';
	var regex = new RegExp('failedMessage-', "i");
    for(var i = 0; i < elements.length; i++) {
        if (elements[i].id.match(regex)) {
			elements[i].style.display = current;
        }
    }
	(document.getElementById("errorMessageSwitch")).src = isOn ? 'errorMessageOff.png': 'errorMessageOn.png';
}

function toggleGroupByMethods() {
    var checked = document.getElementById("groupByMethodSwitch").checked;
    var elementsFirst = checked? document.getElementsByClassName('rowUngrouped'): document.getElementsByClassName('rowGrouped');
    var elementsSecond = checked? document.getElementsByClassName('rowGrouped') : document.getElementsByClassName('rowUngrouped');
    for(var i = 0; i < elementsFirst.length; i++) {
        elementsFirst[i].style.display = 'none';
    }
    for(var i = 0; i < elementsSecond.length; i++) {
        if (!elementsSecond[i].classList.contains('sub')) {
            elementsSecond[i].style.display = 'table-row';
        }
    }
}

function toggleElement(elementId, displayStyle)
{
    var current = getStyle(elementId, 'display');
    document.getElementById(elementId).style.display = (current === 'none' ? displayStyle : 'none');
}

function toggleElements(elementClass, displayStyle, toggleId)
{
    var elements = document.getElementsByClassName(elementClass);
    for(var i = 0; i < elements.length; i++) {
        var current = elements[i].style.display;
        elements[i].style.display = (current === 'none' ? displayStyle : 'none');
    }
    var toggle = document.getElementById(toggleId);
    toggle.textContent = toggle.innerHTML === '\u25b6' ? '\u25bc' : '\u25b6';
}

function getStyle(elementId, property)
{
    var element = document.getElementById(elementId);
    return element.currentStyle ? element.currentStyle[property]
           : document.defaultView.getComputedStyle(element, null).getPropertyValue(property);
}


function toggle(toggleId)
{
    var toggle;
    if (document.getElementById)
    {
        toggle = document.getElementById(toggleId);
    }
    else if (document.all)
    {
        toggle = document.all[toggleId];
    }
    toggle.textContent = toggle.innerHTML === '\u25b6' ? '\u25bc' : '\u25b6';
}

function toggleElement_my(elementId, displayStyle)
{
    
	var elements = document.getElementsByTagName('div');
	
	var current = (document.getElementById("toggle-" + elementId)).innerHTML === '\u25b6' ? 'block': 'none';
	
	var exp = (current === 'block')? "__\\d+$": "__\\d";
	var regex = new RegExp('step-' + elementId + exp, "i");
    for(var i = 0; i < elements.length; i++) {
        if (elements[i].id.match(regex)) {
			elements[i].style.display = current;
        }
    }
}

function toggle_my(toggleId)
{
    toggleId = 'toggle-' + toggleId;
    var toggle;
    if (document.getElementById)
    {
        toggle = document.getElementById(toggleId);
    }
    else if (document.all)
    {
        toggle = document.all[toggleId];
    }
	if (toggle != null) {
		toggle.textContent = toggle.innerHTML === '\u25b6' ? '\u25bc' : '\u25b6';
	}
}

function toggleFewAccounts() {
	if (document.getElementById("fewAccounts")) {
		var elements = document.getElementsByClassName('fewAccounts');
		for(var i = 0; i < elements.length; i++) {
			elements[i].style.display = 'none';
		}
	}
}

function checkCookie() {
    if (readCookie("timeStamp") == "enabled") {
        toggleTimeStamp();
    }
    if (readCookie("errorMessage") == "enabled") {
        toggleErrorMessages();
    }
    toggleFewAccounts();
}

$(document).ready(function() {
    $("a[rel=screenshots]").fancybox({
        'transitionIn'		: 'none',
        'transitionOut'		: 'none',
        'titlePosition' 	: 'inside',
        'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
            return '<span id="fancybox-title-over">' + (title.length ? ' &nbsp; ' + title : '') + '&nbsp;&nbsp;<font style=\'font-size:80%\'>' + (currentIndex + 1) + '/' + currentArray.length +  '</span>';
        }
    });

}  );

function createCookie(name,value,days) {
    var expires = "";
	if (days) {
		var date = new Date();
		date.setTime(date.getTime()+(days*24*60*60*1000));
		expires = "; expires="+date.toGMTString();
	}
	document.cookie = name+"="+value+expires+"; path=/";
}

function readCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++) {
		var c = ca[i];
		while (c.charAt(0)===' ') c = c.substring(1,c.length);
		if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
}

function eraseCookie(name) {
	createCookie(name,"",-1);
}

function color_change(element) {
	element.style.backgroundColor = '#DFDFDF';
}

function switchTab(elementIdToShow, elementIdToHide, elementIdSelected, elementIdDeselected)
{
    document.getElementById(elementIdToShow).style.display = 'block';
    document.getElementById(elementIdToHide).style.display = 'none';
    document.getElementById(elementIdSelected).style.fontWeight = 'bold';
    document.getElementById(elementIdSelected).style.color ='black';
    document.getElementById(elementIdSelected).style.borderColor ='black';
    document.getElementById(elementIdDeselected).style.color ='lightgray';
    document.getElementById(elementIdDeselected).style.fontWeight ='normal';
    document.getElementById(elementIdDeselected).style.borderColor ='lightgray';
}

function showAccountInfo(divId, iconId) {
    var divElement = document.getElementById(divId);
    var current = divElement.style.display;
    var newStyle = 'block';
    if (current == 'block') {
        newStyle = 'none';
    }
    divElement.style.display = newStyle;
    var iconElement = document.getElementById(iconId);
    divElement.style.left = (iconElement.x + iconElement.width) + 'px';
    divElement.style.top = (iconElement.y + iconElement.height) + 'px';
    var elements = document.getElementsByName('AccInfo');
    for(var i=0;i<elements.length;i++) {
        if (elements[i].id != divId) {
            elements[i].style.display = 'none';
        }
    }
}


