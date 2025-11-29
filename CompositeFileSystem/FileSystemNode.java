package CompositeFileSystem;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class FileSystemNode {

    protected String name;
    protected Date createdAt;
    protected FileSystemNode parent;
    public FileSystemNode(String name) {
        this.name = name;
        this.createdAt = new Date();
        this.parent = null;
    }

    public abstract void print(int depth);
    //Getters
    public void setParent(FileSystemNode parent) {
        this.parent = parent;
    }
    public FileSystemNode getParent() {return this.parent;}
    public String getName() {return name;}
    public Date getCreatedAt() {return createdAt;}



}
