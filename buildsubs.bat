cd .\service
START gradle clean build
cd ..
cd .\client
START gradle clean build uberjar jacocotestreport
cd ..