FROM openjdk:11
EXPOSE 8089
ADD /target/timesheetdevops.jar timesheetdevops.jar
ENTRYPOINT ["java", "-jar", "timesheetdevops.jar"]