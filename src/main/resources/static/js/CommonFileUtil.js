// 파일 넘버
let fileNum = 0;

// 파일 현재 필드 갯수
let fileCount = 0;

// 전체 업로드 갯수
let fileTotalCount = 5;

// 첨부파일 배열
let fileList = [];

// 파일 넘버들
let fileNums = "";

// 삭제 파일 넘버 배열
let deleteFileList = [];

// 기존 파일 갯수
let existingFileCount = 0;
let FileCountfromBoard =  parseInt($('.download_attach_div').length);

if( FileCountfromBoard != null){
    existingFileCount = FileCountfromBoard;
}


// 게시물 저장: 파일 추가 버튼 클릭시 숨어있던 input[type="file"] 동작
function writeFileClick(){
    $("#write_hidden_file").click();
}

// 게시물 수정: 파일 추가 버튼 클릭시 숨어있던 input[type="file"] 동작
function detailFileClick(){
    $("#detail_hidden_file").click();
}

// 게시물 수정: 삭제 파일 번호 등록
function registerDeleteNum(target){
    let deleteNum = target.parentNode.previousElementSibling.value;
    deleteFileList.push(deleteNum);
    target.parentNode.parentNode.remove();
    existingFileCount--;
}

// 첨부한 파일 배열에 담기 및 개수 검열
function fileAttach(target){

    let fileAttachId = target.id;

    let fileListContainer = target.parentNode.nextElementSibling.id;

    let files = target.files;

    let fileArray = Array.prototype.slice.call(files);

    if(fileCount + existingFileCount + fileArray.length > fileTotalCount){
        alert("파일은 최대 10개까지 업로드 할 수 있습니다.");
        return false;
    } else{
        fileCount = fileCount + fileArray.length;
    }


    fileArray.forEach(function(f){
        let reader = new FileReader();
        reader.onload = function(e){
            fileList.push(f);

            $('#' + fileListContainer).append(
                '<div id="file' + fileNum + '" style="padding: 15px 0 5px 0;" class="download_file" onclick="deleteFile(\'file' + fileNum + '\')">'
                + '<p style="font-size: 13px; color: #3a3a3a; float: left;">' + f.name + '</p>'
                + '<img src="../images/delete_icon.png" alt="delete_icon" style="width: 12px; height: 12px; margin-left: 5px; filter: invert(17%) sepia(0%) saturate(548%) hue-rotate(163deg) brightness(98%) contrast(76%); cursor: pointer; float: left;">'
                + '<br>'
                + '</div>'
            );
            fileNum++;
        };
        reader.readAsDataURL(f);
    });
    $('#' + fileAttachId).val("");
}

// 첨부파일 배열에서 삭제
function deleteFile(fileNum){
    let num = fileNum.replace(/[^0-9]/g, "");
    fileList[num].is_delete = true;
    $('#' + fileNum).remove();
    fileCount--;
}

// 게시글 작성 액션
function registerAction(){
    loadFileRegisterAjax('/uploadFile', 'POST','registerBoard');
}

// 게시글 수정 액션
function updateAction(){
    loadFileRegisterAjax('/uploadFile', 'POST', 'updateBoard')
}


// step1: DB에 파일 리스트 저장
function loadFileRegisterAjax(link, method, nextAction) {

    let nextActionValue = nextAction;

    let form = $("form")[0];
    const formData = new FormData(form);

    for (let i = 0; i < fileList.length; i++) {
        if (!fileList[i].is_delete){
            formData.append("fileList", fileList[i]);
        }
    }

    $.ajax({
        url: link,
        type: method,
        data: formData,
        encType : 'multipart/form-data',
        processData: false,
        contentType: false,
        success: function(data) {

            fileNums = data.fileNums;

            if (data.result === "SUCCESS"){
                window[nextActionValue]();
            } else{
                alert("파일등록에 오류가 있습니다.");
            }
        },
        error: function(xhr, status, error) {
            alert("파일등록에 오류가 있습니다.");
        }
    })
}

// step2-1: 넘어온 파일 넘버 리스트와 함께 게시물 저장
function registerBoard() {

    const dataList = {
        writer : document.getElementById("writer").value,
        title : document.getElementById("title").value,
        content : document.getElementById("content").value,
        fileNums: fileNums
    }

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

// step2-2: 넘어온 파일 넘버 리스트와 함께 게시물 수정