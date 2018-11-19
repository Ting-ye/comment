function remove(id) {
    if(confirm("确定要删除这条广告吗？")) {
        $("#mainForm").attr("action", $("#basePath").val() + "/business/remove" + id);
        $("#mainForm").submit();
    }
}

function modifyInit(id) {
	location.href = $("#basePath").val() + "/businesses/" + id;
}