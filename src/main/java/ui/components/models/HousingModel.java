package ui.components.models;

import com.google.common.collect.Ordering;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DataProvider;
import utils.Utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static support.web.WebElementHelper.*;
import static ui.components.locators.Locators.HousingPage.*;

public class HousingModel extends MainModel {

    private static final Logger logger = LoggerFactory.getLogger(HousingModel.class);

    public HousingModel(String languagePrefix) {
        super(languagePrefix);
        waitForPage();
    }

    private HousingModel waitForPage() {
        waitForVisibility(LBL_HOUSING_PAGE_MARKER.get());
        return this;
    }

    @Step("Verify that all items are sorted by the newest date")
    public HousingModel verifyAllItemsAreSorterByNewestDate() {
        List<WebElement> allItems = waitForElements(LBL_ITEM_TIME.get());
        List<LocalDateTime> datesAsAList = allItems.stream().map(e -> Utils.stringToDateTime(e.getAttribute("datetime"), "yyyy-MM-dd HH:mm")).collect(Collectors.toList());
        boolean isSorted = Ordering.natural().reverse().isOrdered(datesAsAList);
        Assertions.assertTrue(isSorted, "List with items isn't sorted by newest date");
        return this;
    }

    @Step("Verify that default sorting options are: 'Upcoming', 'Newest', 'Price Up', 'Price down'")
    public HousingModel verifyDefaultSortingOptions(DataProvider data) {
        List<String> allOptions = getAllSortingOptions(CBX_SEARCH_SORT.get());
        SoftAssertions assertions=new SoftAssertions();
        assertions.assertThat(allOptions.size()).isEqualTo(4);
        assertions.assertThat(allOptions.get(0)).isEqualTo(data.getData(languagePrefix, "filter.upcoming"));
        assertions.assertThat(allOptions.get(1)).isEqualTo(data.getData(languagePrefix, "filter.newest"));
        assertions.assertThat(allOptions.get(2)).isEqualTo(data.getData(languagePrefix, "filter.priceUp"));
        assertions.assertThat(allOptions.get(3)).isEqualTo(data.getData(languagePrefix, "filter.priceDown"));
        assertions.assertAll();
        return this;
    }

    @Step("Verify that extended sorting options are: 'Upcoming', 'Newest', 'Relevant', 'Price Up', 'Price down'")
    public HousingModel verifyExtendedSortingOptions(DataProvider data) {
        List<String> allOptions = getAllSortingOptions(CBX_SEARCH_SORT.get());
        SoftAssertions assertions=new SoftAssertions();
        assertions.assertThat(allOptions.size()).isEqualTo(5);
        assertions.assertThat(allOptions.get(0)).isEqualTo(data.getData(languagePrefix, "filter.upcoming"));
        assertions.assertThat(allOptions.get(1)).isEqualTo(data.getData(languagePrefix, "filter.newest"));
        assertions.assertThat(allOptions.get(2)).isEqualTo(data.getData(languagePrefix, "filter.relevant"));
        assertions.assertThat(allOptions.get(3)).isEqualTo(data.getData(languagePrefix, "filter.priceUp"));
        assertions.assertThat(allOptions.get(4)).isEqualTo(data.getData(languagePrefix, "filter.priceDown"));
        assertions.assertAll();
        return this;
    }

    @Step("Sort by price(ascending)")
    public HousingModel sortByPriceAscending() {
        selectValueFromCustomComboBox(CBX_SEARCH_SORT.get(), "priceasc");
        return this;
    }

    @Step("Do a search with search value: {textForSearch}")
    public HousingModel doASearch(String textForSearch) {
        sendKeys(TXT_SEARCH.get(), textForSearch);
        click(BTN_SEARCH.get());
        return this;
    }

    @Step("Sort by price(descending)")
    public HousingModel sortByPriceDescending() {
        selectValueFromCustomComboBox(CBX_SEARCH_SORT.get(), "pricedsc");
        return this;
    }

    @Step("Verify that all items are sorted by the price(ascending)")
    public HousingModel verifyAllItemsAreSorterByPriceAsc() {
        List<WebElement> allItems = waitForElements(LBL_ITEM_PRICE.get());
        List<Integer> priceAsList = allItems.stream().map(e -> Utils.extractOnlyNumbers(e.getText())).collect(Collectors.toList());
        boolean isSorted = Ordering.natural().isOrdered(priceAsList);
        Assertions.assertTrue(isSorted, "List with items isn't sorted by price ascending");
        return this;
    }

    @Step("Verify that all items are sorted by the price(descending)")
    public HousingModel verifyAllItemsAreSorterByPriceDesc() {
        List<WebElement> allItems = waitForElements(LBL_ITEM_PRICE.get());
        List<Integer> priceAsList = allItems.stream().map(e -> Utils.extractOnlyNumbers(e.getText())).collect(Collectors.toList());
        boolean isSorted = Ordering.natural().reverse().isOrdered(priceAsList);
        Assertions.assertTrue(isSorted, "List with items isn't sorted by price descending");
        return this;
    }
}
