layui.use(['element', 'jquery', 'layer'], function() {
    var element = layui.element;
    var $ = layui.jquery;
    var layer = layui.layer;
    var int;
    var video = document.getElementById("video");
    var webGL = document.getElementById("webGL");
    var frame = document.getElementById("myframe");
    var graph = document.getElementById("graph");
    video.onclick=function(){
        window.clearInterval(int);
        frame.src="/index/live.html";
        frame.style.display="";
        graph.style.display="none";
    };
    webGL.onclick=function () {
        window.clearInterval(int);
        frame.src="/index/webGL.html";
        frame.style.display="";
        graph.style.display="none";
    };
    var myChart = echarts.init(graph);
    myChart.setOption({
        tooltip: {
            trigger: 'axis',
            position: function (pt) {
                return [pt[0], '10%'];
            }
        },
        title: {
            left: 'center',
            text: []
        },
        toolbox: {
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: []
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, '100%']
        },
        dataZoom: [{
            type: 'inside',
            start: 0,
            end: 10
        }, {
            start: 0,
            end: 10,
            handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
            handleSize: '80%',
            handleStyle: {
                color: '#fff',
                shadowBlur: 3,
                shadowColor: 'rgba(0, 0, 0, 0.6)',
                shadowOffsetX: 2,
                shadowOffsetY: 2
            }
        }],
        series: [
            {
                name:'模拟数据',
                type:'line',
                smooth:true,
                symbol: 'none',
                sampling: 'average',
                itemStyle: {
                    normal: {
                        color: 'rgb(255, 70, 131)'
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgb(255, 158, 68)'
                        }, {
                            offset: 1,
                            color: 'rgb(255, 70, 131)'
                        }])
                    }
                },
                data: []
            }
        ]
    });
    $(document).ready(function () {
        var postdata = {choose: 'roll'};
        $.post('/choose',postdata, function (result) {
            var times = [];
            var numbers = [];
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (result) {
                console.log(result);
                for (var i = 0; i < result.length; i++) {
                    times.push(result[i].times);    //挨个取出类别并填入类别数组
                    numbers.push({
                        name: result[i].times,
                        value: result[i].datas
                    });
                }
                console.log(times);
                console.log(numbers);
                myChart.hideLoading();    //隐藏加载动画
                myChart.setOption({        //加载数据图表
                    title: {
                      text: 'roll'
                    },
                    xAxis: {
                        data: times
                    },
                    series: [{
                        // 根据名字对应到相应的系列
                        name:'模拟数据',
                        data: numbers
                    }]
                });
                frame.style.display="none";
                graph.style.display="";
            }
            else {
                //请求失败时执行该函数
                alert("图表请求数据失败!");
                myChart.hideLoading();
            }
        });
    });
    var status = true;
    $(".rov").on('click',function () {
        event.preventDefault();
        var choose = $(this).prop('id');
        var t = $(this).text();
        var postdata = {choose: choose};
        window.clearInterval(int);
        int = window.setInterval(function () {
            $.post('/choose', postdata, function (result) {
                var times = [];
                var numbers = [];
                //请求成功时执行该函数内容，result即为服务器返回的json对象
                if (result) {
                    for (var i = 0; i < result.length; i++) {
                        times.push(result[i].times);    //挨个取出类别并填入类别数组
                        numbers.push({
                            name: result[i].times,
                            value: result[i].datas
                        });
                    }
                    console.log(times);
                    console.log(numbers);
                    myChart.hideLoading();    //隐藏加载动画
                    myChart.setOption({        //加载数据图表
                        title: {
                            text: t
                        },
                        xAxis: {
                            data: times
                        },
                        series: [{
                            // 根据名字对应到相应的系列
                            name: '模拟数据',
                            data: numbers
                        }]
                    });
                    frame.style.display = "none";
                    graph.style.display = "";
                }
                else {
                    //请求失败时执行该函数
                    alert("图表请求数据失败!");
                    myChart.hideLoading();
                }
            });
        },500);
    });
});