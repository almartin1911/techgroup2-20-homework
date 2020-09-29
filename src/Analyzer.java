import java.io.File;
import java.util.*;

/*
 * SD2x Homework #3
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */
public class Analyzer {
	public static List<Sentence> readFile(String filename) {
		List<Sentence> sentences = new ArrayList();
		if (filename != null) {
			File file = new File(filename);
			try {
				Scanner fileScanner = new Scanner(file);
				while (fileScanner.hasNextLine()) {
					String line = fileScanner.nextLine();
					if (line.matches("-?[0-2]\\s.*")) {
						String[] lineSplitted = line.split( "\\s", 2);
						sentences.add(new Sentence(Integer.parseInt(lineSplitted[0]), lineSplitted[1]));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return sentences;
	}

	public static Set<Word> allWords(List<Sentence> sentences) {
		Map<String, Word> words = new HashMap();
		if (sentences != null) {
			Iterator<Sentence> sentencesItor = sentences.iterator();
			try {
				while (sentencesItor.hasNext()) {
					Sentence sentence = sentencesItor.next();
					if (sentence != null) {
						String[] sentenceTextSplitted = sentence.text.split("\\s");

						for (String word: sentenceTextSplitted) {
							if (word.length() > 0) {
								word = word.toLowerCase();
								if (Character.isLetter(word.charAt(0))) {
									if (!words.containsKey(word)) {
										Word wordObj = new Word(word);
										wordObj.increaseTotal(sentence.score);
										words.put(word, wordObj);
									} else {
										Word wordObj = words.get(word);
										words.get(word).increaseTotal(sentence.score);
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return new HashSet<>(words.values()); // this line is here only so this code will compile if you don't modify it
	}
	
	public static Map<String, Double> calculateScores(Set<Word> words) {
		Map<String, Double> scores = new HashMap<>();
		if (words != null) {
			Iterator<Word> wordsItor = words.iterator();

			while (wordsItor.hasNext()) {
				Word word = wordsItor.next();
				if (word != null) {
					scores.put(word.text, word.calculateScore());
				}
			}
		}

		return scores;
	}
	
	/*
	 * Implement this method in Part 4
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
		Double score = 0.0;
		int count = 0;

		if (sentence != null && wordScores != null) {
			String[] tokens = sentence.split("\\s");
			for (String token: tokens) {
				if (token.length() > 0) {
					if (Character.isLetter(token.charAt(0))) {
						if (wordScores.containsKey(token.toLowerCase())) {
							score += wordScores.get(token.toLowerCase());
						}
						count++;
					}
				}
			}
		}
		if (count > 0)
			return score / count; // this line is here only so this code will compile if you don't modify it
		else
			return 0;
	}

	public static void main(String[] args) {
//		if (args.length == 0) {
//			System.out.println("Please specify the name of the input file");
//			System.exit(0);
//		}
//		String filename = args[0];
//		System.out.print("Please enter a sentence: ");
//		Scanner in = new Scanner(System.in);
//		String sentence = in.nextLine();
//		in.close();
		String filename = "reviews.txt";
		List<Sentence> sentences = Analyzer.readFile(filename);
		Set<Word> words = Analyzer.allWords(sentences);
		Map<String, Double> wordScores = Analyzer.calculateScores(words);
		String sentence = "This world is coming to an end";
		double score = Analyzer.calculateSentenceScore(wordScores, sentence);
		System.out.println("The sentiment score is " + score);
	}
}
