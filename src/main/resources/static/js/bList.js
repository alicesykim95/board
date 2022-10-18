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

    if(boardCheckList === ""){
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
