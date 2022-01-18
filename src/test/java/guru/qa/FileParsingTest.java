package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Condition;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FileParsingTest {
    private ClassLoader cl = FileParsingTest.class.getClassLoader();
    @Test
    void parsePdfTest() throws Exception {
        open("https://junit.org/junit5/docs/5.2.0/user-guide/");
        File pdfDownload = $(byText("PDF download")).download();
        PDF parsedPdf = new PDF(pdfDownload);
        assertThat(parsedPdf.author).contains("Marc Philipp");
    }

   /* @Test
    void parseXlsTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("example.xlsx")) {
            XLS parsed = new XLS(stream);
            assertThat(parsed.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue()).isEqualTo("gender");
            System.out.println();
        }
    }*/

        @Test
        void parseCsvTest () throws Exception {
            try (InputStream stream = cl.getResourceAsStream("example.csv")) {
                CSVReader reader = new CSVReader(new InputStreamReader(stream));
                List<String[]> list = reader.readAll();
                assertThat(list)
                        .hasSize(3)
                        .contains(new String[] {"Author", "Book"});
                System.out.println();
            }
        }
    }

