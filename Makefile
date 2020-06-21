all: jsonPackage gamePackage main

main:
	javac --release 8 Main.java

gamePackage:
	javac --release 8 gamePackage/*.java

jsonPackage:
	javac --release 8 jsonPackage/*.java

clean:
	rm */*.class
	rm *.class
