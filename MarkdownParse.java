//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);
            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf(")", openParen);
            if(openBracket == -1 || openParen == -1 || closeBracket == -1 || closeParen == -1){
                break;
            }else if(closeBracket != openParen-1){
                currentIndex = openParen +1;
                continue;
            }
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        File fileThing = new File(args[0]);
        if(fileThing.isDirectory()){
            for (File inFolder : fileThing.listFiles()) {
                fileName = Path.of(fileThing.getName()+ "/" + inFolder.getName());
                System.out.println(fileName.getFileName());
                String content = Files.readString(fileName);
                ArrayList<String> links = getLinks(content);
                System.out.println(links);
            }
        }else{
            System.out.println(args[0]);
            String content = Files.readString(fileName);
            ArrayList<String> links = getLinks(content);
            System.out.println(links);
        }
    }
}
