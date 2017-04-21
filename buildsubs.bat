cd .\service
START gradle clean build
cd ..
cd .\client
START gradle build uberjar jacocotestreport
cd ..