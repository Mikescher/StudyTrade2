package logging;

public class STLog {

	public static void log(Exception e) {
		e.printStackTrace();
	}

	public static void Log(String s) {
		System.err.println(s);
	}
}
