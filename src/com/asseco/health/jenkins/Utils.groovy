package com.asseco.health.jenkins

import com.cloudbees.groovy.cps.NonCPS

@NonCPS
def existNewerBuild() {
    def jobname = env.JOB_NAME
    def buildnum = env.BUILD_NUMBER.toInteger()

    def job = Jenkins.instance.getItemByFullName(jobname)
    def maxId = 0
    for (build in job.builds) {
        def n = build.getNumber().toInteger()
        if (maxId < n) {
            maxId = n
        }
    }
    return maxId > buildnum
}

@NonCPS
def isLastBuild() {
    return !existNewerBuild()
}

return this
