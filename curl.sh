curl -X POST -H "Content-Type: application/json" -d '{
  "url": "jdbc:postgresql://localhost:5432/panneau",
  "user": "postgres",
  "pwd": "pixel",
  "build": "maven",
  "lang": "java",
  "framework": "spring",
  "groupId": "com.noarthedev.panneau",
  "projectName": "solaire",
  "fileToGenerate": "entity,repository,service,rest-controller"
}' http://localhost:4567/skfflod --output downloaded_files.zip
