package SearchingStrategy.ConcreteStrategy;

import COR.ConcreteHandler.MaxSizeHandler;
import COR.ConcreteHandler.MinSizeHandler;
import COR.ConcreteHandler.NameHandler;
import COR.Handler;
import COR.HandlerChain;
import CompositeFileSystem.Concrete.Folder;
import CompositeFileSystem.FileSystemNode;
import SearchingStrategy.SearchStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileNameAndSize implements SearchStrategy {

    private HandlerChain handlerChain;
    public FileNameAndSize() {
        handlerChain = new HandlerChain();
        handlerChain.addHandler(new NameHandler());
        handlerChain.addHandler(new MinSizeHandler());
        handlerChain.addHandler(new MaxSizeHandler());

    }


    @Override
    public List<FileSystemNode> search(Folder node, Map<String,Object> params) {
        List<FileSystemNode> fileSystemNodes = new ArrayList<>();
        searchRecursive(node,params,fileSystemNodes);
        return fileSystemNodes;
    }

    private void searchRecursive(Folder node, Map<String,Object> params, List<FileSystemNode> fileSystemNodes) {
        for(FileSystemNode children : node.getChildren()){
            if(handlerChain.handle(children,params))
                fileSystemNodes.add(children);
            if(children instanceof Folder){
                searchRecursive((Folder)children,params,fileSystemNodes);
            }
        }

    }
}
