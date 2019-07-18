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
import { NameGenerator } from '../../utils/NameGenerator';
import { NewWorkspace } from '../../pageobjects/dashboard/NewWorkspace';
import { Ide } from '../../pageobjects/ide/Ide';
import { TopMenu } from '../../pageobjects/ide/TopMenu';
const workspaceName: string = NameGenerator.generate('wksp-test-', 5);
const namespace: string = 'che';
const sampleName: string = 'console-java-simple';
const topMenu: TopMenu = e2eContainer.get(CLASSES.TopMenu);
const loginPage: ILoginPage = e2eContainer.get<ILoginPage>(TYPES.LoginPage);
const newWorkspace: NewWorkspace = e2eContainer.get(CLASSES.NewWorkspace);
const ide: Ide = e2eContainer.get(CLASSES.Ide);


suite('E2E', async () => {

    suite('Login and wait dashboard', async () => {
        test('Login', async () => {
            await loginPage.login();
        });
    });

    suite('Create workspace and add plugin', async () => {
        test('Open \'New Workspace\' page', async () => {
            await newWorkspace.openPageByUI();
        });

        test('Create and open workspace', async () => {
            await newWorkspace.createAndRunWorkspace(namespace, workspaceName, 'Java Maven', sampleName);
        });
    });

    suite('Work with IDE', async () => {
        test('Wait IDE availability', async () => {
            await ide.waitWorkspaceAndIde(namespace, workspaceName);
        });

        test('Check Dialog About', async () => {
            suite('Validation of workspace start, build and run', async () => {

                test('Run debug and check application stop in the breakpoint', async () => {

                    await topMenu.selectOption('Help', 'About');

                });
            });


        });
    });
});
