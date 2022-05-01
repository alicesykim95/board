// 파일 넘버
let fileNum = 0;

// 파일 현재 필드 갯수
let fileCount = 0;

// 전체 업로드 갯수
let totalCount = 10;

// 첨부파일 배열
const fileList = [];

// 파일 넘버들
let fileNums = "";


// 파일 추가 버튼 클릭시 숨어있던 input[type="file"] 동작
function fileClick(){
    $("#write_hidden_file").click();
}

// 첨부한 파일 배열에 담기 및 개수 검열
function fileAttach(target){

    let files = target.files;

    let fileArray = Array.prototype.slice.call(files);

    if(fileCount + fileArray.length > totalCount){
        alert("파일은 최대 10개까지 업로드 할 수 있습니다.");
        return false;
    } else{
        fileCount = fileCount + fileArray.length;
    }


    fileArray.forEach(function(f){
        let reader = new FileReader();
        reader.onload = function(e){
            fileList.push(f);

            $('#file_list').append(
                '<div id="file' + fileNum + '" style="padding-bottom: 10px;" onclick="deleteFile(\'file' + fileNum + '\')">'
                + '<p style="font-size: 12px; color: #3a3a3a; float: left;">' + f.name + '</p>'
                + '<img src="../images/103181_close_remove_delete_cross_icon.png" alt="delete_icon" style="width: 10px; height: 10px; margin-left: 5px; filter: invert(17%) sepia(0%) saturate(548%) hue-rotate(163deg) brightness(98%) contrast(76%); cursor: pointer; float: left;">'
                + '</div>'
                + '<br>'
            );
            fileNum++;
        };
        reader.readAsDataURL(f);
    });
    $("#write_hidden_file").val("");
}

// 첨부파일 배열에서 삭제
function deleteFile(fileNum){
    let num = fileNum.replace(/[^0-9]/g, "");
    fileList[num].is_delete = true;
    $('#' + fileNum).remove();
    fileCount--;
}

// step1: DB에 파일 리스트 저장
function registerAction() {

    console.log("fileList" + fileList);

    let form = $("form")[0];
    const formData = new FormData(form);

    for (let i = 0; i < fileList.length; i++) {
        if (!fileList[i].is_delete){
            formData.append("fileList", fileList[i]);
        }
    }

    $.ajax({
        url: '/uploadFile',
        type: 'POST',
        data: formData,
        encType : 'multipart/form-data',
        processData: false,
        contentType: false,
        success: function(data) {
            console.log(data);
            fileNums = data.fileNums;

            if (data.result === "SUCCESS"){
                registerBoard();
            } else{
                alert("파일등록에 오류가 있습니다.");
            }
        },
        error: function(xhr, status, error) {
            alert("파일등록에 오류가 있습니다.");
        }
    })
}

// step2: 넘어온 파일 넘버 리스트와 함께 게시물 저장
function registerBoard() {

    console.log(fileNum);

    const dataList = {
        writer : document.getElementById("writer").value,
        title : document.getElementById("title").value,
        content : document.getElementById("content").value,
        fileNums: fileNums
    }

    console.log(dataList)

    $.ajax({
        url:'/board',
        type:'POST',
        data: JSON.stringify(dataList),
        contentType: 'application/json',
        success: function () {
            alert("성공하였습니다.");
            location.href="/";
        },
        error: function (){
            alert("실패하였습니다.");
        }
    });

}

