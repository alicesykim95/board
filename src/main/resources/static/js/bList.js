// 체크박스 전체 선택 및 해제 이벤트
function allCheck(target){

    if($(target).is(":checked")){
        $(".rowCheck").prop("checked", true);
    }
    else {
        $(".rowCheck").prop("checked", false);
    }

}

// 개별 체크박스 선택 이벤트
function rowCheck(){

    let allCount = $("input:checkbox[name=rowCheck]").length;

    let checkedCount = $("input:checkbox[name=rowCheck]:checked").length;

    if(allCount === checkedCount){
        $(".allCheck").prop("checked", true);
    }
    else{
        $(".allCheck").prop("checked", false);
    }

}

// 체크박스 선택 게시글 삭제
function deleteBoardList(){
    let boardCheckList = [];
    let list = $("input[name=rowCheck]");
    for (let i = 0; i < list.length; i++) {
        if (list[i].checked){
            boardCheckList.push(list[i].value);
        }
    }

    if(boardCheckList == ""){
        alert("삭제할 항목을 선택해주세요.");
        return false;
    }

    $.ajax({
        url: '/deleteBoardList',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(boardCheckList),
        success: function (){
            alert("성공적으로 삭제되었습니다.");
            location.reload();
        },
        error: function(){
            alert("실패하였습니다.")
        }
    });
}

// 검색
// function search() {
//
//     const keyword = $("#keyword").val();
//
//     $.ajax({
//         url: '/getSearchList',
//         type: 'POST',
//         contentType: 'application/json',
//         data: {keyword: keyword},
//         success: function(result){
//             console.log(result);
//             $("#table_body_container").empty();
//             let html = "";
//             $.each(result, function(idx, list){
//                 html += '<tr>';
//                 html += '<td class="list_td"><input type="checkbox" class="rowCheck" name="rowCheck" onClick="rowCheck()/></td>';
//                 html += '<td class="list_td list_td_no">"+ list.boardNum +"</td>';
//                 html += '<td class="list_td list_title"><a href="/ '+list.boardNum +'">' + list.title + '<small class="board_list_comment_count">' + list.commentCount + ' </small></a></td>';
//                 html += '<td class="list_td">' + list.writer + '</td>';
//                 html += '<td class="list_td">' + list.createdTime + '</td>';
//                 html += '<td class="list_td">' + list.hitCnt + '</td>';
//                 html += '<tr>';
//             });
//             $("#table_body_container").append(html);
//         },
//         error: function (){
//             alert("실패하였습니다.");
//         }
//     })
// }