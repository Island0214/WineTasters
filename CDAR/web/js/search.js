/**
 * Created by island on 2017/7/16.
 */
function search() {
    var input = $('#textField').val();
    alert(input);
    $.ajax({
        url: "/manageAction/searchCase",
        type: "POST",
        // dataType: "json",
        data: {"data": input},
        async: false,
        success: function (data) {
            alert("success");
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("error");
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}