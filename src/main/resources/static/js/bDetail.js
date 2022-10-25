// 전역 변수
const boardNum = document.getElementById("boardNum").value;
const titleSet = document.getElementById("title");
const contentSet = document.getElementById("content");
const updateBtn = document.getElementById("update");
const completeBtn = document.getElementById("complete");

// 게시글 삭제
function deleteBoard() {
    loadAjax('/board', 'DELETE', {boardNum: boardNum}, true);
}

// 게시글 수정 페이지 이동
function goUpdatePage(){

    updateBtn.setAttribute("type", "hidden");
    completeBtn.setAttribute("type", "button");

    titleSet.readOnly = false;
    titleSet.style.cssText = "border-bottom: 2px solid #01987A; outline: none; background-color: #eeeeee;";

    contentSet.readOnly = false;
    contentSet.style.cssText = "border-bottom: 2px solid #01987A; outline: none; background-color: #eeeeee;";

    let commentImgHtml = '';
    commentImgHtml += "<a><img src='../images/delete_icon.png' alt='delete_icon' class='detail_icon file_attach'></a>";
    $("#downloadFileBtn_container").html(commentImgHtml);

}

// 게시글 수정
function updateBoard() {

    titleSet.readonly = true;
    contentSet.readonly = true;

    const detailList = {
        title: document.getElementById("title").value,
        content: document.getElementById("content").value,
        boardNum: boardNum
    }

    loadAjax('/board', 'PUT', detailList, true);
}

// 게시글 삭제 및 수정 ajax 동작
function loadAjax(link, method, dataList, alertOK) {
    $.ajax({
        url: link,
        type: method,
        data: dataList,
        success: function () {
            if (alertOK) {
                alert("성공 하였습니다.");
                location.href = "/boardListPage";
            }
        },
        error: function () {
            if (alertOK) {
                alert("실패 하였습니다.")
            }
        }
    });
}

// 댓글 등록
function insertComment() {

    const commentList = {
        boardNum: document.getElementById("boardNum").value,
        commentContent: document.getElementById("commentContent").value,
        commentWriter: userId
    }

    $.ajax({
        url: '/comment',
        type: 'POST',
        data: commentList,
        success: function () {
            alert("성공 하였습니다.")
            getCommentList();
            $(".commentContent").val("");
            getCommentCount();
        },
        error: function () {
            alert("실패 하였습니다.");
        }
    });

}

// 초기 페이지 로딩시 댓글 불러오기
$(document).ready(function () {
    getCommentList();
});

// 댓글 리스트 출력
function getCommentList() {

    $.ajax({
        url: '/comment',
        type: 'GET',
        data: {boardNum: boardNum},
        success: function (data) {
            let html = "";
            let cCnt = data.length;

            if (data.length > 0) {
                for (i = 0; i < data.length; i++) {

                    let editHtml = "";
                    let deleteHtml = "";
                    if (userId === data[i].commentWriter) {
                        editHtml = "<input type='button' class='comment_update\" + data[i].commentNum + \"' value='수정' onclick='commentUpdate(\"" + data[i].commentNum + "\", \"" + data[i].commentContent + "\")'>";
                        deleteHtml = "<input type='button' class='comment_delete' value='삭제' onclick='commentDelete(\"" + data[i].commentNum + "\", \"" + data[i].boardNum + "\")'>";
                    } else {
                    }

                    html += "<div class = 'comment_list'>";
                    html += "<div class='comment_list_title'>" + data[i].commentWriter + "</div>";
                    html += "<div class='comment_content_container" + data[i].commentNum + "'>";
                    html += "<div class = 'comment_content' >" + data[i].commentContent + "</div>";
                    html += "<div class = 'comment_time'>" + data[i].commentModiTime + "</div>";
                    html += "<div class = 'comment_list_btn' >";
                    html += editHtml;
                    html += deleteHtml;
                    html += "</div>";
                    html += "</div>";
                    html += "</div>";
                }

            } else {
                html += "<div class='comment_list_none'>";
                html += "<div><p class = 'comment_list_title_none'>등록된 댓글이 없습니다.</p></div>";
                html += "</div>";

            }

            $("#cCnt").html(cCnt);
            $("#getCommentList").html(html);

        }
    });

}

