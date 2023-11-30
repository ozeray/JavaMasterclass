package co.ayo.jmc.regex;

import java.util.regex.Pattern;

public class Challenge {

    public static void main(String[] args) {
        String emails = """
                john.boy@valid.com
                john.boy@invalid
                jane.doe-smith@valid.co.uk
                jane_Doe1976@valid.co.uk
                bob-1964@valid.net
                bob!@invalid.com
                elaine@valid-test.com.au
                elaineinvalid1983@.com
                david@valid.io
                david@invalid..com
                """;

//        Pattern emailPattern = Pattern.compile("([\\w-.]+)@([a-zA-Z0-9-]+)[.]([a-zA-Z0-9-]+)([.]([a-zA-Z0-9-]+))?");
        Pattern emailPattern = Pattern.compile("([\\w-.]+)@(([\\w-]+\\.)+([\\w-]{2,}))");
        emailPattern.matcher(emails).results().forEach(mr ->
//                System.out.println(mr.group(0) + "\n\t" + mr.group(1) + "\n\t" + mr.group(2) + "." + mr.group(3) + (mr.group(4) != null ? mr.group(4) : "")));
                System.out.println(mr.group(0) + "\n\t" + mr.group(1) + "\n\t" + mr.group(2)));
    }
}
