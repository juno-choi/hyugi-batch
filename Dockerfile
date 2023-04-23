FROM openjdk:17.0.2-slim

# jar파일 복사
COPY build/libs/hyugi-batch-1.0.jar hyugi-batch.jar
ENTRYPOINT ["tail","-f","/dev/null"]