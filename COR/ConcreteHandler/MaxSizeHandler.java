package COR.ConcreteHandler;

import COR.Handler;
import CompositeFileSystem.Concrete.File;
import CompositeFileSystem.FileSystemNode;

import java.util.Map;

public class MaxSizeHandler extends Handler {

    @Override
    public boolean handle(FileSystemNode node, Map<String, Object> params) {
        if(!(node instanceof File)){ return true;}
        if(params.containsKey("maxSize")){
            Integer maxSize = (Integer) params.get("maxSize");
            if(((File)node).getSize() > maxSize)
                return false;
            else
                return true;
        }
        return true;
    }
}
