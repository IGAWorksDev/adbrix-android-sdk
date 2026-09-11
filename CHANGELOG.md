<details open>
<summary>EN</summary>







## 4.0.1 (2026-09-11)

##### Fixed
- Fixed an issue where deeplink attribution failed when the app was opened via App Links while in the foreground.
## 4.0.0 (2026-09-03)

##### Added
- App Set ID Support (Required Dependency)
    - Added Google Play Services App Set ID collection.
    - App Set ID is a privacy-preserving identifier for cross-app user identification within the same developer account.
    - App Set ID is now included in event common identity payload.
    - ⚠️ Breaking Change: com.google.android.gms:play-services-appset dependency is now required.
    - SDK initialization will halt if this dependency is missing.

##### Fixed
- blockDeferredDeepLinkLaunch now works correctly when called before SDK initialization.

##### Enhancement
- Background & multi-thread stability
    - Enhanced thread-safety in internal data processing to ensure reliable event transmission under high-concurrency async environments.
## 3.0.10 (2026-02-10)

##### Fixed
- Fixed an issue in Unity where float values (e.g., 100.0) were incorrectly converted to integers, causing event attributes to be dropped.
- Resolved a NullPointerException that occurred in unit test environments when null values were passed as inputs.
- Fixed an intermittent issue where queued events were excluded from transmission during consecutive event calls.
## 3.0.9 (2026-01-30)

##### Notes
- Standardized inconsistent log structures and messages across all platforms into a single format.
## 3.0.8 (2025-11-21)

##### Notes
- Event sampling now applies to all events.
## 3.0.7 (2025-09-02)

##### Enhancement
- Improving Event Collection Stability and Collection Rate
## 3.0.6 (2025-07-15)

### Fixed

- Enhanced data processing stability by resolving event duplication, omission, and ordering errors that occurred during concurrent API calls in a multi-threaded environment.
- Fixed a memory leak issue that occurred when using Context during the process of collecting device information.


## 3.0.5 (2025-07-01)

### Changed

- compileSdk version changed to 35.
 
### Fixed

- Fixed a timing issue when calling APIs before SDK initialization.


## 3.0.4 (2025-06-13)
 
### Fixed

