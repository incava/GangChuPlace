# 강추 플레이스
리뷰 이벤트로 인해 신뢰가 깨진 추천 장소들을 아카이빙 하며 친구들끼리 서로 공유하기 위한 앱입니다!
친구들만의 강추 플레이스를 공유해보세요!

# 프로젝트정보 

 #### Laguage : Kotlin
 #### MinSdk : 26  
 #### MaxSdk : 33   
 #### 개발기간 : 23.03.29 ~ 23.04.26 (1달) - 리팩토링 진행 중  
 #### 개발인원 : 1명 
 #### 주요기술 및 라이브러리
 - 리뷰 DB저장을 위해 Firebase제품 중 Storage, FireStore 사용
 - 리뷰 장소를 보여주기 위한 Grid RecyclerView사용
 - 리뷰 장소 글쓰기 위한 장소 검색 API(Naver Search API)사용
 - 카카오 로그인을 위한 Kakao Auth API사용
 - LiveData와 Repository패턴을 사용한 MVVM패턴
 - SingleActivity와 그에 맞게 Navigation패턴을 이용

# 프로젝트를 생각하게 된 계기

### 1. 리뷰 조작, 홍보로 인한 신뢰도 하락

요즈음 리뷰 이벤트, 광고 홍보를 통해 알리는 목적도 있지만 본래의 리뷰과 달리 리뷰 조작으로 인해 사람들은 리뷰에 대한 신뢰도가 많이 낮습니다.

“그 **신뢰도**를 믿을 수 있는 사람이 작성한다면 어떻까?” 라는 생각으로 친구들에게 알려주고 싶은 플레이스를 이 앱을 통해 공유하고 추천해보고자 합니다.

### 2. 주변 친구와의 공통점 찾기

친구들과 이야기 한다면 공통점으로 인해 더욱더 친해지는 계기가 되기도 하고, 한명한명에게 코스 추천을 받을 수도 없다. 만약 친구들이 갔던 곳이라면 거기 어디가 어땠는지, 꿀팁이라던지 물어볼 계기도 생길 수 있고, 긴밀한 관계로 이어질 수 있는 부분이라 생각하여 만들게 되었습니다.

# 실행영상

<table style="width:70%;">
  <tr>
    <th style="text-align:center;"><b> 회원가입, 로그인</b></th>
    <th style="text-align:center;"><b> 강추 화면 및 상세 페이지 </b></th>
  </tr>
  <tr>
    <td><img src="https://github.com/incava/GangChuPlace/assets/68988975/2a2c61d2-41a2-40e3-80b1-30ccf5ffc381.gif" width="250" height="500"/></td>
    <td><img src="https://github.com/incava/GangChuPlace/assets/68988975/5e259f4b-b2ee-4d1b-8815-1539432fa5d1.gif" width="250" height="500"/></td>
  </tr>
  <tr>
    <td>이메일 양식과 비밀번호 양식을 맞추어 회원가입을 진행합니다.</td>
    <td>앱에서 사용한 사람들이 보는 강추 화면입니다. 친추 추천이 몇 명인지 볼 수 있고, 어떤사람들이 리뷰를 남겼는지 확인 가능합니다.</td>
  </tr>
    <tr>
  <th style="text-align:center;"><b> 찜하기 기능 </b></th>
      <th style="text-align:center;"><b> 강추 리뷰 쓰기 </b></th>
  </tr>
  <tr>
    <td><img src="https://github.com/incava/GangChuPlace/assets/68988975/44a9d0ac-ba13-4e63-ba0a-763c661b829e.gif" width="290" height="500"/></td>
    <td><img src="https://github.com/incava/GangChuPlace/assets/68988975/4bf049cd-db48-40fa-b2c5-a9421e04b2a6.gif" width="290" height="500"/></td>
  </tr>
  <tr>
    <td>찜 기능을 통해 자신이 가고 싶은 장소를 저장해둘 수 있습니다.</td>
    <td>강추하는 장소에 리뷰를 달 수 있습니다.</td>
  </tr>
  
 <tr>
    <th style="text-align:center;"><b> 강추 리뷰 필터 기능 </b></th>
    <th style="text-align:center;"><b> 룰렛 기능 </b></th>
  </tr>
  <tr>
    <td><img src="https://github.com/incava/GangChuPlace/assets/68988975/1d9a623f-b24d-4521-947e-9b8b3029bb48.gif" width="290" height="500"/></td>
    <td><img src="https://github.com/incava/GangChuPlace/assets/68988975/11f95d37-51d8-4df9-804b-a17150072859.gif" width="250" height="500"/></td>
  </tr>
  <tr>
    <td>장소들을 여러 필터를 통해 결과를 정렬할 수 있습니다.</td>
    <td>룰렛을 통해 고민되는 장소들 중 선택할 수 있도록 도움을 줄 수 있습니다.</td>
  </tr>
   <tr>
      <th style="text-align:center;"><b> 친구추가 기능 </b></th>
  </tr>
  <tr>
     <td><img src="https://github.com/incava/GangChuPlace/assets/68988975/7b42898c-e607-490e-bfdf-1b9b1f00a792.gif" width="250" height="500"/></td>
  </tr>
  <tr>
        <td>친구 목록과 친구 요청 및 친구 수락 기능을 통해 친구들이 가본 곳을 한눈에 알 수 있습니다.</td>
  </tr>
</table>





# Todo 
DI주입을 통한 MVVM패턴 리팩토링
ViewModel의 의존성과 특징 구분해서 리팩토링


