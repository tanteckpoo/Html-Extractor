Jmeter Html Extractor (JHE)
=======================
  
About
-----------------------

JHE is a Jmeter post processor which extracts text value out of any html. JHE is built on top of the Jsoup lib which uses jQuery synthax to parse html. 
In this version text() method is supported, in future releases who knows.

Requirements
-----------------------

+	Java 1.6 or greater

+ 	JMeter 2.6

+	Maven 2.0 or greater


Build Instructions
-----------------------

-	Go to the top-level directory of the project and run:  
	```
	mvn clean install
	```	 
-	All components will be compiled in the 'target' subdirectory.

Easy Install
-----------------------

-	Copy the jar you just built (html-extractor-jar-with-dependencies.jar) 
	from the 'target' directory to the '/lib/ext' directory in your 
	local $JMETER_HOME installation.
	
-	Copy all the jars from the 'target/lib' directory to the 'lib' 
	directory in your local $JMETER_HOME installation.

After you've finished with above steps in order to test that everything is installed correctly. Please add Dummy Sampler with Response Data:
```
<html><head><title>First parse</title></head><body><p>Html Extractor is working!</p></body></html>
```
Then add a Post Processors -> Html Extractor , in the jquery selector field write ```p``` and store result in variable of your choice (test).
Following this add Debug Sampler, if in your Debug sampler there is variable ```test``` with the value ```Html Extractor is working!``` you're done!