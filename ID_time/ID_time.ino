#include <WiFi.h>
#include "time.h"

const char* ssid       = "AndroidHotspot1440";
const char* password   = "84571440";

const char* ntpServer = "pool.ntp.org";
const long  gmtOffset_sec = 3600;
const int   daylightOffset_sec = 3600;

struct tm timeinfo;

void printLocalTime()
{
  if(!getLocalTime(&timeinfo)){
    Serial.println("Failed to obtain time");
    return;
  }
  Serial.println(&timeinfo, "%A, %B %d %Y %H:%M:%S");
}

void setup()
{
  Serial.begin(115200);
  
  //connect to WiFi
  Serial.printf("Connecting to %s ", ssid);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
      delay(500);
      Serial.print(".");
  }
  Serial.println(" CONNECTED");
  
  //init and get the time
  configTime(gmtOffset_sec, daylightOffset_sec, ntpServer);
  printLocalTime();

  //disconnect WiFi as it's no longer needed
  WiFi.disconnect(true);
  WiFi.mode(WIFI_OFF);
}

void loop()
{
  int years = timeinfo.tm_year + 1900;
  int months = timeinfo.tm_mon + 1;
  int day_s = timeinfo.tm_mday;

  String Cur_day = String(years) + "." + String(months) + "." + String(day_s);

  Serial.printf("Cur day is %s", Cur_day);
  
  delay(1000);
  printLocalTime();
}
