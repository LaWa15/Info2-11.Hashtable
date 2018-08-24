import java.io.IOException;

//@author Pascal Polchow and Larissa Wagnerberger
public class ScrabbleCheater {

	public static void main(String[] args) throws IOException {

		String word = "academy"; // type in your word here

		Dictionary.fillDict();
		System.out.println("Looked up word: " + word);
		System.out.println("Possible permuations:");
		Dictionary.lookup(word);

	}

}
