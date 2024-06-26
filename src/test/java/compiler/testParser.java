package compiler;


import static org.junit.jupiter.api.Assertions.*;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

import compiler.Compiler;
import compiler.frontend.SimpleCPrinter;

import java.io.File;
import java.util.Arrays;
import java.util.List;

class testParser {
	private void testParser(String path) {
		String contentInit = Compiler.readFile(path);
		System.out.println("Initial content is : \n" + contentInit);
		ParseTree tree = Compiler.parse(contentInit);
		System.out.println("Parsed !");
    	SimpleCPrinter astPrinter = new SimpleCPrinter();
    	String genContent = astPrinter.visit(tree);
		System.out.println("Re-generated content is : \n" + genContent);
		ParseTree tree2 = Compiler.parse(genContent);
		System.out.println("Parsed !");
		String genContent2 = astPrinter.visit(tree2);
		System.out.println("Re-re-generated content is : \n" + genContent2);
		assert genContent.equals(genContent2);
	}
	@Test
	void testAllFilesParser() {
		String path = "src/test/resources";
		File folder = new File(path);
		File[] files = folder.listFiles();
		assert files != null;
		List<File> listOfFiles = Arrays.stream(files).sorted().toList();
		for (File file : listOfFiles) {
			if (file.isFile()) {
				testParser(file.getPath());
			}
		}
	}
}