function joinBoard() {

    document.getElementById("userId").value = document.getElementById("userId").value.trim();
    if (document.getElementById("userId").value === 0){
        alert("아이디를 입력해주세요.");
        document.getElementById("userId").focus();
        return false;
    }

    if (document.getElementById("userId").value < 4){
        alert("아이디를 4글자 이상 입력해주세요.");
        document.getElementById("userId").focus();
        return false;
    }

    document.getElementById("userPassword").value = document.getElementById("userPassword").value.trim();
    if(document.getElementById("userPassword").value === 0){
        alert("비밀번호를 입력해주세요.");
        document.getElementById("userPassword").focus();
        return false;
    }

    document.getElementById("userPasswordConfirm").value = document.getElementById("userPasswordConfirm").value.trim();
    if(document.getElementById("userPassword").value !== document.getElementById("userPasswordConfirm").value){
        alert("비밀번호가 일치하지 않습니다.");
        return false;
    }

    const memberList = {
        userId: document.getElementById("userId").value,
        userPassword: document.getElementById("userPassword").value
    }

    $.ajax({
        url: '/userJoin',
        type: 'POST',
        data: memberList,
        success: function() {
            alert("성공하였습니다.");
            location.href="/home";
        },
        error: function() {
            alert("실패하였습니다.");
        }

    });

}