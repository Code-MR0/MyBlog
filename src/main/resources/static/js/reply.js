//校验
$('.ui.form')
    .form({
        on: 'blur',
        fields: {
            name: {
                identifier: 'name',
                rules: [
                    {
                        type: 'empty',
                        prompt: '请输入名称！'
                    }
                ]
            },
            email: {
                identifier: 'email',
                rules: [
                    {
                        type: 'email',
                        prompt: '邮箱为空或格式不正确！'
                    }
                ]
            },
            content: {
                identifier: 'content',
                rules: [
                    {
                        type: 'empty',
                        prompt: '评论内容不可为空！'
                    }
                ]
            },
        }
    });
$('#comment-post-button').click(function () {
    let b = $('.ui.form').form('validate form');
    if (b){
        postReply();
    }
})

// 提交数据
function postReply() {
    $('#reply-list').load("/addComment", {
        "articleId": $("[name='article_id']").val(),
        "replyId": $("[name='reply_id']").val(),
        "content": $("[name='content']").val(),
        "name": $("[name='name']").val(),
        "replyName": $("[name='reply_name']").val(),
        "replyAncestorId": $("[name='reply_ancestor_id']").val(),
        "email": $("[name='email']").val()
    }, function (responseTxt, statusTxt, xhr) {
        doClear();
        $(Window).scrollTo($('#reply-list'),500);
    });
}
// 回复后清除
function doClear() {
    $("[name='content']").val('');
    $("[name='reply_id']").val(-1);
    $("[name='reply_name']").val(-1);
    $("[name='reply_ancestor_id']").val(-1);
    $("[name='content']").attr('placeholder',"快来评论吧！");
}

//去回复
function goReply(obj) {
    let name = $(obj).data('name');
    let replyId = $(obj).data('id');
    let replyAncestorId = $(obj).data('replyancestorid');
    $("[name='content']").attr('placeholder',"@"+name).focus();
    $("[name='reply_id']").val(replyId);
    $("[name='reply_name']").val("@"+name);
    $("[name='reply_ancestor_id']").val(replyAncestorId);
    $(Window).scrollTo($('#reply-box'),500)
}