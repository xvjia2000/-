<html>
<head>
<meta charset="utf-8" />
<title></title>
<style>
html, body {
	width: 100%;
	height: 100%;
}

#box {
	height: 100px;
	width: 100px;
	background: deeppink;
	position: absolute;
}
</style>
</head>
<body>
	 <div id="box">
		<p id="p1"></p>
		<p id="p2"></p>
	</div> 
</body>
</html>
<script>
	var box = document.getElementById("box");
	var p1 = document.getElementById("p1");
	var p2 = document.getElementById("p2");
	document.onmousemove = function(evt) {
		var e = evt || event;
		box.style.left = e.pageX + "px";
		box.style.top = e.pageY + "px";
		p1.innerHTML = box.style.left;
		p2.innerHTML = box.style.top;
		document.body.onmousemove = function(evt) {
			var d = document.createElement("div");
			var e = evt || event;
			d.style.width = "5px";
			d.style.height = "5px";
			d.style.background = "red";
			d.style.borderRadius = "50%";
			d.style.position = "absolute";
			document.body.appendChild(d);
			d.style.left = e.pageX + "px";
			d.style.top = e.pageY + "px";
		}
	}
</script>
