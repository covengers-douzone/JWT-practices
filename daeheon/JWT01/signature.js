const crypto = require('crypto');

const header = {
    "typ": "JWT", // typ: 토큰의 타입을 지정
    "alg": "HS256" // 해싱 알고리즘 지정
};
const payload = {
    "iss": "pdhstumu.tistory.com", // iss: 토큰 발급자
    "exp": "19110129191011", // exp: 토큰의 만료시간
    "https://www.pdhstumu.tistory.com": true,
    "userId": "20153229", // 비공개 클레임
    "username": "bodybuilder"
};
// encode to base64
const encodedHeader = Buffer.from(JSON.stringify(header))
    .toString('base64') //  base64 로 인코딩 되는 과정에서 사라짐
    .replace('=', '');


// encode to base64
const encodedPayload =  Buffer.from(JSON.stringify(payload))
    .toString('base64')
    .replace('=', '');



// 헤더 + . + 정보 -> 비밀키의 값을 secrete  으로 해싱 후 ,  base64로 인코딩

const signature = crypto.createHmac('sha256', 'secret')
    .update(encodedHeader + '.' + encodedPayload)
    .digest('base64')
    .replace('=', '');

console.log('signature: ',signature);