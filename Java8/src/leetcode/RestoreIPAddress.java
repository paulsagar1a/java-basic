package leetcode;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddress {
	static List<String> list = new ArrayList<>();
	public static void main(String[] args) {
		String ip = "0000";
		dfs(new StringBuilder(), ip, 4);
		System.out.println(list);
	}

	private static void dfs(StringBuilder str, String s, int section) {
		int n = s.length();
		if(n == 0 && section == 0) {
			System.out.println("==="+str.toString());
			list.add(str.substring(0, str.toString().length()-1));
			return;
		}
		if(n>0 && section == 0) {
			System.out.println("+++"+str.toString());
			return;
		}
		if(n == 0 && section>0) {
			System.out.println("---"+str.toString());
			return;
		}
		
		StringBuilder sb =  new StringBuilder();
		int min = Math.min(s.length(), 3);
		for(int i=0; i<min; i++) {
			sb.append(s.charAt(i));
			int val = Integer.parseInt(sb.toString());
			//validate the values
			if(sb.length() == 2 && val < 10) continue;
			if(sb.length() == 3 && val < 100) continue;
			if(sb.length() == 3 && val > 255) continue;
			//keep a temporary str value for the back tracking
			StringBuilder tmp = new StringBuilder(str.toString());
			str.append(sb.toString());
			str.append(".");
			dfs(str, s.substring(i+1), section-1);
			str = tmp;
		}
	}

}
