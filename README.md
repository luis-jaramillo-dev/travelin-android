# travelin-android

## API Config

In order to run this project you must pass an api key and base url in `local.properties` file:
We are assuming the usage of "Amadeus API"

```text
API_KEY = YOUR_KEY
BASE_URL = THE BASE URL OF THE API USED
```

# Testing

To facilitate testing, *TravelinAndroid* uses dependency injection with [Hilt][1]

Most of the classes are injected, so you can [mock them in your tests][2].

## Running Tests

In order to run tests you must run 

```bash
./gradlew test 
```

## Create coverage report

Coverage reports came in two "flavours" `release` and `debug`, as CI pipeline we are using
`debug` variant, you can create each variant using:

```bash
./gradlew createDebugCombinedReport # for debug variant
./gradlew createReleaseCombinedReport # for release variant
# ./gradlew createJVMCombinedReport # for jvm libraries not yet implemented

```


[1]: https://developer.android.com/training/dependency-injection/hilt-android "Dependency Injection Framwork"
[2]: https://developer.android.com/training/dependency-injection/hilt-testing "Mocking scoped classes in tests"