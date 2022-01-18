package guru.qa.homework;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.assertj.core.api.Assertions.assertThat;

public class ZipParsingHomework {

    @Test
    void ZipTest() throws Exception {
        ZipFile zipFile = new ZipFile("src/test/resources/example.zip");

        // XLSX test
        ZipEntry XlsEntry = zipFile.getEntry("example.xlsx");
        try (InputStream stream = zipFile.getInputStream(XlsEntry)) {
            XLS parsed = new XLS(stream);
            assertThat(parsed.excel
                    .getSheetAt(0)
                    .getRow(0)
                    .getCell(0)
                    .getStringCellValue())
                    .isEqualTo("I love music");
        }

        // CSV test
        ZipEntry csvEntry = zipFile.getEntry("example.csv");
        try (InputStream stream = zipFile.getInputStream(csvEntry)) {
            CSVReader reader = new CSVReader(new InputStreamReader(stream));
            List<String[]> list = reader.readAll();
            assertThat(list)
                    .hasSize(3)
                    .contains(
                            new String[]{"Voka Gentle", "Branscombe"},
                            new String[]{"Wyldest", "Seastroke"},
                            new String[]{"Helena Deland", "Take It All"}
                    );
        }

        //PDF test
        ZipEntry pdfEntry = zipFile.getEntry("example.pdf");
        try (InputStream stream = zipFile.getInputStream(pdfEntry)) {
            PDF parsed = new PDF(stream);
            assertThat(parsed.text).contains("Provide example of this file type");
        }
    }
}
