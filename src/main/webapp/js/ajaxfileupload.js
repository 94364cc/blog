function upLoad(){
    document.getElementById("imageType").value = "headPicture"; //图片分类，自定义类型，是海报还是个人头像
    $("#fileCoursePoster").click();
}
function uploadCoursePoster() {
    var imageType = document.getElementById("imageType").value;
    var studentId=$("#studentId").val();
    $.ajaxFileUpload({
        url : "/itoo-jrkj-student-web/media/uploadImages",
        secureuri : false,
        data : {
            "studentId" : studentId,
            "imageType" : imageType
        },
        fileElementId : "fileCoursePoster",// 文件选择框的id属性
        dataType : 'json',
        success : function(data) {
            $("#imgCoursePoster").attr(
                "src",
                "http://123.56.120.25:8888/apple/"
                + data);
            $('#headPic').val(data);
            alert("上传成功！");
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            alert("上传失败！");
        }
    });
}