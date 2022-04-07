function loginBoard() {

    const loginInfo = {
        userId: document.getElementById("userId").value,
        userPassword: document.getElementById("userPassword").value
    }

    $.ajax({
        url: '/userLogin',
        type: 'POST',
        data: loginInfo,
        success: function (data) {
            if (data === 1) {
                alert("성공하였습니다.");
                location.href = "/";
            } else {
                alert("실패하였습니다.");
            }
        },
        error: function () {
            alert("실패하였습니다.");
        }
    });

}