let fileNum;

function registerAction() {
    console.log($('#uploadFile'));
    const uploadFile = $('#uploadFile')[0].files[0];
    const formData = new FormData;

    formData.append("uploadFile", uploadFile);

    $.ajax({
        url: '/uploadFile',
        type: 'POST',
        data: formData,
        encType : 'multipart/form-data',
        processData: false,
        contentType: false,
        success: function(data) {
                console.log(data);
                fileNum = data;
        },
        error: function(xhr, status, error) {
            alert("파일등록에 오류가 있습니다.");
        },
        complete: function () {
            registerBoard();
        }

    })
}

function registerBoard() {

    console.log(fileNum);

    const dataList = {
        writer : document.getElementById("writer").value,
        title : document.getElementById("title").value,
        content : document.getElementById("content").value,
        fileNum: fileNum
    }

    // formData.append("writer", document.getElementById("writer").value);
    // formData.append("title", document.getElementById("title").value);
    // formData.append("content", document.getElementById("content").value);
    console.log(dataList)
    $.ajax({
        url:'/board',
        type:'POST',
        data: dataList,
        success: function () {
            alert("성공하였습니다.");
            location.href="/";
        },
        error: function (){
            alert("실패하였습니다.");
        }
    });

}

