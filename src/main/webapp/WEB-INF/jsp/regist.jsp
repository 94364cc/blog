<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script src="js/jquery-3.2.1.min.js"></script>
    <script>
        function isNull(id,msg){
            if($("#"+id).val()==""){
                alert(msg+"不能为空");
                $("#"+id).focus();
                return false;
            }
            return true;
        }
        function check(){
            username=$("#username").val();
            fistPwd=$("#fistPwd").val();
            secPwd=$("#secPwd").val();
            if(username.length<6 || username.length>24){
                alert("用户名长度必须大于6位小于24位");
                return false;
            }
            if(fistPwd.length<6 || fistPwd.length>24){
                alert("密码长度必须大于6位小于24位");
                return false;
            }
            if(isNull("username","用户名")&&isNull("secPwd","密码")&&isNull("fistPwd","密码")){
                if(fistPwd!=secPwd){
                    alert("请保持密码一致");
                    return false;
                }
                return true;
            }
            return false;
        }
        function sub(){
            var b=check();
            if(b==true) {
                $.ajax({
                    url: "/regist",
                    async: false,
                    type: "post",
                    data: {
                        username: $("#username").val(),
                        fistPwd: $("#fistPwd").val()
                    },
                    datatype: "json",
                    success: function (json) {
                        var data=JSON.parse(json)
                        if (data.status == 1) {
                            alert("注册成功");
                            location.href = "/";
                        } else {
                            $("#errMsg").html(data.msg);
                        }
                    }
                });
            }
        }
    </script>
</head>
<body>
<form id="theForm">
    username:<input type="text" name="username" id="username"/><br>
    password: <input type="password" name="fistPwd" id="fistPwd"/><br>
    password:<input type="password" name="secPwd" id="secPwd"/><br>
    <span id="errMsg"></span>
    <input type="button" onclick="javascript:sub();" value="regist"/>
</form>
</body>
</html>
