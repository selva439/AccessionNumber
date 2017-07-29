# EMBL Exercise 

<h6>Read a list of AccessionNumbers and return transformed List of range and in sorted order.</h6>

*AccessionNumbers are nothing but a string followed by digits combination in the format L..LN..N where L denotes Capital Alpha Chars and N denotes 0-9 digits eg: ABC0123*

## Goal of this project

*The goal of the project is to format the list of AccessionNumbers we input*

```
Eg : BB123,ABC345,DSS345 is transformed to sorted list ABC345,BB123,DSS345

ABC0123,ABC0124,ABC0125,ABC123 is transformed to ABC0123-ABC0125,ABC123
//Above format is like if any consecutive N..N share the same prefix L..L are collapsed into an accession number range.

**Note**: N..N should be of same lenth as well

If any list is provided wih other than L..LN..N format the program will throw error.

```

## Getting Started

*Below are the steps to set up the project in local development environment.*

### Prerequisites

* **IDE - Eclipse / IntelliJ**
* **JDK1.8**
* **Maven**

  *And all paths set in environment variables*

### Importing the project

*Please import the java sources [src folder] using maven import from existing sources in the File-> Import options of IDE.
Point the import folder to the folder ( AccessionNumber ) containing the pom.xml*


##### Folder Structure

```
AccessionNumber
 
 |--src
 |   |-main
 |   | |-java
 |   |     |-AccessionNumber.java
 |   |
 |   |-test
 |       |-java
 |	      |-AccessionNumberTest.java
 |--pom.xml
``` 



## Running the tests

*Below are the JUnit test cases written in src/test/AccessionNumberTest.java*

*  **testEmptyAccessionNumberList**
     -  *tests whether Program returns empty String and logs info on to provide a List*
* **testInvalidAccessionNumberList**
     - *test whether Program returns empty String and logs info on to provide a Valid List*
* **testSortedAccessionNumberList** 
     - *test whether Program returns sorted iput List eg : BB123,AB123 should return AB123,BB123*
* **testRangeAccessionNumberList**
     - *test whether Program returns collapsed range of Acc Numbers eg: A123,A124,A125 should return A123-A125*
* **testNotInRangeAccessionNumberList**
     - *test whether Program not returns collapsed range of Acc Numbers eg: A0123,A124,A0125 should return A0123,A124,A0125 as even though the N..N are consecutive their length is different*
* **testAllValidAccessionNumberList**
     - *test whether Program returns correct output for all valid scenarios*

### Building the Project

*Right click pom.xml and select Run as Maven Build and execute the goals **"clean package"** in eclipse
else
Go to AccessionNumber folder path in command prompt and execute below command*

```
D:\AccesionNumber>mvn clean package
```
#### Dependencies

* **commons-lang3**
* **junit**

*As we are using **spring-boot-maven-plugin** to package we will get a single stand-alone jar **AccessionNumber-1.0-SNAPSHOT.jar** in src/target folder*

## Executing the program

*Execute the below command in command prompt*

```
JarPath>java -jar AccessionNumber-1.0-SNAPSHOT.jar <list of AccessionNumbers>

eg : java -jar AccessionNumber-1.0-SNAPSHOT.jar A6,A0,A3
     Output List : A0,A3,A6
```
*Else if you are in Windows run or execute batch script*  **AccessionNumber.bat**


## Authors

* *Selvakumar Kamatchinathan*

## Thanks To

* *Java 8 Streams which made the code easy*
* *EMBL Team for Providing the opportunity to code and learn*

