package SearchingStrategy;

import CompositeFileSystem.Concrete.Folder;
import CompositeFileSystem.FileSystemNode;

import java.util.List;
import java.util.Map;

public interface SearchStrategy {
    List<FileSystemNode> search(Folder node, Map<String,Object> params);
}
