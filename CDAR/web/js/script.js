var keywordsStr = null;
var caseNumber = null;
var similarCases = document.getElementById("similarCases");
function up() {

    var ul = $('#upload ul');

    $('#drop a').click(function () {
        // Simulate a click on the file input button
        // to show the file browser dialog
        // document.getElementById('fileArea').getElementsByTagName('li');
        // if(document.getElementById('fileArea').getElementsByTagName('li').length == 0)
        $(this).parent().find('input').click();
        // else {
        //     alert("已选择文件！");
        // }
    });

    // Initialize the jQuery File Upload plugin
    $('#upload').fileupload({

        // This element will accept file drag/drop uploading
        dropZone: $('#drop'),

        // This function is called when a file is added to the queue;
        // either via the browse button, or via drag/drop:
        add: function (e, data) {
            $('#my-modal-loading').modal();
            var tpl = $('<li class="working"><input type="text" value="0" data-width="48" data-height="48"' +
                ' data-fgColor="#0788a5" data-readOnly="1" data-bgColor="#3e4043" /><p></p><span></span></li>');

            // Append the file name and file size
            // data.files.remove(data.files[1]);
            tpl.find('p').text(data.files[0].name)
                .append('<i>' + formatFileSize(data.files[0].size) + '</i>');
            // Add the HTML to the UL element
            // data.context = "";
            data.context = tpl.appendTo(ul);

            // Initialize the knob plugin
            // tpl.find('input').knob();
            // var jqXHR = data.submit();

            // Listen for clicks on the cancel icon
            tpl.find('span').click(function () {

                // if(tpl.hasClass('working')){
                //     jqXHR.abort();
                // }

                tpl.fadeOut(function () {
                    tpl.remove();
                    data.files.remove(0);
                    // data.remove(data.index())
                });

            });

            // Automatically upload the file once it is added to the queue
            // $('#confirm').click(function(){
            //     alert(data.files.length);

            // alert(data);
            data.submit().success(function (data) {
                $('#my-modal-loading').modal('close');
                if (data.success == "true") {
                    success_alert("上传成功");
                    $('#resultPage').css("display", "block");
                    findCase(data.caseID);

                    // $('.title').text(data.);
                }
                else {
                    fail_alert("该案件已存在！");
                }
            });
            // alert(j);
            // if(j.success == "true"){
            //     alert("success");
            // }else{
            //     alert("fail");
            // }
            // });
        },

        progress: function (e, data) {

            // Calculate the completion percentage of the upload
            var progress = parseInt(data.loaded / data.total * 100, 10);

            // Update the hidden input field and trigger a change
            // so that the jQuery knob plugin knows to update the dial
            data.context.find('input').val(progress).change();

            if (progress == 100) {
                data.context.removeClass('working');
            }
        },

        fail: function (e, data) {
            $('#my-modal-loading').modal('close');
            fail_alert("上传失败");
            // Something has gone wrong!
            data.context.addClass('error');
        }

    });


    // Prevent the default action when a file is dropped on the window
    $(document).on('drop dragover', function (e) {
        e.preventDefault();
    });

    // Helper function that formats the file sizes
    function formatFileSize(bytes) {
        if (typeof bytes !== 'number') {
            return '';
        }

        if (bytes >= 1000000000) {
            return (bytes / 1000000000).toFixed(2) + ' GB';
        }

        if (bytes >= 1000000) {
            return (bytes / 1000000).toFixed(2) + ' MB';
        }

        return (bytes / 1000).toFixed(2) + ' KB';
    }

}

function findCase(id) {
    $('#my-modal-loading').modal();
    $.ajax({
        url: "/manageAction/findCase",
        type: "POST",
        dataType: "json",
        data: {
            "id": id
        },
        async: false,
        success: function (data) {
            // alert(data.success);
            if (data && data.success == "true") {
                $('.title').text("无");
                $('.caseNumber').text("无");
                $('.origin_document').text("无");
                $('#courtName').text("无");
                $('#property').text("无");
                $('#publicProsecution').text("无");
                $('#process').text("无");
                $('#endDate').text("无");
                $('#litigant').text("无");
                $('#evidence').text("无");
                $('#keywords').text("无");
                if (data.content.title != null)
                    $('.title').text(data.content.title);
                if (data.content.id != null)
                    $('.caseNumber').text(data.content.caseNumber);
                if (data.content.originDocument != null) {
                    var content = data.content.originDocument;

                    var text = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    $.each(content.split("<br>"), function (i, item) {
                        text = text + item + "<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" ;
                    });
                    $('.origin_document').html(text);
                }
                if (data.content.court != null)
                    $('#courtName').text(data.content.court);
                if (data.content.property != null)
                    $('#property').text(data.content.property);
                if (data.content.publicProsecution != null)
                    $('#publicProsecution').text(data.content.publicProsecution);
                if (data.content.process != null)
                    $('#process').text(data.content.process);
                if (data.content.endDate != null)
                    $('#endDate').text(data.content.endDate);
                if (data.content.litigant != null)
                    $('#litigant').text(data.content.litigant);
                if (data.content.evidence != null)
                    $('#evidence').text(data.content.evidence);
                if (data.content.keywords != null) {
                    $('#keywords').text(data.content.keywords);
                    keywordsStr = data.content.keywords;
                    caseNumber = data.content.caseNumber;
                    getSimilarCases(keywordsStr, caseNumber);
                }

            }
            $('#my-modal-loading').modal('close');

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $('#my-modal-loading').modal('close');
            fail_alert("上传失败");
            // alert(XMLHttpRequest.status);
            // alert(XMLHttpRequest.readyState);
            // alert(textStatus);
        }
    });
}

function getSimilarCases(keywordsStr, caseNumber) {
    $('#my-modal-loading').modal();
    $.ajax({
        url: "/manageAction/getSimilarCases",
        type: "POST",
        dataType: "json",
        data: {
            "keywords": keywordsStr,
            "caseNumber": caseNumber
        },
        async: false,
        success: function (data) {
            var similarCases = document.getElementById("similarCases");
            $.each(data.content, function (i, item) {
                var link = document.createElement("a");
                console.log(item.title);
                link.appendChild(document.createTextNode(item.title));
                link.target = "_blank";
                var caseNum = (encodeURIComponent(item.caseNumber));
                link.href = "/views/docDetail.jsp?id=" + caseNum;
                similarCases.appendChild(link);
            });
            $('#my-modal-loading').modal('close');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $('#my-modal-loading').modal('close');
            fail_alert("上传失败");
            // alert(XMLHttpRequest.status);
            // alert(XMLHttpRequest.readyState);
            // alert(textStatus);
        }
    });
}