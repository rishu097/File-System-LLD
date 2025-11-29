import CompositeFileSystem.Concrete.File;
import CompositeFileSystem.FileSystemNode;
import SearchingStrategy.ConcreteStrategy.FileNameAndSize;
import SearchingStrategy.SearchStrategy;

import java.util.HashMap;
import java.util.List;

public class FileSystemDemo {
    public static void main(String[] args) {
        FileSystem fileSystem = FileSystem.getFileSystem();
        fileSystem.mkdir("/rishu/raj");
        fileSystem.mkdir("/rishu/prince");
        fileSystem.addFile("/rishu/prince/freind.txt","They are good Freinds");
        fileSystem.mkdir("/rishu/prince/freind");
        System.out.println(fileSystem.readFile("/rishu/prince/freind.txt"));
        fileSystem.print();
        fileSystem.cd("rishu");
        fileSystem.cd("prince");
        System.out.println(fileSystem.pwd());
        fileSystem.cd("freind");
        System.out.println(fileSystem.pwd());
        fileSystem.cdBack();
        System.out.println(fileSystem.pwd());

        SearchStrategy strategy = new FileNameAndSize();
        HashMap<String,Object> map = new HashMap<>();
        map.put("nameRegex","freind");
        map.put("minSize",0);
        map.put("maxSize",100);

        List<FileSystemNode> nodes = fileSystem.search("/",strategy,map);
        for(FileSystemNode node:nodes){
            System.out.println(node.getName());
        }
    }

}
