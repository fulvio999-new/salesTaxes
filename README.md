# salesTaxes
Sales Taxes application

* How to configure *
the configuration parameters are in appConfiguration.properties file 


* How to build *
To get a jar with only source code run:
mvn clean package

To crete a standalone jar (ie with also the dependency) run:
mvn assembly:assembly

Travis link: https://travis-ci.org/fulvio999/salesTaxes

* How to run *
a) From your IDE run SalesTaxesMain.java class

b) As standalone jar
from a command line execute:
java -jar salesTaxes-<version>-bin.jar

note: on *nix systems can be necessary add the execution permission with : 
chmod +x salesTaxes-<version>-bin.jar 


* Logging *
In the same folder of the jar file is created the application log file


* NOTES *
- Instead of create two list with medical and food goods, is requested at the user to provide the 'category' of the purchased good (book, food, medical, other).
This solution is adopted to have more flexible solution to decide when apply the 'basic sales tax'.