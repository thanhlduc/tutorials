1) Build project:
- mvn clean install

2) Configure project
- create a folder named "config" for 3 configuration files (log4j.properties, simplePingApp.properties, ehcache.xml)
- log4j.properties: configure log file name, log level


3) Run application:
- Windows:
 java -Xmx1024m -cp Path\\To\\config;Path\\To\\simple-ping-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.docler.holdings.simplepingapp.ApplicationStarter

- Linux: 
 java -Xmx1024m -cp /Path/To/config:/Path/To/simple-ping-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.docler.holdings.simplepingapp.ApplicationStarter

