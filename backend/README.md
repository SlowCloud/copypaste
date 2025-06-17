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

### 환경변수 설정하기

#### 키페어 등록

JWT 인증 구현을 위해, 생성된 키 파일의 내용을 환경변수로 등록해주세요.
생성된 키페어는 `SecurityConfig`에서 가져와 사용하게 됩니다.

공개키는 `JWT_PUBLIC_KEY`,
개인키는 `JWT_PRIVATE_KEY`로 등록해주면 됩니다.

#### SQL `USERNAME`, `PASSWORD` 등록

SQL에서 사용하는 `USERNAME`, `PASSWORD`를 등록해주세요.

`USERNAME`은 `COPYPASTE_SQL_USERNAME`,
`PASSWORD`는 `COPYPASTE_SQL_PASSWORD`에 등록해주시면 됩니다.

`USERNAME`은 기본값으로 `root`, `PASSWORD`는 공란으로 설정됩니다.

### `application.properties` 수정하기

Spring Cloud GCP를 통해 SQL을 사용하기 위해서,
`spring.cloud.gcp.sql.database-name`과 `spring.cloud.gcp.sql.instance-connection-name`를
사용할 프로젝트에 알맞게 수정해주세요.

[다음 링크](https://github.com/GoogleCloudPlatform/spring-cloud-gcp/blob/main/spring-cloud-gcp-samples/spring-cloud-gcp-sql-mysql-sample/README.adoc)
에서 관련 내용을 찾을 수 있습니다.
