package ui.components.models;

import com.google.common.collect.Ordering;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static support.web.WebElementHelper.*;
import static ui.components.locators.Locators.HousingPage.*;

public class HousingModel extends MainModel {

    private static final Logger logger = LoggerFactory.getLogger(HousingModel.class);

    public HousingModel() {
        waitForPage();
    }

    private HousingModel waitForPage() {
        waitForVisibility(LBL_HOUSING_PAGE_MARKER.get());
        return this;
    }

    public HousingModel verifyAllItemsAreSorterByNewestDate() {
        List<WebElement> allItems = waitForElements(LBL_ITEM_TIME.get());
        List<LocalDateTime> datesAsAList = allItems.stream().map(e -> Utils.stringToDateTime(e.getAttribute("datetime"), "yyyy-MM-dd HH:mm")).collect(Collectors.toList());
        boolean isSorted = Ordering.natural().reverse().isOrdered(datesAsAList);
        Assertions.assertThat(isSorted).isEqualTo(true);
        return this;
    }

    public HousingModel verifyDefaultSortingOptions(List<String> options) {
        List<String> allOptions = getAllSortingOptions(CBX_SEARCH_SORT.get());
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(allOptions.size()).isEqualTo(options.size());
        for (int i = 0; i < options.size(); i++) {
            assertions.assertThat(allOptions.get(i)).isEqualTo(options.get(i));
        }
        assertions.assertAll();
        return this;
    }

    public HousingModel sortByPriceAscending() {
        selectValueFromCustomComboBox(CBX_SEARCH_SORT.get(), "priceasc");
        return this;
    }

    public HousingModel doASearch(String textForSearch) {
        sendKeys(TXT_SEARCH.get(), textForSearch);
        click(BTN_SEARCH.get());
        return this;
    }

    public HousingModel sortByPriceDescending() {
        selectValueFromCustomComboBox(CBX_SEARCH_SORT.get(), "pricedsc");
        return this;
    }

    public HousingModel verifyAllItemsAreSorterByPriceAsc() {
        List<WebElement> allItems = waitForElements(LBL_ITEM_PRICE.get());
        List<Integer> priceAsList = allItems.stream().map(e -> Utils.extractOnlyNumbers(e.getText())).collect(Collectors.toList());
        boolean isSorted = Ordering.natural().isOrdered(priceAsList);
        Assertions.assertThat(isSorted).isEqualTo(true);
        return this;
    }

    public HousingModel verifyAllItemsAreSorterByPriceDesc() {
        List<WebElement> allItems = waitForElements(LBL_ITEM_PRICE.get());
        List<Integer> priceAsList = allItems.stream().map(e -> Utils.extractOnlyNumbers(e.getText())).collect(Collectors.toList());
        boolean isSorted = Ordering.natural().reverse().isOrdered(priceAsList);
        Assertions.assertThat(isSorted).isEqualTo(true);
        return this;
    }
}
