JC = javac
JFLAGS = -classpath .
JD = javadoc
JDFLAGS = -protected -splitindex -use -author -version -d ./javadoc
RM = rm
JR = java

CLASSES = \
		AVLTree.java \
		GUIVisualization.java \
		InputFileGenerator.java \
		Main.java \
		Stock.java \
		StockDataManager.java \

all : Main.class

run: Main.class
	$(JR) -Xint Main $(filter-out $@,$(MAKECMDGOALS))

classes : $(CLASSES:.java=.class)

%.class : %.java
	$(JC) $(JFLAGS) $<

doc:
	$(JD) $(JDFLAGS) *.java 

clean:
	$(RM) *.class 

cleandoc:
	$(RM) -r ./javadoc

rand:
	$(JR) InputFileGenerator