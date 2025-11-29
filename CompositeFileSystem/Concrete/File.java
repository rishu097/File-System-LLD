package CompositeFileSystem.Concrete;

import CompositeFileSystem.FileSystemNode;

public class File extends FileSystemNode {

    private String content;
    private int size;
    public File(String name) {
        super(name);
        this.size = 0;
    }

    public void print(int depth) {
        String indent = " ".repeat(depth * 2);
        System.out.println(indent + getName());
    }

    public int getSize() {
        return size;
    }

    public void addContent(String content) {this.content = content; this.size =  content.length();}
    public String getContent() {return content; }
    public void updateContent(String content) {this.content = content; this.size = content.length();}

}
