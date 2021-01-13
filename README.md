# SMS Receiver
[![javadoc](https://javadoc.io/badge2/com.chunhoong/sms-receiver-lib/javadoc.svg)](https://javadoc.io/doc/com.chunhoong/sms-receiver-lib)

SMS Receiver is a library built on top of the deprecated [SMSLib V3.5.x](https://github.com/tdelenikas/smslib-v3) to read SMS from 3G USB modem.

## Motivation
- The original SMSLib has dependency towards [RXTX](https://github.com/rxtx/rxtx), which mostly only work well with Java 7 nowadays.

- RXTX is not setup friendly - The jar file and binary need to be pasted into inner JRE folder of JDK installation path.

- SMS Receiver solves this problem by replacing rxtx with its fork, [nrjavaserial](https://github.com/NeuronRobotics/nrjavaserial), which works well with Java 11 and no more manual work to copy-paste rxtx jar and its binary. 

## Usage
If you are on Maven, add this to `pom.xml`
```xml
<dependency>
  <groupId>com.chunhoong</groupId>
  <artifactId>sms-receiver-lib</artifactId>
  <version>1.0.2</version>
  <type>pom</type>
</dependency>
```

If you are on Gradle, add this to `build.gradle`
```groovy
implementation 'com.chunhoong:sms-receiver-lib:1.0.2'
```

### Start receiver
```java
// Obtain com port number as shown in: https://raw.githubusercontent.com/chunhoong/sms-receiver/master/doc/screenshot.png
int comPortNumber = 5;

// Start sms receiver
SmsReceiver.start(comPortNumber);
```


### Add listener
```java
SmsReceiver.addListener(sms -> { /* You can access your incoming sms from here. */ });      
```

### Add one time listener
```java
SmsReceiver.once(sms -> { 
  // You can access your incoming sms from here.
});
```

### Remove single listener
```java
int listenerId = SmsReceiver.addListener(sms -> { /* You can access your incoming sms from here. */ });

// Remove listener by passing in listenerId.
SmsReceiver.removeListener(listenerId);
```

### Remove all listener
```java
// Assume three listeners are added.
SmsReceiver.addListener(sms -> { /* You can access your incoming sms from here. */ });
SmsReceiver.addListener(sms -> { /* You can access your incoming sms from here as well. */ });
SmsReceiver.addListener(sms -> { /* You can access your incoming sms from here too. */ });

// Remove all three listeners.
SmsReceiver.removeAllListeners();
```

### Stop SMS receiver
```
SmsReceiver.stop();
```

## Credits
- [Thanasis Delenikas](https://github.com/tdelenikas) for SMSLib
- [Neuron Robotics](https://github.com/NeuronRobotics) for nrjavaserial.

## License
[Apache License 2.0](https://choosealicense.com/licenses/apache-2.0/)
