
import java.io.PrintStream;
import java.io.File;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class bib {

    public static void main(String[] args) {

        try {
            
            File input = new File("input.html");

            //String cite = "MsoBibliography";
            //String desc = "Bibliography2";
            
            String cite = "AnnotatedBiblio";
            String desc = "2AnnotatedBiblio";

            Document localDocument = Jsoup.parse(input, null);

            if (localDocument != null) {

                Elements elemCite = localDocument.select("p[class='" + cite + "']:has(span)");
                Elements elemDesc = localDocument.select("p[class='" + desc + "']");

                if ((elemCite.size() != 0) && (elemDesc.size() != 0)) {
                    if (elemCite.size() == elemDesc.size()) {
                        System.out.print("<ul>");
                        for (int i = 0; i < elemCite.size(); i++) {
                            System.out.print("<li>");
                            System.out.print("<p>");
                            for (Element localElement : ((Element) elemCite.get(i)).children()) {
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

                            System.out.print(String.format("<blockquote><p>%s</p></blockquote>", new Object[]{((Element) elemDesc.get(i)).text()}));
                            System.out.print("</li>");
                        }
                        System.out.println("</ul>");
                    } else {
                        System.out.println("Odd number of elements.");
                    }
                } else {
                    System.out.println(elemCite.size());
                    System.out.println(elemDesc.size());
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
