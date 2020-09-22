package ui.components.models;

import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static support.web.WebElementHelper.*;
import static ui.components.locators.Locators.MainPage.*;

public class MainModel {

    private static final Logger logger = LoggerFactory.getLogger(MainModel.class);
    protected String languagePrefix = "";


    public HousingModel navigateToHousing() {
        click(LNK_HOUSING.get());
        return new HousingModel();
    }

    public MainModel verifyMainHeader() {
        Assertions.assertThat("craigslist").isEqualTo(getText(LBL_TOP_HEADER.get()));
        return this;
    }
}
