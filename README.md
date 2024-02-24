## 📍 Version
- openjdk version "17.0.10" 2024-01-16 LTS
- SpringBoot 3.2.1

## 📱 How to install
1. git clone
   ```
   git clone https://github.com/Eat-solution-challenge/eat_server.git
   ```
3. Set application-secret.yml file
  ```java
   spring:
    datasource:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: {your_database_url}
      username: {your_database_username}
      password: {your_database_password}
   jwt:
    secret: {your_jwt_secret_key}
  ```
