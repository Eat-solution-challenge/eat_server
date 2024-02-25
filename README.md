## 📍 Version
- openjdk version "17.0.10" 2024-01-16 LTS
- SpringBoot 3.2.1

## 📱 How to install
1. git clone
   ```
   git clone https://github.com/Eat-solution-challenge/eat_server.git
   ```
2. Set application-secret.yml file
     ```
      spring:
       datasource:
         driver-class-name: com.mysql.cj.jdbc.Driver
         url: {your_database_url}
         username: {your_database_username}
         password: {your_database_password}
      jwt:
       secret: {your_jwt_secret_key}
     ```
3. Set main.iml file
    ```
    <?xml version="1.0" encoding="UTF-8"?>
    <module type="JAVA_MODULE" version="4">
       <component name="NewModuleRootManager" inherit-compiler-output="true">
       <exclude-output />
       <content url="file://$MODULE_DIR$">
         <sourceFolder url="file://$MODULE_DIR$/java" isTestSource="false" />
       </content>
       <orderEntry type="inheritedJdk" />
       <orderEntry type="sourceFolder" forTests="false" />
     </component>
   </module>    
    ```
