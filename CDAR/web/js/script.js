function up(){

    var ul = $('#upload ul');

    $('#drop a').click(function(){
        // Simulate a click on the file input button
        // to show the file browser dialog
        $(this).parent().find('input').click();
    });

    // Initialize the jQuery File Upload plugin
    $('#upload').fileupload({

        // This element will accept file drag/drop uploading
        dropZone: $('#drop'),

        // This function is called when a file is added to the queue;
        // either via the browse button, or via drag/drop:
        add: function (e, data) {

            var tpl = $('<li class="working"><input type="text" value="0" data-width="48" data-height="48"'+
                ' data-fgColor="#0788a5" data-readOnly="1" data-bgColor="#3e4043" /><p></p><span></span></li>');

            // Append the file name and file size
            tpl.find('p').text(data.files[0].name)
                         .append('<i>' + formatFileSize(data.files[0].size) + '</i>');
            // Add the HTML to the UL element
            data.context = tpl.appendTo(ul);

            // Initialize the knob plugin
            // tpl.find('input').knob();

            // Listen for clicks on the cancel icon
            tpl.find('span').click(function(){

                if(tpl.hasClass('working')){
                    jqXHR.abort();
                }

                tpl.fadeOut(function(){
                    tpl.remove();
                });

            });

            // Automatically upload the file once it is added to the queue
            $('#confirm').click(function(){
               // alert(data);
               data.submit().success(function (data) {
                   if(data.success == "true"){
                       alert("success");
                       $('#resultPage').css("display", "block");
                       findCase(data.caseID);

                       // $('.title').text(data.);
                   }
                   else{
                       alert("fail");
                   }
               });
               // alert(j);
               // if(j.success == "true"){
               //     alert("success");
               // }else{
               //     alert("fail");
               // }
            });
            // var jqXHR = data.submit();
        },

        progress: function(e, data){

            // Calculate the completion percentage of the upload
            var progress = parseInt(data.loaded / data.total * 100, 10);

            // Update the hidden input field and trigger a change
            // so that the jQuery knob plugin knows to update the dial
            data.context.find('input').val(progress).change();

            if(progress == 100){
                data.context.removeClass('working');
            }
        },

        fail:function(e, data){
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
    $.ajax({
        url: "/manageAction/findCase",
        type: "POST",
        dataType: "json",
        data: {"id": id},
        async: false,
        success: function (data) {
            // alert(data.success);
            if (data && data.success == "true") {
                $('.title').text("无");
                $('.caseNumber').text("无");
                $('.origin_document').text("无");
                $('#courtName').text("无");
                $('#property').text("无");
                $('#reason').text("无");
                $('#process').text("无");
                $('#endDate').text("无");
                $('#litigant').text("无");
                $('#evidence').text("无");
                $('#keywords').text("无");
                if(data.content.title != null)
                    $('.title').text(data.content.title);
                if(data.content.id != null)
                    $('.caseNumber').text(data.content.id);
                if(data.content.originDocument != null)
                    $('.origin_document').text(data.content.originDocument);
                if(data.content.court != null)
                    $('#courtName').text(data.content.court);
                if(data.content.property != null)
                    $('#property').text(data.content.property);
                if(data.content.reason != null)
                    $('#reason').text(data.content.reason);
                if(data.content.process != null)
                    $('#process').text(data.content.process);
                if(data.content.endDate != null)
                    $('#endDate').text(data.content.endDate);
                if(data.content.litigant != null)
                    $('#litigant').text(data.content.litigant);
                if(data.content.evidence != null)
                    $('#evidence').text(data.content.evidence);
                if(data.content.keywords != null)
                    $('#keywords').text(data.content.keywords);
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