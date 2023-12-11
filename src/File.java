public class File extends Node{
    private final String content;
    public File(Catalog parentNode, String name, String content) {
        super(parentNode, name);
        parentNode.addChild(this);
        this.content=content;
    }

    public String getContent() {
        return content;
    }


}
