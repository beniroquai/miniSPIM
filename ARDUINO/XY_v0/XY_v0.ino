//const int motorPin1 = 6;  // Blue   - In 1
//const int motorPin2 = 7;  // Pink   - In 2
//const int motorPin3 = 8; // Yellow - In 3
//const int motorPin4 = 9; // Orange - In 4
//                          // Red    - pin 5 (VCC)


// Red    - pin
int motorPin_X[] = {2, 3, 4, 5};
int motorPin_Y[] = {9, 7, 8, 6};
int motorPin_Z[] = {13, 11, 12, 10};

//int motorPin_X[] = {13,12,14,27};
//int motorPin_Y[] = {26,25,23,32};
//int motorPin_Z[] = {2, 3, 4, 5};



unsigned int lowSpeed  = 10; // Notabene: nicht über 16000
unsigned int highSpeed =  20;
int amplitude = 20;

void setup() {
  pinMode(motorPin_X[0], OUTPUT);
  pinMode(motorPin_X[1], OUTPUT);
  pinMode(motorPin_X[2], OUTPUT);
  pinMode(motorPin_X[3], OUTPUT);
  pinMode(motorPin_Y[0], OUTPUT);
  pinMode(motorPin_Y[1], OUTPUT);
  pinMode(motorPin_Y[2], OUTPUT);
  pinMode(motorPin_Y[3], OUTPUT);
  pinMode(motorPin_Z[0], OUTPUT);
  pinMode(motorPin_Z[1], OUTPUT);
  pinMode(motorPin_Z[2], OUTPUT);
  pinMode(motorPin_Z[3], OUTPUT);
}

void loop()
{
  if (0) {
    drive_left(highSpeed, motorPin_X, 100);
    stop(motorPin_X);
    drive_right(highSpeed, motorPin_X, 100);
    stop(motorPin_X);
  }


  if (1) {
    drive_right(highSpeed, motorPin_Y, amplitude );
    stop(motorPin_Y);
    drive_left(highSpeed, motorPin_Y, amplitude );
    stop(motorPin_Y);
  }


  if (0) {
    drive_right(highSpeed, motorPin_Z, 50);
    stop(motorPin_Z);
    drive_left(highSpeed, motorPin_Z, 50);
    stop(motorPin_Z);
  }


}

void drive_right(unsigned int motorSpeed, int motorPin[], int steps)
{ // 1

  int motorPin1 = motorPin[0];
  int motorPin2 = motorPin[1];
  int motorPin3 = motorPin[2];
  int motorPin4 = motorPin[3];

  for (int i = 0; i < steps; i++)
  {
    digitalWrite(motorPin4, HIGH);
    digitalWrite(motorPin3, LOW);
    digitalWrite(motorPin2, LOW);
    digitalWrite(motorPin1, LOW);
    delay(motorSpeed);

    // 2
    digitalWrite(motorPin4, HIGH);
    digitalWrite(motorPin3, HIGH);
    digitalWrite(motorPin2, LOW);
    digitalWrite(motorPin1, LOW);
    delay(motorSpeed);

    // 3
    digitalWrite(motorPin4, LOW);
    digitalWrite(motorPin3, HIGH);
    digitalWrite(motorPin2, LOW);
    digitalWrite(motorPin1, LOW);
    delay(motorSpeed);

    // 4
    digitalWrite(motorPin4, LOW);
    digitalWrite(motorPin3, HIGH);
    digitalWrite(motorPin2, HIGH);
    digitalWrite(motorPin1, LOW);
    delay(motorSpeed);

    // 5
    digitalWrite(motorPin4, LOW);
    digitalWrite(motorPin3, LOW);
    digitalWrite(motorPin2, HIGH);
    digitalWrite(motorPin1, LOW);
    delay(motorSpeed);

    // 6
    digitalWrite(motorPin4, LOW);
    digitalWrite(motorPin3, LOW);
    digitalWrite(motorPin2, HIGH);
    digitalWrite(motorPin1, HIGH);
    delay(motorSpeed);

    // 7
    digitalWrite(motorPin4, LOW);
    digitalWrite(motorPin3, LOW);
    digitalWrite(motorPin2, LOW);
    digitalWrite(motorPin1, HIGH);
    delay(motorSpeed);

    // 8
    digitalWrite(motorPin4, HIGH);
    digitalWrite(motorPin3, LOW);
    digitalWrite(motorPin2, LOW);
    digitalWrite(motorPin1, HIGH);
    delay(motorSpeed);
  }
}

void drive_left(unsigned int motorSpeed, int motorPin[], int steps)
{ // 1

  int motorPin1 = motorPin[0];
  int motorPin2 = motorPin[1];
  int motorPin3 = motorPin[2];
  int motorPin4 = motorPin[3];

  for (int i = 0; i < steps; i++)
  {
    // 1
    digitalWrite(motorPin1, HIGH);
    digitalWrite(motorPin2, LOW);
    digitalWrite(motorPin3, LOW);
    digitalWrite(motorPin4, LOW);
    delay(motorSpeed);

    // 2
    digitalWrite(motorPin1, HIGH);
    digitalWrite(motorPin2, HIGH);
    digitalWrite(motorPin3, LOW);
    digitalWrite(motorPin4, LOW);
    delay(motorSpeed);

    // 3
    digitalWrite(motorPin1, LOW);
    digitalWrite(motorPin2, HIGH);
    digitalWrite(motorPin3, LOW);
    digitalWrite(motorPin4, LOW);
    delay(motorSpeed);

    // 4
    digitalWrite(motorPin1, LOW);
    digitalWrite(motorPin2, HIGH);
    digitalWrite(motorPin3, HIGH);
    digitalWrite(motorPin4, LOW);
    delay(motorSpeed);

    // 5
    digitalWrite(motorPin1, LOW);
    digitalWrite(motorPin2, LOW);
    digitalWrite(motorPin3, HIGH);
    digitalWrite(motorPin4, LOW);
    delay(motorSpeed);

    // 6
    digitalWrite(motorPin1, LOW);
    digitalWrite(motorPin2, LOW);
    digitalWrite(motorPin3, HIGH);
    digitalWrite(motorPin4, HIGH);
    delay(motorSpeed);

    // 7
    digitalWrite(motorPin1, LOW);
    digitalWrite(motorPin2, LOW);
    digitalWrite(motorPin3, LOW);
    digitalWrite(motorPin4, HIGH);
    delay(motorSpeed);

    // 8
    digitalWrite(motorPin1, HIGH);
    digitalWrite(motorPin2, LOW);
    digitalWrite(motorPin3, LOW);
    digitalWrite(motorPin4, HIGH);
    delay(motorSpeed);
  }
}


void stop(int motorPin[])
{ 
  
  int motorPin1 = motorPin[0];
  int motorPin2 = motorPin[1];
  int motorPin3 = motorPin[2];
  int motorPin4 = motorPin[3];

  digitalWrite(motorPin4, LOW);
  digitalWrite(motorPin3, LOW);
  digitalWrite(motorPin2, LOW);
  digitalWrite(motorPin1, LOW);
}
