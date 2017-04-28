mvn clean install \
 -Dmaven.test.skip=true \
 -Dskip-validate-sources \
 -Dmdep.analyze.skip=true \
 -pl "!:che-plugin-machine-ext-server" \
 -pl "!:che-plugin-debugger-ide" \
 -pl "!:che-dashboard-war" \
 -pl "!:che-plugin-java-plain-ide" \
 -pl "!:che-plugin-java-ext-lang-client" \
 -pl "!:che-plugin-java-debugger-ide" \
 -pl "!:che-plugin-git-ext-git" \
 -pl "!:che-plugin-testing-ide" \
 -pl "!:che-plugin-testing-junit-ide" \
 -pl "!:che-plugin-maven-ide" \
 -pl "!:che-plugin-gdb-ide" \
 -pl "!:che-plugin-sdk-ext-plugins" \
 -pl "!:che-plugin-github-ide" \
 -pl "!:che-plugin-gwt-ext-gwt" \
 -pl "!:che-plugin-svn-ext-server" \
 -pl "!:che-plugin-svn-ext-ide" \
 -pl "!:che-plugin-ssh-machine" \
 -pl "!:che-plugin-languageserver-ide" \
 -pl "!:che-plugin-composer-ide" \
 -pl "!:che-plugin-testing-testng-ide" \
 -pl "!:che-sample-plugin-json-ide" \
 -pl "!:che-sample-plugin-wizard-ide" \
 -pl "!:che-sample-plugin-nativeaccess-ide" \
 -pl "!:che-sample-plugin-serverservice-ide" \
 -pl "!:postgresql-tck" \
 -pl "!:che-core-git-impl-jgit" \
 -pl "!:che-plugin-pullrequest-ide" \
  $@
