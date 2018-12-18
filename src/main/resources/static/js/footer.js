layui.config({
    base: '/static/js/modules/' //你存放新模块的目录，注意，不是layui的模块目录
}).use('login'); //加载入口


var tab;
function qqLoginImg() {
    var width = $(window).width();
    var qWidth = 691;
    var left = (width - qWidth) / 2;
    tab = window.open('/qqLogin', '_blank', 'top=200,left=' +
        left +
        ',height=535, width=' +
        qWidth +
        ',toolbar=0,location=0,menubar=0');
    // alert($(window).width()); //浏览器时下窗口可视区域宽度
    // alert(window.location.host)
}

