import CompositeFileSystem.Concrete.File;
import CompositeFileSystem.Concrete.Folder;
import CompositeFileSystem.FileSystemNode;
import SearchingStrategy.SearchStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.reverse;

public class FileSystem {
    private static FileSystem fileSystem;
    private Folder root;
    private Folder currentDirectory;
    //Singleton Design Pattern for the FileSystem
    private FileSystem(){
        root = new Folder("root");
        currentDirectory = root;
    };
    public static FileSystem getFileSystem() {
        if(fileSystem == null){
            fileSystem = new FileSystem();
        }
        return fileSystem;
    }
    public void cd(String name){
        if(!(currentDirectory.getChild(name) instanceof Folder)){
            throw new IllegalArgumentException("Not a folder");
        }
        currentDirectory = (Folder)currentDirectory.getChild(name);
    }
    public void cdBack(){
        if(currentDirectory.getParent()==null){
            throw new IllegalArgumentException("Already on Root");
        }
        currentDirectory = (Folder)currentDirectory.getParent();
    }
    public String pwd(){
        FileSystemNode helper = currentDirectory;
        StringBuilder path = new StringBuilder();
        List<String> components = new ArrayList<>();
        while(helper.getParent()!=null){
            components.add(helper.getName());
            helper = helper.getParent();
        }
        reverse(components);
        path.append("/root");
        for(String component : components){
            path.append("/").append(component);
        }
        return path.toString();
    }
    private Folder traverse(String path, boolean createMissingFolder){
        String[] components = path.split("/");
        Folder helper = root;
        for(String component : components){
            if(component.isEmpty()){ continue; }
            if(!(helper.getChild(component) instanceof Folder)){
                if(createMissingFolder){
                    Folder folder = new Folder(component);
                    folder.setParent(helper);
                    helper.addChild(folder);
                }
                else
                    return null;
            }
            helper = (Folder)helper.getChild(component);
        }
        return helper;
    }
    public void mkdir(String path){
        traverse(path,true);
    }
    public boolean addFile(String path,String content){
        Folder parent = traverse(path.substring(0,path.lastIndexOf('/')),true);
        if(parent == null){
            throw new IllegalArgumentException("Enter a valid File Path");
        }
        String fileName = path.substring(path.lastIndexOf("/") + 1);
        File node = (File) parent.getChild(fileName);
        if(node == null){
            node = new File(fileName);
            node.setParent(parent);
            parent.addChild(node);
            node.addContent(content);
        }
        else {
            node.updateContent(content);
        }
        return true;
    }
    public String readFile(String path) {
        Folder parent = traverse(path.substring(0,path.lastIndexOf('/')),false);
        if(parent == null){
            throw new IllegalArgumentException("Enter a valid File Path");
        }
        String fileName = path.substring(path.lastIndexOf("/") + 1);
        File node = (File) parent.getChild(fileName);
        if(node==null){
            throw new IllegalArgumentException("File does not exist");
        }
        else {
            return node.getContent();
        }

    }
    public void print(){
        root.print(0);
    }

    List<FileSystemNode> search(String path, SearchStrategy strategy, Map<String,Object> params){
        Folder folder = traverse(path,false);
        if(folder==null){
            throw new IllegalArgumentException("Enetered Folder does not exist");
        }
        return strategy.search(folder,params);
    }
}
