
#define TRIG 32
#define ECHO 33

long ultrason_duration;
float distance_cm;

void setup() {
  Serial.begin(115200);
  pinMode(TRIG, OUTPUT); // We configure the trig as output
  pinMode(ECHO, INPUT); // We configure the echo as input
}

void loop() {
  // Set up the signal
  digitalWrite(TRIG, LOW);
  delayMicroseconds(2);
 // Create a 10 µs impulse
  digitalWrite(TRIG, HIGH);
  delayMicroseconds(10);
  digitalWrite(TRIG, LOW);

  // Return the wave propagation time (in µs)
  ultrason_duration = pulseIn(ECHO, HIGH);

//distance calculation
  distance_cm = ultrason_duration * 17 /1000;

  // We print the distance on the serial port
  Serial.print("Distance (cm): ");
  Serial.println(distance_cm);

  delay(1000);
}
