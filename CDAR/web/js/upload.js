/**
 * Created by island on 2017/7/19.
 */
function upload() {
    // var tpl = $('<li class="working"><input type="text" value="0" data-width="48" data-height="48"'+
    //     ' data-fgColor="#0788a5" data-readOnly="1" data-bgColor="#3e4043" /><p></p><span></span></li>');
    // alert(tpl.find('p').val());
    // alert($('#file').val());

    var ul = $('#upload ul li p');

    alert(ul.text().split("<")[0]);

    $.ajaxFileUpload({
        url: '/CDAR/manageAction/upload',
        secureuri: false,
        fileElementId: 'file',
        dataType: 'json',
        success: function (data) {
            success_alert(data.success);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            fail_alert("上传失败");
            // alert(XMLHttpRequest.status);
            // alert(XMLHttpRequest.readyState);
            // alert(textStatus);
        }
    });
}