## 3.0.4 (2025-06-13)
<details open>
 <summary>EN</summary>
 
### Fixed

- Fixed an issue where [installReferrer](https://developer.android.com/google/play/installreferrer) data would be missing if startSession was called before init.
- Fixed an issue where `last_deeplink_id` would be missing if a deep link open event occurred during a session.
- Fixed an issue where the endSession event could be called twice if startSession was called during a session.

</details>
<details open>
 <summary>KO</summary>

### 고쳐짐

- init 이전에 startSession이 호출 될 경우 [installReferrer 관련 데이터](https://developer.android.com/google/play/installreferrer)가 누락되는 문제를 해결했습니다.
- 세션 도중 딥링크 오픈 이벤트 발생시 `last_deeplink_id` 데이터가 누락되는 문제를 해결했습니다.
- 세션 도중 startSession 호출 할 경우 endSession 이벤트가 2번 인입될 수있는 문제를 해결했습니다.

</details>

## 3.0.3 (2025-06-10)
<details open>
 <summary>EN</summary>
 
### Fixed

- Fixed the issue where Double type event properties would be missing when appending `.0` to the end.
- Fixed the issue where Double type would be missing when inputting `ABEventProperty.ITEM_PRICE` value.

</details>
<details open>
 <summary>KO</summary>

### 고쳐짐

- Double 타입 이벤트 속성 끝에 `.0`을 붙일 경우 누락되는 문제를 해결했습니다.
- `ABEventProperty.ITEM_PRICE` 값에 Double 타입을 입력할 경우 누락되는 문제를 해결했습니다.

</details>

## 3.0.2 (2025-06-05)
<details open>
 <summary>EN</summary>
 
- The validation conditions for event names and properties have been changed.
  - If the required type of a predefined property is Long

    |Before|After|
    |---|---|
    |Allow only Long|Allow Long, Integer |

  - If the required type of a predefined property is Double

    |Before|After|
    |---|---|
    |Allow only Double|Allow Double, Float |

</details>
<details open>
 <summary>KO</summary>
 
- 이벤트 이름 및 속성에 대한 검증 조건이 변경되었습니다.
  - 기 정의된 속성의 필요 타입이 Long 일 경우

      |기존|변경|
      |---|---|
      |Long만 허용|Long, Integer 허용 |
  
  - 기 정의된 속성의 필요 타입이 Double 일 경우

      |기존|변경|
      |---|---|
      |Double만 허용|Double, Float 허용 |

</details>

## 3.0.1 (2025-05-13)
<details open>
 <summary>EN</summary>
 
- Added validation for event name and properties
- Changed to handle deep link when deep link occurs during session

</details>
<details open>
 <summary>KO</summary>
 
- 이벤트 이름 및 속성에 대한 검증 추가
- 세션 도중 딥링크 발생시 딥링크 처리하도록 변경

</details>

## 3.0.0 (2025-04-18)
<details open>
 <summary>EN</summary>
 
- Initial release.

</details>
<details open>
 <summary>KO</summary>
 
- 최초 배포.

</details>