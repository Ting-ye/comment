$(function() {
	common.showMessage($("#message").val());
});



function remove(id) {
	if(confirm("确定要删除这条广告吗？ id是：")) {
		$("#id").val(id);
		$("#mainForm").attr("action",$("#basePath").val() + "/ad/remove");
		$("#mainForm").submit();
	}
}
function modifyInit(id) {
	$("#id").val(id);
	$("#mainForm").attr("action",$("#basePath").val() + "/ad/modifyInit");
	$("#mainForm").submit();
}