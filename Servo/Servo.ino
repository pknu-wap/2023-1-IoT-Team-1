#include <ESP32Servo.h>
Servo myservo;
 
int pos = 0;
int servoPin = 13;
 
void setup() {
  ESP32PWM::allocateTimer(0);
  ESP32PWM::allocateTimer(1);
  ESP32PWM::allocateTimer(2);
  ESP32PWM::allocateTimer(3);
  myservo.setPeriodHertz(50);    
  myservo.attach(servoPin, 500, 2400); 
}

void loop() {
  for (pos = 0; pos <= 180; pos += 1) {
    myservo.write(pos); 
    delay(15); 
  }
  for (pos = 180; pos >= 0; pos -= 1) { 
    myservo.write(pos);
    delay(15);
  }
}
