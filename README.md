# KRanvas - Canvas app by K.Rajesh
A basic console based drawing application

## Building and running
This is a maven based Java application. Please install Maven 3 to build.
```
cd kranvas
mvn clean install
cd console
mvn clean package
cd target
java -jar com.kranvas.console-1.0-SNAPSHOT.jar
```

## Using the tool
The tool allows you to create a canvas and draw lines and rectangles. It also has a bucket-fill tool.
**Please note that all the co-ordinates are zero based, i.e. the top left corner is (0, 0)**
Please type 'H' for help

## Structure
The project is divided into 3 modules
1. Core - This provides the basic graphic elements such as Canvas, Image, Pixel, etc. It also provides validation tools.
2. Tools - The tools manipulate the image as per the specified parameters.
3. Console - This is responsible for rendering the image to the console and to take instructions from the user via the command line

The idea behind this categorization is that **Tools** can be developed by third parties and hence are not part of the core system.
The image is just a logical representation, it does not know how to display itself to the user.
This is where the **console** package comes in as one of the several possible rendering mechanisms.