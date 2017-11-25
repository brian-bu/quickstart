window.onload = function start() {
	var arr = new Array();
	arr.push(-3, 1, 2, -3, 4);
	var result = new Array();
	for(var i = 0; i < arr.length; i++){
		var cache = arr[i];
		for(var j = 0; j < arr.length; j++) {
			if((i != j)) {
				cache = cache + arr[j];
			}
			if(cache == 0) {
				result.push(i, j);
				break;
			}
		}
		alert('result: ' + result);
	}
}