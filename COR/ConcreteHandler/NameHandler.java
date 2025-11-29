package COR.ConcreteHandler;

import COR.Handler;
import CompositeFileSystem.Concrete.File;
import CompositeFileSystem.FileSystemNode;

import java.util.List;
import java.util.Map;

public class NameHandler extends Handler {
    @Override
    public boolean handle(FileSystemNode node,Map<String, Object> params) {
        if(params.containsKey("nameRegex")){
            String name = params.get("nameRegex").toString();
            if(node.getName().contains(name)){
                return true;
            }
            else
                return false;
        }
        return true;
    }
}
