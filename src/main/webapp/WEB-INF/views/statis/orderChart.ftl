


<script type="text/javascript">
var lineChartData = {
			labels : [${dataStr}],
			datasets : [
				{
					label: "chart",
					fillColor : "rgba(151,187,205,0.5)",
					strokeColor : "rgba(151,187,205,1)",
					pointColor : "gba(151,187,205,1)",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "rgba(220,220,220,1)",
					data :${data}
				}
			]
		}
var ctx = document.getElementById("canvas").getContext("2d");
var myNewChart = new Chart(ctx).Line(lineChartData,{responsive: false});
</script>
<canvas id="canvas" width="1440" height="650"></canvas>