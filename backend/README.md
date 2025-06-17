# CopyPaste

텍스트 공유 사이트

## 사용 설명서

### 키페어 생성하기

서버를 만들기 전, 개인키 / 공개키 생성을 진행해야 합니다.

다음은 `openssl`을 활용하여 키쌍을 생성하는 방법입니다.
다음 아래의 명령어를 입력하여 키페어를 생성해주세요.

```shell
openssl genrsa -out private.pem 2048
openssl rsa -in private.pem -pubout -out public.pem
```

이후 환경변수로 해당 키들을 등록해줍니다. 생성된 키 파일의 내용을 환경변수로 등록해주세요.

공개키는 `JWT_PUBLIC_KEY`, 개인키는 `JWT_PRIVATE_KEY`로 등록해주면 됩니다.

생성된 키페어는 `SecurityConfig`에서 가져와 사용하게 됩니다.
