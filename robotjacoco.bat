cd .\service
START java -jar build\libs\gamegogy-service-1.0.jar 
cd ..
timeout 8
cd .\client\build\reports\jacoco\test\html 
start index.html
cd ..
cd ..
cd ..
cd ..
cd ..
gradle runrobot