

// 전역변수
const $email = document.getElementById('user_email')
const $emailMsg = document.getElementById('emailChk')


async function fetchDuplicateEmailCheck($email) {
    const res = await fetch(`http://localhost:8383/members/check?type=email&keyword=${$email}`)
    const flag = await res.json();
    emailFlag = flag
}
    let emailFlag = false;

$email.addEventListener('keyup', async e => {
    // 이메일 검사 정규표현식
    const emailPattern = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

    let emailValue = $email.value;

    if (emailValue.trim() === '') {
        $email.style.borderColor = 'red';
        $emailMsg.innerHTML = '<b class="warning">[이메일은 필수값입니다.]</b>';
    } else if (!emailPattern.test(emailValue)) {
        $email.style.borderColor = 'red';
        $emailMsg.innerHTML = '<b class="warning">[@를 필수로 포함해야 합니다.]</b>';
    } else {
        await fetchDuplicateEmailCheck(emailValue);

        if (emailFlag) {
            $email.style.borderColor = 'red';
            $emailMsg.innerHTML = '<b class="warning">[이메일이 중복되었습니다. 다른 이메일을 사용하세요!]</b>';
        } else {
            $email.style.borderColor = 'skyblue';
            $emailMsg.innerHTML = '<b class="success">[사용가능한 이메일입니다.]</b>';
            emailConfirm = true;

        }


    }

})

