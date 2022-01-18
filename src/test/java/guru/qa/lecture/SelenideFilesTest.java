package guru.qa.lecture;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SelenideFilesTest {
    private ClassLoader cl = SelenideFilesTest.class.getClassLoader();

/*    @Test
    void downloadTest() throws Exception {
        Configuration.proxyEnabled = true;
        Configuration.fileDownload = FileDownloadMode.PROXY;

        open("https://github.com/junit-team/junit5/blob/main/LICENSE.md");
        File downloadedFile = $("#raw-url").download();
        try (InputStream is = new FileInputStream(downloadedFile)) {
            assertThat(new String(is.readAllBytes(), StandardCharsets.UTF_8))
                    .contains("Eclipse Public License - v 2.0");
        }
    }*/

    @Test
    void uploadFile() {
        open("https://the-internet.herokuapp.com/upload");
        $("input[type='file']").uploadFromClasspath("cat.png");
        $("#file-submit").click();
        $("#uploaded-files").shouldHave(Condition.text("cat.png"));
    }
}
