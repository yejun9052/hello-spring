# Spring Boot 기초 강의 1일차

## 정적 컨텐츠
- 그냥 html파일을 그대로 전달해준다

## mvc와 template 엔진
- 서버에서 불러온 데이터를 view가 화면에 끼워넣어 HTML을 구성한다.

## api
- 객체를 전달해준다. (json)

## MVC

Model = Controller가 요청을 받고 전달해줄 데이터를 담는 곳

View = Controller에게 전달받은 데이터로 화면(html)을 만드는곳

Controller = 요청을 받고 어떻게 처리할지 결정하는곳

localhost:8080/hello 라고 요청을 보내면 Controller가 요청을 받고 어떻게 처리할지 결정한 다음 Model에게 데이터를 담아서 View에게 전달해주면 View는 Model에 담겨있는 데이터를 토대로 화면(HTML)을 구성한다.

## HTTP 메시지 컨버터

서버가 전달하거나 서버에 전달하는 내용을 양쪽 모두가 알아볼 수 있는 형식( default : JSON ) 으로 변환해준다.

```jsx
@RequestBody -> JSON 에서 객체 (들어올 때)
@ResponseBody -> 객체 에서 JSON (나갈 때)
```

에서 지시된다.

## [README.md](../README.md)