## JSON Web Token (JWT)

#### JWT comprises 3 parts Header, Payload and Verify Signature

## JWT Example
#### 1. Header : Algorithm & Token Type
      {
      "alg": "HS256",
      "typ": "JWT"
      }


#### 2. Payload: Data
   {
   "sub": "1234567890",
   "name": "John Doe",
   "iat": 1516239022
   }


#### 3. Verify Signature:
   It uses the secret base64 encoded value

    Example: D0CYbB51NrVpBSD-YSlF2kptsALGgnbsyq3a2SfIhP4

#### Whole encoded token should look like this

    eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.D0CYbB51NrVpBSD-YSlF2kptsALGgnbsyq3a2SfIhP4



## JWT Authentication Flow:
 1. Client sends request to the server using the user credentials.
    

 2. Server validates the user credentials from request in the database if they are valid


 3. Server provides the jwt token back to the client only if the server finds the user credentials are valid from the previous step. Otherwise the server may throw an 401 or 403 forbidden response back.


 4. Client uses the jwt token in the request header to access the api and server validates if the token from the request header is a valid token then if it is valid then the Client can access the api resource



## JWT Dependencies

    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.11.5</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-impl</artifactId>
        <version>0.11.5</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-jackson</artifactId>
        <version>0.11.5</version>
    </dependency>
    <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-security</artifactId>
      </dependency>
