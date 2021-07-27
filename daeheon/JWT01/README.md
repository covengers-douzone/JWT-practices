- JWT 는 . 을 구분자로 3가지의 문자열
  
> aaaaa.bbbbb.ccccc
- a : 헤더 
- b : 내용 
- c : 서명

> #### 요약 
[header]
```javascript
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

const encodeedHeader = Buffer.from(JSON.stringify(header))
    .toString('base64') //  base64 로 인코딩 되는 과정에서 사라짐
    .replace('=', '');

const encodedPayload =  Buffer.from(JSON.stringify(payload))
    .toString('base64')
    .replace('=', '');

const signature = crypto.createHmac('sha256', 'secret')
    .update(encodedHeader + '.' + encodedPayload)
    .digest('base64')
    .replace('=', '');


// header : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9 
// payload :  eyJpc3MiOiJwZGhzdHVtdS50aXN0b3J5LmNvbSIsImV4cCI6IjE5MTEwMTI5MTkxMDExIiwiaHR0cHM6Ly93d3cucGRoc3R1bXUudGlzdG9yeS5jb20iOnRydWUsInVzZXJJZCI6IjIwMTUzMjI5IiwidXNlcm5hbWUiOiJib2R5YnVpbGRlciJ9
// signature: cTNYY4y8A6eC9xkpUPzCeMD+CtnSb3UWJcBDkOIXC2I
// encode to base64
```
- header 값 . payload 값 . signature값  이런식으로 토큰 값이 나온다 

**즉!** 해당된 값을 통해선 
```
eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.
eyJpc3MiOiJwZGhzdHVtdS50aXN0b3J5LmNvbSIsImV4cCI6IjE5MTEwMTI5MTkxMDExIiwiaHR0cHM6Ly93d3cucGRoc3R1bXUudGlzdG9yeS5jb20iOnRydWUsInVzZXJJZCI6IjIwMTUzMjI5IiwidXNlcm5hbWUiOiJib2R5YnVpbGRlciJ9
.cTNYY4y8A6eC9xkpUPzCeMD+CtnSb3UWJcBDkOIXC2I
```
로 토큰값이 발행 되어 진다.



> #### 주의사항 
- 이렇게 까지 코드 작성해서 만들 필요 없다.

- JWT  라이브러리 에서 설정 하면 된다.
 