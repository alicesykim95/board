function joinBoard() {

    document.getElementById("userName").value = document.getElementById("userName").value.trim();
    if (document.getElementById("userName").value === 0){
        alert("이름을 입력해주세요.");
        document.getElementById("userName").focus();
        return false;
    }

    document.getElementById("userEmail").value = document.getElementById("userEmail").value.trim();
    if (document.getElementById("userEmail").value === 0){
        alert("이메일을 입력해주세요.");
        document.getElementById("userEmail").focus();
        return false;
    }

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
        userPassword: document.getElementById("userPassword").value,
        userName: document.getElementById("userName").value,
        userEmail: document.getElementById("userEmail").value
    }

    $.ajax({
        url: '/userJoin',
        type: 'POST',
        data: memberList,
        success: function() {
            alert("성공하였습니다.");
            location.href="/boardListPage";
        },
        error: function() {
            alert("실패하였습니다.");
        }

    });

}