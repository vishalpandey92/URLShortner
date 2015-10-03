import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;



public class URLShort {
	
	private HashMap<String, String> key_map; 
	private HashMap<String, String> val_map;
	private String my_domain; 
	private char my_char[]; 
	private Random random; 
	private int len; 


	// Default Constructor
	URLShort() {
		key_map = new HashMap<String, String>();
		val_map = new HashMap<String, String>();
		random = new Random();
		len = 8;
		my_char = new char[62];
		for (int index = 0; index < 62; index++) {
			int j = 0;
			if (index < 10) {
				j = index + 48;
			} else if (index > 9 && index <= 35) {
				j = index + 55;
			} else {
				j = index + 61;
			}
			my_char[index] = (char) j;
		}
		my_domain = "http://vishal.in";
	}


	
	URLShort(int length, String newmy_domain) {
		this();
		this.len = length;
		if (!newmy_domain.isEmpty()) {
			newmy_domain = execute(newmy_domain);
			my_domain = newmy_domain;
		}
	}


	
	boolean validate(String URL) {
		return true;
	}
	// generate
		private String generate() {
			String key = "";
			boolean flag = true;
			while (flag) {
				key = "";
				for (int i = 0; i <= len; i++) {
					key += my_char[random.nextInt(62)];
				}
				// System.out.println("Iteration: "+ counter + "Key: "+ key);
				if (!key_map.containsKey(key)) {
					flag = false;
				}
			}
			return key;
		}


		String execute(String URL) {
			if (URL.substring(0, 8).equals("https://"))
				URL = URL.substring(8);
			if (URL.substring(0, 7).equals("http://"))
				URL = URL.substring(7);
			if (URL.charAt(URL.length() - 1) == '/')
				URL = URL.substring(0, URL.length() - 1);
			return URL;
		}



	// shorten
	// the public method which can be called to shorten a given URL
	public String shorten(String Original) {
		String short_URL = "";
		if (validate(Original)) {
			Original = execute(Original);
			if (val_map.containsKey(Original)) {
				short_URL = my_domain + "/" + val_map.get(Original);
			} else {
				short_URL = my_domain + "/" + get_key(Original);
			}
		}
		// add http part
		return short_URL;
	}



	private String get_key(String Original) {
		String key;
		key = generate();
		key_map.put(key, Original);
		val_map.put(Original, key);
		return key;
	}


	// expansion
	// public method which returns back the original URL given the shortened URL
	public String expansion(String short_URL) {
		String Original = "";
		String key = short_URL.substring(my_domain.length() + 1);
		Original = key_map.get(key);
		return Original;
	}



	
	
	// test the code
	public static void main(String args[]) {
		URLShort u = new URLShort(5, "www.vishal.com/");
		System.out.println("Enter the number of URLs to enter");
		Scanner n=new Scanner(System.in);
		int no=n.nextInt();
		int i=0;
		while(i<no){
		System.out.println("Enter URL");
		
		Scanner url=new Scanner(System.in);
		String input_url=url.nextLine();
		
		System.out.println("Original URL:" + input_url + "\tShortened to: "
				+ u.shorten(input_url) + "\tExpansion: "
				+ u.expansion(u.shorten(input_url)));
		i++;
		}
		
	}
}
