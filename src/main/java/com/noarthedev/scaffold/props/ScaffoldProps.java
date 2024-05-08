package com.noarthedev.scaffold.props;

import lombok.Data;

@Data
public class ScaffoldProps {
   String url;
   String user;
   String pwd;
   String build;
   String lang;
   String framework;
   String groupId;
   String projectName;
   String fileToGenerate;
   String tableToGenerate;
   boolean loadPrevious;
}
