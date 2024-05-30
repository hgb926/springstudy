
const $userName = document.getElementById('user_name');


$userName.addEventListener('keyup', e => {

    // 이름 검사 정규표현식, 한글로만 작성할 수 있도록
    const namePattern = /^[가-힣]+$/;
    const $nameMsg = document.getElementById('nameChk')
    const nameValue = $userName.value;

    if (nameValue.trim() === '') {
        $userName.style.borderColor = 'red';
        $nameMsg.innerHTML =  '<b class="warning">[이름은 필수값입니다.]</b>';
    } else if (!namePattern.test(nameValue)) {
        $userName.style.borderColor = 'red';
        $nameMsg.innerHTML =  '<b class="warning">[이름은 한글로만 작성 가능합니다.]</b>';
    } else {
        $userName.style.borderColor = 'skyblue';
        $nameMsg.innerHTML =  '<b class="success">[사용 가능한 아이디입니다.]</b>';

    }

})