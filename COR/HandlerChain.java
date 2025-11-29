package COR;

import CompositeFileSystem.FileSystemNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HandlerChain {
    private List<Handler> handlers;

    public HandlerChain() {
        handlers = new ArrayList<>();
    }
    public void addHandler(Handler handler) {
        handlers.add(handler);
    }

    public boolean handle(FileSystemNode node,Map<String,Object> params) {
        for(Handler h : handlers) {
            if(!h.handle(node,params)) return false;
        }
        return true;
    }
}
