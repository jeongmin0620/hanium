#include "DHT.h"
#define DHTTYPE DHT22
#define DHTPIN 14
#define RED 4
#define GREEN 5
#define BLUE 2

#include <Boards.h>
#include <Firmata.h>
#include <FirmataConstants.h>
#include <FirmataDefines.h>
#include <FirmataMarshaller.h>
#include <FirmataParser.h>

// WiFi
#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>

const char* ssid     = "Awwwwooov-v";
const char* password = "11223344556678";
String host = "http://101.101.218.94";
WiFiServer server(80);
WiFiClient client;
HTTPClient http;

void httpclient(String char_input) {
  String data = char_input;
  String cmd = "/plantcare/write001.php?temp=" + data;
  String phpHost = host + cmd;
  Serial.print("Connect to ");
  Serial.println(phpHost);

  http.begin(client, phpHost);
  http.setTimeout(1000);
  int httpCode = http.GET();

  if (httpCode > 0) {
    Serial.printf("GET code : %d\n\n", httpCode);

    if (httpCode == HTTP_CODE_OK) {
      String payload = http.getString();
    }
  }
  else {
    Serial.printf("GET failed, error: %s\n", http.errorToString(httpCode).c_str());
  }
  http.end();
}

int soil = 16;
int result = 0;
String a = "";

#define AA 12
#define AB 13


DHT dht(DHTPIN, DHTTYPE);



void setup() {
  Serial.begin(115200);
  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);
  server.begin();
  Serial.println("test!");
  pinMode(RED, OUTPUT);
  pinMode(GREEN, OUTPUT);
  pinMode(BLUE, OUTPUT);

  pinMode(AA, OUTPUT);
  pinMode(AB, OUTPUT);
  pinMode(soil, INPUT);

}

void loop() {
  result = digitalRead(soil);
  Serial.print("측정 값 : ");
  Serial.println(result);

  if (result == 1) {
    a = "매우 건조함!";
    Serial.println("매우 건조함!");
    digitalWrite(AA, HIGH) ;
    digitalWrite(AB, LOW);
    delay(1000);
  } else if (result == 0) {
    a = "충분함";
    Serial.println("충분함");
    digitalWrite(AA, LOW);
    digitalWrite(AB, LOW);
    delay(1000);
  }// 토양습도 + 모터


  float h = dht.readHumidity();
  float t = dht.readTemperature();
  Serial.println(h);
  Serial.println(t);

  if (t >= 30 && h >= 80) { //온도가 30도 이상 , 습도가 80이상일 때
    digitalWrite(RED, HIGH); //그린 LED on
    digitalWrite(GREEN, LOW);
    digitalWrite(BLUE, HIGH);
    delay(2000);
  } else if (t <= 29 && h <= 79) { // 온도가 30도 이하, 습도가 80이하일 때
    digitalWrite(RED, LOW);
    digitalWrite(GREEN, LOW);
    digitalWrite(BLUE, LOW);
    delay(2000);// 레드,그린,블루 LED on -> 기본 LED 색
  } // 온습도


  /*uint16_t rawVal; //정수변수 선언
    rawVal = analogRead(A0); // A1포트값을 0~1023사이의 값으로 데이터 수집
    float vVal = rawVal * 5 / 1023.0; //전압값으로 변환
    Serial.println("UV detecting. . .");
    Serial.print("Raw Value(0-1023) : ");
    Serial.print(rawVal); // 측정값 시리얼모니터로 출력
    Serial.print(" = ");
    Serial.print(vVal, 3); // 변환된 전압값을 소수점 3자리까지 출력
    Serial.print("[V]");
    Serial.println();
    Serial.print("UV Index(0 - 10) : ");
    Serial.println(vVal * 10, 1); // 전압값 x10으로 UVI 변환값 출력

    if (rawVal >= 25) { // 자외선인 25 이상일 때
    digitalWrite(RED, LOW); //레드 LED on
    digitalWrite(GREEN, HIGH);
    digitalWrite(BLUE, HIGH);
    delay(1000);
    } else if (rawVal <= 25) { // 자외선이 25 이하일 때
    digitalWrite(RED, LOW);
    digitalWrite(GREEN, LOW);
    digitalWrite(BLUE, LOW);
    delay(1000);// 레드,그린,블루 LED on -> 기본 LED 색
    } // uv*/


  int level = analogRead(A0);  // 수위측정센서의 신호를 측정합니다.
  Serial.println(level); //시리얼 모니터에 값을 출력합니다.

  if (level <= 100) {
    digitalWrite(RED, HIGH);
    digitalWrite(GREEN, HIGH);
    digitalWrite(BLUE, LOW);
  }

  else {
    digitalWrite(RED, LOW);
    digitalWrite(GREEN, LOW);
    digitalWrite(BLUE, LOW);
  } // 수위측정

  String str_output = String(t) + "&humidity=" + String(h) + "&level=" + String(level) + "&soil=" + String(result);
  delay(1000);
  httpclient(str_output);

  delay(1000);

}