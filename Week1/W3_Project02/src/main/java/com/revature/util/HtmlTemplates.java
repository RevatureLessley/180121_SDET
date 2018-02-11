package com.revature.util;

import java.io.PrintWriter;

public class HtmlTemplates {
	public static void goBackButton(PrintWriter out) {
		out.println(
				"<input type='button' value='GO BACK' onclick='goBack()'>"
				+ "<script>function goBack(){window.history.back(); }</script>"
				);
	}
}
