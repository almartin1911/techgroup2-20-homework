import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

/*
 * SD2x Homework #2
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

public class HtmlValidator {
	
	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {

		Stack<HtmlTag> htmlTags = new Stack<HtmlTag>();

		Iterator<HtmlTag> tagsIterator = tags.iterator();
		while (tagsIterator.hasNext()) {
			HtmlTag currentTag=  tagsIterator.next();

			if (currentTag.isOpenTag()) {
				htmlTags.push(currentTag);
			} else {
				if (!currentTag.isSelfClosing()) {
					if (htmlTags.empty()) {
						return null;
					}
					if (!currentTag.matches(htmlTags.peek())) {
						break;
					} else {
						htmlTags.pop();
					}
				}
			}
		}
		
		return htmlTags; // this line is here only so this code will compile if you don't modify it
	}
	

}

