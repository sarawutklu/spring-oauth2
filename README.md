# spring-oauth2

<!--
```
@startuml

actor User

participant Client
participant AuthorizationServer
participant Microservices

User -> Client: Initiate OAuth 2.0 PKCE flow
Client -> AuthorizationServer: Send Authorization Request with code challenge
AuthorizationServer -> User: Prompt for Authorization
User -> AuthorizationServer: Grant Authorization
AuthorizationServer -> Client: Send Authorization Code and code challenge
Client -> AuthorizationServer: Send code verifier and code along with the request for Token
AuthorizationServer -> Client: Send Access Token
Client -> Microservices: Send Access Token with the request
Microservices -> Client: Send the requested data

@enduml

```
-->