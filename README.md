# demo

- 멀티 모듈
- spring boot
- spring mvc
- 린트
- swagger

User
id
nickname
coordinate
캐릭터 생김새 id (캐릭터의 생김새는 유저마다 다르다)
움직임 상태
 - 프론트에서 들고있는 정보 : 서있다, 걷고있다
 - 프론트에서 주기적으로 정보를 서버로 전달함 : 서 t/f, 걷 t/f (노이즈 발생 가능)
 - timeseries db
 - 캐릭터의 행동 1,2,3,4,5 (앉아있음, 걷고있음) 판별 가능
미션 진행 상태
 - 미션 시작, 중, 미션 종료 여부는 클라에서 판단
내가 받은 티징 딱지
+  내가 쏜 티징 딱지
나의 하이파이브 상태(t/f)
created_at
updated_at
npc npc 유저 여부
userIdForNpc 어떤 유저의 npc인지

Tease
id
from
to
message
mission
 - 미션의 종류로 ui 달라질 수 있음
created_at

Kinetic history
user_id
stand
- t/f
walk 
- t/f
created_at

url 입장 (놀림 받는 포지션에서 놀리는 포지션으로)
-> 자동 게스트 로그인 *User 생성(User 내가 받은 딱지가 default 여러 개 (미션도 세팅) , 움직임 상태는 기본 all false)
-> 미션 (아무거나) 시작 -> 종료 -> 포인트 획득하고, 내 딱지 전체 삭제(안보이게)
-> 놀리는 포지션 시작 (지도에 npc 띄움)
-> 유저가 npc 하나 선택 (Tease / Take)
-> Tease > 아무거나 선택 > npc 업데이트 (놀림 당하는 중만 보여주면 됨)
-> Take > 하이파이브 인식 > npc 업데이트

### API 뽑기
- [X] 게스트 로그인 User 생성
- [X] 미션 시작
- [X] 미션 종료 & 내 tease 전체 삭제
- [X] 유저가 npc 하나 선택해서 Tease 요청
- [X] 특정 Tease 삭제
- [X] 유저가 npc 하나 선택해서 Take (하이파이브) 요청
- [ ] 유저가 npc 하나 선택해서 Take (하이파이브) 완료 (해당 npc 업데이트)
- [ ] Kinetic 데이터 api
- [ ] 유저의 Coordinate 움직임 상태를 업데이트
- [ ] 유저의 움직임 상태를 조회
- [ ] 여러 가지 어드민 api... (crud 등)
