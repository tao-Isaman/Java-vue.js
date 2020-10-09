FROM gradle:4.10.0-jdk8-alpine 
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle test
CMD [ "gradle", "bootRun" ]
# RUN gradle build

