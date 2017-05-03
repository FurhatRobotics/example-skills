/*******************************************************************************
 * Copyright (c) 2014 Gabriel Skantze.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Gabriel Skantze - initial API and implementation
 ******************************************************************************/
package iristk.app.quiz;

import java.util.Arrays;
import java.util.List;

import iristk.util.RandomList;

public class Question {

	private  String id;
	private String question;
	private String category;
	private String[] answers = new String[4];
	private int correct;
	
	public Question(String id, String[] cols) {
		this.id = id;
		question = cols[0].trim();
		List<Integer> order = Arrays.asList(new Integer[]{0, 1, 2, 3});
		RandomList.shuffle(order);
		for (int i=0; i< order.size(); i++) {
			answers[order.get(i)] = cols[i+1].trim();
		}
		correct = order.get(0);
		if (cols.length > 5) {
			category = cols[5].trim();
		}
	}
	
	public String getFullQuestion() {
		return question + " " + answers[0] + ", " + answers[1] + ", " + answers[2] + ", or " + answers[3];
	}
	
	public String getOptions() {
		return answers[0] + ", " + answers[1] + ", " + answers[2] + ", or " + answers[3];
	}
	
	public List<String> getOptionList() {
		return Arrays.asList(answers[0], answers[1], answers[2], answers[3]);
	}
	
	public String getGrammar() {
		StringBuilder grammar = new StringBuilder();
		//"<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
		grammar.append("<grammar xml:lang=\"en-US\" version=\"1.0\" root=\"root\" xmlns=\"http://www.w3.org/2001/06/grammar\" tag-format=\"semantics/1.0\">");
		grammar.append("<rule id=\"root\" scope=\"public\"><one-of>");
		for (int i = 0; i < 4; i++) {
			grammar.append("<item>" + answers[i] + "<tag>out.answer=\"answer" + i + "\"</tag></item>");
		}
		grammar.append("</one-of></rule></grammar>");	
		return grammar.toString();
	}
	
	public String getCategory() {
		return category;
	}

	public String getCorrectAnswer() {
		//Used by Furhat to SAY the correct answer.
		return answers[correct];
	}

	public boolean isCorrect(Object answer) {
		//Sent answer# . Not comparing strings but the correct answer number.
		return answer != null && answer.toString().equals("answer" + correct);// intersting that it isn't comparison with correct but with "answer" + correct.
	}

	public String getId() {
		return id;
	}
	
	
}
