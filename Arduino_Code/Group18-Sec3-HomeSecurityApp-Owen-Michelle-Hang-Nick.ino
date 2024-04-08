// Arduino Control Code
// Group18-Sec3-HomeSecurityApp-Owen-Michelle-Hang-Nick

#include <WiFi.h>                                                
#include <IOXhop_FirebaseESP32.h>    
#include <ESP32Servo.h>
 
                                         
#define FIREBASE_Host "security-app-d5288-default-rtdb.firebaseio.com"                 
#define FIREBASE_authorization_key "4iKq1XzFQ09ryXA9zOj8YR2x7ZhgbWTFIDDq8XlE" 
#define Your_SSID "SM-S911W3043"     
#define Your_PASSWORD "iheznuk7nie7czn"     
#define led_gpio 2
#define led_gpio15 15
#define motion_gpio 21
#define servo_gpio 19
#define speaker_gpio 18
#define alarms_gpio 22
int pos = 0;    // variable to store the servo position

Servo myservo;

String led_state = "";       
String motion_mode = "";
String motion_state = "";    
String servo_state = "";      
String speaker_state = "";      
String alarms_state = "";                                                                    


void setup() {

  Serial.begin(115200);
  delay(1000);
  pinMode(led_gpio, OUTPUT);        
  pinMode(motion_gpio, INPUT);   
  pinMode(speaker_gpio, OUTPUT);   
  pinMode(alarms_gpio, OUTPUT);   
  	// Allow allocation of all timers
	ESP32PWM::allocateTimer(0);
	ESP32PWM::allocateTimer(1);
	ESP32PWM::allocateTimer(2);
	ESP32PWM::allocateTimer(3);
	myservo.setPeriodHertz(50);    // standard 50 hz servo
	myservo.attach(19, 500, 2400);

  WiFi.begin(Your_SSID, Your_PASSWORD);                                      
  Serial.print("Connecting to WIFI");
  while (WiFi.status() != WL_CONNECTED) {
  Serial.print(".");
  delay(500);
  }

  Serial.println();
  Serial.print("Connected to WIFI!");
  Serial.println();
  Serial.print("IP Address: ");
  Serial.println(WiFi.localIP());                                             
  Firebase.begin(FIREBASE_Host, FIREBASE_authorization_key);                              
  Firebase.setString("LED State: ", "OFF");                                    

}

void loop() {

  turnLight_LED();

  turnMotion_Sensor();

  turnServo_LED();

  turnSpeaker_LED();

  turnAlarms_LED();
}

void turnLight_LED()
{
  led_state = Firebase.getString("LED_State");         
  if (led_state == "ON") {      

    Serial.println("Led is ON");  

    digitalWrite(led_gpio, HIGH);   

  }

  else if (led_state == "OFF") {             

    Serial.println("Led is OFF");

    digitalWrite(led_gpio, LOW);                                                         

  }

  else {

     Serial.println("Wrong value entered! Only ON or OFF accepted");

  }
}

void turnMotion_Sensor()
{
    motion_mode = Firebase.getString("MOTION_Mode");

  if(motion_mode == "ON")
  {
    int motionDetected = digitalRead(motion_gpio);
    
    if (motionDetected == HIGH) {

      Serial.println("Motion detected!");

      motion_state = "ON";

      digitalWrite(led_gpio15, HIGH); 

    } else {

      Serial.println("No motion detected.");

      motion_state = "OFF";

      digitalWrite(led_gpio15, LOW); 

    }

    Firebase.setString("MOTION_State", motion_state);

    delay(500);
  }
}

void turnServo_LED()
{
  servo_state = Firebase.getString("DOOR_State");

  if(servo_state == "ON")
  {
    moveServo();    

    Firebase.setString("DOOR_State", "OFF");
  } 
}

void turnSpeaker_LED()
{
  speaker_state = Firebase.getString("SPEAKER_State");

  if (speaker_state == "ON") {                   

    Serial.println("Speaker is ON");     

    digitalWrite(speaker_gpio, HIGH);                                                         

  }

  else if (speaker_state == "OFF") {             

    Serial.println("Speaker is OFF");

    digitalWrite(speaker_gpio, LOW);                                                         

  }
}

void turnAlarms_LED()
{
  alarms_state = Firebase.getString("ALARMS_State");         
  if (alarms_state == "ON") {                   

    Serial.println("ALARMS is ON");            

    digitalWrite(alarms_gpio, HIGH);                                                         

  }

  else if (alarms_state == "OFF") {             

    Serial.println("ALARMS is OFF");

    digitalWrite(alarms_gpio, LOW);                                                         

  }
}

void moveServo() {
  for (int pos = 0; pos <= 180; pos += 1) {
    myservo.write(pos);
    
    delay(15);
  }
}