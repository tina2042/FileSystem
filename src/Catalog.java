import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Catalog extends Node{
    private final List<Node> childrenNodes;

    public Catalog(Catalog parentNode, String name) {
        super(parentNode, name);
        if(parentNode!=null){
        parentNode.addChild(this);}
        this.childrenNodes = new ArrayList<>();
    }

    public Node[] getChildrenNodes() {
        return childrenNodes.toArray(Node[]::new);
    }

    public void addChild(Node child) {
        childrenNodes.add(child);
    }

    public void removeChild(Node child) {
        childrenNodes.remove(child);
    }

    public boolean containsChild(String name) {
        for (Node child : childrenNodes) {
            if (child instanceof Catalog && child.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Node getChild(String name) {
        if (childrenNodes != null) {
            for (Node child : childrenNodes) {
                if (child.getName().equals(name)) {
                    return child;
                }
            }
        }
        return null;
    }
}
