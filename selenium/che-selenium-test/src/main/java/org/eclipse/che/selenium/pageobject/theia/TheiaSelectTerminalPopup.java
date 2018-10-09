/*
 * Copyright (c) 2012-2018 Red Hat, Inc.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package org.eclipse.che.selenium.pageobject.theia;

import static java.lang.String.format;
import static org.eclipse.che.selenium.pageobject.theia.TheiaSelectTerminalPopup.Locators.PROPOSAL_CONTAINER_XPATH;
import static org.eclipse.che.selenium.pageobject.theia.TheiaSelectTerminalPopup.Locators.PROPOSAL_ITEM_XPATH_TEMPLATE;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.eclipse.che.selenium.core.webdriver.SeleniumWebDriverHelper;
import org.eclipse.che.selenium.pageobject.TestWebElementRenderChecker;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

@Singleton
public class TheiaSelectTerminalPopup {
  private final SeleniumWebDriverHelper seleniumWebDriverHelper;
  private final TestWebElementRenderChecker testWebElementRenderChecker;

  @Inject
  private TheiaSelectTerminalPopup(
      SeleniumWebDriverHelper seleniumWebDriverHelper,
      TestWebElementRenderChecker testWebElementRenderChecker) {
    this.seleniumWebDriverHelper = seleniumWebDriverHelper;
    this.testWebElementRenderChecker = testWebElementRenderChecker;
  }

  public interface Locators {
    String PROPOSAL_CONTAINER_XPATH = "//div[@class='quick-open-tree']";
    String PROPOSAL_ITEM_XPATH_TEMPLATE =
        "//div[@class='monaco-icon-label-description-container']//span[text()='%s']";
  }

  public void waitForm() {
    testWebElementRenderChecker.waitElementIsRendered(By.xpath(PROPOSAL_CONTAINER_XPATH));
  }

  public void waitProposalItem(String proposalText) {
    final String proposalXpath = format(PROPOSAL_ITEM_XPATH_TEMPLATE, proposalText);

    seleniumWebDriverHelper.waitVisibility(By.xpath(proposalXpath));
  }

  private void clickOnItem(String proposalText) {
    final String proposalXpath = format(PROPOSAL_ITEM_XPATH_TEMPLATE, proposalText);

    seleniumWebDriverHelper.waitAndClick(By.xpath(proposalXpath));
  }

  public void clickOnProposalItem(String proposalText) {
    seleniumWebDriverHelper.waitNoExceptions(
        () -> clickOnItem(proposalText), StaleElementReferenceException.class);
  }
}
