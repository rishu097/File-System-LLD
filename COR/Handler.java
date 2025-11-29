package COR;

import CompositeFileSystem.FileSystemNode;

import java.util.List;
import java.util.Map;

public abstract class Handler {
    public abstract boolean handle(FileSystemNode node,Map<String,Object> params);
}
