# Input Java files (components).
JAVA_FILES=BinaryAspect.java BinaryTree.java
# Aspect in Java (with annotations).
JAVA_ASPECT=Aspect1.java

ASPECTJRT={path to aspectjrt.jar}

justJavaCompile: ${JAVA_FILES}
	@javac -cp ${ASPECTJRT} ${JAVA_FILES}

justJavaRun: justJavaCompile
	@echo "== Running without AspectJ =="
	@java BinaryAspect

aspectJCompile: ${JAVA_FILES} ${JAVA_ASPECT}
	@ajc -source 1.8 ${JAVA_FILES} ${JAVA_ASPECT}

aspectJRun: aspectJCompile
	@echo "== Running with AspectJ =="
	@java -cp ${ASPECTJRT}:. BinaryAspect
