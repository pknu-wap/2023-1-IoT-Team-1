/*
 Unsolved problem 
 - get Time and date from NTPClient.
*/

#include <WiFi.h>
#include <Firebase_ESP_Client.h>
#include <addons/TokenHelper.h>
#include <addons/RTDBHelper.h>
#include <NTPClient.h>
#include <OneWire.h>
#include "time.h"

#define WIFI_SSID "AndroidHotspot1440"
#define WIFI_PASSWORD "password"

#define API_KEY "password"
#define DATABASE_URL "wap-iot-9494c-default-rtdb.asia-southeast1.firebasedatabase.app" 

#define USER_EMAIL "kjghost1@pukyong.ac.kr"
#define USER_PASSWORD "password!"

#define PIN_RED 21 // GIOP21
#define PIN_GREEN 22 // GIOP22
#define PIN_BLUE 23 // GIOP23
#define TRIG 32
#define ECHO 33
#define DS_pin 2

// Define Firebase Data object
FirebaseData fbdo;
FirebaseAuth auth;
FirebaseConfig config;

const char* ntpServer= "pool.ntp.org";
const long gmtOffset_sec = 3600;
const int daylightOffset_sec = 3600;

WiFiUDP ntpUDP;
NTPClient timeClient(ntpUDP, "pool.ntp.org", 9 * 3600);
OneWire ds(DS_pin);

unsigned long sendDataPrevMillis = 0;
unsigned long count = 0;
int year, month, day, Hour, Min, Sec;
int R, G, B;

long Ultra(){
  long duration, distance;

  digitalWrite(TRIG, LOW);
  delayMicroseconds(2);
  digitalWrite(TRIG, HIGH);
  delayMicroseconds(10);
  digitalWrite(TRIG, LOW);
  duration = pulseIn(ECHO, HIGH);

  distance = duration * 17 / 1000;

  return distance;
}

float getTemp(){
  byte data_[12];
  byte addr[8];

  if(!ds.search(addr)){
    ds.reset_search();
    return -1000;
  }
  if(OneWire::crc8(addr, 7)!= addr[7]) {
    Serial.println("CRC is not valid.");
    return -1000;
  }
  if(addr[0] != 0x10 && addr[0] != 0x28) {
    Serial.println("Device is not recognized");
    return -1000;
  }
  ds.reset();
  ds.select(addr);
  ds.write(0x44, 1);
  byte present = ds.reset();
  ds.select(addr);
  ds.write(0xBE);

  for(int i = 0; i < 9; i++)
  {
    data_[i] = ds.read();
  }

  ds.reset_search();
  byte MSB = data_[1];
  byte LSB = data_[0];
  float tempRead = ((MSB << 8) | LSB);
  float TemperatureSum = tempRead / 16;
  return TemperatureSum;
}

void setColor(int r, int g, int b)
{
  ledcWrite(0, r);
  ledcWrite(1, g);
  ledcWrite(2, b);
}

void printLocalTime()
{
  struct tm timeinfo;
  if(!getLocalTime(&timeinfo)){
    Serial.println("Failed to obtain time");
    return;
  }
  Sec = timeinfo.tm_min;
  Min = timeinfo.tm_min;
  Hour = timeinfo.tm_hour;
  day = timeinfo.tm_mday;
  month = timeinfo.tm_mon + 1;
  year = timeinfo.tm_year + 1900;
}

void GetLED() {
  if(Firebase.RTDB.getInt(&fbdo, "/users/arduino/led/R")){
    if(fbdo.dataType() == "int"){
      R = fbdo.intData();
      Serial.printf("RED value: %d", R);
    }
  }
  if(Firebase.RTDB.getInt(&fbdo, "/users/arduino/led/G")){
    if(fbdo.dataType() == "int"){
      G = fbdo.intData();
      Serial.printf("Green value: %d", G);
    }
  }
  if(Firebase.RTDB.getInt(&fbdo, "/users/arduino/led/B")){
    if(fbdo.dataType() == "int"){
      B = fbdo.intData();
      Serial.printf("Blue value: %d", B);
    }
  }  
}

void setup() {  
  Serial.begin(115200);
  
  ledcAttachPin(PIN_RED, 0);
  ledcAttachPin(PIN_GREEN, 1);
  ledcAttachPin(PIN_BLUE, 2);
  ledcSetup(0, 5000, 8);
  ledcSetup(1, 5000, 8);
  ledcSetup(2, 5000, 8);
  
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("Connecting to Wi-Fi");
  unsigned long ms = millis();
  while (WiFi.status() != WL_CONNECTED)
  {
    Serial.print(".");
    delay(300);
  }
  
  Serial.println();
  Serial.print("Connected with IP: ");
  Serial.println(WiFi.localIP());
  Serial.println();
  Serial.printf("Firebase Client v%s\n\n", FIREBASE_CLIENT_VERSION);
  config.api_key = API_KEY;
  auth.user.email = USER_EMAIL;
  auth.user.password = USER_PASSWORD;
  config.database_url = DATABASE_URL;
  config.token_status_callback = tokenStatusCallback; // see addons/TokenHelper.h
  fbdo.setResponseSize(2048);
  Firebase.begin(&config, &auth);
  delay(2000);
  
  Firebase.reconnectWiFi(true);
  Firebase.setDoubleDigits(5);
  configTime(gmtOffset_sec, daylightOffset_sec, ntpServer);
  config.timeout.serverResponse = 10 * 1000;
}

void loop() {
  int R_s, B_s, G_s;
  long Dist;
  float Temp;
  Firebase.ready();
  Firebase.authenticated();

  if(Firebase.ready() && (millis() - sendDataPrevMillis > 15000 || sendDataPrevMillis == 0))
  {
    sendDataPrevMillis = millis();
    printLocalTime(); // NTP서버에서 시간 받아오기
    String time_string = "00 : 00 , January 1, 2023";
    Dist = Ultra(); // 먹이통 잔여량 확인
    Temp = getTemp(); // 수온 센서 값 받아오기
    
    Serial.printf("Post day... %s\n", Firebase.RTDB.setString(&fbdo, F("/users/arduino/time"), time_string)); // 시간 올려주기
    Serial.printf("Get day... %s\n", Firebase.RTDB.getString(&fbdo, F("/users/arduino/time")) ? fbdo.to<const char *>() : fbdo.errorReason().c_str());
    Serial.printf("Set distance... %s\n", Firebase.RTDB.setInt(&fbdo, F("/users/arduino/sensors/ultrasonic"), Dist) ? "ok" : fbdo.errorReason().c_str());
    Serial.printf("Get distacne... %s\n", Firebase.RTDB.getInt(&fbdo, F("/users/arduino/sensors/ultrasonic")) ? String(fbdo.to<int>()).c_str() : fbdo.errorReason().c_str());
    Serial.printf("Set Temperature... %s\n", Firebase.RTDB.setInt(&fbdo, F("/users/arduino/sensors/temp"), Temp) ? "ok" : fbdo.errorReason().c_str());
    Serial.printf("Get Temperature... %s\n", Firebase.RTDB.getInt(&fbdo, F("/users/arduino/sensors/temp")) ? String(fbdo.to<int>()).c_str() : fbdo.errorReason().c_str());
    
    R_s = R;
    B_s = B;
    G_s = G;
    GetLED();
    if(R!= R_s || B != B_s || G != G_s) {
      setColor(R, G, B);
    }
    delay(500);
  }

}
