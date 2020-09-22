package steps;


import config.ApplicationProperties;
import config.webdriver.DriverBase;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import ui.components.models.HousingModel;
import ui.components.models.MainModel;

import java.util.List;

import static config.ApplicationProperties.ApplicationProperty.APP_URL;
import static support.web.WebElementHelper.navigateToPage;

public class StepDefinition{

    protected WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverBase.getDriver();
    }

    @After
    public void doSomethingBefore() {
        DriverBase.clearCookies();
        DriverBase.closeCurrentDriver();
    }

    @Given("Application is deployed and opened")
    public void application_is_deployed_and_opened() {
        navigateToPage(ApplicationProperties.getString(APP_URL)+"/?lang=en");
    }

    @When("User navigates to the Housing screen")
    public void user_navigates_to_the_housing_screen() {
        new MainModel().navigateToHousing();
    }

    @When("User does a search {string}")
    public void user_does_a_search(String searchString) {
        new HousingModel().doASearch(searchString);
    }

    @Then("Verify that sorting by price works properly")
    public void verify_that_sorting_by_price_works_properly() {
        new HousingModel().verifyAllItemsAreSorterByNewestDate()
                .sortByPriceAscending()
                .verifyAllItemsAreSorterByPriceAsc()
                .sortByPriceDescending()
                .verifyAllItemsAreSorterByPriceDesc();
    }

    @Then("Verify that all sorting options are displayed")
    public void verify_that_all_sorting_options_are_displayed(DataTable dataTable) {
        new HousingModel().verifyDefaultSortingOptions(dataTable.asList());
    }
}
