package hangman;

public class LinkedListGameModel implements GameModel {

	/** hung state */
	int state;
	
	/** the word I need guess*/
	LLCharacterNode guessHead;
	
	/** compare with guessWord*/
	LLCharacterNode answerHead;
	
	/**the list stores all guesses*/
	LLCharacterNode allGuessesHead;
	LLCharacterNode allGuessesPointer;

	
	/** store the guess and convert to string*/
	String s = "";
	/** test is guess is correct*/     
	String guessWord;
	int guessCount;
	
	
	
	
	public LinkedListGameModel(String guessWord){
		this.guessWord = guessWord;
		state = 0;
		LLCharacterNode guessPointer;
		guessHead = new LLCharacterNode(guessWord.charAt(0));
		guessPointer = guessHead;
		for(int i=1; i<this.guessWord.length(); i++){
			LLCharacterNode link = new LLCharacterNode(guessWord.charAt(i));
			guessPointer.setLink(link);
			guessPointer = guessPointer.getLink();
		}
		
		answerHead = new LLCharacterNode('_');
		LLCharacterNode answerPointer = answerHead;
		for(int j=1; j<this.guessWord.length(); j++){
			LLCharacterNode newLink = new LLCharacterNode('_');
			answerPointer.setLink(newLink);
			answerPointer = answerPointer.getLink();
		}
		
	}
	
	@Override
	public boolean isPriorGuess(char guess) {
		// TODO Auto-generated method stub
		boolean result = false;
		if(allGuessesHead == null){
			return false;
		}
		LLCharacterNode link = allGuessesHead;
		while(link != null){
			if(link.getInfo() == guess){
				result = true;
			}
			link = link.getLink();
		}
		return result;
	}

	@Override
	public int numberOfGuesses() {
		// TODO Auto-generated method stub
		int count = 0;
		if(allGuessesHead == null)
			return count;
		LLCharacterNode link = allGuessesHead;
		while(link != null){
			count++;
			link = link.getLink();
		}
		return count;
	}

	@Override
	public boolean isCorrectGuess(char guess) {
		// TODO Auto-generated method stub
		boolean result = false;
		LLCharacterNode link = guessHead;
		while(link != null){
			if(link.getInfo() == guess && isPriorGuess(guess) == false){
				result = true;
			}
			link = link.getLink();
		}
		return result;
	}

	@Override
	public boolean doMove(char guess) {
		// TODO Auto-generated method stub
		boolean result = true;
		LLCharacterNode link = guessHead;
		LLCharacterNode answerLink = answerHead;
		if(isPriorGuess(guess) == true)
			result = false;
		
		if(isPriorGuess(guess) == false && isCorrectGuess(guess) == false){
			state++;
			result = false;
		}
		
		if(isCorrectGuess(guess) == true && isPriorGuess(guess) == false){
			for(int i=0; i<this.guessWord.length(); i++){
				if(link.getInfo() == guess){
					answerLink.setInfo(guess);
				}
				link=link.getLink();
				answerLink = answerLink.getLink();
			}
		}
		
		
		allGuessesPointer = allGuessesHead;
		LLCharacterNode newlink2 = new LLCharacterNode(guess);
		if(allGuessesPointer == null){
			allGuessesPointer =  newlink2;
			allGuessesHead = allGuessesPointer;
		}else{
			while(allGuessesPointer.getLink() != null){
				allGuessesPointer = allGuessesPointer.getLink();
			}
			allGuessesPointer.setLink(newlink2);
		}
		return result;
		
	}

	@Override
	public boolean inWinningState() {
		// TODO Auto-generated method stub
		boolean result = true;
		LLCharacterNode link = answerHead;
		while(link != null){
			if(link.getInfo() == '_'){
				result = false;
			}
			link = link.getLink();
		}
		return result;
	}

	@Override
	public boolean inLosingState() {
		// TODO Auto-generated method stub
		boolean result = false;
		if(state == 10){
			result = true;
		}
		return result;
	}

	@Override
	public int getState() {
		// TODO Auto-generated method stub
		return state;
	}
	
	
	public String toString(){
		String s = "";
		LLCharacterNode link = answerHead;
		if(answerHead == null)
			return s;
		while(link.getLink() != null){
			s = s + link.getInfo() + " ";
			link = link.getLink();
		}
		s = s + link.getInfo();
		return s;
	}

	@Override
	public String previousGuessString() {
		// TODO Auto-generated method stub
		String s = "";
		LLCharacterNode link = allGuessesHead;
		if(allGuessesHead == null)
			return s;
		s = s + "[";
		while(link.getLink() != null){
			s = s + link.getInfo() + ", ";
			link = link.getLink();
		}
		s+= link.getInfo()+"]";
		return s;
	}

	@Override
	public String getWord() {
		// TODO Auto-generated method stub
		return this.guessWord;
	}


}