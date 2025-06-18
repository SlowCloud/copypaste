# CopyPaste

텍스트 공유 서비스

## 사용 설명서

### 환경변수 설정하기

- `JWT_PUBLIC_KEY`: 공개키를 등록해주세요. JWT 인증에 사용됩니다.
- `JWT_PRIVATE_KEY`: 개인키를 설정해주세요. JWT 발급에 사용됩니다.
- `COPYPASTE_SQL_USERNAME`: MySQL의 사용자명입니다. 생략 시 `root`로 적용됩니다.
- `COPYPASTE_SQL_PASSWORD`: MYSQL의 사용자 비밀번호입니다. 생략 시 공란으로 적용됩니다.
- `COPYPASTE_GCP_SQL_NAME`: 사용할 SQL의 DB 이름입니다.
- `COPYPASTE_GCP_INSTNANCE_CONNECTION_NAME`: SQL 인스턴스의 연결 이름입니다.
  - 생성한 SQL 인스턴스의 `연결 이름` 항목을 복사하여 입력해주세요.
  - 자세한 내용은 [다음 링크](https://googlecloudplatform.github.io/spring-cloud-gcp/6.2.2/reference/html/index.html#cloud-sql-configuration-properties)를 참고해주세요.

Google ADC 설정 등을 위해, 가급적 GCP 환경 내에서 실행하는 것을 권장드립니다.

### 키페어 생성하기

JWT 생성 및 인증 관리를 위해, 개인키 / 공개키 생성을 진행해야 합니다.

다음은 `openssl`을 활용하여 키쌍을 생성하는 방법입니다.
다음 아래의 명령어를 입력하여 키페어를 생성해주세요.

```shell
openssl genrsa -out private.pem 2048
openssl rsa -in private.pem -pubout -out public.pem
```
