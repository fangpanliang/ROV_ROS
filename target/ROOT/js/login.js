layui.use(['element', 'form', 'jquery', 'layer'], function () {
    var form = layui.form;
    var element = layui.element;
    var $ = layui.jquery;
    var layer = layui.layer;
    form.on('submit(loginSubmit)', function (data) {
        var postData = data.field;
        $.post('login', postData, function (logInData) {
            if(logInData.status){
                layer.open({
                    title: '消息'
                    , content: '登录成功!'
                    , yes: function () {
                        location.href = '/';
                    }, cancel: function () {
                        location.href = '/';
                    }
                });
            }else{
                layer.open({
                    title: '消息'
                    , content: logInData.message
                });
                $('#captcha-img').prop('src', 'login/captcha?v=' + Math.random());
            }
            $("#input-rov").val("");
            $("#input-cam").val("");
        });
        return false;
    });

    $('#captcha-img').click(function () {
        $('#captcha-img').prop('src', 'login/captcha?v=' + Math.random());
    });

});
