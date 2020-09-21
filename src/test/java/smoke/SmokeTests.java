package smoke;

import config.annotations.Dataset;
import context.TestContext;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Feature("Smoke")
public class SmokeTests extends TestContext{

//    @ParameterizedTest(name = "Verify sorting works properly, language: {0}")
//    @ValueSource(strings = {"EN", "RU"})
//    @Story("Login")
//    @Dataset("Dataset")
//    void verifyLoginWorksProperly(String language) {
//        open(language)
//                .navigateToHousing()
//                .verifyAllItemsAreSorterByNewestDate()
//                .sortByPriceAscending()
//                .verifyAllItemsAreSorterByPriceAsc()
//                .sortByPriceDescending()
//                .verifyAllItemsAreSorterByPriceDesc();
//    }
//
//    @ParameterizedTest(name = "Verify default sorting options, language: {0}")
//    @ValueSource(strings = {"EN", "RU"})
//    @Story("Login")
//    @Dataset("Dataset")
//    void verifyDefaultSortingOptions(String language) {
//        open(language)
//                .navigateToHousing()
//                .verifyDefaultSortingOptions(data);
//    }
//
//    @ParameterizedTest(name = "Verify extended sorting options, language: {0}")
//    @ValueSource(strings = {"EN", "RU"})
//    @Story("Login")
//    @Dataset("Dataset")
//    void verifyExtendedSortingOptions(String language) {
//        open(language)
//                .navigateToHousing()
//                .verifyDefaultSortingOptions(data)
//                .doASearch("Luxury")
//                .verifyExtendedSortingOptions(data);
//    }

    @ParameterizedTest(name = "Verify that main header is 'Creigslist'")
    @ValueSource(strings = {"EN"})
    @Story("Login")
    @Dataset("Dataset")
    void verifyThatScreens(String language) {
        open(language)
                .verifyMainHeader();
    }
}
