#!/usr/bin/env groovy

import jenkins.model.*
import hudson.util.PersistedList
import jenkins.branch.*
import jenkins.plugins.git.*
import org.jenkinsci.plugins.workflow.multibranch.*
 
// Define Git repository
String git = "https://github.com/Roleyner/backbase-tomcat-app.git"
 
// Define Jenkins Job name
String job = "backbase"
 
// Create pipeline job
Jenkins jenkins = Jenkins.get()
WorkflowMultiBranchProject mbp = jenkins.createProject(WorkflowMultiBranchProject.class, job)
 
// Load Git repository
GitSCMSource gitSCMSource = new GitSCMSource("not_null", git, "", "*", "", false)
BranchSource branchSource = new BranchSource(gitSCMSource)
PersistedList sources = mbp.getSourcesList()
sources.add(branchSource)
 
// Run Jenkinsfile found
jenkins.getItem(job).scheduleBuild()
 
// Save Jenkins config
jenkins.save()
