const public = {
    "https://www.pdhstumu.tistory.com" : true
} // 공개 클레임
//  충돌이 방지된  이름을 가지고 있어야 한다  클래스 이름을 URI 로 지어야한다.
const private = {
    "username": "velopert"
} // 비공개 (private) 클레임
// 등록된 클레임도아니고, 공개된 클레임들도 아니다
// 클라이언트 서버간의 임의로 사용되는 클레임 이름


// 예신
const payload = {
    "iss": "pdhstumu.tistory.com", // iss: 토큰 발급자
    "exp": "19110129191011", // exp: 토큰의 만료시간
    "https://www.pdhstumu.tistory.com": true,
    "userId": "20153229", // 비공개 클레임
    "username": "bodybuilder"
};

// encode to base64
const encodedPayload =  Buffer.from(JSON.stringify(payload))
    .toString('base64')
    .replace('=', '');

console.log('payload: ',encodedPayload);