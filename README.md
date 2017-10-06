[![CircleCI](https://circleci.com/gh/KamilKalfas/docy/tree/develop.svg?style=svg)](https://circleci.com/gh/KamilKalfas/docy/tree/develop)

# docy
Our internal annotation processor to generate documentation based on annotation processing

## Usage
Add this annotation to your tests

```java
@Test
@BDD(issue = "SPACE-0001",
    feature = "multi-module-support",
    acs = {
            @AC(number = "AC1", description = "Lorem ipsum dolor sit amet consectetur adipiscing elit."),
            @AC(number = "AC2", description = "Lorem ipsum dolor sit amet consectetur adipiscing elit. Donec quis consequat felis id maximus ante.")
    })
public void test_method_of_smth_important() {
  // test logic
}
```

## Install
Define env variable ```DOCY_MODULES``` and specify amount of modules that you want to run it on

```shell
export DOCY_MODULES=2
```


Add url to main ```build.gradle```
```groovy
allprojects {
  repositories {
    ...
    maven { url "https://jitpack.io" }
  }
}
```

And add dependencies 
```groovy
dependencies {
      ...
      testAnnotationProcessor 'com.github.KamilKalfas.docy:compiler:0.0.4'
      testImplementation 'com.github.KamilKalfas.docy:annotations:0.0.4'
}
```