import static org.junit.Assert.*;
//Import the junit library
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
//Create Test class
public class MarkdownParseTest {
    //Test declaration and create method to test addition
    @Test
    public void addition() {
        //Self explanatory
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testGetLinks() throws IOException{
        ArrayList<String> actual = new ArrayList<>();
        actual.add("https://something.com");
        actual.add("some-thing.html");
        String content = Files.readString(Path.of("test-file.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertArrayEquals(actual.toArray(), links.toArray());
    }

    @Test
    public void testOne() throws IOException{
        ArrayList<String> actual = new ArrayList<>();
        actual.add("https://something.com");
        String content = Files.readString(Path.of("new-test.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertArrayEquals(actual.toArray(), links.toArray());
    }

    @Test
    public void testTwo() throws IOException{
        ArrayList<String> actual = new ArrayList<>();
        String content = Files.readString(Path.of("new-test2.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertArrayEquals(actual.toArray(), links.toArray());
    }

    @Test
    public void testThree() throws IOException{
        ArrayList<String> actual = new ArrayList<>();
        String content = Files.readString(Path.of("new-test3.md"));
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertArrayEquals(actual.toArray(), links.toArray());
    }

    // @Test
    // public void failure(){
    //     assertEquals(2, 3);
    // }

    @Test
    public void testLabInputOne() throws IOException{
        String content = Files.readString(Path.of("labtest1.md"));
        List<String> expected = List.of("'google.com", "google.com", "ucsd.edu");
        assertEquals(expected, MarkdownParse.getLinks(content));
    }

    @Test
    public void testLabInputTwo() throws IOException{
        String content = Files.readString(Path.of("labtest2.md"));
        List<String> expected = List.of("a.com","a.com(())","example.com");
        assertEquals(expected, MarkdownParse.getLinks(content));
    }

    @Test
    public void testLabInputThree() throws IOException{
        String content = Files.readString(Path.of("labtest3.md"));
        List<String> expected = List.of("https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule");
        assertEquals(expected, MarkdownParse.getLinks(content));
    }
}