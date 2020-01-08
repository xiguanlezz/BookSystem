function CheckAndRemoveuserName(obj) {
    //console.log($(obj).attr("name"));
    $(obj).next('span').html('').removeClass('err');
}

function CheckAndShowuserName(obj) {
    var msgBox = $(obj).next('span');
    var url = "/E_CommerceSystem/AdminUserAddCheck?";
    var value = $(obj).val();
    url += $(obj).attr("name") + "=" + value;
    console.log(url);
    $.get(url, function (data) {
        if (data == "already exist") {
            msgBox.addClass("err");
            msgBox.html("用户名已存在");
        } else if (data == "user-true") {

        } else if (data == "username-false") {
            msgBox.addClass("err");
            msgBox.html("用户名必须是6-12位, 且必须是字母开头");
        }
    });
}

function CheckAndRemovepassword(obj) {
    //console.log($(obj).attr("name"));
    $(obj).next('span').html('').removeClass('err');
}

function CheckAndShowpassword(obj) {
    var msgBox = $(obj).next('span');
    var url = "/E_CommerceSystem/AdminUserAddCheck?";
    var value = $(obj).val();
    url += $(obj).attr("name") + "=" + value;
    console.log(url);
    $.get(url, function (data) {
        if (data == "password-true") {

        } else if (data == "password-false") {
            msgBox.addClass("err");
            msgBox.html("只能包含字母数字, 长度在6-16位之间");
        }
    });
}

function CheckAndRemoverepassword(obj) {
    //console.log($(obj).attr("name"));
    $(obj).next('span').html('').removeClass('err');
}

function CheckAndShowrepassword(obj) {
    var msgBox = $(obj).next('span');
    console.log($(obj).val() == $("input[name='password']").val());
    if($(obj).val() != $("input[name='password']").val()) {
        msgBox.addClass("err");
        msgBox.html("两次密码不一致");
    }
}

function CheckAndRemovemobile(obj) {
    //console.log($(obj).attr("name"));
    $(obj).next('span').html('').removeClass('err');
}

function CheckAndShowmobile(obj) {
    var msgBox = $(obj).next('span');
    var url = "/E_CommerceSystem/AdminUserAddCheck?";
    var value = $(obj).val();
    url += $(obj).attr("name") + "=" + value;
    console.log(url);
    $.get(url, function (data) {
        if (data == "mobile-true") {

        } else if (data == "mobile-false") {
            msgBox.addClass("err");
            msgBox.html("手机号不存在");
        }
    });
}