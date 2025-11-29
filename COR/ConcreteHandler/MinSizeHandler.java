package COR.ConcreteHandler;

import COR.Handler;
import CompositeFileSystem.Concrete.File;
import CompositeFileSystem.FileSystemNode;

import java.util.Map;

public class MinSizeHandler extends Handler {

    @Override
    public boolean handle(FileSystemNode node, Map<String, Object> params) {
        if(!(node instanceof File)) return true;
        File fileNode = (File)node;
        if(params.containsKey("minSize")){
            Integer minSize = (Integer) params.get("minSize");
            if(fileNode.getSize() < minSize)
                return false;
            else
                return true;
        }
        return true;
    }
}
