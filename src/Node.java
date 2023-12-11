public class Node {
    private final Catalog parentNode;
    private  String name;
    public Node(Catalog parentNode, String name){
        this.parentNode=parentNode;
        this.name=name;
    }
    public String getName(){
        return name;
    }

    public Catalog getParentNode() {
        return parentNode;
    }

    public void setName(String destinationFileName) {
        this.name=destinationFileName;
    }
}
