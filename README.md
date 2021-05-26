# String Store

Simple interface for storing and serving strings.

## Deployment

The project is setup to be released on [Sonatype OSSRH](https://s01.oss.sonatype.org).

### Performing a Snapshot Deployment

Snapshot deployment are performed when the version ends in `-SNAPSHOT`.

```
mvn clean deploy
```

Successfully deployed SNAPSHOT versions will be found in [Sonatype OSSRH](https://s01.oss.sonatype.org/content/repositories/snapshots/)

### Performing a Release Deployment

Main steps for the release:

```
# Set the version
mvn versions:set -DnewVersion=0.0.1

# Deploy
mvn clean deploy -P release
```

### Performing a Release Deployment with the Maven Release Plugin

```
mvn release:clean release:prepare

mvn release:perform
```