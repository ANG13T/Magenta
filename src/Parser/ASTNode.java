package Parser;

import java.util.Arrays;

import Magenta.parser.generatednodes.Parser;

public class ASTNode extends SimpleNode implements Node {
	public ASTNode(int id) {
		super(id);
	}

	public ASTNode(Parser p, int id) {
		super(p, id);
	}


    public void setFilteredValueForToken(Token t) {
		String value = t.image;

        this.value = value.substring(
            value.indexOf(' ') + 1,
            value.length() - 1
        );
    }

    public String getNodeName() {
		return super.toString();
    }

    public String getNodeValue() {
		return value.toString();
    }
    
    @Override
    public String toString() {
        if (value != null) {
            return getNodeName() + ":" + getNodeValue().toString();
        }

        return getNodeName();
    }

    public ASTNode getChild(int index) {
      	ASTNode[] allChildren = getChildren();
		if(allChildren[index] != null) {
			return allChildren[index];
		}

		return null;
    }

	public ASTNode[] getChildren() {
        if (children == null) {
            return new ASTNode[] {};
        }

        return Arrays.copyOf(children, children.length, ASTNode[].class);
    }

    public void printNodeAndChildren(
        String linePrefix,
        String indent,
        String itemPrefix
    ) {
        System.out.println(linePrefix + itemPrefix + toString());

        if (children == null) {
            return;
        }

        for (int i = 0; i < children.length; i++) {
            ASTNode childNode = (ASTNode) children[i];

            if (childNode == null) {
                continue;
            }

            childNode.printNodeAndChildren(linePrefix + indent, indent, itemPrefix);
        }
    }

    @Override
    public void dump(String prefix) {
        printNodeAndChildren(prefix, "  ", "- ");
    }
    
}