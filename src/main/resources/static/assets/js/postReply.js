
import { BASE_URL } from "./reply.js";
import { renderReplies } from "./getReply.js";


// 서버에 댓글 등록을 요청하는 비동기 함수
const textInput = document.getElementById('newReplyText')
const writerInput =document.getElementById('newReplyWriter')



// 화살표 함수로 작성하려면 () 앞에 async 붙임
export const fetchReplyPost = async () => {

    // 서버로 보낼 데이터
    const payload = {
        // ReplyDetailDto 의 필드명과 맞춰준다
        text: textInput.value,
        author: writerInput.value,
        bno: document.getElementById('wrap').dataset.bno
    };
    console.log(payload)

    const res = await fetch(`${BASE_URL}`, {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(payload)
    });

    const replies = await res.json();
    textInput.value = '';
    writerInput.value = '';

    renderReplies(replies)
    // window.scrollTo(0,900)


}

