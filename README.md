# miniSPIM

This is the repository for the open-source low-cost light-sheet microscope based on a monochromatic cellphone CMOS sensor and commercially available laser-scanning video projector.

<p align="center">
<img src="./IMAGES/IMG_20191014_164835.jpg" width="400">
</p>


Disucssion can also be found in [Twitter](https://twitter.com/beniroquai/status/1185129736726560768)

Everything on your own risk, no guarantees that it works or that it harms you! It's just a fun project without any professional use in mind! 

## Motivation

*  Low price (<500€) 
*  Portable
*  Small footprint
*  ready for the incubator 
*  Isotropic resolution of about 3µm 
*  Excitation Wavelength: 450, 530, 605nm 
*  Mechanical X/Y-Stage for coarse sample movevement 
*  Electrical X/Y-Stage for fine through-focus series of the sample 

## Setup

### Optical Setup
<p align="center">
<img src="./IMAGES/miniSPIM_opticalsetup.png" width="400">
</p>

### Remove the lens of the Huawei P9 (at your own risk!!)

In this setup, we had to remove the lens to have bare accessoto the Sony IMX286 monochromatic chip. A minimum set of tools was necessary. A brief description with pictures is shown below

#### Open the cellphone
<p align="center">
<img src="./IMAGES/minispim1.jpeg" width="400">
</p>

#### Get out the camera module
<p align="center">
<img src="./IMAGES/minispim2.jpeg" width="400">
</p>

#### Get out the camera module II
<p align="center">
<img src="./IMAGES/minispim3.jpeg" width="400">
</p>

#### Get rid of the left (!) lens module - take a knife to remove the soldering gently (!)
<p align="center">
<img src="./IMAGES/minispim4.jpeg" width="400">
</p>


#### Get rid of the lens module 
<p align="center">
<img src="./IMAGES/minispim6.jpeg" width="400">
</p>

#### Put the lens module back into the cellphone
<p align="center">
<img src="./IMAGES/minispim7.jpeg" width="400">
</p>

#### Hope that everything went well!
<p align="center">
<img src="./IMAGES/minispim8.jpeg" width="400">
</p>


### Mechanical Setup


#### Sample mount 

One can use asimple syrringe for to prepare a sample e.g. in agarose. To reduce scattering, we provide a simple sample chaber which an be filled with water. The tip of the agarose can be put inside the water. 

### Electronic setup

For the wiring of the two bipolar motors from the low-cost XY-stage, we used an Arduino Nano equipped with two H-bridges directly connected to the motor. 

The following gives you a very brief introduction of the workin principle of the low-cost, but very precise Z-stage which is used in our setups. You can find a more detailed version of the description at [aliexpress](https://aliexpress.com/item/Micro-stepping-motor-cross-platform-small-mobile-digital-microscope-XY-axis-table-experiment-two-slide/32790147861.html) (chineses though). 
I have absolutely no clue where this piece is actually coming from, but it is working really great! 

It is equipped with two bipolar stepper motors for X and Y direction. The wiring follows (from left to right):

	1 -- A+	
	2 -- B+	
	3 -- A-	
	4 -- B-
	
![xypins](./images/XYpins.png)

It can be controlled with an ESP32 or Arduino for example. Code is very easy as it relies on the $stepper.h$ library in Arduion IDE. We tested seveal H-Bridges to amplify the current coming from the Arduino GPIOs. The L293m works fine, but the DRV8833 is also good and even much smaller. The complete tutorial for can also be found at [Adafruit](https://learn.adafruit.com/adafruit-drv8833-dc-stepper-motor-driver-breakout-board/stepper-motor-usage). 

The motor can be connected as follows:

![xypins](./images/XY_Stage_v0_Schaltplan.png)

If one uses an H-Bridge one simply need to make sure, that A+/A- and B+/B- are connected to MOTOR A and MOTOR B respectively. 

#### Properties
	- Impedance: ~45 ohm
	- Stepsize: 18 degree
	- Voltage: 5 V
	- Peak Current: 100mA 
	- Movement X/Y: 2.4 mm

	
### Code 
The code is from the Adafruit example with the wiring explained above:

	#include <Stepper.h>
 
	// change this to the number of steps on your motor
	#define STEPS 200
	 
	// create an instance of the stepper class, specifying
	// the number of steps of the motor and the pins it's
	// attached to
	Stepper stepper(STEPS, 4, 5, 6, 7);
	 
	 
	void setup()
	{
	  Serial.begin(9600);
	  Serial.println("Stepper test!");
	  // set the speed of the motor to 30 RPMs
	  stepper.setSpeed(60);
	}
	 
	void loop()
	{
	  Serial.println("Forward");
	  stepper.step(STEPS);
	  Serial.println("Backward");
	  stepper.step(-STEPS);
	}
	
	
### NOTES
Make sure you're releasing the Motor after usage, otherwise it can get quiet hot!



## Parts 

### 3D printed parts 
All files can be found in the folder [STL](./STL). 

* 1x [Base Plate](./STL/0_MiniSPIM_Base_v1.stl)
* 1x [Sample Chamber](./STL/10_MiniSPIM_Samplechamber.stl)
* 1x [Space for Smartphone](./STL/10_MiniSPIM_Smarthponebase.stl)
* 1x [Lid for the Baseplate](./STL/10_MiniSPIM_Lid.stl)
* 1x [Adapter for the XY-Stage: Arm which holds the syrringe](./STL/10_MiniSPIM_XY_Stageplate.stl)
* 1x [Adapter for the XY-Stage: Arm which holds the electronic XY-stage](./STL/10_MiniSPIM_XY_Stageplate.stl)

### Bill of materials

(Links to the shops are not sponsored!)

* 1x cellphone (Huawei P9, remove the lens from the monochromatic camera) - ***150€***
* 1x XY-Stage (60x60xmm range) - ***60€***
* 1x motorized XY-Stage ([aliexpress](https://aliexpress.com/item/Micro-stepping-motor-cross-platform-small-mobile-digital-microscope-XY-axis-table-experiment-two-slide/32790147861.html) ) - ***3€***
* 1x ESP32 - ***5€***
* Set of M3 screws - ***1€***
* 3x Coverglass, 18x18mm - ***1€***
* 1x Laser-scanning projector, SONY MHL XXX - ***250€***
* Filterset ***1€***
* Wires, USB-Cable, HDMI-Cable - ***15€***

## Results

### Lenstissue Focus-Sweep

Here a lens-tissue "labelled" with fluorescent marker (Stabilo, Fluorescent Yellow) was swept through the focus. A theater filter blocks the light of the blue exciting laser. 

<p align="center">
<img src="./IMAGES/miniSPIM_Focus_Sweep_Lenstissue.gif" width="400">
</p>

A 3D projection of the result in ClearVolume looks like this: 
<p align="center">
<img src="./IMAGES/miniSPIM_lenstissue.png" width="400">
</p>

### Bright-field and light-sheet mode of Pollen

<p align="center">
<img src="./IMAGES/IMG_20191014_163941.jpg" width="400">
</p>

<p align="center">
<img src="./IMAGES/IMG_20191014_164712.jpg" width="400">
</p>

Better images are coming soon! 


## License
Hardware follows the CERN license. Everything else is licensed under CC-BY-NC. 

Everything was developed in the [@Heintzmann-Lab](www.nanoimaging.de) around Rainer Heintzmann at the IPHT Jena. 

## Contribution

We need proper samples! If you know how to prepare easy to use cleared fluorescent samples ready for the SPIM, please contact us!! :) 

In case you have any questions, please fiel an issue or write us an email. We try to improve the setup. 

