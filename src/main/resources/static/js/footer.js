layui.config({
    base: '/static/js/util/'
}).use(['element', 'laypage', 'jquery', 'menu'], function () {
    element = layui.element, laypage = layui.laypage, $ = layui.$, menu = layui.menu;
    laypage.render({
        elem: 'demo'
        , count: 70 //数据总数，从服务端得到
    });
    menu.init();


})


layui.use('layer', function () { //独立版的layer无需执行这一句
    var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
    //触发事件
    var active = {
        setTop: function () {
            var that = this;
            //多窗口模式，层叠置顶
            layer.open({
                type: 2 //此处以iframe举例
                , title: '当你选择该窗体时，即会在最顶端'
                , area: ['390px', '260px']
                , shade: 0
                , maxmin: true
                , offset: [ //为了演示，随机坐标
                    Math.random() * ($(window).height() - 300)
                    , Math.random() * ($(window).width() - 390)
                ]
                , content: '//layer.layui.com/test/settop.html'
                , btn: ['继续弹出', '全部关闭'] //只是为了演示
                , yes: function () {
                    $(that).click();
                }
                , btn2: function () {
                    layer.closeAll();
                }

                , zIndex: layer.zIndex //重点1
                , success: function (layero) {
                    layer.setTop(layero); //重点2
                }
            });
        }
        , confirmTrans: function () {
            //配置一个透明的询问框
            layer.msg('大部分参数都是可以公用的<br>合理搭配，展示不一样的风格', {
                time: 20000, //20s后自动关闭
                btn: ['明白了', '知道了', '哦']
            });
        }
        , notice: function () {
            //示范一个公告层
            layer.open({
                type: 1
                ,
                title: false //不显示标题栏
                ,
                closeBtn: false
                ,
                area: '300px;'
                ,
                shade: 0.8
                ,
                id: 'LAY_layuipro' //设定一个id，防止重复弹出
                ,
                btn: ['火速围观', '残忍拒绝']
                ,
                btnAlign: 'c'
                ,
                moveType: 1 //拖拽模式，0或者1
                ,
                content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">' +
                    '你知道吗？亲！<br>layer ≠ layui<br><br>layer只是作为Layui的一个弹层模块，由于其用户基数较大，所以常常会有人以为layui是layerui' +
                    '<br><br>layer虽然已被 Layui 收编为内置的弹层模块，但仍然会作为一个独立组件全力维护、升级。<br><br>我们此后的征途是星辰大海 ^_^</div>'
                ,
                success: function (layero) {
                    var btn = layero.find('.layui-layer-btn');
                    btn.find('.layui-layer-btn0').attr({
                        href: 'http://www.layui.com/'
                        , target: '_blank'
                    });
                }
            });
        }
        , offset: function (othis) {
            var type = othis.data('type')
                , text = othis.text();
            layer.open({
                title: '登录',
                type: 1
                , offset: type //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerDemo' + type //防止重复弹出
                , content:
                    '<form class="layui-form layui-form-pane" style="padding: 20px" action="">' +
                    '<div class="layui-form-item">\n' +
                    '    <label class="layui-form-label">用户</label>\n' +
                    '    <div class="layui-input-inline">\n' +
                    '      <input type="text" name="username" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">\n' +
                    '    </div>\n' +
                    '  </div>' +
                    '  <div class="layui-form-item">\n' +
                    '    <label class="layui-form-label">短输入框</label>\n' +
                    '    <div class="layui-input-inline">\n' +
                    '      <input type="text" name="username" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">\n' +
                    '    </div>\n' +
                    '  </div>' +
                    ' <div class="layui-form-item">\n' +
                    '    <button class="layui-btn" style="width: 100%" lay-submit="" lay-filter="demo2">跳转式提交</button>\n' +
                    '  </div>' +

                    '<span>第三方登录</span>' +
                    '<p><img src="/static/img/icon/qq.png" onclick="qqLoginImg()" style="margin-top: 10px;cursor: pointer"  width="35px" height="35px"/></p>' +
                    '</form>'
                , btnAlign: 'c' //按钮居中
                , shade: 0.5 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
            });
        }
    };

    $('.model').on('click', function () {
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
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

