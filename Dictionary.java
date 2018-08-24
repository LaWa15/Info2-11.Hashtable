import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//@author Pascal Polchow and Larissa Wagnerberger
public class Dictionary {
	private static int m = 25013;
	private static LinkedList<Long>[] dict = new LinkedList[m];
	public static String[] words = new String[400000];

	public static int listOfWords() throws IOException {
		BufferedReader fr = new BufferedReader(new FileReader(
				"C:\\Users\\Jette\\Documents\\eclipse-workspace\\ScrabbleCheaterBasicFin\\ScrabbleCheaterBasic\\words.txt"));
		String read = "";
		int index = 0;
		while ((read = fr.readLine()) != null) {
			if (read.length() < 8 && read.length() > 1) {
				words[index] = read;
				index++;
			}
		}
		fr.close();
		return index;
	}

	public static void fillDict() throws IOException {
		for (int j = 0; j < m; j++) {
			dict[j] = new LinkedList();
		}
		int length = listOfWords();
		int collision = 0;
		for (int i = 0; i < length; i++) {
			String word = words[i];
			long value = letterToNumber(word);
			int k = hashValue(word);
			boolean spaceFound = false;
			int index = 0;
			if (i < m) {
				while (!spaceFound) {
					int h = (k + index) % m;
					index++;
					if (dict[h].isEmpty()) {
						dict[h].add(value);
						spaceFound = true;
					}
				}
			} else {
				dict[k].add(value);
				collision++;
			}
		}
		System.out.println("Number of collisions: " + collision);
	}

	public static String[] lookup(String word) {
		String lookup = word.toUpperCase();
		long value = letterToNumber(lookup);
		String[] result = new String[1000];
		int hashValue = hashValue(lookup);
		int size = dict[hashValue].size();
		for (int i = 0; i < size; i++) {
			result[i] = numberToLetter(dict[hashValue].get(i));
		}
		int index = size;
		for (int i = 0; i < dict.length; i++) {
			if (i == hashValue) {
			} else {
				if (hashValue(numberToLetter(dict[i].peek())) == hashValue) {
					result[index] = numberToLetter(dict[i].peek());
					index++;
				}
			}
		}

		for (int i = 0; i < index; i++) {
			if (result[i].length() == 7)
				System.out.println(result[i]);
		}
		return result;
	}

	public static void printWords() throws IOException {
		int index = listOfWords();
		for (int i = 0; i < index; i++)
			System.out.println(words[i]);
	}

	public static void printDict() {
		for (int i = 0; i < dict.length; i++) {
			Iterator it = dict[i].listIterator();
			while (it.hasNext()) {
				long value = (long) it.next();
				System.out.print(value + " (" + numberToLetter(value) + ") - ");
			}
			System.out.println("");
		}
	}

	public static String numberToLetter(long numb) {
		String letter = "";
		String value = String.valueOf(numb);
		for (int i = 0; i <= value.length() - 2; i = i + 2) {
			int num = Integer.valueOf(value.substring(i, i + 2));
			letter += String.valueOf((char) num);
		}
		return letter;
	}

	public static long letterToNumber(String word) {
		String numbValue = "";
		char[] charArray = word.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			numbValue += ((int) charArray[i]);
		}
		return Long.valueOf(numbValue);
	}

	public static int maxValue() {
		int max = 0;
		for (int i = 0; i < dict.length; i++) {
			if (dict[i].size() > max)
				max = dict[i].size();
		}
		return max;
	}

	private static int hashValue(String word) {
		char[] letters = word.toCharArray();
		Arrays.sort(letters);
		String result = String.valueOf(letters);
		int hashValue = (int) (letterToNumber(result) % (long) m);
		return hashValue;
	}

}
