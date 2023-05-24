#include <WiFi.h>
#include <Firebase_ESP_Client.h>
#include <FirebaseESP32.h>
#include <FirebaseJson.h>
#include <addons/TokenHelper.h>
#include <addons/RTDBHelper.h>

#define WIFI_SSID "AndroidHotspot1440"
#define WIFI_PASSWORD "password"
#define API_KEY "password"
/* 3. Define the RTDB URL */
#define DATABASE_URL "test-for-connect-3424c-default-rtdb.asia-southeast1.firebasedatabase.app" //<databaseName>.firebaseio.com or <databaseName>.<region>.firebasedatabase.app
// Do not include https:// 
/* 4. Define the user Email and password that alreadey registerd or added in your project */
#define USER_EMAIL "mithmake@gmail.com"
#define USER_PASSWORD "password"

FirebaseData fbdo;
FirebaseAuth auth;
FirebaseConfig config;

String led_state;
unsigned long sendDataPrevMillis = 0;
unsigned long count = 0;

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
  Firebase.reconnectWiFi(true);
  Firebase.setDoubleDigits(5);

  config.timeout.serverResponse = 10 * 1000;
}

void loop() {
  Firebase.ready();
  Firebase.authenticated();
  
  if(Firebase.ready()){
    led_state = (Firebase.RTDB.getString(&fbdo, F("test/LED/State")));
    int iVal = 0;
    Serial.printf("Set int... %s\n", Firebase.RTDB.setInt(&fbdo, F("/test/int"), count) ? "ok" : fbdo.errorReason().c_str());
    Serial.printf("Get int... %s\n", Firebase.RTDB.getInt(&fbdo, F("/test/int")) ? String(fbdo.to<int>()).c_str() : fbdo.errorReason().c_str());
    Serial.printf("Get int ref... %s\n", Firebase.RTDB.getInt(&fbdo, F("/test/int"), &iVal) ? String(iVal).c_str() : fbdo.errorReason().c_str());
//    Serial.println(getValue);
    if(led_state == "BLUE")
    {
      
    }
    else if(led_state == "Green")
    {
      
    }
    else
    {
      
    }

    if (count == 0)
    {
      json.set("value/round/" + String(count), F("cool!"));
      json.set(F("value/ts/.sv"), F("timestamp"));
      Serial.printf("Set json... %s\n", Firebase.RTDB.set(&fbdo, F("/test/json"), &json) ? "ok" : fbdo.errorReason().c_str());
    }
    else
    {
      json.add(String(count), F("smart!"));
      Serial.printf("Update node... %s\n", Firebase.RTDB.updateNode(&fbdo, F("/test/json/value/round"), &json) ? "ok" : fbdo.errorReason().c_str());
    }

    Serial.println();

    count++;
  
  }
  delay(1000);
}