- Fixed an issue where [installReferrer](https://developer.android.com/google/play/installreferrer) data would be missing if startSession was called before init.
- Fixed an issue where `last_deeplink_id` would be missing if a deep link open event occurred during a session.
- Fixed an issue where the endSession event could be called twice if startSession was called during a session.


## 3.0.3 (2025-06-10)
 
### Fixed

- Fixed the issue where Double type event properties would be missing when appending `.0` to the end.
- Fixed the issue where Double type would be missing when inputting `ABEventProperty.ITEM_PRICE` value.


## 3.0.2 (2025-06-05)
 
- The validation conditions for event names and properties have been changed.
  - If the required type of a predefined property is Long

    |Before|After|
    |---|---|
    |Allow only Long|Allow Long, Integer |

  - If the required type of a predefined property is Double

    |Before|After|
    |---|---|
    |Allow only Double|Allow Double, Float |


## 3.0.1 (2025-05-13)
 
- Added validation for event name and properties
- Changed to handle deep link when deep link occurs during session


## 3.0.0 (2025-04-18)
 
- Initial release.

</details>
<details open>
<summary>KO</summary>







## 4.0.1 (2026-09-11)

##### 고쳐짐
- 포그라운드 상태에서 앱 링크를 통한 딥링크 오픈 시 성과 측정이 정상 동작하지 않던 문제를 수정하였습니다.
## 4.0.0 (2026-09-03)

##### 추가됨
- App Set ID 지원 (필수 의존성 추가)
    - Google Play Services App Set ID 수집 기능이 추가되었습니다.
    - App Set ID는 동일 개발사의 앱 간 사용자 식별에 활용되는 프라이버시 보호 식별자입니다.
    - 이벤트 전송 시 공통 신원 정보에 App Set ID가 포함됩니다.
    - ⚠️ 주요 변경: com.google.android.gms:play-services-appset 의존성이 필수로 변경되었습니다.
    - 해당 의존성이 누락된 경우 SDK 초기화가 중단됩니다.

##### 고쳐짐
- SDK init 전 blockDeferredDeepLinkLaunch 호출시 미작동하던 문제가 해결되었습니다.

##### 개선
- 백그라운드 및 멀티스레드 환경 안정화
    - SDK 내부 데이터 처리 구조의 동기화(Thread-Safety)를 강화하여, 고성능 비동기 환경에서도 데이터가 끊김 없이 안전하게 전송되도록 개선했습니다.
## 3.0.10 (2026-02-10)

##### 고쳐짐
- Unity에서 100.0과 같은 실수형 데이터가 정수형으로 변환되어 이벤트 속성에서 누락되던 타입을 수정하였습니다.
- 유닛 테스트 환경에서 null 값이 입력될 경우 NullPointerException이 발생하던 문제를 해결하였습니다.
- 연속적인 이벤트 발생 시, 메모리 큐에 대기 중인 이벤트가 발송 대상에서 간헐적으로 누락되던 문제를 해결하였습니다.
## 3.0.9 (2026-01-30)

##### 주요 사항
- 플랫폼별로 상이했던 로그 구조와 메시지가 통일된 규격으로 변경되었습니다.
## 3.0.8 (2025-11-21)

##### 주요 사항
- 이벤트 샘플링이 모든 이벤트에 적용되도록 변경되었습니다.
## 3.0.7 (2025-09-02)

##### 개선
- 이벤트 수집 안정성 및 수집률 향상
## 3.0.6 (2025-07-15)

### 고쳐짐

- 다중 스레드 환경에서 API 동시 호출 시 발생하던 이벤트 정보의 중복, 누락 및 순서 오류를 해결하여 데이터 처리 안정성을 강화했습니다.
- 기기 정보를 수집하는 과정에서 Context 사용시 발생하던 메모리 누수 문제를 해결하였습니다.


## 3.0.5 (2025-07-01)

### 변경사항

- compileSdk 버전이 35로 변경되었습니다.

### 고쳐짐

- SDK 초기화 전 API 호출 시 드물게 발생하던 타이밍 이슈를 수정했습니다.


## 3.0.4 (2025-06-13)

### 고쳐짐

- init 이전에 startSession이 호출 될 경우 [installReferrer 관련 데이터](https://developer.android.com/google/play/installreferrer)가 누락되는 문제를 해결했습니다.
- 세션 도중 딥링크 오픈 이벤트 발생시 `last_deeplink_id` 데이터가 누락되는 문제를 해결했습니다.
- 세션 도중 startSession 호출 할 경우 endSession 이벤트가 2번 인입될 수있는 문제를 해결했습니다.


## 3.0.3 (2025-06-10)

### 고쳐짐

- Double 타입 이벤트 속성 끝에 `.0`을 붙일 경우 누락되는 문제를 해결했습니다.
- `ABEventProperty.ITEM_PRICE` 값에 Double 타입을 입력할 경우 누락되는 문제를 해결했습니다.


## 3.0.2 (2025-06-05)
 
- 이벤트 이름 및 속성에 대한 검증 조건이 변경되었습니다.
  - 기 정의된 속성의 필요 타입이 Long 일 경우

      |기존|변경|
      |---|---|
      |Long만 허용|Long, Integer 허용 |
  
  - 기 정의된 속성의 필요 타입이 Double 일 경우

      |기존|변경|
      |---|---|
      |Double만 허용|Double, Float 허용 |


## 3.0.1 (2025-05-13)
 
- 이벤트 이름 및 속성에 대한 검증 추가
- 세션 도중 딥링크 발생시 딥링크 처리하도록 변경


## 3.0.0 (2025-04-18)
 
- 최초 배포.

</details>