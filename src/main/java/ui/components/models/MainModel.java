package ui.components.models;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static support.web.WebElementHelper.*;
import static support.web.WebElementHelper.jsClick;
import static ui.components.locators.Locators.MainPage.*;

public class MainModel {

    private static final Logger logger = LoggerFactory.getLogger(MainModel.class);
    protected String languagePrefix = "";

    public MainModel(String languagePrefix) {
        this.languagePrefix = languagePrefix.toUpperCase();
    }

    @Step("Navigate to the housing tab")
    public HousingModel navigateToHousing() {
        click(LNK_HOUSING.get());
        return new HousingModel(languagePrefix);
    }

    @Step("Verify the main header")
    public MainModel verifyMainHeader() {
        Assertions.assertEquals("craigslist", getText(LBL_TOP_HEADER.get()));
        return this;
    }
}
