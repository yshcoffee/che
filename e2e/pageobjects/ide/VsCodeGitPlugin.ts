import 'reflect-metadata';
import {injectable, inject} from 'inversify';
import {CLASSES} from '../../inversify.types';
import {DriverHelper} from '../../utils/DriverHelper';
import {By} from 'selenium-webdriver';
import {TestConstants} from '../../TestConstants';

/*********************************************************************
 * Copyright (c) 2019 Red Hat, Inc.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 **********************************************************************/
@injectable()
export class VsCodeGitPlugin {
    private static readonly OCTOCAT_ICON_LOCATOR: By = By.css('div.plugin-view-container-icon-github-pull-requests[style]');
    private static readonly VS_CODE_GIT_PLUGIN_DOC_PANEL_LOCATOR: By = By.css('div#views-container-widget-github-pull-requests');
    private static readonly VS_CODE_GIT_PLUGIN_PR_LABEL_LOCATOR_: By = By.xpath('//div[@class=\'theia-sidepanel-title noWrapInfo\' and text()=\'GitHub Pull Requests\']');


    constructor(
        @inject(CLASSES.DriverHelper) private readonly driverHelper: DriverHelper
    ) {
    }

    async clickOctoCatIcon() {
        await this.driverHelper.waitAndClick(VsCodeGitPlugin.OCTOCAT_ICON_LOCATOR, TestConstants.TS_SELENIUM_DEFAULT_TIMEOUT);
    }

    async waitVsCodegitIsOpened() {
        const elements: Array<By> = [VsCodeGitPlugin.VS_CODE_GIT_PLUGIN_DOC_PANEL_LOCATOR, VsCodeGitPlugin.VS_CODE_GIT_PLUGIN_PR_LABEL_LOCATOR_];
        await this.driverHelper.waitAllVisibility(elements);
    }

}


