package hangman;

public class LLCharacterNode {
char info;
LLCharacterNode link;
	public LLCharacterNode(char info){
		this.info = info;
		link = null;
	}
public char getInfo(){
	return info;
}
public void setInfo(char a){
	info = a;
}
public LLCharacterNode getLink(){
	return link;
}


public void setLink(LLCharacterNode nLink){
	link = nLink;
}
}
