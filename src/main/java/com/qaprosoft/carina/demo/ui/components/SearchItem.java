package com.qaprosoft.carina.demo.ui.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SearchItem extends AbstractUIObject {

    @FindBy(xpath = "//header//label[@class='md-search__icon md-icon']")
    private static ExtendedWebElement searchLogo;

    @FindBy(xpath = "//header//input")
    private static ExtendedWebElement searchInput;

    public SearchItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean isSearchLogoPresent() {
        return searchLogo.isElementPresent();
    }

    public boolean isSearchInputPresent() {
        return searchInput.isElementPresent();
    }

    public String getSearchInputPlaceholder() {
        String searchPlaceholder = "";
        searchPlaceholder = searchInput.getAttribute("ariaLabel");
        return searchPlaceholder;
    }
}
