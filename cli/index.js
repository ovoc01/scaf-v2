#!/usr/bin/env node

const p = require('@clack/prompts');
const { setTimeout } = require('node:timers/promises');
const color = require('picocolors');
const fsPromises = require('node:fs/promises');
const fs = require('node:fs');
const path = require('node:path');
const axios = require('axios');
const ProgressBar = require('progress');
const { pipeline, Transform } = require('node:stream');
const { rejects } = require('node:assert');
const unzip = require('unzip-stream');



const BASE_URL = "http://localhost:3456/skfflod";
const optionsFilePath = path.join(process.cwd(), '.scaffold-options.json');

async function getScaffoldProps(loadPrevious = false) {
    let options = {};
    if (loadPrevious) {
        const prev_options = (require("./.scaffold-options.json"));
        options = prev_options;
        console.log(options)
    }

    //console.log(options)

    const answers = {};
    if(!loadPrevious){
        answers.url = await p.text({
            message: 'Enter the URL:',
            initialValue: options.url || 'jdbc:postgresql://localhost:5432/postgres',
            
        });
    
        answers.user = await p.text({
            message: 'Enter the username:',
            initialValue: options.user||'postgres',
        });
    
        answers.pwd = await p.password({
            message: 'Enter the password:',
            initialValue: options.pwd,
        });
    
        answers.build = await p.select({
            message: 'Select the build tool:',
            initialValue: options.build||'maven',
            options: [
                { value: "maven", label: "maven" },
                { value: "gradle", label: "gradle" }
            ]
        });
    
        answers.lang = await p.select({
            message: 'Select the programming language:',
            initialValue: options.lang||'java',
            options: [
                { value: "java", label: "Java" },
                { value: "kotlin", label: "kotlin" },
                { value: "dotnet", label: "dotnet" }
            ]
        });
    
        answers.framework = await p.select({
            message: 'Select the framework:',
            initialValue: options.framework||'spring',
            options: [
                { value: "spring", label: "Spring + Spring Data Jpa" },
                { value: "quarkus", label: "Quarkus + Panache" },
                { value: "spring-bddobject", label: "Spring + BDD Object" },
                { value: "framework", label: "Framework Mr Naina + BDD Object" },
            ]
        });
    
        answers.groupId = await p.text({
            message: 'Enter the Group ID:',
            initialValue: options.groupId ||'com.example',
        });
    
        answers.projectName = await p.text({
            message: 'Enter the Project Name:',
            initialValue: options.projectName ||'demo',
        });
    
        answers.fileToGenerate = await p.text({
            message: 'Enter the file to generate:',
            initialValue: options.fileToGenerate ,
        });
    }

    if(loadPrevious){
        answers.deliveringPath = await p.text({
            message: 'Give the path to delivering the file generate '
        })
        answers.fileToGenerate = await p.text({
            message: 'Enter the file to generate:',
            initialValue: options.fileToGenerate ,
        });
    }

    answers.tableToGenerate = await p.text({
        message: 'Enter the table to generate:',
    });

    // Store options for next time, excluding file and table names
    const optionsToStore = { ...answers };
    //console.log(optionsToStore)
    const { fileToGenerate, tableToGenerate, ...rest } = optionsToStore;
    
    console.log(rest)
    const jsonData = JSON.stringify(rest);
    if(!loadPrevious){
        fs.writeFile('.scaffold-options.json', jsonData, (err) => {
            if (err) {
              console.error(err);
            } else {
              console.log("Your setting saved ");
            }
          });
    }

    return {...answers,...options,loadPrevious};
}


const temp = {
    "url": "jdbc:postgresql://localhost:5432/spring_based_application",
    "user": "postgres",
    "pwd": "pixel",
    "build": "maven",
    "lang": "java",
    "framework": "spring",
    "groupId": "com.prepeval",
    "projectName": "app",
    "fileToGenerate": "entity,repository,service",
    "tableToGenerate": "role"
}


function supplementProject(extractPath){
    fs.createReadStream('./supplement.zip')
    .pipe(unzip.Extract({path:extractPath}))
    .on('error',(err)=>{
        console.error('Error extracting zip file:', err);
    })
    .on('close',()=>{
        console.log('Zip file extracted successfully to:', extractPath);
    })
}

async function main() {
    console.clear();
    await setTimeout(100);

    p.intro(
        `${color.bgMagenta(
            color.black(' Focus on main functionality not on boilerplate code. ')
        )}`
    );

    const createNewProject = await p.confirm({message:'Create a new project ?'});

    let props = {};
    if(createNewProject){
        props = await getScaffoldProps();
        
    }else{
        const loadPrevious = await p.confirm({ message: 'Load previous options?' });
        props = await getScaffoldProps(loadPrevious);
        console.log(props)
    }

    
    

    const s = p.spinner();
    s.start('Sending data and downloading...');

    try {

        const response = await axios.post(BASE_URL, props, {
            responseType: 'stream',
        }).then((res => {
            const contentDisposition = res.headers['content-disposition']
            const filenameMatch = contentDisposition.match(/filename="(.+)"/);
            let filename = filenameMatch ? filenameMatch[1] : 'downloaded_file.zip';
            filename = createNewProject ? filename : 'supplement.zip'
            const timestamp = Date.now();

            
            
            const writer = fs.createWriteStream(filename, 'binary');
            res.data.pipe(writer)

            if(props.loadPrevious){
                supplementProject(props.deliveringPath)
            }

            /* return new Promise((resolve, reject) => {
                writer.on('finish', resolve);
                writer.on('error', reject);
            }) */

        }))

        

        s.stop();
        console.log('Download complete!');
    }
    catch (error) {
        s.stop();
        console.error('Error downloading file:', error);
    }
}

main().catch(console.error);
