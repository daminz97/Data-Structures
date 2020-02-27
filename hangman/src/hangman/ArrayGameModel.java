package hangman;

/**
 * The Array implementation of the GameModel interface.
 */
public class ArrayGameModel implements GameModel {
	/** The number of characters (lower/upper). */
	private static final int ALPHABET_COUNT = 26*2;
	
	/** hung state */
	int		state;
	
	/** the word I need guess*/
	char[] guess;
	
	/** compare with guessword*/
	char[] answer;
	
	/**the array stores all guesses*/
	char[] array = new char[ALPHABET_COUNT];
	int arrayAmount=0;
	
	/** the guess amount*/
	int guessAmount = 0;
	
	/** store the guess and convert to string*/
	String[] s;
	/** test is guess is correct*/     
	String guessWord;	
	/**
	 * Creates a new ArrayGameModel object.
	 * 
	 *  guessWord the word to guess
	 */
	public ArrayGameModel(String guessWord) {
		// TODO (1)
		state = 0;
		guess = guessWord.toCharArray();
		answer = new char[guess.length];
		this.s = new String[guessWord.length()];
		for(int i=0; i<this.s.length-1; i++){
			this.s[i] = "_ ";
		}
		this.s[this.s.length-1] = "_";
		this.guessWord = guessWord;
	}
		
	public boolean isPriorGuess(char guess) {
		// TODO (2)
		boolean result = false;
		for(char sample : array){
			if(sample == guess){
				result = true;
				break;
			}else{
				result = false;
			}
		}
		return result;
	}
	
	public int numberOfGuesses() {
		// TODO (3)
		int count = arrayAmount;
		return count;
	}
	
	public boolean isCorrectGuess(char guess) {
		// TODO (4)
		boolean result = false;
		for(int i=0; i<this.guess.length; i++){
			if(this.guess[i] == guess && isPriorGuess(guess) == false){
				result = true;
				break;
			}else{
				result = false;
			}
		}
		return result;
	}
	
	public boolean doMove(char guess) {
		// TODO (5)
		boolean result = false;
		
		
		if(isCorrectGuess(guess) == true && isPriorGuess(guess) == false){
			for(int i=0; i<this.guess.length-1; i++){
				if(this.guess[i] == guess){
					this.s[i] = ""+guess + " ";
				}
				result = true;
			}
			this.s[this.guess.length-1] = ""+guess;
		}
		else
			result = false;
		
		if(isCorrectGuess(guess) == true){
			for(int j=0; j<this.guess.length; j++){
				if(guess == this.guess[j]){
					answer[j] = guess;
				}
			}
		}
		
		
		if(isPriorGuess(guess) == false && isCorrectGuess(guess) == false){
			state++;
		}
		
		
		
		if(isPriorGuess(guess) == false){
			array[guessAmount] = guess;
			guessAmount++;
			arrayAmount++;
		}
		return result;
	}

	public boolean inWinningState() {
		// TODO (6)
		boolean isWinning = false;
		for(int i=0; i<this.guessWord.length(); i++){
			if(answer[i] == this.guess[i])
				isWinning = true;
			else{
				isWinning = false;
				break;
			}
		}
		return isWinning;
	}

	public boolean inLosingState() {
		// TODO(7)
		boolean result = false;
		if(state == 10)
			result = true;
		return result;
	}
	
	public String toString() {
		String s = "";
		for(String sample : this.s){
			s = s + sample;
		}
		// TODO (8)
		
		return s;
	}

	public String previousGuessString(){
		String s = "";
		for(int i=0; i<array.length; i++){
			if(i == 0){
				s = s+"["+array[i];
			}
			
			if(array[i] != 0 && i != 0){
				s = s+", "+array[i];
			}
			if(i == arrayAmount-1){
				s = s+"]";
			}
			
		}
		// TODO (9)
		return s;
	}
	
	public int getState() {
		return state;
	}

	public String getWord() {
		String s = "";
		for(char sample : guess){
			s = s + ""+sample;
		}
		// TODO (10)

		return s;
	}
  
}
