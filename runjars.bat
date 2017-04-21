cd .\service
START java -jar build\libs\gamegogy-service-1.0.jar 
cd ..
timeout 10
cd .\client 
java -jar build\libs\client-1.0-all.jar http://localhost:8080/gamegogy
cd ..