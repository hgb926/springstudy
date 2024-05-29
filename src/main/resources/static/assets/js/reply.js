import {fetchInfScrollReplies, fetchReplies, setupInfiniteScroll} from "./getReply.js";
import { fetchReplyPost } from "./postReply.js";


// ====== 전역 변수 ========
export const BASE_URL = 'http://localhost:8383/api/v1/replies';

// export const bno = document.getElementById('wrap').dataset.bno;
// 유의할 점.
// const bno = '${bbb.boardNo}'; detail.jsp에서는 사용 되는데 다른 파일에서는 안된다.
// 같은 파일에 있으면 bbb를 사용할 수 있는데, 다른 파일에서 사용하려면 그 파일에서 데이터속성으로 심어놔야 한다.

// ====== 함수 정의 ========


// ====== 실행 코드 ========


// 댓글 목록 서버에서 불러오기
fetchInfScrollReplies() // 일단 1페이지 데이터 그려놓기
setupInfiniteScroll() // 무한 스크롤 이벤트 등록

// 댓글 작성 이벤트 등록
document.getElementById('replyAddBtn').addEventListener('click', e => {
   // 댓글 등록 로직
    fetchReplyPost();

});

// 댓글 페이지 클릭 이벤트 등록
// replyPageClickEvent();





