function decodeHtml(html){
	var txt = document.createElement("textarea");
	txt.innerHTML = html;
	return txt.value;
}

var chartDataStr = decodeHtml(chartData);
var chartJsonArray = JSON.parse(chartDataStr);
var arrayLen = chartJsonArray.length;

var numericData = [];
var labelData = []; 

for(var i=0; i<arrayLen; ++i){
	numericData[i] = chartJsonArray[i].count;
	labelData[i] = chartJsonArray[i].stage;
}
new Chart (document.getElementById('myPieChart'), {
	type: 'pie',
	data: {
		labels: labelData,
		datasets: [{
			label: 'my first dataset',
			backgroundColor: ['red', 'blue', 'green'],
			borderColor: 'rgb(255, 99, 132)',
			data: numericData
		}]
	},
	options: {
		title: {
			display: true,
			text: 'project statuses'
		}
	}
});

