//隐藏错误提示
$(".icons").hide();
$(".modal_icons").hide();

//登录表单
var phone = $("#phone");
var phone_null = $("#phone_null");
var phone_error = $("#phone_error");
//输入框失去焦点
phone.blur(function() {
	var phone_len = phone.val().length;
	if (phone_len == 0) {
		phone_null.show();
	}
	if (phone_len != 0) {
		var pattern = /^1[345789]\d{9}$/;
		var phone_value = phone.val();
		if (!pattern.test(phone_value)) {
			phone_error.show();
		}
	}
});
//输入框获得焦点
var login_error = $(".login_error");
phone.focus(function() {
	phone_null.hide();
	phone_error.hide();
	login_error.css("visibility", "hidden");
});
//登录表单提交
var loginFormSubmit = $("#loginFormSubmit");
var password = $("#password");
var password_null = $("#password_null");
loginFormSubmit.click(function() {
	var password_len = password.val().length;
	var phone_len = phone.val().length;
	var phone_value = phone.val();
	var pattern1 = /^1[345789]\d{9}$/;;
	if (password_len != 0 && phone_len != 0 && pattern1.test(phone_value)) {
		return true;
	} else {
		if (phone_len == 0) {
			phone_null.show();
		}
		if (!pattern1.test(phone_value) && phone_len != 0) {
			phone_error.show();
		}
		if (password_len == 0) {
			password_null.show();
		}
		return false;
	}
});
password.focus(function() {
	password_null.hide();
	login_error.css("visibility", "hidden");
});