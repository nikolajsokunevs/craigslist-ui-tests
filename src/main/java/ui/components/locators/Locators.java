package ui.components.locators;

import org.openqa.selenium.By;

import java.util.function.Function;

import static java.lang.String.format;

public interface Locators {

    enum MainPage implements Locators {

        LNK_HOUSING(By::cssSelector, "#hhh a"),
        LBL_TOP_HEADER(By::cssSelector, "#topban>div>h2");

        private String id;
        private Function<String, By> function;

        MainPage(Function<String, By> function, String id) {
            this.function = function;
            this.id = id;
        }

        public By get() {
            return function.apply(id);
        }
    }

    enum HousingPage implements  Locators {

        LBL_HOUSING_PAGE_MARKER(By::cssSelector, "select#catAbb option[value='hhh'][selected]"),
        LBL_ITEM_TIME(By::xpath, "//div[@id='sortable-results']/ul//h4[@class='ban nearby']/preceding-sibling::li/p/time"),
        LBL_ITEM_PRICE(By::xpath, "//div[@id='sortable-results']/ul//h4[@class='ban nearby']/preceding-sibling::li/p//span[@class='result-price']"),
        CBX_SEARCH_SORT(By::cssSelector, "#searchform div.search-sort"),
        TXT_SEARCH(By::cssSelector, "#query"),
        BTN_SEARCH(By::cssSelector, "button.searchbtn"),
        ;

        private String id;
        private Function<String, By> function;

        HousingPage(Function<String, By> function, String id) {
            this.function = function;
            this.id = id;
        }

        public By get() {
            return function.apply(id);
        }

        public By get(String value) {
            return function.apply(format(id, value));
        }
    }
}
