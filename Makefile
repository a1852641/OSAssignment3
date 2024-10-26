all:
	javac -d bin src/main/java/com/example/*.java
	jar cvfe assignment3.jar com.example.Main -C bin .

clean:
	rm -rf bin/*.class assignment3.jar
