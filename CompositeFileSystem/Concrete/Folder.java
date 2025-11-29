package CompositeFileSystem.Concrete;

import CompositeFileSystem.FileSystemNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Folder extends FileSystemNode {

    private HashMap<String,FileSystemNode> children;
    public Folder(String name) {
        super(name);
        this.children = new HashMap<>();
    }

    public void print(int depth) {
        String indent = " ".repeat(depth * 2);
        System.out.println(indent + getName());
        for (FileSystemNode child : children.values()) {
            child.print(depth+1);
        }
    }

    public boolean hasChild(String name){
        return children.containsKey(name);
    }
    public void addChild(FileSystemNode child) {
        this.children.put(child.getName(), child);
    }
    public FileSystemNode getChild(String name) {
        return this.children.get(name);
    }
    public FileSystemNode removeChild(String name) {
        return this.children.remove(name);
    }
    public List<FileSystemNode> getChildren() {
        return new ArrayList<>(children.values());
    }


}
