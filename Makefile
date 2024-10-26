all: assignment3.jar assignment3

assignment3.jar:
	javac -d bin src/main/java/com/example/*.java
	jar cvfe assignment3.jar com.example.MultiThreadedServer -C bin .

assignment3: assignment3.jar
	echo '#!/bin/bash\njava -jar assignment3.jar "$$@"' > assignment3
	chmod +x assignment3

clean:
	rm -rf bin/com/example/*.class assignment3.jar assignment3
