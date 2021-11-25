# Cucumber Framework for Canvas Calculater

## Tech stack Used
Java, Cucumber, Selenium Webdriver, TestNG, Extent, Docker

# Prerequisites 
- Java 8 and above 
- Any IDE 
- Install Maven and set the Environment Variable
- For Cucumber's actual flavour, install a plugin for cucumber in IDE and convert project into cucumber project
- Download and Extract this project to your local or use git clone https://github.com/aravind12c/flinktestassesment.git
- Import as a Maven project in any IDE

### To run in Docker
- Install Docker or Docker Desktop (Refer url https://docs.docker.com/engine/install/)
- Open Docker Desktop or Start the Docker Engine
- Open Command and give cd "Project Folder"
- Then enter command docker-compose up -d
    
## Project Setup 
- Driver setup and capabilities : \src\main\java\com.canvas.driverInit\
- Page Obejcts 					: \src\main\java\com.canvas.pageObjects\
- Web Utilities 				: \src\main\java\com.canvas.webUtilities\
- Web Configs 					: \src\test\resources\WebConfig
- Test Runner  					: \src\test\java\com.canvas.testfactory\
- Step Definitions 				: \src\test\java\com.canvas.stepDefinitions\
- Feature files 				: \src\test\resources\Features\
- Extent Configs				: \src\test\resources\extent-config\
- Extent Properties				: \src\test\resources\
- Reports       				: \reports

## Pre-Steps
- If you want to update URL, please open config.properties from Web Config and update it.
- In you want to run the project in docker, in config properties change the runmode to remote and if you want to run in local change the runmode to local(By default its in local)

## Steps to run
- Open TestRunner file, from "Test Runner" folder
- Now run as JUNIT, please configure it so that it can be run as JUnit 4
- or
- go to the project folder and use mvn clean install
- or
- run the pom.xml as maven build

## Approach 
Below are the points considered while creating the framework
- Project - Maven Project
- Design pattern - Page object model
- Framework - Cucumber(For Test approach), Selenium Driver(For UI Automation), TestNG(For Parallel Execution and Retry Failed Cases), Extent(For Report)
- Language - Java
- Java OCR - Used Aspose OCR free version, to read the content from the image. 
- Report - Extent Report is used. We will have the screenshot as well attached on failed scenarios.

This project is canvas project, where the Calculator is made of Canvas tag. To perform calculations, I used Keyboard actions and to get results I used Aspose OCR to get the values.

## Note 
This project is just for POC, it has to be upgraded, to handle the pop-up, and creating thread safety way to execute in parallel, etc...
