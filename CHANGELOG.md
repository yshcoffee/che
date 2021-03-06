# Change Log

## [7.0.0-rc-4.0](https://github.com/eclipse/che/tree/7.0.0-rc-4.0) (2019-07-25)
[Full Changelog](https://github.com/eclipse/che/compare/7.0.0-rc-3.0...7.0.0-rc-4.0)

**Merged pull requests:**

- RELEASE: Set next development version [\#14026](https://github.com/eclipse/che/pull/14026) ([vparfonov](https://github.com/vparfonov))
- \[TS\_SELENIUM\] Resolve problem with notifications interaction \(bug \#13864\) [\#14019](https://github.com/eclipse/che/pull/14019) ([Ohrimenko1988](https://github.com/Ohrimenko1988))
- Avoid using unsupported project's properties [\#13998](https://github.com/eclipse/che/pull/13998) ([ashumilova](https://github.com/ashumilova))
- \[TS SELENIUM\] Fix the \#13865 issue \(Happy Path test is sometimes failing on "Error highlighting" step\) [\#13987](https://github.com/eclipse/che/pull/13987) ([Ohrimenko1988](https://github.com/Ohrimenko1988))
- Add missing reference and referenceContent for action in DtoConverter [\#13957](https://github.com/eclipse/che/pull/13957) ([sleshchenko](https://github.com/sleshchenko))
- Remove the "npm" dependency vulnerability [\#13948](https://github.com/eclipse/che/pull/13948) ([Ohrimenko1988](https://github.com/Ohrimenko1988))
- Store github token in session storage, remove on logout [\#13947](https://github.com/eclipse/che/pull/13947) ([ashumilova](https://github.com/ashumilova))
- Fix project clonePath is not considered on workspace creation [\#13923](https://github.com/eclipse/che/pull/13923) ([ashumilova](https://github.com/ashumilova))
- Set wildcard '\*' host name if key name start from 'default-' according to the the https://github.com/eclipse/che/issues/13494\#issuecomment-512761661 [\#13901](https://github.com/eclipse/che/pull/13901) ([vparfonov](https://github.com/vparfonov))
-  Add custom image with all required packages for init containers [\#13900](https://github.com/eclipse/che/pull/13900) ([mshaposhnik](https://github.com/mshaposhnik))
- Helm chart deploy registries [\#13890](https://github.com/eclipse/che/pull/13890) ([sparkoo](https://github.com/sparkoo))
- Adapt Java selenium e2e tests to Openshift OAuth v4 [\#13887](https://github.com/eclipse/che/pull/13887) ([dmytro-ndp](https://github.com/dmytro-ndp))
- \[TS-SELENIUM\] Adapt WorkspaceCreationAndLsInitialization test according workspace creation from Dashboard changes [\#13885](https://github.com/eclipse/che/pull/13885) ([SkorikSergey](https://github.com/SkorikSergey))
- Selenium: Correct the e2e selenium tests related to changes on dashboard [\#13884](https://github.com/eclipse/che/pull/13884) ([artaleks9](https://github.com/artaleks9))
- Mount the SSH keys as secrets in all workspace containers: OpenShift Env [\#13883](https://github.com/eclipse/che/pull/13883) ([vparfonov](https://github.com/vparfonov))
- Set default version of Che Theia image [\#13879](https://github.com/eclipse/che/pull/13879) ([vparfonov](https://github.com/vparfonov))
- Make name for SSH Key Config Map unique [\#13877](https://github.com/eclipse/che/pull/13877) ([vparfonov](https://github.com/vparfonov))
- Deprecate /api/devfile REST API [\#13868](https://github.com/eclipse/che/pull/13868) ([skabashnyuk](https://github.com/skabashnyuk))
- Store the container-machine mapping predictably [\#13858](https://github.com/eclipse/che/pull/13858) ([metlos](https://github.com/metlos))
- \[TS-SELENIUM\] Fix HappyPath Java language server validation [\#13851](https://github.com/eclipse/che/pull/13851) ([SkorikSergey](https://github.com/SkorikSergey))
- Stabilize part for waiting Dashboard in WorkspaceCreationAndLsInitialization test  [\#13850](https://github.com/eclipse/che/pull/13850) ([musienko-maxim](https://github.com/musienko-maxim))
- \[TS-SELENIUM\] Fix containers-happy-path.yaml reference url [\#13831](https://github.com/eclipse/che/pull/13831) ([SkorikSergey](https://github.com/SkorikSergey))
- Adapt project selector widget to manage projects from devfile [\#13825](https://github.com/eclipse/che/pull/13825) ([ashumilova](https://github.com/ashumilova))
- Remove Diagnostics widget on UD [\#13815](https://github.com/eclipse/che/pull/13815) ([akurinnoy](https://github.com/akurinnoy))
- fix\(helm\): Keep helm labels sync with che-operator so chectl is able to deploy multiuser without errors [\#13812](https://github.com/eclipse/che/pull/13812) ([benoitf](https://github.com/benoitf))
- Mount the SSH keys as secrets in all workspace containers [\#13809](https://github.com/eclipse/che/pull/13809) ([vparfonov](https://github.com/vparfonov))
- Update the use of Machines in Che [\#13808](https://github.com/eclipse/che/pull/13808) ([skabashnyuk](https://github.com/skabashnyuk))
- \[TS-SELENIUM\] Update HappyPath test according to fixed bug [\#13802](https://github.com/eclipse/che/pull/13802) ([SkorikSergey](https://github.com/SkorikSergey))
- Add `securityContext.fsGroup`. [\#13798](https://github.com/eclipse/che/pull/13798) ([monaka](https://github.com/monaka))
- \[keycloak\] Rename environment variables. [\#13791](https://github.com/eclipse/che/pull/13791) ([monaka](https://github.com/monaka))
- Initial cleanup of che6 legacy code [\#13788](https://github.com/eclipse/che/pull/13788) ([skabashnyuk](https://github.com/skabashnyuk))
- Update CODEOWNERS for the endgame [\#13786](https://github.com/eclipse/che/pull/13786) ([l0rd](https://github.com/l0rd))
- fix \#13660 - Replace mentions of 'oAuth', by more accurate 'OAuth'. \(See https://oauth.net/\) [\#13664](https://github.com/eclipse/che/pull/13664) ([themr0c](https://github.com/themr0c))

## [7.0.0-rc-3.0](https://github.com/eclipse/che/tree/7.0.0-rc-3.0) (2019-07-05)
[Full Changelog](https://github.com/eclipse/che/compare/7.0.0-RC-1.1...7.0.0-rc-3.0)

**Merged pull requests:**

- chore\(github\): Update issue template to have several choices [\#13777](https://github.com/eclipse/che/pull/13777) ([benoitf](https://github.com/benoitf))
- RELEASE: Set next development version [\#13711](https://github.com/eclipse/che/pull/13711) ([vparfonov](https://github.com/vparfonov))
- Revert "Fix handling of error status of a workspace " [\#13699](https://github.com/eclipse/che/pull/13699) ([akurinnoy](https://github.com/akurinnoy))
- \[E2E\] Change export logic [\#13697](https://github.com/eclipse/che/pull/13697) ([Katka92](https://github.com/Katka92))
- Set default Che Theia vesrsion to 7.0.0-rc-3.0 [\#13696](https://github.com/eclipse/che/pull/13696) ([vparfonov](https://github.com/vparfonov))
- Updating codeowners for Che 7 endgame code reviews [\#13684](https://github.com/eclipse/che/pull/13684) ([l0rd](https://github.com/l0rd))
- \[Selenium\] Adapt selenium tests from ocpoauth package [\#13663](https://github.com/eclipse/che/pull/13663) ([SkorikSergey](https://github.com/SkorikSergey))
- Set version 7.0.0-rc-3.0-SNAPSHOT \(rc in lowercase\) [\#13655](https://github.com/eclipse/che/pull/13655) ([vparfonov](https://github.com/vparfonov))
- Remove Docker CLI and images parts of the CLI [\#13652](https://github.com/eclipse/che/pull/13652) ([benoitf](https://github.com/benoitf))
- Set new version 7.0.0-RC-3.0-SNAPSHOT [\#13651](https://github.com/eclipse/che/pull/13651) ([vparfonov](https://github.com/vparfonov))
- modify url to get index.json [\#13645](https://github.com/eclipse/che/pull/13645) ([tobespc](https://github.com/tobespc))
- Workspace termination time metrics [\#13635](https://github.com/eclipse/che/pull/13635) ([sparkoo](https://github.com/sparkoo))
- \[TS-SELENIUM\] Cover the "Use Java IDE features and the inner loop" step from "Happy path" scenario [\#13615](https://github.com/eclipse/che/pull/13615) ([Ohrimenko1988](https://github.com/Ohrimenko1988))
- Deploy legacy variable only for che6 workspaces [\#13612](https://github.com/eclipse/che/pull/13612) ([skabashnyuk](https://github.com/skabashnyuk))
- Fix the deployment failure with default `values.yaml`. [\#13598](https://github.com/eclipse/che/pull/13598) ([monaka](https://github.com/monaka))
- Support mountSources for K8S/OS components in devfile [\#13595](https://github.com/eclipse/che/pull/13595) ([mshaposhnik](https://github.com/mshaposhnik))
- Selenium: Update the E2E selenium tests according to the changes on dashboard [\#13589](https://github.com/eclipse/che/pull/13589) ([artaleks9](https://github.com/artaleks9))
- Deprecate workspaces with Devfile stored as workspace config [\#13588](https://github.com/eclipse/che/pull/13588) ([mkuznyetsov](https://github.com/mkuznyetsov))
- Fix editor version in Che Devfile [\#13572](https://github.com/eclipse/che/pull/13572) ([mkuznyetsov](https://github.com/mkuznyetsov))
- Upgrade dashboard dependencies [\#13571](https://github.com/eclipse/che/pull/13571) ([evidolob](https://github.com/evidolob))
- Add dockerfile to execute E2E Che 7 typescript tests. [\#13542](https://github.com/eclipse/che/pull/13542) ([Katka92](https://github.com/Katka92))
- Apply TS formatter and linter to workspace-loader [\#13532](https://github.com/eclipse/che/pull/13532) ([akurinnoy](https://github.com/akurinnoy))

## [7.0.0-RC-1.1](https://github.com/eclipse/che/tree/7.0.0-RC-1.1) (2019-06-19)
[Full Changelog](https://github.com/eclipse/che/compare/7.0.0-RC-2.0...7.0.0-RC-1.1)

**Merged pull requests:**

- Make Che Plugin Broker use self-signed certificate [\#13565](https://github.com/eclipse/che/pull/13565) ([sleshchenko](https://github.com/sleshchenko))
- RELEASE: Set next development version [\#13564](https://github.com/eclipse/che/pull/13564) ([vparfonov](https://github.com/vparfonov))

## [7.0.0-RC-2.0](https://github.com/eclipse/che/tree/7.0.0-RC-2.0) (2019-06-19)
[Full Changelog](https://github.com/eclipse/che/compare/7.0.0-RC-1.0...7.0.0-RC-2.0)

**Merged pull requests:**

- Fix the startup failure on the non-root runtime. [\#13573](https://github.com/eclipse/che/pull/13573) ([monaka](https://github.com/monaka))
- Allow registry passwords longer than 128 chars [\#13569](https://github.com/eclipse/che/pull/13569) ([johnmcollier](https://github.com/johnmcollier))
- Update Openshift v4 provider download URL [\#13567](https://github.com/eclipse/che/pull/13567) ([davidfestal](https://github.com/davidfestal))
- Operator-related fix and openshift v4 support [\#13554](https://github.com/eclipse/che/pull/13554) ([davidfestal](https://github.com/davidfestal))
- \#13547 fix typo gralde [\#13550](https://github.com/eclipse/che/pull/13550) ([nickboldt](https://github.com/nickboldt))
- Change plugin registry to v3 [\#13548](https://github.com/eclipse/che/pull/13548) ([nickboldt](https://github.com/nickboldt))
- Add creation of k8s secrets during deploying of broker [\#13546](https://github.com/eclipse/che/pull/13546) ([sleshchenko](https://github.com/sleshchenko))
- \[E2E\] Update typescript tests according to workspace creation changes [\#13544](https://github.com/eclipse/che/pull/13544) ([Ohrimenko1988](https://github.com/Ohrimenko1988))
- Fix handling of error status of a workspace  [\#13535](https://github.com/eclipse/che/pull/13535) ([akurinnoy](https://github.com/akurinnoy))
- Remove non-null constraints from some devfile fields; [\#13516](https://github.com/eclipse/che/pull/13516) ([mshaposhnik](https://github.com/mshaposhnik))
- Do not serialize DTO fields with empty or null objects and arrays [\#13515](https://github.com/eclipse/che/pull/13515) ([mkuznyetsov](https://github.com/mkuznyetsov))
- Use the metadata.name instead of name. [\#13513](https://github.com/eclipse/che/pull/13513) ([metlos](https://github.com/metlos))
- Use memory limit provided by meta.yaml in apache-camel stack [\#13505](https://github.com/eclipse/che/pull/13505) ([svor](https://github.com/svor))
- Polishing of monitoring dashboards [\#13501](https://github.com/eclipse/che/pull/13501) ([skabashnyuk](https://github.com/skabashnyuk))
- \[E2E typescript\] Fix plugins adding [\#13496](https://github.com/eclipse/che/pull/13496) ([Katka92](https://github.com/Katka92))
- K8s-like devfile naming [\#13490](https://github.com/eclipse/che/pull/13490) ([metlos](https://github.com/metlos))
- \[TS-SELENIUM\] Cover the "Start a Workspace from a devfile" step from "Happy path" scenario [\#13479](https://github.com/eclipse/che/pull/13479) ([Ohrimenko1988](https://github.com/Ohrimenko1988))
- Add devfile validation to /workspace/devfile endpoint [\#13472](https://github.com/eclipse/che/pull/13472) ([metlos](https://github.com/metlos))
- Create workspace from devfile [\#13469](https://github.com/eclipse/che/pull/13469) ([ashumilova](https://github.com/ashumilova))
- Selenium: remove selenium tests that check Che 6 compatible factories from CheSuite test suite [\#13468](https://github.com/eclipse/che/pull/13468) ([SkorikSergey](https://github.com/SkorikSergey))
- Initialize the empty binding for allowed environment type upgrades in k8s infrastructure [\#13466](https://github.com/eclipse/che/pull/13466) ([metlos](https://github.com/metlos))
- Rework dynamodule exclusion regexps to match nested WARs as well [\#13464](https://github.com/eclipse/che/pull/13464) ([mkuznyetsov](https://github.com/mkuznyetsov))
- RELEASE: Set next development version [\#13460](https://github.com/eclipse/che/pull/13460) ([vparfonov](https://github.com/vparfonov))
- Adding owners for `e2e` directory. [\#13457](https://github.com/eclipse/che/pull/13457) ([rhopp](https://github.com/rhopp))
- \[E2E tests\] Changes needed to reuse the tests [\#13456](https://github.com/eclipse/che/pull/13456) ([Katka92](https://github.com/Katka92))
- Attempt to create the che project multiple times if it was previously deleted. [\#13443](https://github.com/eclipse/che/pull/13443) ([metlos](https://github.com/metlos))
- Add possibility to specify reference or registry url for chePlugin/cheEditor type components [\#13297](https://github.com/eclipse/che/pull/13297) ([mshaposhnik](https://github.com/mshaposhnik))
- Decoupled monitoring configuration and deployment [\#13152](https://github.com/eclipse/che/pull/13152) ([skabashnyuk](https://github.com/skabashnyuk))

## [7.0.0-RC-1.0](https://github.com/eclipse/che/tree/7.0.0-RC-1.0) (2019-06-03)
[Full Changelog](https://github.com/eclipse/che/compare/6.19.5...7.0.0-RC-1.0)

**Merged pull requests:**

- Make workspace start errors more Che7 friendly [\#13462](https://github.com/eclipse/che/pull/13462) ([skabashnyuk](https://github.com/skabashnyuk))
- Enable to set URLs for custom registries. [\#13455](https://github.com/eclipse/che/pull/13455) ([monaka](https://github.com/monaka))
- Add -Duser.home parameter to maven opts in java-maven stack [\#13453](https://github.com/eclipse/che/pull/13453) ([amisevsk](https://github.com/amisevsk))
- Fix handling no-environment in workspace config, introduced in Che7 [\#13450](https://github.com/eclipse/che/pull/13450) ([ashumilova](https://github.com/ashumilova))
- CHE-12918 add checks for incompatible factories [\#13446](https://github.com/eclipse/che/pull/13446) ([olexii4](https://github.com/olexii4))
- Change version of redhat-java plugin to latest in stacks [\#13440](https://github.com/eclipse/che/pull/13440) ([svor](https://github.com/svor))
- Add PHP and PHP + MySQL Che-7 stacks with samples [\#13434](https://github.com/eclipse/che/pull/13434) ([svor](https://github.com/svor))
- Integrate Che Server with Devfile Registry [\#13430](https://github.com/eclipse/che/pull/13430) ([sleshchenko](https://github.com/sleshchenko))
- Merge api-devfile and api-workspace together [\#13417](https://github.com/eclipse/che/pull/13417) ([metlos](https://github.com/metlos))

## [6.19.5](https://github.com/eclipse/che/tree/6.19.5) (2019-05-29)
[Full Changelog](https://github.com/eclipse/che/compare/6.19.4...6.19.5)

**Merged pull requests:**

- Fix templates for helm 2.14.0 \(validating option is enabled\) [\#13432](https://github.com/eclipse/che/pull/13432) ([benoitf](https://github.com/benoitf))
- Used latest keycloak 6.0.1 [\#13429](https://github.com/eclipse/che/pull/13429) ([skabashnyuk](https://github.com/skabashnyuk))
- \[deploy/helm\] backport changes from chectl for templates [\#13425](https://github.com/eclipse/che/pull/13425) ([benoitf](https://github.com/benoitf))
- switch to RC version scheme [\#13424](https://github.com/eclipse/che/pull/13424) ([riuvshin](https://github.com/riuvshin))
- Fix handling supported versions based on the devfile introduction [\#13418](https://github.com/eclipse/che/pull/13418) ([ashumilova](https://github.com/ashumilova))
- Fix loader animation on factory loading page [\#13415](https://github.com/eclipse/che/pull/13415) ([akurinnoy](https://github.com/akurinnoy))
- Add an ability to disable waiting for PVCs to become bound [\#13409](https://github.com/eclipse/che/pull/13409) ([sleshchenko](https://github.com/sleshchenko))
- Add successful stopped workspaces metric [\#13404](https://github.com/eclipse/che/pull/13404) ([mkuznyetsov](https://github.com/mkuznyetsov))
- Remove dummy workspace config if devfile is present [\#13403](https://github.com/eclipse/che/pull/13403) ([sleshchenko](https://github.com/sleshchenko))
- Add preferences for chePlugin components in Devfile [\#13341](https://github.com/eclipse/che/pull/13341) ([sleshchenko](https://github.com/sleshchenko))

## [6.19.4](https://github.com/eclipse/che/tree/6.19.4) (2019-05-23)
[Full Changelog](https://github.com/eclipse/che/compare/7.0.0-beta-5.0...6.19.4)

**Merged pull requests:**

- RELEASE: Update CHANGELOG [\#13400](https://github.com/eclipse/che/pull/13400) ([riuvshin](https://github.com/riuvshin))
- Restore account linking on newest keycloak versions [\#13398](https://github.com/eclipse/che/pull/13398) ([mshaposhnik](https://github.com/mshaposhnik))
- Fix selenium tests [\#13371](https://github.com/eclipse/che/pull/13371) ([tolusha](https://github.com/tolusha))
