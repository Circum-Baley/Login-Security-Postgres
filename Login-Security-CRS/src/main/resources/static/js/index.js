Highcharts.chart('container', {
	chart: {
		type:'line',
		width:500
	},
	title: {
		text: 'consumo graficado en linea'
	},
	xAxix:  {
		categories: ["jan","feb","mar","apr","may"]
	},
	tooltip: {
		formatter: function(){
			console.log(this);
		}
	},
	series: [{
		data: [10, 20, 30, 40, 50]
	}]
})