import { e2eContainer } from '../../inversify.config';
import { CLASSES } from '../../inversify.types';
import { Ide } from '../../pageobjects/ide/Ide';
import {VsCodeGitPlugin} from '../../pageobjects/ide/VsCodeGitPlugin';
import {DriverHelper} from '../../utils/DriverHelper';

const ide: Ide = e2eContainer.get(CLASSES.Ide);
const vsCodeGitPlugin: VsCodeGitPlugin = e2eContainer.get(CLASSES.VsCodeGitPlugin);
const driverHelper: DriverHelper = e2eContainer.get(CLASSES.DriverHelper);

suite('E2E', async () => {
    suite('Open Github widget', async () => {
        test('Login', async () => {
            await driverHelper.navigateTo('localhost:3000');
            await ide.waitIde();
            await vsCodeGitPlugin.clickOctoCatIcon();
            await vsCodeGitPlugin.waitVsCodegitIsOpened();
            });
    });
});
