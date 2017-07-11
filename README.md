# 네이버 코틀린 SDK (NAVER SDK for Kotlin) 

[![Build Status](https://travis-ci.org/kimsuelim/naver-sdk-kotlin.svg?branch=master)](https://travis-ci.org/kimsuelim/naver-sdk-kotlin)
[![Download](https://api.bintray.com/packages/kimsuelim/maven/naver-sdk-kotlin/images/download.svg) ](https://bintray.com/kimsuelim/maven/naver-sdk-kotlin/_latestVersion)

네이버 오픈API 코틀린 클라이언트 (A Kotlin client for the NAVER OpenAPI)

## Links of Interest

* [네이버 개발자 센터](https://developers.naver.com)
* [API 문서](https://developers.naver.com/docs/common/openapiguide/)
* [Changelog](https://github.com/kimsuelim/naver-sdk-kotlin/blob/master/CHANGELOG.md)
* [네이버 루비 SDK](https://github.com/kimsuelim/naver-sdk-ruby)

## Installation
You should be using jCenter() as a repository for your libraries, if not, add this to your main build.gradle:

```groovy
allprojects {
    repositories {
        jcenter()
    }
}
```

If you're using `Gradle`, add the following line:

Gradle을 이용할 경우 build.gradle에 다음의 내용을 추가하시면 됩니다:

```groovy
compile 'org.naver.sdk:kotlin-lib:0.2.0'
```

### Configuration

요청하기 전에 애플리케이션 등록 시 발급받은 Client ID와 Client Secret 값을 설정해주셔야 활용 가능합니다.

```kotlin
import org.naver.Naver
val naver = Naver(clientId = "YOUR CLIENT ID", clientSecret = "YOUR CLIENT SECRET")
```

## 얼굴인식
입력된 얼굴 사진을 분석해서 닮은 연예인이나 얼굴 감지

### 유명인 얼굴인식(Beta)
```kotlin
import java.io.File

val file = File("src/test/resources/park.png")
val result = naver.vision().celebrity(image = file)
// Celebrities(info=Info(size=ImageSize(width=683, height=377), faceCount=1), faces=[CelebrityFace(celebrity=Celebrity(value=박성웅, confidence=1.0))])

result.faces.forEach { face -> face.celebrity.value }
// "박성웅"
```

테스트에서 사용한 이미지 보기 [src/test/resources/park.png](src/test/resources/park.png)

### 얼굴 감지(Beta)
```kotlin
import java.io.File

val file = File("src/test/resources/park.png")
val result = naver.vision().face(image = file)
// Faces(info=Info(size=ImageSize(width=683, height=377), faceCount=1), faces=[Face(roi=Roi(x=214, y=106, width=117, height=117), landmark=Landmark(leftEye=LeftEye(x=241, y=137), rightEye=RightEye(x=296, y=126), nose=Nose(x=271, y=166), leftMouth=LeftMouth(x=250, y=195), rightMouth=RightMouth(x=309, y=185)), gender=Gender(value=male, confidence=0.999884), age=Age(value=44~48, confidence=0.79507), emotion=Emotion(value=smile, confidence=0.988639), pose=Pose(value=frontal_face, confidence=0.99785))])

result.faces.forEach { face -> face.gender.value }
// "male"

result.faces.forEach { face -> face.emotion.value }
// "smile"
```

테스트에서 사용한 이미지 보기 [src/test/resources/park.png](src/test/resources/park.png)

## 음성합성
입력된 텍스트를 성우의 낭독 음성으로 합성

### tts(Beta)
```kotlin
import okio.Okio
import java.io.File

val results = naver.voice().tts(speaker = "mijin", speed = 0, text = "기술은 사람을 대체하는 것이 아니라 기술의 도움으로 사람을 더 창조적으로 만들 것이라 믿는다.")
val downloadedFile = File("test.mp3")
val sink = Okio.buffer(Okio.sink(downloadedFile))
sink.writeAll(results)
sink.close()

// test.mp3
```

합성된 음성 듣기 [src/test/resources/tts.mp3](src/test/resources/tts.mp3)

## 파파고

### 한글인명 - 로마자 변환

```kotlin
val results = naver.papago().romanization(query = "김수림")
// Romanizations(firstName=김, items=[Romanization(name=Kim Soorim, score=99), Romanization(name=Kim Soolim, score=97), Romanization(name=Kim Surim, score=78), Romanization(name=Kim Sulim, score=76)])

results.firstName
// 김

results.items.forEach { item -> item.name }
// Kim Soorim
```

### 기계번역(Beta)

```kotlin
val result = naver.papago().translate(source = "ko", target = "en", text = "기술은 사람을 대체하는 것이 아니라 기술의 도움으로 사람을 더 창조적으로 만들 것이라 믿는다.")
// Translation(translatedText=It is to replace the technology is more creative with the help of technology, not believe that it would make., srcLangType=ko)

result.translatedText
// It is to replace the technology is more creative with the help of technology, not believe that it would make.
```

## 지도

### 주소 -> 좌표 변환

```kotlin
val locations = naver.map().geocode(query = "불정로 6")
// Locations(total=1, userquery=불정로 6, items=[Location(address=경기도 성남시 분당구 불정로  6 NAVER그린팩토리, addrdetail=AddressDetail(country=대한민국, sido=경기도, sigugun=성남시 분당구, dongmyun=불정로, rest= 6 NAVER그린팩토리), isRoadAddress=true, isAdmAddress=false, point=Point(x=127.1052133, y=37.3595316))]) 

locations.items.forEach { location -> location.address }
// 경기도 성남시 분당구 불정로  6 NAVER그린팩토리
```

### 좌표 -> 주소 변환

```kotlin
val locations = naver.map().reverseGeocode(lat = "127.1141382", lng = "37.3599968")
// Locations(total=2, userquery=127.1141382,37.3599968, items=[Location(address=경기도 성남시 분당구 정자동 257-1, addrdetail=AddressDetail(country=대한민국, sido=경기도, sigugun=성남시 분당구, dongmyun=정자동, rest=257-1), isRoadAddress=false, isAdmAddress=false, point=Point(x=127.1164925, y=37.3597611)), Location(address=경기도 성남시 분당구 정자3동 257-1, addrdetail=AddressDetail(country=대한민국, sido=경기도, sigugun=성남시 분당구, dongmyun=정자3동, rest=257-1), isRoadAddress=false, isAdmAddress=true, point=Point(x=127.1195385, y=37.3607965))])

locations.items.forEach { location -> location.address }
// 경기도 성남시 분당구 정자동 257-1
// ...

locations.items.forEach { location -> println("${location.poinx.x}, ${location.point.y}") }
// 127.1164925, 37.3607965
// ...
```

## 단축URL

### url
```kotlin
val result = naver.shortUrl().shorten(url = "https://github.com/kimsuelim")
// ShortenUrl(hash=Fr4K6WFj, url=http://me2.do/Fr4K6WFj, orgUrl=https://github.com/kimsuelim)

result.url
// http://me2.do/Fr4K6WFj
```

## 네이버 공유하기

### url
```
naver.share.url(url: "https://github.com/kimsuelim", title: "The unofficial NAVER SDK for Kotlin")
# => "http://share.naver.com/web/shareView.nhn?url=https%3A%2F%2Fgithub.com%2Fkimsuelim&title=The+unofficial+NAVER+SDK+for+Kotlin"
```

## 검색

### 블로그
```kotlin
val search_results = naver.search().blog(query = "블로그")
// Blogs(lastBuildDate=Thu, 06 Jul 2017 12:27:39 +0900, total=26354266, start=1, display=10, items=[Blog(title=6월 이달의 <b>블로그</b>를 소개합니다~!, link=http://blog.naver.com/blogpeople?Redirect=Log&amp;logNo=221041242670, description=북상하는 장마전선과 함께 찾아온, 6월 이달의 <b>블로그</b>를 소개합니다! 네이버 이달의 <b>블로그</b> 매달 새로운 주제로 찾아옵니다. <b>블로그</b> 세상 속 다양한 이야기를 만나보세요. m.blog.naver.com 6월, 이달의 주제 요리... , bloggername=네이버 블로그팀 공식블로그, bloggerlink=http://blog.naver.com/blogpeople, postdate=20170630)])

search_results.total
// 26223041

search_results.items.forEach { blog -> blog.bloggername }
// 호러천국
// ...
```

### 뉴스
```kotlin
val search_results = naver.search().news(query = "뉴스")
// News(lastBuildDate=Thu, 06 Jul 2017 12:30:42 +0900, total=45868166, start=1, display=10, items=[NewsItem(title=최조웅 시의원, 위례별초 교실증축 15억 확보 쾌적한 교육환경 조성, originallink=http://www.kns.tv/news/articleView.html?idxno=326563, link=http://www.kns.tv/news/articleView.html?idxno=326563, description=[KNS<b>뉴스</b>통신=장효남 기자] 최조웅 서울시의원(더불어민주당, 송파6)이 위례별초 교실증축을 위한 예산 15억원을 확보하면서 &quot;이번 교실 증축을 통해 아이들이 지금보다 더 쾌적한 환경에서 교육받을 수... , pubDate=Sun Jul 09 15:45:00 KST 2017)]) 

search_results.items.forEach { news -> news.title }
// 최조웅 시의원, 위례별초 교실증축 15억 확보 쾌적한 교육환경 조성
// ...
```

### 책
```kotlin
val search_results = naver.search().book(query = "책")
// books_with_items
Books(lastBuildDate=Thu, 06 Jul 2017 12:33:20 +0900, total=557232, start=1, display=10, items=[Book(
    title=너의 이름은,
    link=http://book.naver.com/bookdb/book_detail.php?bid=11463103,
    image=http://bookthumb.phinf.naver.net/cover/114/631/11463103.jpg?type=m1&udate=20170512,
    author=신카이 마코토,
    price=11000,
    discount=9900,
    publisher=대원씨아이,
    pubdate=20170117,
    isbn=1133440053 9791133440054,
    description=아직 만난 적 없는 너를....),
    ...
  ]
)

search_results.items.forEach { book -> book.title }
// 너의 이름은
// ...
```

### 성인 검색어 판별
```kotlin
val adult_results = naver.search().adult(query = "성인")
// Adult(adult=1)

adult_results.isAdult()
// true

val not_adult_results = naver.search().adult(query = "청소년")
// Adult(adult=0)

adult_results.isAdult()
// false
```

### 백과 사전
```kotlin
val search_results = naver.search().encyclopedia(query = "백과 사전")
// Encyclopedias(lastBuildDate=Fri, 07 Jul 2017 18:03:49 +0900, total=4337, start=1, display=10, items=[Encyclopedia(title=<b>백과사전</b>, link=http://terms.naver.com/entry.nhn?docId=556891&cid=46669&categoryId=46669, description= 과학·자연 및 인간의 활동에 따른 일체의 지식을 압축 정리해서 각 사항을 가나다순(알파벳순), 분류순 또는 기타 일정한 순서로 배열하여 엮은 책. [내용] 동양, 그 가운데에서도 특히 중국에서는... , thumbnail=http://openapi-dbscthumb.phinf.naver.net/2644_000_9/20150328034129739_UX34MP75L.jpg/4b8709b1-60ab-47.jpg?type=m160_160)])

search_results.items.forEach { encyclopedia -> encyclopedia.title }
// <b>백과사전</b>
// ...
```

### 영화
```kotlin
val search_results = naver.search().movie(query = "킬빌")
// Movies(lastBuildDate=Fri, 07 Jul 2017 18:05:46 +0900, total=3, start=1, display=3, items=[Movie(title=<b>킬 빌</b> - 2부, link=http://movie.naver.com/movie/bi/mi/basic.nhn?code=37493, image=http://imgmovie.naver.com/mdi/mit110/0374/C7493-00.jpg, subtitle=Kill Bill: Vol. 2, pubDate=2014, director=쿠엔틴 타란티노|, actor=우마 서먼|데이빗 캐러딘|, userRating=7.48)])

search_results.items.forEach { movie -> movie.title }
// <b>킬 빌</b> - 2부
// ...
```

### 카페글
```kotlin
val search_results = naver.search().cafearticle(query = "카페글")
// Cafearticles(lastBuildDate=Fri, 07 Jul 2017 18:08:51 +0900, total=217366, start=1, display=10, items=[Cafearticle(title=예전 <b>카페글</b> 복습하기, link=http://cafe.naver.com/cosmicordering/26633, description=많아서 공유글도 나쁘지는 않지만 요즘 글들은 어째 예전 거보다 질적인 측면에서 많이 떨어진다는 생각이... 이것만큼은 꼭 읽으면 도움된다 하는 글들 위주로 쪽지, 혹은 <b>카페글</b>로 올려드릴게요. :) 그럼 한 주도 화잇팅~~~ , cafename=우루사 _ 우주 택배를 받고 소원을 이..., cafeurl=http://cafe.naver.com/cosmicordering)])

search_results.items.forEach { cafearticle -> cafearticle.title }
// 예전 <b>카페글</b> 복습하기
// ...
```

### 지식 iN
```kotlin
val search_results = naver.search().jisigin(query = "지식인")
// Jisigins(lastBuildDate=Fri, 07 Jul 2017 18:09:51 +0900, total=7200244, start=1, display=10, items=[Jisigin(title=<b>지식인</b> 질문, link=http://kin.naver.com/qna/detail.nhn?d1id=1&dirId=1060101&docId=279522526&qb=7KeA7Iud7J24&enc=utf8&section=kin.qna&rank=1&search_sort=0&spq=0, description=<b>지식인</b> 질문 삭제 어케 해여? 등록하신 질문에 답변이 등록되기 전이라면 질문을 등록한 ID로 로그인하신 후... 더 자세한 사항은 <b>지식인</b>공식 블로그를 링크합니다.. http://blog.naver.com/kin_friend/220892944780 또, 질문에 답변이... )])

search_results.items.forEach { jisigin -> jisigin.title }
// <b>지식인</b> 질문
// ...
```

### 지역
```kotlin
val search_results = naver.search().place(query = "제주도")
// Places(lastBuildDate=Fri, 07 Jul 2017 18:11:00 +0900, total=75096, start=1, display=10, items=[Place(title=제주특별자치도청, link=http://www.jeju.go.kr/, category=공공,사회기관>도청, description=제주 뉴스, 생활민원, 여권발급, 문화, <b>제주도</b> 관광, 산업경제, 복지, 여성 정보 수록., telephone=, address=제주특별자치도 제주시 연동 312-1 , roadAddress=제주특별자치도 제주시 문연로 6, mapx=260641, mapy=100237)])

search_results.items.forEach { place -> place.title }
// "제주특별자치도청"
// ...
```

### 오타변환
```kotlin
val response = naver.search().errata(query = "오타")
// Errata(errata=)

response.errata
// ""

response.isErrata()
// false


val response = naver.search().errata(query = "dhxk")
// Errata(errata=오타)

response.errata
// "오타"

response.isErrata()
// true
```

### 웹문서
```kotlin
val search_results = naver.search().webkr(query = "웹문서")
// Webkrs(lastBuildDate=Fri, 07 Jul 2017 18:15:01 +0900, total=6252409, start=1, display=10, items=[Webkr(title=<b>웹문서</b>, link=http://www.happycampus.com/reportTag/%EC%9B%B9%EB%AC%B8%EC%84%9C/, description=<b>웹문서</b>와 디자인, [<b>웹문서</b>][<b>웹문서</b> 분류체계][<b>웹문서</b> 탐색도구][연계시스템][웹검색엔진][문서]<b>웹문서</b>의 분류체계, <b>웹문서</b>의 탐색도구, <b>웹문서</b>와)])

search_results.items.forEach { webkr -> webkr.title }
// "카카오톡 <b>웹문서</b> 수집 인정 &quot;다음 검색 연동 중단&quot; - 오마이뉴스"
// ...
```

### 이미지
```kotlin
val search_results = naver.search().image(query = "이미지")
// Images(lastBuildDate=Fri, 07 Jul 2017 18:17:33 +0900, total=1660857, start=1, display=10, items=[Image(title=볼매뿌뿌's StyleShare, link=https://usercontents-a.styleshare.kr/images/9764328/original, thumbnail=http://tv02.search.naver.net/ugc?q=https://usercontents-a.styleshare.kr/images/9764328/original, sizeheight=285, sizewidth=420)])

search_results.items.forEach { image -> image.title }
// 볼매뿌뿌's StyleShare
// ...
```

### 쇼핑
```kotlin
val search_results = naver.search().shop(query = "쇼핑")
// Shops(lastBuildDate=Fri, 07 Jul 2017 18:18:26 +0900, total=7718652, start=1, display=10, items=[Shop(title=팩앤롤 접이식 휴대용 핸드 <b>쇼핑</b>카트, link=http://shopping.naver.com/gate.nhn?id=11449581836, image=http://shopping.phinf.naver.net/main_1144958/11449581836.2.jpg, lprice=10900, hprice=0, mallName=베이직기프트, productId=11449581836, productType=2)])

search_results.items.forEach { shop -> shop.title }
// 팩앤롤 접이식 휴대용 핸드 <b>쇼핑</b>카트
// ...
```

### 전문자료
```kotlin
val search_results = naver.search().doc(query = "전문자료")
// Docs(lastBuildDate=Fri, 07 Jul 2017 18:19:46 +0900, total=42902, start=1, display=10, items=[Doc(title=이미지 검색 기능을 가진 문서간 <b>전문자료</b>의 유사도 검색 시스템, link=http://academic.naver.com/view.nhn?doc_id=189708176, description=The full-text document similarity retrieval system including image retrieval)])

search_results.items.forEach { doc -> doc.title }
// 이미지 검색 기능을 가진 문서간 <b>전문자료</b>의 유사도 검색 시스템
// ...
```

## Authentication

네이버 아이디로 로그인 API는 네이버 로그인 인증 요청 API, 접근 토큰 발급/갱신/삭제 요청API로 구성되어 있습니다.

### OAuth access tokens

네이버 아이디로 로그인 API는 네이버 로그인 인증 요청 API, 접근 토큰 발급/갱신/삭제 요청API로 구성되어 있습니다.
네이버 로그인 인증 요청 API는 여러분의 웹 또는 앱에 네이버 로그인 화면을 띄우는 API입니다.

```
TODO...
```

Upon authorization, Naver will return to you an authentication code via your OAuth
callback handler. With that you can generate an access token:

이용자가 네이버 회원 인증에 성공하면 API로부터 받은 code 값을 이용해서 접근 토큰 발급 요청 API를 호출합니다.

```
TODO...
```

접근 토큰 발급 요청 API를 통해 받은 접근 토큰(access token) 값은 다음과 같이 회원 프로필 조회를 비롯하여 여러가지 로그인 오픈 API를 호출하는데 사용할 수 있습니다.

## Advanced usage

Since naver-sdk employs [OkHttp][OkHttp] under the hood, some behavior can be
extended via middleware.

### Debugging

Often, it helps to know what naver-sdk is doing under the hood. You can add a
logger to the middleware that enables you to peek into the underlying HTTP
traffic:

```kotlin
import org.naver.Naver
val naver = Naver(clientId = "YOUR CLIENT ID", clientSecret = "YOUR CLIENT SECRET", debug = true)
naver.search().blog(query = "블로그")
```

```
정보: --> GET https://openapi.naver.com/v1/search/blog?query=%EB%B8%94%EB%A1%9C%EA%B7%B8 http/1.1
정보: User-Agent: NAVER Kotlin SDK
정보: Accept: application/json; q=0.5
정보: X-Naver-Client-Id: GssyV0tCqRYPMRkDKsgG
정보: X-Naver-Client-Secret: ScVEl_cDJA
정보: --> END GET
정보: <-- 200 OK https://openapi.naver.com/v1/search/blog?query=%EB%B8%94%EB%A1%9C%EA%B7%B8 (640ms)
정보: Server: nginx
정보: Date: Sun, 09 Jul 2017 12:17:46 GMT
정보: Content-Type: application/json;charset=UTF-8
정보: Transfer-Encoding: chunked
정보: Connection: keep-alive
정보: Keep-Alive: timeout=5
정보: Vary: Accept-Encoding
정보: X-Powered-By: Naver
정보: Cache-Control: no-cache, no-store, must-revalidate
정보: Pragma: no-cache
정보: 
정보: {
"lastBuildDate": "Sun, 09 Jul 2017 21:17:46 +0900",
"total": 26416872,
"start": 1,
"display": 10,
"items": [
{
"title": "<b>블로그</b>광고 업체선정이매우중요!",
"link": "http://blog.naver.com/oktaesokkk?Redirect=Log&amp;logNo=221045944161",
"description": "<b>블로그</b>광고를어디에맡겨야 효과적일지고민하고계셨다면잠깐! 오늘그효과적인방법에대해서 나눠보려고하니까눈크게뜨고확인해보세요 <b>블로그</b>광고효과가투자비용에비해좋다는걸... ",
"bloggername": "하루나의 잡동사니 리뷰",
"bloggerlink": "http://blog.naver.com/oktaesokkk",
"postdate": "20170707"

},
...
]
}

정보: <-- END HTTP (6243-byte body)
...
```

## Development

After checking out the repo, run `./gradlew build` to install dependencies. Then, run `./gradlew test` to run the tests.

## Contributing

Bug reports and pull requests are welcome on GitHub at https://github.com/kimsuelim/naver-sdk-kotlin. This project is intended to be a safe, welcoming space for collaboration, and contributors are expected to adhere to the [Contributor Covenant](http://contributor-covenant.org) code of conduct.

## License

The SDK is available as open source under the terms of the [MIT License](http://opensource.org/licenses/MIT).

## Code of Conduct

Everyone interacting in the Naver::Sdk project’s codebases, issue trackers, chat rooms and mailing lists is expected to follow the [code of conduct](https://github.com/kimsuelim/naver-sdk-kotlin/blob/master/CODE_OF_CONDUCT.md).

[OkHttp]: http://square.github.io/okhttp
