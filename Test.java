import java.io.IOException;

//@author Pascal Polchow and Larissa Wagnerberger
public class Test {

	public static void main(String[] args) throws IOException {
		// System.out.println(Dictionary.letterToNumber("HALLO"));
		// Dictionary.listOfWords();
		// System.out.println("done");
		// Dictionary.printWords();
		// System.out.println(Dictionary.numberToLetter(656565));
		// System.out.println(Dictionary.letterToNumber("BCA"));

		Dictionary.fillDict();
		System.out.println(Dictionary.maxValue());
		System.out.println("Looked up word: RECITAL");
		Dictionary.lookup("MASSIVE");

		//
		// Dictionary.printDict();
	}

}
