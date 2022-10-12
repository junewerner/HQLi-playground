FROM jboss/wildfly:13.0.0.Final
LABEL mantainer "Gustavo Muniz do Carmo <gustavo@esign.com.br>"

USER root
ADD standalone.xml ${JBOSS_HOME}/standalone/configuration/
WORKDIR ${JBOSS_HOME}/modules/system/layers/base/com/mysql/main

ADD TestConnection.java .
ADD module.xml .
ADD mysql-connector-java-8.0.12.jar .
RUN javac -cp mysql-connector-java-8.0.12.jar TestConnection.java && \
    chown -R jboss:0 ${JBOSS_HOME} && \
    chmod -R g+rw ${JBOSS_HOME}

USER jboss
ADD wait-for-mysql.sh /usr/local/bin/
ADD hqli.playground.war /opt/jboss/wildfly/standalone/deployments/
CMD ["wait-for-mysql.sh", "/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
