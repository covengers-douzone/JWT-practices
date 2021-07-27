const header = {
    "typ": "JWT", // typ: 토큰의 타입을 지정
    "alg": "HS256" // 해싱 알고리즘 지정
};

// encode to base64
const encodeedHeader = Buffer.from(JSON.stringify(header))
    .toString('base64') //  base64 로 인코딩 되는 과정에서 사라짐
    .replace('=', '');
console.log('header',encodeedHeader);
