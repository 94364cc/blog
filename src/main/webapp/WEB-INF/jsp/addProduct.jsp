<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/ajaxfileupload.js"></script>
    <title>增加商品</title>
    <style type="text/css">
        body {font:12px Tahoma;margin:0px;background:#FFF;}

        #container {width:100%; margin:0px; padding:0px;}

        #HeaderDiv {width:100%;margin:0 auto;height:95px;background:#D2E9FF}

        #PageBody {width:100%;margin:0 auto;height:800px;}

        #FooterDiv {width:100%;margin:0 auto; text-align:center;height:25px;background:#DDDDDD}

        #Sidebar {background:#F0F0F0; float:left; width:200px ; height:100%}

        #MainBody{height:100%;width: 100%}

        .picBox {
            width: 9px;
            height: 100%;
            float: left;
            cursor: pointer;
        }
    </style>
</head>
<body>
<form>
    <div id="container"><%--主窗体--%>
        <div id="HeaderDiv" style="text-align:center;"><%--头部--%>
            <div><span><font size="20">商品操作界面</font></span></div>
    </div>
        <div id="PageBody"><%--中间部分--%>
            <div id="Sidebar"><%--伸缩部分--%>
                <font size="5">商品列表</font><br>
            </div>
            <div id="MainBody"><%--显示内容部分--%>
                <div>
                    <p class="ui-tips">上传头像会自动生成头像缩略图片，您也可以拖动大图的裁剪区域，调整缩图内容。</p>
                    <p class="ui-tips">支持JPG、GIF、PNG等图片格式 推荐尺寸：180*180像素</p>
                    <input type="file" onchange="uploadCoursePoster();" id="fileCoursePoster"
                           style="display: none;" name="fileCoursePoster" />
                    <span id="errorCoursePoster" style="color: red; display: none;">请上传图片！</span>
                    <input type="button" onclick="upLoad()" value="上传头像" class="btn btn-sm btn-normal">
                    <input type="hidden" id="imageType" name="imageType">
                </div>
            </div>
        </div>
        <div id="FooterDiv"><%--底部--%>

        </div>
    </div>
</form>
</body>
</html>

