/*********************************************************************
 * Copyright (c) 2019 Red Hat, Inc.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 **********************************************************************/

import { e2eContainer } from '../../inversify.config';
import { TYPES, CLASSES } from '../../inversify.types';
import { ILoginPage } from '../../pageobjects/login/ILoginPage';
import { DriverHelper } from '../../utils/DriverHelper';
import { logging } from 'selenium-webdriver';

// const workspaceName: string = NameGenerator.generate('wksp-test-', 5);
// const namespace: string = 'che';
// const sampleName: string = 'console-java-simple';
// const fileFolderPath: string = `${sampleName}/src/main/java/org/eclipse/che/examples`;
// const tabTitle: string = 'HelloWorld.java';
const driverHelper: DriverHelper = e2eContainer.get(CLASSES.DriverHelper);
const loginPage: ILoginPage = e2eContainer.get<ILoginPage>(TYPES.LoginPage);
// const dashboard: Dashboard = e2eContainer.get(CLASSES.Dashboard);
// const newWorkspace: NewWorkspace = e2eContainer.get(CLASSES.NewWorkspace);
// const ide: Ide = e2eContainer.get(CLASSES.Ide);
// const projectTree: ProjectTree = e2eContainer.get(CLASSES.ProjectTree);
// const editor: Editor = e2eContainer.get(CLASSES.Editor);

suite('E2E', async () => {

    suite('Login and wait dashboard', async () => {
        test('Login', async () => {
            await loginPage.login();
        });
    });

    // suite('Create workspace and add plugin', async () => {
    //     test('Open \'New Workspace\' page', async () => {
    //         await newWorkspace.openPageByUI();
    //     });

    //     test('Create and open workspace', async () => {
    //         await newWorkspace.createAndOpenWorkspace(workspaceName, 'Java Maven');

    //     });
    // });

    suite('Work with IDE', async () => {
        test('Wait IDE availability', async () => {
           // await ide.waitWorkspaceAndIde(namespace, workspaceName);
            const browserLogsPerform: logging.Entry[] = await driverHelper.getDriver().manage().logs().get(logging.Type.PERFORMANCE);
            const messages: any[] = [];
            browserLogsPerform.forEach(element => {
                // console.log('[%s] %s', element.level.name, element.message);
                messages.push(JSON.parse(element.message));
            });

            // console.log(messages);

            const filtered = messages.filter(item => {
                if (item && item.message) {
                    return item.message.method === 'Network.responseReceived';
                }
                return false;
            });

            console.log('---------------------------------FILTERED: ------------------------------------------');
            const result: any[] = [];
            filtered.forEach(i => result.push(i.message));
            console.log(result);
        });

    //     test('Open project tree container', async () => {
    //         await projectTree.openProjectTreeContainer();
    //     });

    //     test('Wait project imported', async () => {
    //         await projectTree.waitProjectImported(sampleName, 'src');
    //     });

    //     test('Expand project and open file in editor', async () => {
    //         await projectTree.expandPathAndOpenFile(fileFolderPath, tabTitle);
    //     });

    //     test('Check "Java Language Server" initialization by statusbar', async () => {
    //         await ide.waitStatusBarContains('Starting Java Language Server');
    //         await ide.waitStatusBarTextAbsence('Starting Java Language Server', 60000);
    //     });

    //     test('Check "Java Language Server" initialization by suggestion invoking', async () => {
    //         await ide.closeAllNotifications();
    //         await editor.waitEditorAvailable(tabTitle);
    //         await editor.clickOnTab(tabTitle);
    //         await editor.waitEditorAvailable(tabTitle);
    //         await editor.waitTabFocused(tabTitle);
    //         await editor.moveCursorToLineAndChar(tabTitle, 6, 20);
    //         await editor.pressControlSpaceCombination(tabTitle);
    //         await editor.waitSuggestion(tabTitle, 'append(CharSequence csq, int start, int end) : PrintStream');
    //     });

    // });

    // suite('Stop and remove workspace', async () => {
    //     test('Stop workspace', async () => {
    //         await dashboard.stopWorkspaceByUI(workspaceName);
    //     });

    //     test('Delete workspace', async () => {
    //         await dashboard.deleteWorkspaceByUI(workspaceName);
    //     });

    });

});
