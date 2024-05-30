
// 전역 변수 영역

const $firstPw = document.getElementById('password');
const $secondPw = document.getElementById('password_check');
const $firstMsg = document.getElementById('pwChk')
const $secondMsg = document.getElementById('pwChk2')



$firstPw.addEventListener('keyup', e => {
    const passwordPattern = /([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/;
    const first = $firstPw.value;
    if (!passwordPattern.test(first)) {
        $firstPw.style.borderColor = 'red'
        $firstMsg.innerHTML = '<b class="warning">[특수문자, 영문, 숫자를 1개씩 포함해야 합니다.]</b>';
    } else {
        $firstPw.style.borderColor = 'skyblue';
        $firstMsg.innerHTML = '<b class="success">[사용가능한 비밀번호입니다.]</b>';
    }
})
// 패스워드 체크

// 첫 pw 값을 가지고 비교 해야 함으로 한 함수 안에서 두개 비교
$secondPw.addEventListener('keyup',e => {

    // 패스워드 검사 정규표현식

    // 입력값 읽어오기
    const first = $firstPw.value;
    const second = $secondPw.value;




    if (first === second) {
        $secondPw.style.borderColor = 'skyblue'
        $secondMsg.innerHTML = '<b class="success">[동일한 값입니다!]</b>';

    } else if (first.length === second.length && first !== second) {
        $secondPw.style.borderColor = 'red'
        $secondMsg.innerHTML = '<b class="warning">[비밀번호를 재확인 해주세요!]</b>';
    } else if (first.length < second.length) {
        $secondPw.style.borderColor = 'red'
        $secondMsg.innerHTML = '<b class="warning">[비밀번호를 재확인 해주세요!]</b>';
    }

    // 정규식 검사

});