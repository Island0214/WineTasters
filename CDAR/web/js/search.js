/**
 * Created by island on 2017/7/16.
 */
function search() {
    var input = $('#textField').val();
    // $.ajax({
    //     url:"/views/civilCase.jsp",
    //     dataType:"jsp",
    //     type:"get",
    //     success:function(data){
    //         //div加载页面
    //         $("#div").html(data);
    //     }
    // });
    // window.location.href = "views/civilCase.jsp";
    // document.location.href='/views/civilCase.jsp';
    // alert(input);
    $.ajax({
        url: "/manageAction/searchCase",
        type: "POST",
        // dataType: "json",
        data: {"data": input,
        "page": 1},
        async: false,
        success: function (data) {
            // alert("success");
            if (data.success == "true") {
                // alert(window.location.href);
                // alert('/views/civilCase.jsp?input=' + input);
                window.location.href = '../views/civilCase.jsp?input=' + input;
                // $('#caseSearch').html(input);
                // clearCaseList();
                // $('.pagination').jqPagination({
                //     link_string: '/?page={page_number}',
                //     max_page: 1,
                //     paged: function (page) {
                //         // do something with the page variable
                //         $('.log').prepend('<li>Requested page ' + page + '</li>');
                //     }
                //
                // });
                // $.each(data.content, function (i, item) {
                //     addCaseItem(item);
                //     // alert(item.id);
                // });
            } else {
                alert(data.searchInfo);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("error");
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function upload() {
    alert($('#file').val());
    $.ajaxFileUpload({
        url: '/manageAction/upload',
        secureuri: false,
        fileElementId: 'file',
        dataType: 'json',
        success: function () {
            alert("上传成功");
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("上传失败");
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function getPageSize() {
    jQuery.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: '/manageAction/getPageSize',
        dataType: 'json',
        success: function (data) {
            if (data && data.success == "true") {
                $('.pagination').jqPagination({
                    link_string: '/?page={page_number}',
                    max_page: data.pageSize,
                    paged: function (page) {
                        // do something with the page variable
                        $('.log').prepend('<li>Requested page ' + page + '</li>');
                        getCaseOfEachPage(page);
                    }
                });
            }
        },
        error: function () {
            alert("error")
        }
    });
}

function clearCaseList() {
    var case_list = document.getElementById('case_list');
    var num = case_list.childNodes.length;
    for (num - 1; num >= 0; num--) {
        if (case_list.childNodes.item(num) != null)
        // alert(case_list.childNodes.item(num));
            case_list.removeChild(case_list.childNodes.item(num));
    }
}

function getCaseOfEachPage(pageNum) {
    clearCaseList();
    $.ajax({
        url: "/manageAction/getPageContent",
        type: "POST",
        dataType: "json",
        data: {"page": pageNum},
        async: false,
        success: function (data) {
            if (data && data.success == "true") {
                $.each(data.content, function (i, item) {
                    addCaseItem(item);
                    // alert(item.id);
                });
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("error");
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function getReasonTypes() {
    jQuery.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: '/manageAction/getReasonType',
        dataType: 'json',
        success: function (data) {
            if (data && data.success == "true") {
                var as = document.getElementById('as-ul1');
                $.each(data.types, function (i, item) {
                    if (item != null) {
                        var li = document.createElement("li");
                        var a = document.createElement("a");
                        a.appendChild(document.createTextNode(item));
                        a.value = item;
                        // a.value(item);
                        a.onclick = function () {
                            getTypeSize(this.value);
                        };
                        li.appendChild(a);
                        as.appendChild(li);
                    }
                });
            }
        },
        error: function () {
            alert("error")
        }
    });
}

function addCaseItem(item) {
    var case_list = document.getElementById('case_list');
    var case_item = document.createElement("div");
    case_item.className = "case_item";
    case_list.appendChild(case_item);

    var title_a = document.createElement("a");
    var title_p = document.createElement("h4");
    title_p.appendChild(document.createTextNode(item.title));
    title_p.className = "title";
    title_a.appendChild(title_p);
    case_item.appendChild(title_a);

    var court = document.createElement("p");
    court.className = "court";
    court.appendChild(document.createTextNode(item.court));
    case_item.appendChild(court);

    var caseName = document.createElement("p");
    caseName.className = "caseName";
    caseName.appendChild(document.createTextNode(item.caseNumber));
    case_item.appendChild(caseName);

    var endDate = document.createElement("p");
    endDate.className = "endDate";
    endDate.appendChild(document.createTextNode(item.endDate));
    case_item.appendChild(endDate);

    var reason = document.createElement("h5");
    reason.appendChild(document.createTextNode("【裁判理由】"));
    case_item.appendChild(reason);

    var judge_reason = document.createElement("p");
    judge_reason.className = "judge_reason";
    judge_reason.appendChild(document.createTextNode(item.judge_reason));
    case_item.appendChild(judge_reason);
}

function getTypeSize(type) {
    getTypePage(type, 1);
    jQuery.ajax({
        url: '/manageAction/getTypeSize',
        type: "POST",
        dataType: "json",
        data: {"type": type},
        async: false,
        success: function (data) {
            if (data && data.success == "true") {
                $('.pagination').jqPagination({
                    link_string: '/?page={page_number}',
                    max_page: data.pageSize,
                    paged: function (page) {
                        // do something with the page variable
                        $('.log').prepend('<li>Requested page ' + page + '</li>');
                        getTypePage(type, page);
                    }
                });
            }
        },
        error: function () {
            alert("error")
        }
    });
}


function getTypePage(type, page) {
    // alert(page);
    clearCaseList()
    $.ajax({
        url: "/manageAction/getTypeContent",
        type: "POST",
        dataType: "json",
        data: {
            "page": page,
            "reason": type
        },
        async: false,
        success: function (data) {
            if (data && data.success == "true") {
                $.each(data.content, function (i, item) {
                    addCaseItem(item);
                    // alert(item.id);
                });
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("error");
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function GetArgsFromHref(sHref, sArgName)
{
    var args    = sHref.split("?");
    var retval = "";

    if(args[0] == sHref) /*参数为空*/
    {
        return retval; /*无需做任何处理*/
    }
    var str = args[1];
    args = str.split("&");
    for(var i = 0; i < args.length; i ++)
    {
        str = args[i];
        var arg = str.split("=");
        if(arg.length <= 1) continue;
        if(arg[0] == sArgName) retval = arg[1];
    }
    return retval;
}