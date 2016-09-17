/**
 * Created by admin on 2016/9/14.
 */
$(function () {
    var charts = $('#content').highcharts({
        title: {
            text: '马坦、工信保网络ping值测试',
            x: -20 //center
        },
        subtitle: {
            text: 'zff',
            x: -20
        },
        xAxis: {
            tickInterval: 1
        },
        yAxis: {
            title: {
                text: 'category(30)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '毫秒'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: 'SYS',
            data: [70, 69, 95, 70, 69, 95, 145, 182, 70, 23, 95, 215, 23, 12, 12, 23, 21, 2]
        }, {
            name: 'BUI',
            data: [70, 220, 248, 241, 201, 141, 86, 25]
        }, {
            name: 'APP',
            data: [135, 170, 186, 179, 143, 90, 39, 10]
        }, {
            name: '马坦'
            /*data: [94, 152, 145, 166,23 , 103, 66, 48]*/
        }]
    });
});


$(document).ready(function() {
    var chart = {
        type: 'spline',
        animation: Highcharts.svg, // don't animate in IE < IE 10.
        marginRight: 10,
        events: {
            load: function () {
                // set up the updating of the chart each second
                var series = this.series[0];
                setInterval(function () {
                    var x = (new Date()).getTime(), // current time
                        y = Math.random();
                    series.addPoint([x, y], true, true);
                }, 1000);
            }
        }
    };
    var title = {
        text: 'Live random data'
    };
    var xAxis = {
        type: 'datetime',
        tickPixelInterval: 150
    };
    var yAxis = {
        title: {
            text: 'Value'
        },
        plotLines: [{
            value: 0,
            width: 1,
            color: '#808080'
        }]
    };
    var tooltip = {
        formatter: function () {
            return '<b>' + this.series.name + '</b><br/>' +
                Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                Highcharts.numberFormat(this.y, 2);
        }
    };
    var plotOptions = {
        area: {
            pointStart: 1940,
            marker: {
                enabled: false,
                symbol: 'circle',
                radius: 2,
                states: {
                    hover: {
                        enabled: true
                    }
                }
            }
        }
    };
    var legend = {
        enabled: false
    };
    var exporting = {
        enabled: false
    };
    var series= [{
        name: 'Random data',
        data: (function () {
            // generate an array of random data
            var data = [],time = (new Date()).getTime(),i;
            for (i = -19; i <= 0; i += 1) {
                data.push({
                    x: time + i * 1000,
                    y: Math.random()
                });
            }
            return data;
        }())
    }];

    var json = {};
    json.chart = chart;
    json.title = title;
    json.tooltip = tooltip;
    json.xAxis = xAxis;
    json.yAxis = yAxis;
    json.legend = legend;
    json.exporting = exporting;
    json.series = series;
    json.plotOptions = plotOptions;


    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });
    $('#content1').highcharts(json);

});

var vue = new Vue({
    el: "#app",
    data: {
        list: [],
        url: {},
        id: {},
        dt: []
    },
    methods: {
        search: function () {
            api.search();
        },
        addURL: function (url) {
            console.info(url);
            var url = $("#text").val().toString();
            if (url == "") {
                alert("请输入网址！");
                console.info(1)
            } else {
                api.addURL(url)
            }
        },
        delect: function (id, url) {
            console.info($("#" + id).val(id));
            if ($("#" + id).val(id) == "执行") {
                alert("请先停止执行任务！")
            } else {
                api.delect(id, url)
            }
        },
        jobid: function (id) {
            api.jobid(id);

        },
        stop: function (id) {
            api.stop(id);

        },
        print: function () {
            api.print();
        }

    }
});

var api = {
    addURL: function (url) {
        $.ajax({
            type: 'post',
            url: '/add',
            data: {url: url},
            error: function (requst) {
                alert("error")
            },
            success: function (data) {
                console.info(1)
            }
        })
    },
    search: function () {
        $.ajax({
            type: 'post',
            url: "/demo",
            datatype: 'json',

            error: function (requst) {
                alert("error")
            },
            success: function (data) {
                console.info(data);
                vue.list = jQuery.parseJSON(data);
            }
        })
    },
    delect: function (id, url) {
        $.ajax({
            type: 'post',
            url: "/del",
            data: {id: id, url: url},
            error: function (requst) {
                alert("error")
            },
            success: function (data) {
                console.info(1);
            }
        })
    },
    jobid: function (id) {
        $.ajax({
            type: 'post',
            url: '/jobid',
            data: {id: id},
            error: function (requst) {
                alert("error")
            },
            success: function (data) {
                console.info(data);
                $("#" + id).empty();
                $("#" + id).append("<option value=\"执行\">执行</option>");
            }
        })
    },
    stop: function (id) {
        $.ajax({
            type: 'post',
            url: '/stop',
            data: {id: id},
            error: function (requst) {
                alert("error")
            },
            success: function (data) {
                console.info(data);
                $("#" + id).empty();
                $("#" + id).append("<option value=\"停止\">停止</option>");
            }
        })
    },
    print: function () {
        $.ajax({
            type: 'post',
            url: "/datajob",
            datatype: 'json',
            error: function (requst) {
                alert("error")
            },
            success: function (data) {
                console.info(data);
                vue.dt = $.parseJSON(data);
            }
        });
    }
};
api.search();
