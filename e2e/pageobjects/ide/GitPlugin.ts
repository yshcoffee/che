import 'reflect-metadata';
import { injectable, inject } from 'inversify';
import { CLASSES } from '../../inversify.types';
import { DriverHelper } from '../../utils/DriverHelper';
import { TestConstants } from '../../TestConstants';
import { By, WebElement } from 'selenium-webdriver';
import {Editor} from './Editor';
import {Ide} from './Ide'
import { ProjectTree } from './ProjectTree';

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
export class GitPlugin {
    constructor(
        @inject(CLASSES.DriverHelper) private readonly driverHelper: DriverHelper,
        @inject(CLASSES.Ide) private readonly ide: Ide,
        @inject(CLASSES.ProjectTree) private readonly projectTree: ProjectTree
        ) { }

}