// 댓글 수정: 댓글 내용 출력을 input form으로 변경
function commentUpdate(commentNum, commentContent, boardNum) {
    let html = '';

    html += "<div class='comment_content_container'>";
    html += "<input type='text'  class='commentContent_update' id ='commentContent" + commentNum + "' value='" + commentContent + "'/>"
    html += "<input type='button' id='commentBtn' value='완료' onclick='commentUpdatePro(" + commentNum + ")'/>";
    html += "<div class='comment_list_btn'>";
    html += "<input type='button' class='comment_cancel' value='취소' onclick='commentCancel()'>";
    html += "<input type='button' class='comment_delete' value='삭제' onclick='commentDelete(\"" + commentNum + "\", \"" + boardNum + "\")'>";
    html += "</div>";
    html += "</div>";

    $(".comment_content_container" + commentNum).html(html);
}

// 댓글 수정 취소
function commentCancel() {
    getCommentList();
}

// 댓글 수정 데이터 전송
function commentUpdatePro(commentNum) {

    $.ajax({
        url: '/comment',
        type: 'PUT',
        data: {
            commentContent: document.getElementById("commentContent" + commentNum + "").value,
            commentNum: commentNum
        },
        success: function () {
            getCommentList();
        }
    });

}

// 댓글 삭제
function commentDelete(commentNum) {

    $.ajax({
        url: '/comment',
        type: 'DELETE',
        data: {commentNum: commentNum, boardNum: boardNum},
        success: function () {
            alert("삭제되었습니다.");
            getCommentList();
            getCommentCount();
        },
        error: function () {
            alert("삭제를 하지 못하였습니다.");
        }
    });

}

// 댓글 갯수 업데이트
function getCommentCount() {

    $.ajax({
        url: '/board',
        type: 'GET',
        data: {boardNum: boardNum},
        success: function(data){
            $("#comment_cnt").empty();
            $("#comment_cnt").append(data.commentCount);
        },
        error: function(){
            alert("댓글 갯수를 가져오는데 실패하였습니다.")
        }
    });

}



// 파일 다운로드
function fileDownload() {

    $.ajax({
        url: '/downloadFile',
        type: 'GET',
        data: {fileNum: fileNum},
        success: function () {
                alert("다운로드에 성공하였습니다.");
        },
        error: function () {
            alert("다운로드에 실패하였습니다.");
        }
    });

}

// 좋아요 싫어요 변수 선언
let dislikeTotalCount = $("#dislikeTotalCount");
let likeTotalCount = $("#likeTotalCount");
let dislikeBtn = $("#dislikeBtn");
let likeBtn = $("#likeBtn");

// 좋아요
function like(){

    $.ajax({
        url: '/likeInfoUpdate',
        type: 'POST',
        data: {boardNum: boardNum},
        success: function(data){
            if (data.likeCheck === 1){
                likeBtn.attr("src", "../images/like_btn_click.png");
                likeTotalCount.empty();
                likeTotalCount.append(data.likeTotalCount);
            }
            else if (data.likeCheck == 2){
                likeBtn.attr("src", "../images/like_btn.png");
                likeTotalCount.empty();
                likeTotalCount.append(data.likeTotalCount);
            }
            else if (data.likeCheck === 3){
                dislikeBtn.attr("src", "../images/dislike_btn.png");
                dislikeTotalCount.empty();
                dislikeTotalCount.append(data.dislikeTotalCount);

                likeBtn.attr("src", "../images/like_btn_click.png");
                likeTotalCount.empty();
                likeTotalCount.append(data.likeTotalCount);
            }
        },
        error: function(){
            alert("실패하였습니다.")
        }

    })
}

// 싫어요
function dislike(){

    $.ajax({
        url: '/dislikeInfoUpdate',
        type: 'POST',
        data: {boardNum: boardNum},
        success: function(data){
            if (data.dislikeCheck === 1){
                dislikeBtn.attr("src", "../images/dislike_btn_click.png");
                dislikeTotalCount.empty();
                dislikeTotalCount.append(data.dislikeTotalCount);
            }
            else if (data.dislikeCheck === 2){
                dislikeBtn.attr("src", "../images/dislike_btn.png");
                dislikeTotalCount.empty();
                dislikeTotalCount.append(data.dislikeTotalCount);
            }
            else if (data.dislikeCheck === 3){
                likeBtn.attr("src", "../images/like_btn.png");
                likeTotalCount.empty();
                likeTotalCount.append(data.likeTotalCount);

                dislikeBtn.attr("src", "../images/dislike_btn_click.png");
                dislikeTotalCount.empty();
                dislikeTotalCount.append(data.dislikeTotalCount);
            }
        },
        error: function(){
            alert("실패하였습니다.")
        }

    })
}

