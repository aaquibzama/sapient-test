FROM openjdk:11

USER root

ENV DIRPATH /app

ADD target/league.standings.jar $DIRPATH/league.standings.jar
RUN chmod 755 $DIRPATH/league.standings.jar
WORKDIR $DIRPATH

CMD export TZ=$(date +%Z) &&\
    exec java -jar league.standings.jar
