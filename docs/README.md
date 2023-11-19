# Documentations



## Settings

##### 환경

- JDK 17

- Spring Boot 3.1.5
- MySQL 8.0.35

<br>

##### MySQL

- dba 권한이 있는 유저(root or admin) 호스트로 접속

- 유저 생성 및 권한 부여

  ```sql
  -- moi 유저 생성 및 비밀번호 설정 / 권한 부여
  -- application.yml 내 dataSource 정보 참조
  CREATE USER 'moi'@'%' IDENTIFIED BY 'MoiUser12!';
  GRANT ALL PRIVILEGES ON *.* TO 'moi'@'%';
  
  -- 변경사항 적용
  FLUSH PRIVILEGES;
  ```

- table 생성

  - `/resources/sql/ddl/ddl.sql` ddl 쿼리로 테이블 생성

<br>

##### 서버 동작 확인

- 서버 실행
- `http://localhost:8080/healthcheck` 호출 후 `ok` 응답 확인

