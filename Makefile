all:
	javac -d bin src/main/java/com/example/*.java
	jar cvfe assignment3.jar com.example.MultiThreadedServer -C bin .

clean:
	rm -rf bin/com/example/*.class assignment3.jar
