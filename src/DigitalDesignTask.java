
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DigitalDesignTask {
    static String pattern = "\\d+\\[[a-zA-Z]+\\]";

    public static void main(String[] args) {
        System.out.println(parseString("3[xyz]4[xy]z"));
        System.out.println(parseString("2[3[x]y]"));
    }

    public static String parseString(String inputString) {
        String result = "";
        if (isCorrect(inputString)) {
            Pattern p = Pattern.compile(pattern);
            StringBuilder stringBuilder = new StringBuilder(inputString);
            Matcher matcher = p.matcher(stringBuilder);
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                String patternString = stringBuilder.substring(start, end);
                stringBuilder.replace(start, end, repeatedString(patternString));
                matcher = p.matcher(stringBuilder);
            }

            result = stringBuilder.toString();
        }
        else {
            return inputString + " - this string is not correct";
        }

        return result;
    }

    public static String repeatedString(String string) {
        String result = "";

        String[] strings = string.split("\\[");
        int count = Integer.parseInt(strings[0]);
        String stringForRepeat = strings[1].replace("]", "");
        StringBuilder resultString = new StringBuilder();
        while (count>0) {
            resultString.append(stringForRepeat);
            count--;
        }
        result = resultString.toString();
        return result;
    }

    public static boolean isCorrect(String inputString) {
        boolean result = false;

        Pattern p = Pattern.compile(pattern);
        StringBuilder stringBuilder = new StringBuilder(inputString);
        Matcher matcher = p.matcher(stringBuilder);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            stringBuilder.replace(start, end, "");
            matcher = p.matcher(stringBuilder);
        }
        String s = stringBuilder.toString().replaceAll("[a-zA-Z]+", "");
        if (s.length()==0) {
            result = true;
        }

        return result;
    }

}
