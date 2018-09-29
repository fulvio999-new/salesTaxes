# salesTaxes
Sales Taxes application: a Java based application to calculate the taxes over goods.

* How to configure the application

The configuration parameters are in appConfiguration.properties file under the sources tree.
Note: the application is already configured with default parameters.

* How to build the application

The building is done with Maven
Application can be packaged as single jar (without dependency) or as standalone jar (jar + dependency):

To get a single jar run:
mvn clean package

To crete a standalone jar run:
mvn assembly:assembly

To build with Travis: https://travis-ci.org/fulvio999/salesTaxes

* How to run the appplication

The application can be execute using two methods (depends on if you have the source or the jar)

a) From your IDE (eg. Eclipse) run SalesTaxesMain.java class

b) As standalone jar (jar with dependency)
from the command line execute:
java -jar salesTaxes-<version>-bin.jar

note: on *nix systems can be necessary add the execution permission with : 
chmod +x salesTaxes-<version>-bin.jar 

* Logging 

In the same folder of the jar file is created the application log file

* NOTES:

Due to the fact that is necessary discern between medical and food goods;
instead of create two fixed list with medical and food goods, is requested at the user to provide the 'category' of the purchased good (book,food,medical,other).This solution is adopted to have more flexible solution working with different input.

* Tested with sample input

* Issues/improvements

  See Issues section.
