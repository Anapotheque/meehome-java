SET DEPLOY_CMD= -DgroupId=fr.generali.declarations-sinistre.flex -Dpackaging=swf
SET DEPLOY_CMD= %DEPLOY_CMD% -Durl=http://maven-proxy.groupe.generali.fr/nexus/content/repositories/projects-releases  -DrepositoryId=artifacts-server
SET DEPLOY_CMD= %DEPLOY_CMD% -Dfile=%SWF_FILE% -DartifactId=%SWF_ARTIFACT_ID% -Dversion=%SWF_VERSION% 

echo "=============================================" 
echo "==  %WF_ARTIFACT_ID% :  %SWF_VERSION%     ===" 
echo "=============================================" 

mvn org.apache.maven.plugins:maven-deploy-plugin:2.5:deploy-file %DEPLOY_CMD%