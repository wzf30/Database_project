/**
 * Created by zhangyipeng on 2021/06/12.
 */

http_url = "http:localhost:8080/";

// 用于同步rem与浏览器宽度比例
function updateRootSize() {
	let docEle = document.documentElement;
	docEle.style.fontSize = docEle.clientWidth/50+"px";
};

window.onload = function() {
	// 更新rootSize
	setInterval(updateRootSize,80);
};
window.onresize = updateRootSize;

function setCookie(name, value) {
	document.cookie = name + "=" + value;
};

function getCookie(name) {
	var cookies = document.cookie.split(";");
	for(var i = 0;i < cookies.length;++i) {
		var cookie = cookies[i].split("=");
		if(cookie[0].trim() == name) {
			return cookie[1];
		}
	}
	return "";
};
