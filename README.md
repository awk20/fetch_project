# fetch_project
Instructions on how to build and run the receipts project

### Prereq
Java 21 <br/>
Docker running on machine <br/>
(Optional) Postman (I used it for ease of testing) <br/>

## Steps
### Step 1: Build Project File
Create the .jar file with the following command
```
./mvnw clean package
```
This creates your application file in the ```target/``` directory

### Step 2: Create Docker Image
Run follwoing command
```
docker build -t fetch_project .
```
This will create the Docker image needed to run the project

### Step 3: Run Application in Container
```
docker run -p 8080:8080 fetch_project
```
This runs the project on the exposes port 8080

### Step 4: Project Running
Project can be accessed on ```http://localhost:8080``` if desired
