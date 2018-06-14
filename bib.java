
import java.io.PrintStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class bib {

    public static void main(String[] args) {

        try {
            if (args.length == 0) {
                System.out.println("Missing argument");
                System.exit(0);
            }

            String cite = args[0];
            String desc = args[1];

            Document localDocument = Jsoup.parse(args[2]);

            if (localDocument != null) {

                Elements localElements1 = localDocument.select("p[class='" + cite + "']:has(span)");
                Elements localElements2 = localDocument.select("p[class='" + desc + "']");

                if ((localElements1.size() != 0) && (localElements2.size() != 0)) {
                    if (localElements1.size() == localElements2.size()) {
                        System.out.print("<ul>");
                        for (int i = 0; i < localElements1.size(); i++) {
                            System.out.print("<li>");
                            System.out.print("<p>");
                            for (Element localElement : ((Element) localElements1.get(i)).children()) {
                                if ((!localElement.attr("style").contains("mso-bidi-font-weight:normal")) && (!localElement.attr("style").contains("style='mso-tab-count"))) {
                                    if (localElement.tagName() == "i") {
                                        System.out.print("<em>");
                                    }
                                    System.out.print(localElement.text());
                                    if (localElement.tagName() == "i") {
                                        System.out.print("</em>");
                                    }
                                }
                            }
                            System.out.print("</p>");

                            System.out.print(String.format("<blockquote><p>%s</p></blockquote>", new Object[]{((Element) localElements2.get(i)).text()}));
                            System.out.print("</li>");
                        }
                        System.out.println("</ul>");
                    } else {
                        System.out.println("Odd number of elements.");
                    }
                } else {
                    System.out.println("No elements found.");
                }
            } else {
                System.out.println("Unable to parse html.");
                System.exit(1);
            }
            System.exit(0);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
