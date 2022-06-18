function login() {

    let userId = $('input[name="userId"]').val();
    let userPw = $('input[name="userPw"]').val();
    let saveIdCheck = $('#saveIdCheck:checked').val();

    /**
     * localStorage를 활용 아이디 기억
     * 체크박스 선택 상태로 로그인 버튼 클릭
     * 다음 login 페이지로 접속 시 document.ready 시점에 localStorage.getItem("saveId")
     * 값이 '', null, 'N' 중 없을 시에만 id input에 값을 준다
     */

    if (saveIdCheck == 'on') {
        localStorage.setItem("sveId", userId);
    } else {
        localStorage.setItem("saveId", 'N');
    }

    let loginData = {"userId": userId, "userPw": userPw};

    $.ajax({
        type: "POST",
        url:  "loginCheck",
        contentType: "application/json",
        data: JSON.stringify(loginData),
        success: function (result) {
            if (result == 0) {
                alert("아이디와 비밀번호가 틀립니다.");
                return false;
            } else if (result == 9) {
                alert("통신 오류");
                return false;
            } else { //로그인 성공시
                window.location.href = "../../../templates/index.html";
            }
        },
        error: function (jqXHR, status, error) {
            alert("에러 [ " + error + "]");
        }
    });
}
