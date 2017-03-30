'use strict';
layui.use([ 'jquery' ], function() {
	window.jQuery = window.$ = layui.jquery;
	$(".layui-canvs").width($(window).width());
	$(".layui-canvs").height($(window).height());
});

$(document).ready(function(){   
});  
  
/**  
 * 更换图片  
 */  
function changeImg() {  
    var imgSrc = $("#imgObj");  
    var src = imgSrc.attr("src");  
    imgSrc.attr("src", chgUrl(src));  
};  
/**  
 * 时间戳    
 * 为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳   
 */  
function chgUrl(url) {  
    var timestamp = (new Date()).valueOf();  
    var stamp = $("#timestamp");  
//    alert(url);  
    url = url.substring(0, 60);  
    if ((url.indexOf("&") >= 0)) {  
        url = url + "×tamp=" + timestamp;  
    } else {  
        url = url + "?timestamp=" + timestamp;  
        stamp.val(timestamp);  
    }  
    return url;  
}; 


$('#imageContent').bind('keypress', function (event) {
    if (event.keyCode == "13") {
        clickBtn();
    }
}); 
/**  
 * 提交  
 */  
function clickBtn(){  
    //帐号  
    var username = $("#username").val().trim();  
    //密码  
    var password = $("#password").val().trim();  
    //验证码  
    var imageContent = $("#imageContent").val().trim();  
    if(username.length == 0){  
    	alert("请输入帐号");
        return false;  
    }  
//  console.log(password);  
      
    if(password.length == 0){  
        alert("请输入密码");
        return false;  
    }  
    if(imageContent.length == 0){  
       alert("请输入验证码");
        return false;   
    }else if(imageContent.length < 4){  
        alert("验证码错误");  
        return false;  
    }else{
    	//去后台校验验证码
    	if(checkImageCode(imageContent)){
    		var newpassword=$.md5(password);
        	$("#password").val(newpassword);
        	$("#userForm").submit();
    	}else{
    		alert("验证码错误"); 
    	}
    	
        //密码判断  
//        $.ajax({  
//            type : 'post',  
//            url : 'user/login',  
//            data : {  
//                "username" : username,  
//                "password" : $.md5(password)  
//            },  
//            success : function(data) {  
//                //'0'失败,'1'成功  
//                if(data==0){  
//                	alert("账号或密码错误");  
//                    return false;  
//                }else if(checkImageCode(imageContent)){  
//                    //成功提交  
//                	alert("登陆成功");  
//
//                }  
//            }  
//        });  
          
    }  
};  
/**  
 * 验证码校验  
 */  
function checkImageCode(s) {  
    //验证码  
    var code = s.trim();  
    var timestamp = $("#timestamp").val().trim();  
//    console.log(code + " " + timestamp);  
    var status = "";  
    var boo=false;  
    if(code.length != 0 ){  
        $.ajax({  
            type : 'post',  
            async : false,  
            url : 'captcha/checkCaptcha',  
            data : {  
                "code" : code  
            },  
            success : function(data) {  
                status = data;  
            }  
        });  
        if(status=="true"){  
            boo = true;  
        }else{  
            changeImg();  
            return false;  
        }  
    } 
    return boo;  
}  