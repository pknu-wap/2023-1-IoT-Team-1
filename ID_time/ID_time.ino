#include <WiFi.h>
#include <Firebase_ESP_Client.h>
#include <addons/TokenHelper.h>
#include <addons/RTDBHelper.h>
#include <NTPClient.h>
#include "time.h"

#define WIFI_SSID "AndroidHotspot1440"
#define WIFI_PASSWORD "password"

#define API_KEY "password"
#define DATABASE_URL "wap-iot-9494c-default-rtdb.asia-southeast1.firebasedatabase.app" 

#define USER_EMAIL "kjghost1@pukyong.ac.kr"
#define USER_PASSWORD "password"

// Define Firebase Data object
FirebaseData fbdo;
FirebaseAuth auth;
FirebaseConfig config;

const char* ntpServer= "pool.ntp.org";
const long gmtOffset_sec = 3600;
const int daylightOffset_sec = 3600;

WiFiUDP ntpUDP;
NTPClient timeClient(ntpUDP, "pool.ntp.org", 9 * 3600);

unsigned long sendDataPrevMillis = 0;
unsigned long count = 0;
int year, month, day, Hour, Min, Sec;
int R, G, B;

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

void setup()
{
  Serial.begin(115200);
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
  // The WiFi credentials are required for Pico W
  // due to it does not have reconnect feature.
  
  // Comment or pass false value when WiFi reconnection will control by your code or third party library
  Firebase.reconnectWiFi(true);

  Firebase.setDoubleDigits(5);

  configTime(gmtOffset_sec, daylightOffset_sec, ntpServer);
  config.timeout.serverResponse = 10 * 1000;
}

void loop()
{
  Firebase.ready();
  Firebase.authenticated();

  // Firebase.ready() should be called repeatedly to handle authentication tasks.

  if (Firebase.ready() && (millis() - sendDataPrevMillis > 15000 || sendDataPrevMillis == 0))
  {
    printLocalTime();
    Serial.printf("Post year... %d\n", Firebase.RTDB.setInt(&fbdo, F("/users/arduino/time/year"), count + 10) ? "ok" : fbdo.errorReason().c_str());
    
    Serial.printf("Set bool... %s\n", Firebase.RTDB.setBool(&fbdo, F("/users/arduino/sensors/bool_test"), count % 2 == 0) ? "ok" : fbdo.errorReason().c_str());
    Serial.printf("Get bool... %s\n", Firebase.RTDB.getBool(&fbdo, FPSTR("/users/arduino/sensors/bool_test")) ? fbdo.to<bool>() ? "true" : "false" : fbdo.errorReason().c_str());
    bool bVal;
    Serial.printf("Get bool ref... %s\n", Firebase.RTDB.getBool(&fbdo, F("/users/arduino/sensors/bool_test"), &bVal) ? bVal ? "true" : "false" : fbdo.errorReason().c_str());
    Serial.printf("Set int... %s\n", Firebase.RTDB.setInt(&fbdo, F("/users/arduino/sensors/ultrasonic"), count) ? "ok" : fbdo.errorReason().c_str());
    Serial.printf("Get int... %s\n", Firebase.RTDB.getInt(&fbdo, F("/users/arduino/sensors/ultrasonic")) ? String(fbdo.to<int>()).c_str() : fbdo.errorReason().c_str());
    int iVal = 0;
    Serial.printf("Get int ref... %s\n", Firebase.RTDB.getInt(&fbdo, F("/users/arduino/sensors/ultrasonic"), &iVal) ? String(iVal).c_str() : fbdo.errorReason().c_str());
    Serial.printf("Set pH... %s\n", Firebase.RTDB.setInt(&fbdo, F("/users/arduino/sensors/pH"), count + 10) ? "ok" : fbdo.errorReason().c_str());
    Serial.printf("Get pH... %s\n", Firebase.RTDB.getInt(&fbdo, F("/users/arduino/sensors/pH")) ? String(fbdo.to<float>()).c_str() : fbdo.errorReason().c_str());

    if(Firebase.RTDB.getInt(&fbdo, "/users/arduino/led/R")){
      if(fbdo.dataType() == "int"){
        R = fbdo.intData();
        Serial.println(R);
      }
    }
    if(Firebase.RTDB.getInt(&fbdo, "/users/arduino/led/G")){
      if(fbdo.dataType() == "int"){
        G = fbdo.intData();
        Serial.println(G);
      }
    }
    if(Firebase.RTDB.getInt(&fbdo, "/users/arduino/led/B")){
      if(fbdo.dataType() == "int"){
        B = fbdo.intData();
        Serial.println(B);
      }
    }
    
    Serial.printf("get from firebase...%d\n", R);
    Serial.printf("get from firebase...%d\n", G);
    Serial.printf("get from firebase...%d\n", B);
    
/*
    Serial.printf("Set double... %s\n", Firebase.RTDB.setDouble(&fbdo, F("/test/double"), count + 35.517549723765) ? "ok" : fbdo.errorReason().c_str());
    Serial.printf("Get double... %s\n", Firebase.RTDB.getDouble(&fbdo, F("/test/double")) ? String(fbdo.to<double>()).c_str() : fbdo.errorReason().c_str());
    Serial.printf("Set string... %s\n", Firebase.RTDB.setString(&fbdo, F("/test/string"), F("Hello World!")) ? "ok" : fbdo.errorReason().c_str());
    Serial.printf("Get string... %s\n", Firebase.RTDB.getString(&fbdo, F("/test/string")) ? fbdo.to<const char *>() : fbdo.errorReason().c_str());
*/

    FirebaseJson json;

    json.add(String(count), F("send"));
    Serial.printf("Update node... %s\n", Firebase.RTDB.updateNode(&fbdo, F("/test/json/value/round"), &json) ? "ok" : fbdo.errorReason().c_str());
    
    Serial.println();
    count++;
  }
}
