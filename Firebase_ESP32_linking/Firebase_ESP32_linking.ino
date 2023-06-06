#include <WiFi.h>
#include <Firebase_ESP_Client.h>

// Provide the token generation process info.
#include <addons/TokenHelper.h>

// Provide the RTDB payload printing info and other helper functions.
#include <addons/RTDBHelper.h>

/* 1. Define the WiFi credentials */
#define WIFI_SSID "AndroidHotspot1440"
#define WIFI_PASSWORD "password"

// For the following credentials, see examples/Authentications/SignInAsUser/EmailPassword/EmailPassword.ino

/* 2. Define the API Key */
#define API_KEY "password"
#define DATABASE_URL "wap-iot-9494c-default-rtdb.asia-southeast1.firebasedatabase.app"  //<databaseName>.firebaseio.com or <databaseName>.<region>.firebasedatabase.app
// Do not include https:// 

/* 4. Define the user Email and password that alreadey registerd or added in your project */
#define USER_EMAIL "kjghost1@pukyong.ac.kr"
#define USER_PASSWORD "password"


// Define Firebase Data object
FirebaseData fbdo;
FirebaseAuth auth;
FirebaseConfig config;

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

  /* Assign the api key (required) */
  config.api_key = API_KEY;

  /* Assign the user sign in credentials */
  auth.user.email = USER_EMAIL;
  auth.user.password = USER_PASSWORD;

  /* Assign the RTDB URL (required) */
  config.database_url = DATABASE_URL;

  /* Assign the callback function for the long running token generation task */
  config.token_status_callback = tokenStatusCallback; // see addons/TokenHelper.h

  // Or use legacy authenticate method
  // config.database_url = DATABASE_URL;
  // config.signer.tokens.legacy_token = "<database secret>";

  // To connect without auth in Test Mode, see Authentications/TestMode/TestMode.ino

  //////////////////////////////////////////////////////////////////////////////////////////////
  // Please make sure the device free Heap is not lower than 80 k for ESP32 and 10 k for ESP8266,
  // otherwise the SSL connection will fail.
  //////////////////////////////////////////////////////////////////////////////////////////////
  // Limit the size of response payload to be collected in FirebaseData
  fbdo.setResponseSize(2048);

  Firebase.begin(&config, &auth);

  // The WiFi credentials are required for Pico W
  // due to it does not have reconnect feature.
  
  // Comment or pass false value when WiFi reconnection will control by your code or third party library
  Firebase.reconnectWiFi(true);

  Firebase.setDoubleDigits(5);

  config.timeout.serverResponse = 10 * 1000;

}

void loop()
{
  Firebase.ready();
  Firebase.authenticated();
  // Firebase.ready() should be called repeatedly to handle authentication tasks.

  if (Firebase.ready() && (millis() - sendDataPrevMillis > 15000 || sendDataPrevMillis == 0))
  {
    sendDataPrevMillis = millis();

    Serial.printf("Set bool... %s\n", Firebase.RTDB.setBool(&fbdo, F("/test/bool"), count % 2 == 0) ? "ok" : fbdo.errorReason().c_str());

    Serial.printf("Get bool... %s\n", Firebase.RTDB.getBool(&fbdo, FPSTR("/test/bool")) ? fbdo.to<bool>() ? "true" : "false" : fbdo.errorReason().c_str());

    bool bVal;
    Serial.printf("Get bool ref... %s\n", Firebase.RTDB.getBool(&fbdo, F("/test/bool"), &bVal) ? bVal ? "true" : "false" : fbdo.errorReason().c_str());

    Serial.printf("Set int... %s\n", Firebase.RTDB.setInt(&fbdo, F("/test/int"), count) ? "ok" : fbdo.errorReason().c_str());

    Serial.printf("Get int... %s\n", Firebase.RTDB.getInt(&fbdo, F("/test/int")) ? String(fbdo.to<int>()).c_str() : fbdo.errorReason().c_str());

    int iVal = 0;
    Serial.printf("Get int ref... %s\n", Firebase.RTDB.getInt(&fbdo, F("/test/int"), &iVal) ? String(iVal).c_str() : fbdo.errorReason().c_str());

    Serial.printf("Set float... %s\n", Firebase.RTDB.setFloat(&fbdo, F("/test/float"), count + 10.2) ? "ok" : fbdo.errorReason().c_str());

    Serial.printf("Get float... %s\n", Firebase.RTDB.getFloat(&fbdo, F("/test/float")) ? String(fbdo.to<float>()).c_str() : fbdo.errorReason().c_str());

    Serial.printf("Set double... %s\n", Firebase.RTDB.setDouble(&fbdo, F("/test/double"), count + 35.517549723765) ? "ok" : fbdo.errorReason().c_str());

    Serial.printf("Get double... %s\n", Firebase.RTDB.getDouble(&fbdo, F("/test/double")) ? String(fbdo.to<double>()).c_str() : fbdo.errorReason().c_str());

    Serial.printf("Set string... %s\n", Firebase.RTDB.setString(&fbdo, F("/test/string"), F("Hello World!")) ? "ok" : fbdo.errorReason().c_str());

    Serial.printf("Get string... %s\n", Firebase.RTDB.getString(&fbdo, F("/test/string")) ? fbdo.to<const char *>() : fbdo.errorReason().c_str());

    // For the usage of FirebaseJson, see examples/FirebaseJson/BasicUsage/Create_Edit_Parse.ino
    FirebaseJson json;

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
}
