//一般直接写在一个js文件中

layui.use('layer', function () {
    var $ = layui.jquery, layer = layui.layer;
    //触发事件
    var active = {
        offset: function (othis) {
            var type = othis.data('method');
            var text = othis.text();
            console.log(type)
            console.log(text)
            layer.open({
                title: '登录',
                type: 1
                ,offset: 't' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
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
        var othis = $(this);
        var method = othis.data('method');
        console.log(method)
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

