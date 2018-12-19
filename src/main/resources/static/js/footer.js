//初始化登录模块
layui.config({
    base: '/static/js/modules/' //你存放新模块的目录，注意，不是layui的模块目录
}).use('login'); //加载入口

//初始化菜单
layui.config({
    base: '/static/js/util/'
}).use(['menu'],function(){
   menu = layui.menu;
    menu.init();
})

layui.use('element', function(){
    var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

    //监听导航点击
    element.on('nav', function(elem){
        //console.log(elem)
        layer.msg(elem.text());
    });
});
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

